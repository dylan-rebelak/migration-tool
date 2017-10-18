package com.liferay.data.migration.tool.internal;

import com.liferay.data.migration.tool.configuration.MigrationToolConfiguration;
import com.liferay.data.migration.tool.model.Migration;
import com.liferay.data.migration.tool.service.EntityService;
import com.liferay.data.migration.tool.service.MigrationExecutor;
import com.liferay.data.migration.tool.service.MigrationLocalService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.Portal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Dylan Rebelak
 */
@Component(
	configurationPid = "com.liferay.data.migration.tool.service.configuration.MigrationToolConfiguration",
	immediate = true, service = MigrationExecutor.class
)
public class MigrationExecutorImpl implements MigrationExecutor {

	@Override
	public void execute() {
		if (_log.isInfoEnabled()) {
			_log.info(">>> Starting Data Migration...");
		}

		Date start = new Date();

		Migration migration = _migrationLocalService.addMigration(start);

		migration.setConfiguration(_configuration);

		migrateAllEntities(migration);

		if (_log.isInfoEnabled()) {
			_log.info(">>> Data Migration finished.");
		}

		migration.setEnd(new Date());
		_migrationLocalService.updateMigration(migration);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration = ConfigurableUtil.createConfigurable(
			MigrationToolConfiguration.class, properties);
	}

	protected void bind(EntityService entityService) {
		if (_entityServices == null) {
			_entityServices = new ArrayList<>();
		}

		_entityServices.add(entityService);
	}

	protected void migrateAllEntities(final Migration migration) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(
			_configuration.entityServicethreadPoolSize());

		forkJoinPool.submit(oneThreadPerEntityService(migration));
	}

	protected Runnable oneThreadPerEntityService(final Migration migration) {
		return () -> _entityServices.parallelStream().forEach(
			entityService -> _migrationLocalService.migrateEntities(
				entityService, migration));
	}

	protected void unbind(EntityService entityService) {
		_entityServices.remove(entityService);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MigrationExecutorImpl.class);

	private MigrationToolConfiguration _configuration;

	@Reference(
		bind = "bind", cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC, service = EntityService.class,
		unbind = "unbind"

	)
	private List<EntityService> _entityServices;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private MigrationLocalService _migrationLocalService;

	@Reference
	private Portal _portal;

}