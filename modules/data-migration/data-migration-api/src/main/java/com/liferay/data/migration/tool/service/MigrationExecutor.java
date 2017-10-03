package com.liferay.data.migration.tool.service;

/**
 * @author Dylan Rebelak
 */
@FunctionalInterface
public interface MigrationExecutor {

	public void execute();

}