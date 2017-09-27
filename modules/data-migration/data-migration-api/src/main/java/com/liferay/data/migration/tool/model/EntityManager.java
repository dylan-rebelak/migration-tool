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
 * The extended model interface for the EntityManager service. Represents a row in the &quot;SYNC_EntityManager&quot; database table, with each column mapped to a property of this class.
 *
 * @author Dylan Rebelak
 * @see EntityManagerModel
 * @see com.liferay.data.migration.tool.model.impl.EntityManagerImpl
 * @see com.liferay.data.migration.tool.model.impl.EntityManagerModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.data.migration.tool.model.impl.EntityManagerImpl")
@ProviderType
public interface EntityManager extends EntityManagerModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.data.migration.tool.model.impl.EntityManagerImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<EntityManager, String> ENTITY_NAME_ACCESSOR = new Accessor<EntityManager, String>() {
			@Override
			public String get(EntityManager entityManager) {
				return entityManager.getEntityName();
			}

			@Override
			public Class<String> getAttributeClass() {
				return String.class;
			}

			@Override
			public Class<EntityManager> getTypeClass() {
				return EntityManager.class;
			}
		};
}