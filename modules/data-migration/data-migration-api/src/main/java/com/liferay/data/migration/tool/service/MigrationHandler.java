package com.liferay.data.migration.tool.service;

import java.util.Date;

/**
 * @author Dylan Rebelak
 */
public interface MigrationHandler {

	public void runMigration();

	public void runMigration(Date fromDate);

}