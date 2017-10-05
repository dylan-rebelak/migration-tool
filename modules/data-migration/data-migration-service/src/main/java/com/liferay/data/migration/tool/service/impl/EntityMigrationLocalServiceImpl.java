/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.data.migration.tool.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.data.migration.tool.model.EntityMigration;
import com.liferay.data.migration.tool.service.base.EntityMigrationLocalServiceBaseImpl;

import java.util.Date;

/**
 * The implementation of the entity migration local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.data.migration.tool.service.EntityMigrationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Dylan Rebelak
 * @see EntityMigrationLocalServiceBaseImpl
 * @see com.liferay.data.migration.tool.service.EntityMigrationLocalServiceUtil
 */
@ProviderType
public class EntityMigrationLocalServiceImpl
	extends EntityMigrationLocalServiceBaseImpl {

	public EntityMigration addEntityMigration(
		long migrationId, String entityName) {

		long entityMigrationId = counterLocalService.increment(
			getModelClassName());

		EntityMigration entityMigration = entityMigrationPersistence.create(
			entityMigrationId);

		entityMigration.setEntityName(entityName);
		entityMigration.setLastCompletion(_EPOCH);
		entityMigration.setMigrationId(migrationId);

		return addEntityMigration(entityMigration);
	}

	public EntityMigration fetchLastEntityMigration(
		long parentId, String entityName) {

		return entityMigrationPersistence.fetchByMigrationIdAndEntityName(
			parentId, entityName);
	}

	private static final Date _EPOCH = new Date(0);

}