package com.liferay.data.migration.tool;

import java.util.Date;

/**
 * @author Dylan Rebelak
 */
public interface MigrationTask {

	public void blockUntilDone();

	public long getImportCount();

	public void run(Date fromDate);

}