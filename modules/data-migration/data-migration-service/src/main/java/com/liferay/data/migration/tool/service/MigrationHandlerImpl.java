package com.liferay.data.migration.tool.service;

import com.liferay.data.migration.tool.MigrationEntityService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.Portal;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.data.migration.tool.MigrationHandler;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.liferay.data.migration.tool.service.MigrationConstants.MIGRATION_DATE_ATTRIBUTE;

/**
 * @author Dylan Rebelak
 */
@Component(
	immediate = true, service = MigrationHandler.class
)
public class MigrationHandlerImpl implements MigrationHandler {

	@Override
	public void runMigration() {
		Date timeStarted = new Date();

		Group group = _groupLocalService.fetchGroup(
			_portal.getDefaultCompanyId(), MAIN_SITE);

		if (group == null) {
			_log.error(
				">>> Cannot run Data Migration: " + MAIN_SITE + " does not exist");

			return;
		}

		_groupExpandoBridge = group.getExpandoBridge();

		Date fromDate = (Date)_groupExpandoBridge.getAttribute(
			MIGRATION_DATE_ATTRIBUTE, false);

		if (_log.isInfoEnabled()) {
			_log.info(">>> Starting Data Migration...");
		}

		long entityCount = runEntityServices(timeStarted);

		if (_log.isInfoEnabled()) {
			_log.info(">>> Data Migration finished.");
		}

		_migrationManagerLocalService.recordMigrationStatistics(fromDate, timeStarted, entityCount);
	}



	private long runEntityServices(final Date startDate) {
		final AtomicLong count = new AtomicLong();

		_migrationEntityServices.parallelStream().forEach(
			migrationEntityService ->
				_migrationManagerLocalService.runEntityService(
					migrationEntityService, startDate, count));

		return count.longValue();
	}

	@Reference(
		cardinality= ReferenceCardinality.MULTIPLE, bind="bind",
		unbind="unbind", service = MigrationEntityService.class,
		policy = ReferencePolicy.DYNAMIC
	)
	private List<MigrationEntityService> _migrationEntityServices;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private MigrationManagerLocalService _migrationManagerLocalService;

	@Reference
	private Portal _portal;

	private static final Log _log = LogFactoryUtil.getLog(
		MigrationHandlerImpl.class);

	protected void bind(MigrationEntityService entityService){
		if(_migrationEntityServices == null){
			_migrationEntityServices = new ArrayList<MigrationEntityService>();
		}
		_migrationEntityServices.add(entityService);
	}

	protected void unbind(MigrationEntityService entityService){
		_migrationEntityServices.remove(entityService);
	}
}
