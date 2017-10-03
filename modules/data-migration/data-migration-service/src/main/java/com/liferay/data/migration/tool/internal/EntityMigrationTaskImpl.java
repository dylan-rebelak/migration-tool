package com.liferay.data.migration.tool.internal;

import static com.liferay.data.migration.tool.internal.MigrationConstants.MAX_BATCH_QUEUE_SIZE;
import static com.liferay.data.migration.tool.internal.MigrationConstants.SYNC_REC_COUNT;
import static com.liferay.data.migration.tool.internal.MigrationConstants.THREAD_POOL_SIZE;

import com.liferay.data.migration.tool.service.EntityMigrationTask;
import com.liferay.data.migration.tool.service.EntityService;
import com.liferay.data.migration.tool.service.MigrationLocalService;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Dylan Rebelak
 */
public class EntityMigrationTaskImpl implements EntityMigrationTask {

	public EntityMigrationTaskImpl(EntityService entityService) {
		this(
			entityService, SYNC_REC_COUNT, THREAD_POOL_SIZE,
			MAX_BATCH_QUEUE_SIZE);
	}

	public EntityMigrationTaskImpl(
		EntityService entityService, int batchSize, int threadPoolSize,
		int maxQueue) {

		_entityService = entityService;
		_batchSize = batchSize;
		_count = new AtomicLong();

		BlockingQueue<Runnable> jobQueue = new LinkedBlockingQueue<>(maxQueue);

		_threadPoolExecutor = new ThreadPoolExecutor(
			threadPoolSize, threadPoolSize, _DEFAULT_KEEP_ALIVE_TIME,
			TimeUnit.MILLISECONDS, jobQueue,
			new ThreadPoolExecutor.CallerRunsPolicy());
	}

	@Override
	public void blockUntilDone() {
		boolean terminated = false;

		while (!terminated) {
			try {
				terminated = _threadPoolExecutor.awaitTermination(
					1000, TimeUnit.MILLISECONDS);
			}
			catch (InterruptedException ie) {
			}
		}
	}

	@Override
	public long getMigrationCount() {
		return _count.get();
	}

	@Override
	public void run(Date from, Date to) {
		int start = 0;

		while (true) {
			List<Object> batch = _entityService.getEntitiesModifiedSinceDate(
				from, to, start, start + _batchSize);

			start += _batchSize;

			if (ListUtil.isEmpty(batch)) {
				break;
			}

			_threadPoolExecutor.execute(_createSyncJob(batch));
		}

		_threadPoolExecutor.shutdown();
	}

	public void setMigrationLocalService(
		MigrationLocalService migrationLocalService) {

		_migrationLocalService = migrationLocalService;
	}

	private EntityMigrationBatchExecutor _createSyncJob(List<Object> batch) {
		EntityMigrationBatchExecutor batchExecutor =
			new EntityMigrationBatchExecutor(_entityService, batch, _count);

		batchExecutor.setMigrationLocalService(_migrationLocalService);

		return batchExecutor;
	}

	private static final int _DEFAULT_KEEP_ALIVE_TIME = 60000;

	private int _batchSize;
	private AtomicLong _count;
	private EntityService _entityService;
	private MigrationLocalService _migrationLocalService;
	private ThreadPoolExecutor _threadPoolExecutor;

}