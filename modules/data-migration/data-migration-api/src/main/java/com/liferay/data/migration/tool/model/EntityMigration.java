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

package com.liferay.data.migration.tool.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the EntityMigration service. Represents a row in the &quot;MIG_EntityMigration&quot; database table, with each column mapped to a property of this class.
 *
 * @author Dylan Rebelak
 * @see EntityMigrationModel
 * @see com.liferay.data.migration.tool.model.impl.EntityMigrationImpl
 * @see com.liferay.data.migration.tool.model.impl.EntityMigrationModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.data.migration.tool.model.impl.EntityMigrationImpl")
@ProviderType
public interface EntityMigration extends EntityMigrationModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.data.migration.tool.model.impl.EntityMigrationImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<EntityMigration, Long> ENTITY_MIGRATION_ID_ACCESSOR =
		new Accessor<EntityMigration, Long>() {
			@Override
			public Long get(EntityMigration entityMigration) {
				return entityMigration.getEntityMigrationId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<EntityMigration> getTypeClass() {
				return EntityMigration.class;
			}
		};
}