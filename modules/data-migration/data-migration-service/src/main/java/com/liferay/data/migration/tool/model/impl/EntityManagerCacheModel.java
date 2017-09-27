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

package com.liferay.data.migration.tool.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.data.migration.tool.model.EntityManager;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing EntityManager in entity cache.
 *
 * @author Dylan Rebelak
 * @see EntityManager
 * @generated
 */
@ProviderType
public class EntityManagerCacheModel implements CacheModel<EntityManager>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EntityManagerCacheModel)) {
			return false;
		}

		EntityManagerCacheModel entityManagerCacheModel = (EntityManagerCacheModel)obj;

		if (entityName.equals(entityManagerCacheModel.entityName)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, entityName);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{entityName=");
		sb.append(entityName);
		sb.append(", lastSyncDate=");
		sb.append(lastSyncDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EntityManager toEntityModel() {
		EntityManagerImpl entityManagerImpl = new EntityManagerImpl();

		if (entityName == null) {
			entityManagerImpl.setEntityName(StringPool.BLANK);
		}
		else {
			entityManagerImpl.setEntityName(entityName);
		}

		if (lastSyncDate == Long.MIN_VALUE) {
			entityManagerImpl.setLastSyncDate(null);
		}
		else {
			entityManagerImpl.setLastSyncDate(new Date(lastSyncDate));
		}

		entityManagerImpl.resetOriginalValues();

		return entityManagerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		entityName = objectInput.readUTF();
		lastSyncDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (entityName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(entityName);
		}

		objectOutput.writeLong(lastSyncDate);
	}

	public String entityName;
	public long lastSyncDate;
}