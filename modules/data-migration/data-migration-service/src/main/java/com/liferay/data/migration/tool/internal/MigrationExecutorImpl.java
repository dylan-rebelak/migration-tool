package com.liferay.data.migration.tool.internal;

import com.liferay.data.migration.tool.model.Migration;
import com.liferay.data.migration.tool.service.EntityService;
import com.liferay.data.migration.tool.service.MigrationExecutor;
import com.liferay.data.migration.tool.service.MigrationLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.Portal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Dylan Rebelak
 */
@Component(immediate = true, service = MigrationExecutor.class)
public class MigrationExecutorImpl implements MigrationExecutor {

	@Override
	public void execute() {
		execute(_getLastMigrationStartTime());
	}

	@Override
	public void execute(Date fromDate) {
		if (_log.isInfoEnabled()) {
			_log.info(">>> Starting Data Migration...");
		}

		Date startTime = new Date();

		long entityCount = migrateEntities(startTime);

		if (_log.isInfoEnabled()) {
			_log.info(">>> Data Migration finished.");
		}

		_migrationLocalService.addMigration(fromDate, startTime, entityCount);
	}

	protected void bind(EntityService entityService) {
		if (_entityServices == null) {
			_entityServices = new ArrayList<>();
		}

		_entityServices.add(entityService);
	}

	protected long migrateEntities(final Date startDate) {
		final AtomicLong count = new AtomicLong();

		_entityServices.parallelStream().forEach(
			entityService -> _migrationLocalService.migrateEntities(
				entityService, startDate, count));

		return count.longValue();
	}

	protected void unbind(EntityService entityService) {
		_entityServices.remove(entityService);
	}

	private Date _getLastMigrationStartTime() {
		Date startTime = _EPOCH;

		Migration lastMigration = _migrationLocalService.getLastMigration();

		if (lastMigration != null) {
			startTime = lastMigration.getTimeStarted();
		}

		return startTime;
	}

	private static final Date _EPOCH = new Date(0);

	private static final Log _log = LogFactoryUtil.getLog(
		MigrationExecutorImpl.class);

	@Reference(
		bind = "bind", cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC, service = EntityService.class,
		unbind = "unbind"

	)
	private volatile List<EntityService> _entityServices;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private MigrationLocalService _migrationLocalService;

	@Reference
	private Portal _portal;

}