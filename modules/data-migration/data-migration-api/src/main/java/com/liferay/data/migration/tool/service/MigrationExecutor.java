package com.liferay.data.migration.tool.service;

import java.util.Date;

/**
 * @author Dylan Rebelak
 */
public interface MigrationExecutor {

	public void execute();

	public void execute(Date fromDate);

}