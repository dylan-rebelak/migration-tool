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

import com.liferay.data.migration.tool.model.MigrationManager;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MigrationManager in entity cache.
 *
 * @author Dylan Rebelak
 * @see MigrationManager
 * @generated
 */
@ProviderType
public class MigrationManagerCacheModel implements CacheModel<MigrationManager>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MigrationManagerCacheModel)) {
			return false;
		}

		MigrationManagerCacheModel migrationManagerCacheModel = (MigrationManagerCacheModel)obj;

		if (managerId == migrationManagerCacheModel.managerId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, managerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{managerId=");
		sb.append(managerId);
		sb.append(", timeStarted=");
		sb.append(timeStarted);
		sb.append(", timeCompleted=");
		sb.append(timeCompleted);
		sb.append(", fromDate=");
		sb.append(fromDate);
		sb.append(", recordsSynced=");
		sb.append(recordsSynced);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MigrationManager toEntityModel() {
		MigrationManagerImpl migrationManagerImpl = new MigrationManagerImpl();

		migrationManagerImpl.setManagerId(managerId);

		if (timeStarted == Long.MIN_VALUE) {
			migrationManagerImpl.setTimeStarted(null);
		}
		else {
			migrationManagerImpl.setTimeStarted(new Date(timeStarted));
		}

		if (timeCompleted == Long.MIN_VALUE) {
			migrationManagerImpl.setTimeCompleted(null);
		}
		else {
			migrationManagerImpl.setTimeCompleted(new Date(timeCompleted));
		}

		if (fromDate == Long.MIN_VALUE) {
			migrationManagerImpl.setFromDate(null);
		}
		else {
			migrationManagerImpl.setFromDate(new Date(fromDate));
		}

		migrationManagerImpl.setRecordsSynced(recordsSynced);

		migrationManagerImpl.resetOriginalValues();

		return migrationManagerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		managerId = objectInput.readLong();
		timeStarted = objectInput.readLong();
		timeCompleted = objectInput.readLong();
		fromDate = objectInput.readLong();

		recordsSynced = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(managerId);
		objectOutput.writeLong(timeStarted);
		objectOutput.writeLong(timeCompleted);
		objectOutput.writeLong(fromDate);

		objectOutput.writeLong(recordsSynced);
	}

	public long managerId;
	public long timeStarted;
	public long timeCompleted;
	public long fromDate;
	public long recordsSynced;
}