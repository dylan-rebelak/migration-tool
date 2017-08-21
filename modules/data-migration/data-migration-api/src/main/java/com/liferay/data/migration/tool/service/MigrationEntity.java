package com.liferay.data.migration.tool.service;

/**
 * @author Dylan Rebelak
 */
public interface MigrationEntity {

	public default String getEntityName() {
		Class<? extends MigrationEntity> entityClass = this.getClass();

		return removePrefix(entityClass.getSimpleName());
	}

	public String getPrimKey();

	public default String removePrefix(String entityName) {
		return entityName.substring(3);
	}

}