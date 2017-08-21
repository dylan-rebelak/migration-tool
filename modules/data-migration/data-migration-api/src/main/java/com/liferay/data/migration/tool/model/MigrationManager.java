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
 * The extended model interface for the MigrationManager service. Represents a row in the &quot;SYNC_MigrationManager&quot; database table, with each column mapped to a property of this class.
 *
 * @author Dylan Rebelak
 * @see MigrationManagerModel
 * @see com.liferay.data.migration.tool.model.impl.MigrationManagerImpl
 * @see com.liferay.data.migration.tool.model.impl.MigrationManagerModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.data.migration.tool.model.impl.MigrationManagerImpl")
@ProviderType
public interface MigrationManager extends MigrationManagerModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.data.migration.tool.model.impl.MigrationManagerImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MigrationManager, Long> MANAGER_ID_ACCESSOR = new Accessor<MigrationManager, Long>() {
			@Override
			public Long get(MigrationManager migrationManager) {
				return migrationManager.getManagerId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<MigrationManager> getTypeClass() {
				return MigrationManager.class;
			}
		};
}