package com.liferay.data.migration.tool.service;

import java.util.Date;

/**
 * @author Dylan Rebelak
 */
public interface EntityMigrationTask {

	public void blockUntilDone();

	public long getMigrationCount();

	public void run(Date from, Date to);

}