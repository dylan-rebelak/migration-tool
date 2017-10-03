package com.liferay.data.migration.tool.internal;

import com.liferay.data.migration.tool.service.EntityService;
import com.liferay.data.migration.tool.service.MigrationLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Dylan Rebelak
 */
public class EntityMigrationBatchExecutor implements Runnable {

	@Override
	public void run() {
		String entityName = _entityService.getEntityName();

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		long count = _migrationLocalService.migrateEntityBatch(
			_entityService, _batch);

		if (_log.isDebugEnabled()) {
			StringBundler msg = new StringBundler(8);

			msg.append(">>> Successful sync ");
			msg.append(count);
			msg.append(" of ");
			msg.append(_batch.size());
			msg.append(" total entities of type ");
			msg.append(entityName);
			msg.append(" in: ");
			msg.append(stopWatch.getTime());

			_log.debug(msg.toString());
		}

		_count.getAndAdd(count);
	}

	protected EntityMigrationBatchExecutor(
		EntityService entityService, List<Object> batch, AtomicLong count) {

		_entityService = entityService;
		_batch = batch;
		_count = count;
	}

	protected void setMigrationLocalService(
		MigrationLocalService migrationLocalService) {

		_migrationLocalService = migrationLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EntityMigrationBatchExecutor.class);

	private List<Object> _batch;
	private AtomicLong _count;
	private EntityService _entityService;
	private MigrationLocalService _migrationLocalService;

}