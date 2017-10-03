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

import com.liferay.data.migration.tool.model.EntityMigration;

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
 * The cache model class for representing EntityMigration in entity cache.
 *
 * @author Dylan Rebelak
 * @see EntityMigration
 * @generated
 */
@ProviderType
public class EntityMigrationCacheModel implements CacheModel<EntityMigration>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EntityMigrationCacheModel)) {
			return false;
		}

		EntityMigrationCacheModel entityMigrationCacheModel = (EntityMigrationCacheModel)obj;

		if (entityMigrationId == entityMigrationCacheModel.entityMigrationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, entityMigrationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{entityMigrationId=");
		sb.append(entityMigrationId);
		sb.append(", migrationId=");
		sb.append(migrationId);
		sb.append(", entityName=");
		sb.append(entityName);
		sb.append(", start=");
		sb.append(start);
		sb.append(", end=");
		sb.append(end);
		sb.append(", count=");
		sb.append(count);
		sb.append(", lastCompletion=");
		sb.append(lastCompletion);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public EntityMigration toEntityModel() {
		EntityMigrationImpl entityMigrationImpl = new EntityMigrationImpl();

		entityMigrationImpl.setEntityMigrationId(entityMigrationId);
		entityMigrationImpl.setMigrationId(migrationId);

		if (entityName == null) {
			entityMigrationImpl.setEntityName(StringPool.BLANK);
		}
		else {
			entityMigrationImpl.setEntityName(entityName);
		}

		if (start == Long.MIN_VALUE) {
			entityMigrationImpl.setStart(null);
		}
		else {
			entityMigrationImpl.setStart(new Date(start));
		}

		if (end == Long.MIN_VALUE) {
			entityMigrationImpl.setEnd(null);
		}
		else {
			entityMigrationImpl.setEnd(new Date(end));
		}

		entityMigrationImpl.setCount(count);

		if (lastCompletion == Long.MIN_VALUE) {
			entityMigrationImpl.setLastCompletion(null);
		}
		else {
			entityMigrationImpl.setLastCompletion(new Date(lastCompletion));
		}

		entityMigrationImpl.resetOriginalValues();

		return entityMigrationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		entityMigrationId = objectInput.readLong();

		migrationId = objectInput.readLong();
		entityName = objectInput.readUTF();
		start = objectInput.readLong();
		end = objectInput.readLong();

		count = objectInput.readLong();
		lastCompletion = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(entityMigrationId);

		objectOutput.writeLong(migrationId);

		if (entityName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(entityName);
		}

		objectOutput.writeLong(start);
		objectOutput.writeLong(end);

		objectOutput.writeLong(count);
		objectOutput.writeLong(lastCompletion);
	}

	public long entityMigrationId;
	public long migrationId;
	public String entityName;
	public long start;
	public long end;
	public long count;
	public long lastCompletion;
}