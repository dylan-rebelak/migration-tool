package com.liferay.data.migration.tool.internal;

import com.liferay.data.migration.tool.model.MigrationManager;
import com.liferay.data.migration.tool.service.MigrationEntityService;
import com.liferay.data.migration.tool.service.MigrationHandler;
import com.liferay.data.migration.tool.service.MigrationManagerLocalService;
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
@Component(immediate = true, service = MigrationHandler.class)
public class MigrationHandlerImpl implements MigrationHandler {

	@Override
	public void runMigration() {
		Date timeStarted = new Date();

		Date fromDate = new Date(0);
		List<MigrationManager> migrationManagers =
			_migrationManagerLocalService.getMigrationManagers(0, 1);
		if(migrationManagers.size() > 0) {
			fromDate = migrationManagers.get(0).getTimeStarted();
		}

		if (_log.isInfoEnabled()) {
			_log.info(">>> Starting Data Migration...");
		}

		long entityCount = _runEntityServices(timeStarted);

		if (_log.isInfoEnabled()) {
			_log.info(">>> Data Migration finished.");
		}

		_migrationManagerLocalService.recordMigrationStatistics(
			fromDate, timeStarted, entityCount);
	}

	protected void bind(MigrationEntityService entityService) {
		if (_migrationEntityServices == null) {
			_migrationEntityServices = new ArrayList<>();
		}

		_migrationEntityServices.add(entityService);
	}

	protected void unbind(MigrationEntityService entityService) {
		_migrationEntityServices.remove(entityService);
	}

	private long _runEntityServices(final Date startDate) {
		final AtomicLong count = new AtomicLong();

		_migrationEntityServices.parallelStream().forEach(
			migrationEntityService ->
				_migrationManagerLocalService.runEntityService(
					migrationEntityService, startDate, count));

		return count.longValue();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MigrationHandlerImpl.class);

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference(
		bind = "bind", cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		service = MigrationEntityService.class, unbind = "unbind"

	)
	private volatile List<MigrationEntityService> _migrationEntityServices;

	@Reference
	private MigrationManagerLocalService _migrationManagerLocalService;

	@Reference
	private Portal _portal;

}