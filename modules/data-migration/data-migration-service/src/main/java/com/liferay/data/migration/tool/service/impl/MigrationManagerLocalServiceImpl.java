/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.data.migration.tool.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.data.migration.tool.internal.MigrationTaskImpl;
import com.liferay.data.migration.tool.model.MigrationManager;
import com.liferay.data.migration.tool.service.MigrationEntityService;
import com.liferay.data.migration.tool.service.MigrationTask;
import com.liferay.data.migration.tool.service.base.MigrationManagerLocalServiceBaseImpl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.time.StopWatch;

/**
 * The implementation of the migration manager local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.data.migration.tool.service.MigrationManagerLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Dylan Rebelak
 * @see MigrationManagerLocalServiceBaseImpl
 * @see com.liferay.data.migration.tool.service.MigrationManagerLocalServiceUtil
 */
@ProviderType
public class MigrationManagerLocalServiceImpl
	extends MigrationManagerLocalServiceBaseImpl {

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public long migrateEntityBatch(
		MigrationEntityService entityService, List<Object> batch) {

		long count = 0;

		for (Object entity : batch) {
			try {
				entityService.syncEntity(entity);

				count++;
			}
			catch (Exception e) {
				StringBundler msg = new StringBundler(4);

				msg.append(">>> Could not sync ");
				//msg.append(entity.getEntityName());
				msg.append(": ");
				//msg.append(entity.getPrimKey());

				_log.error(msg.toString(), e);
			}
		}

		return count;
	}

	public void recordMigrationStatistics(
		Date fromDate, Date timeStarted, long count) {

		long syncId = counterLocalService.increment(
			MigrationManager.class.getName());

		MigrationManager manager = migrationManagerPersistence.create(syncId);

		/*_groupExpandoBridge.setAttribute(
			MIGRATION_DATE_ATTRIBUTE, timeStarted, false);*/

		manager.setTimeCompleted(new Date());
		manager.setFromDate(fromDate);
		manager.setRecordsSynced(count);
		manager.setTimeStarted(timeStarted);

		addMigrationManager(manager);
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.data.migration.tool.service.MigrationManagerLocalServiceUtil} to access the migration manager local service.
	 */

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public AtomicLong runEntityService(
		MigrationEntityService entityService, Date startDate,
		AtomicLong count) {

		String entityName = entityService.getEntityName();

		try {
			/*Date fromDate = (Date)_groupExpandoBridge.getAttribute(
				MigrationConstants.getSyncDateAttributeName(entityName), false);
			*/
			Date fromDate = new Date();

			count.addAndGet(doRunEntityService(entityService, fromDate));
			/*
			_groupExpandoBridge.setAttribute(
				MigrationConstants.getSyncDateAttributeName(entityName),
				startDate,
				false);*/
		}
		catch (Exception e) {
			_log.error(">>> Failed to sync " + entityName, e);
		}

		return count;
	}

	protected MigrationTask createEntitySync(
		MigrationEntityService entityService) {

		MigrationTaskImpl task = new MigrationTaskImpl(entityService);

		task.setMigrationManagerLocalService(migrationManagerLocalService);

		return task;
	}

	protected long doRunEntityService(
		MigrationEntityService entityService, final Date fromDate) {

		String entityName = entityService.getEntityName();

		if (_log.isDebugEnabled()) {
			long total = entityService.countEntities(fromDate);

			StringBundler msg = new StringBundler(4);

			msg.append(">>> Total number of ");
			msg.append(entityName);
			msg.append(" to sync: ");
			msg.append(total);

			_log.debug(msg.toString());
		}

		StopWatch entitySyncStopWatch = new StopWatch();

		entitySyncStopWatch.start();

		MigrationTask task = createEntitySync(entityService);

		task.run(fromDate);
		task.blockUntilDone();

		long count = task.getImportCount();

		if (_log.isInfoEnabled()) {
			StringBundler msg = new StringBundler(6);

			msg.append(">>> Completed sync of ");
			msg.append(count);
			msg.append(" entities of type ");
			msg.append(entityName);
			msg.append(" in: ");
			msg.append(entitySyncStopWatch.getTime());

			_log.info(msg.toString());
		}

		return count;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MigrationManagerLocalServiceImpl.class);

}