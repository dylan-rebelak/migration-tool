package com.liferay.data.migration.tool.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Dylan Rebelak
 */
@ExtendedObjectClassDefinition(category = "other")

@Meta.OCD(
	id = "com.liferay.data.migration.tool.service.configuration.MigrationToolConfiguration"
)
public interface MigrationToolConfiguration {

	@Meta.AD(deflt = "4", required = false)
	public int migrationTaskThreadPoolSize();

	@Meta.AD(deflt = "20", required = false)
	public int entityServiceThreadPoolSize();

	@Meta.AD(deflt = "10", required = false)
	public int maxBatchQueueSize();

	@Meta.AD(deflt = "200", required = false)
	public int batchSize();

}