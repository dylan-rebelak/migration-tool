package com.liferay.data.migration.tool.service;

import com.liferay.data.migration.tool.MigrationEntity;
import com.liferay.data.migration.tool.MigrationEntityService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Dylan Rebelak
 */
public class MigrationEntityBatchExecutor implements Runnable {

	@Override
	public void run() {
		String entityName = _entityService.getEntityName();

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		long count = _migrationManagerLocalService.migrateEntityBatch(
			_entityService, _entityBatch);

		if (_log.isDebugEnabled()) {
			StringBundler msg = new StringBundler(8);

			msg.append(">>> Successful sync ");
			msg.append(count);
			msg.append(" of ");
			msg.append(_entityBatch.size());
			msg.append(" total entities of type ");
			msg.append(entityName);
			msg.append(" in: ");
			msg.append(stopWatch.getTime());

			_log.debug(msg.toString());
		}

		_count.getAndAdd(count);
	}

	protected MigrationEntityBatchExecutor(
		MigrationEntityService entityService, List<MigrationEntity> batch,
		AtomicLong count) {

		_entityService = entityService;
		_entityBatch = batch;
		_count = count;
	}

	protected void setMigrationManagerLocalService(
		MigrationManagerLocalService migrationManagerLocalService) {

		_migrationManagerLocalService = migrationManagerLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MigrationEntityBatchExecutor.class);

	private AtomicLong _count;
	private List<MigrationEntity> _entityBatch;
	private MigrationEntityService _entityService;
	private MigrationManagerLocalService _migrationManagerLocalService;

}