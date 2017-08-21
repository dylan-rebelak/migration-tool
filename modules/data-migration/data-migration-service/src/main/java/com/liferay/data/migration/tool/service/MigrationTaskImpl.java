package com.liferay.data.migration.tool.service;

import com.liferay.data.migration.tool.MigrationEntity;
import com.liferay.data.migration.tool.MigrationEntityService;
import com.liferay.data.migration.tool.MigrationTask;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static com.liferay.data.migration.tool.service.MigrationConstants.MAX_BATCH_QUEUE_SIZE;
import static com.liferay.data.migration.tool.service.MigrationConstants.SYNC_REC_COUNT;
import static com.liferay.data.migration.tool.service.MigrationConstants.THREAD_POOL_SIZE;

/**
 * @author Dylan Rebelak
 */
public class MigrationTaskImpl implements MigrationTask{

	public MigrationTaskImpl(MigrationEntityService entityService) {
		this(
			entityService, SYNC_REC_COUNT, THREAD_POOL_SIZE,
			MAX_BATCH_QUEUE_SIZE);
	}

	public MigrationTaskImpl(
		MigrationEntityService entityService, int batchSize, int threadPoolSize,
		int maxQueue) {

		_batchSize = batchSize;
		_count = new AtomicLong();
		_entityService = entityService;

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
	public long getImportCount() {
		return _count.get();
	}

	@Override
	public void run(Date fromDate) {
		int start = 0;

		while (true) {
			List<MigrationEntity> batch = _entityService.getEntities(
				fromDate, start, start + _batchSize);

			start += _batchSize;

			if (ListUtil.isEmpty(batch)) {
				break;
			}

			_threadPoolExecutor.execute(createSyncJob(batch));
		}

		_threadPoolExecutor.shutdown();
	}

	public void setMigrationManagerLocalService(
		MigrationManagerLocalService migrationManagerLocalService) {

		 _migrationManagerLocalService = migrationManagerLocalService;
	}

	protected MigrationEntityBatchExecutor createSyncJob(List<MigrationEntity> batch) {
		MigrationEntityBatchExecutor batchSyncExecutor =
			new MigrationEntityBatchExecutor(_entityService, batch, _count);

		batchSyncExecutor.setMigrationManagerLocalService(
			_migrationManagerLocalService);

		return batchSyncExecutor;
	}

	private static final int _DEFAULT_KEEP_ALIVE_TIME = 60000;

	private int _batchSize;
	private AtomicLong _count;
	private MigrationEntityService _entityService;
	private MigrationManagerLocalService _migrationManagerLocalService;
	private ThreadPoolExecutor _threadPoolExecutor;
}
