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

import com.liferay.data.migration.tool.model.Migration;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Migration in entity cache.
 *
 * @author Dylan Rebelak
 * @see Migration
 * @generated
 */
@ProviderType
public class MigrationCacheModel implements CacheModel<Migration>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MigrationCacheModel)) {
			return false;
		}

		MigrationCacheModel migrationCacheModel = (MigrationCacheModel)obj;

		if (migrationId == migrationCacheModel.migrationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, migrationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{migrationId=");
		sb.append(migrationId);
		sb.append(", start=");
		sb.append(start);
		sb.append(", end=");
		sb.append(end);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Migration toEntityModel() {
		MigrationImpl migrationImpl = new MigrationImpl();

		migrationImpl.setMigrationId(migrationId);

		if (start == Long.MIN_VALUE) {
			migrationImpl.setStart(null);
		}
		else {
			migrationImpl.setStart(new Date(start));
		}

		if (end == Long.MIN_VALUE) {
			migrationImpl.setEnd(null);
		}
		else {
			migrationImpl.setEnd(new Date(end));
		}

		migrationImpl.resetOriginalValues();

		return migrationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		migrationId = objectInput.readLong();
		start = objectInput.readLong();
		end = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(migrationId);
		objectOutput.writeLong(start);
		objectOutput.writeLong(end);
	}

	public long migrationId;
	public long start;
	public long end;
}