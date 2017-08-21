package com.liferay.data.migration.tool.internal;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Dylan Rebelak
 */
public class MigrationConstants {

	public static final int MAX_BATCH_QUEUE_SIZE = GetterUtil.getInteger(
		PropsUtil.get("data.migration.max.batch.queue.size"), 10);

	public static final String MIGRATION_DATE_ATTRIBUTE =
		"last-data-migration-date";

	public static final int SYNC_REC_COUNT = GetterUtil.getInteger(
		PropsUtil.get("data.migration.batch.size"), 200);

	public static final int THREAD_POOL_SIZE = GetterUtil.getInteger(
		PropsUtil.get("data.migration.thread.pool.size"), 20);

	public static String getSyncDateAttributeName(String entityName) {
		return MIGRATION_DATE_ATTRIBUTE + StringPool.UNDERLINE + entityName;
	}

}