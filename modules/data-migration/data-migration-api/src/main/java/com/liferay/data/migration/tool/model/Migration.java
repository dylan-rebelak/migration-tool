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
 * The extended model interface for the Migration service. Represents a row in the &quot;SYNC_Migration&quot; database table, with each column mapped to a property of this class.
 *
 * @author Dylan Rebelak
 * @see MigrationModel
 * @see com.liferay.data.migration.tool.model.impl.MigrationImpl
 * @see com.liferay.data.migration.tool.model.impl.MigrationModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.data.migration.tool.model.impl.MigrationImpl")
@ProviderType
public interface Migration extends MigrationModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.data.migration.tool.model.impl.MigrationImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Migration, Long> MIGRATION_ID_ACCESSOR = new Accessor<Migration, Long>() {
			@Override
			public Long get(Migration migration) {
				return migration.getMigrationId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Migration> getTypeClass() {
				return Migration.class;
			}
		};
}