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

import com.liferay.data.migration.tool.configuration.MigrationToolConfiguration;
import com.liferay.data.migration.tool.internal.EntityMigrationTaskImpl;
import com.liferay.data.migration.tool.internal.MigrationConstants;
import com.liferay.data.migration.tool.model.EntityMigration;
import com.liferay.data.migration.tool.model.Migration;
import com.liferay.data.migration.tool.service.EntityMigrationTask;
import com.liferay.data.migration.tool.service.EntityService;
import com.liferay.data.migration.tool.service.base.MigrationLocalServiceBaseImpl;
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
 * The implementation of the migration local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.data.migration.tool.service.MigrationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Dylan Rebelak
 * @see MigrationLocalServiceBaseImpl
 * @see com.liferay.data.migration.tool.service.MigrationLocalServiceUtil
 */
@ProviderType
public class MigrationLocalServiceImpl extends MigrationLocalServiceBaseImpl {

	public Migration addMigration(Date start) {
		long migrationId = counterLocalService.increment(
			Migration.class.getName());

		Migration migration = migrationPersistence.create(migrationId);

		migration.setStart(start);

		return addMigration(migration);
	}

	public EntityMigration fetchLastOrCreateNewEntityMigration(
		Migration migration, String entityName) {

		long lastMigrationId = getLastMigrationId();

		EntityMigration entityMigration =
			entityMigrationLocalService.fetchLastEntityMigration(
				lastMigrationId, entityName);

		if (entityMigration == null) {
			entityMigration = entityMigrationLocalService.addEntityMigration(
				migration.getMigrationId(), entityName);
		}

		return entityMigration;
	}

	public long getLastMigrationId() {
		List<Migration> migrations = getMigrations(0, 2);

		if (!migrations.isEmpty()) {
			Migration lastMigration = migrations.get(1);

			return lastMigration.getMigrationId();
		}

		return MigrationConstants.DEFAULT_MIGRATION_ID;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.data.migration.tool.service.MigrationLocalServiceUtil} to access the migration local service.
	 */

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void migrateEntities(
		EntityService entityService, Migration migration) {

		try {
			EntityMigration entityMigration =
				fetchLastOrCreateNewEntityMigration(
					migration, entityService.getEntityName());

			// Migration window

			Date from = entityMigration.getLastCompletion();

			Date to = migration.getStart();

			AtomicLong count = new AtomicLong();

			entityMigration.setStart(new Date());

			// Migrate entities

			count.addAndGet(
				doMigrateEntities(
					entityService, from, to, migration.getConfiguration()));

			// Save stats to database

			entityMigration.setEnd(new Date());
			entityMigration.setLastCompletion(migration.getStart());
			entityMigration.setCount(count.longValue());

			entityMigrationLocalService.updateEntityMigration(entityMigration);
		}
		catch (Exception e) {
			_log.error(
				">>> Failed to migrate " + entityService.getEntityName(), e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public long migrateEntityBatch(
		EntityService entityService, List<Object> batch) {

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

	protected EntityMigrationTask createEntityMigrationTask(
		EntityService entityService, MigrationToolConfiguration configuration) {

		EntityMigrationTaskImpl task = new EntityMigrationTaskImpl(
			entityService, configuration);

		task.setMigrationLocalService(migrationLocalService);

		return task;
	}

	protected long doMigrateEntities(
		EntityService entityService, final Date from, final Date to,
		MigrationToolConfiguration configuration) {

		String entityName = entityService.getEntityName();

		StopWatch entityMigrationStopWatch = new StopWatch();

		entityMigrationStopWatch.start();

		EntityMigrationTask task = createEntityMigrationTask(
			entityService, configuration);

		task.run(from, to);
		task.blockUntilDone();

		long count = task.getMigrationCount();

		if (_log.isInfoEnabled()) {
			StringBundler msg = new StringBundler(6);

			msg.append(">>> Completed sync of ");
			msg.append(count);
			msg.append(" entities of type ");
			msg.append(entityName);
			msg.append(" in: ");
			msg.append(entityMigrationStopWatch.getTime());

			_log.info(msg.toString());
		}

		return count;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MigrationLocalServiceImpl.class);

}