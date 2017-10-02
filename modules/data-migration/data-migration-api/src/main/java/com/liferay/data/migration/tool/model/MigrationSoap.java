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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Dylan Rebelak
 * @generated
 */
@ProviderType
public class MigrationSoap implements Serializable {
	public static MigrationSoap toSoapModel(Migration model) {
		MigrationSoap soapModel = new MigrationSoap();

		soapModel.setMigrationId(model.getMigrationId());
		soapModel.setFromDate(model.getFromDate());
		soapModel.setTimeStarted(model.getTimeStarted());
		soapModel.setTimeCompleted(model.getTimeCompleted());
		soapModel.setRecordsSynced(model.getRecordsSynced());

		return soapModel;
	}

	public static MigrationSoap[] toSoapModels(Migration[] models) {
		MigrationSoap[] soapModels = new MigrationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MigrationSoap[][] toSoapModels(Migration[][] models) {
		MigrationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MigrationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MigrationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MigrationSoap[] toSoapModels(List<Migration> models) {
		List<MigrationSoap> soapModels = new ArrayList<MigrationSoap>(models.size());

		for (Migration model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MigrationSoap[soapModels.size()]);
	}

	public MigrationSoap() {
	}

	public long getPrimaryKey() {
		return _migrationId;
	}

	public void setPrimaryKey(long pk) {
		setMigrationId(pk);
	}

	public long getMigrationId() {
		return _migrationId;
	}

	public void setMigrationId(long migrationId) {
		_migrationId = migrationId;
	}

	public Date getFromDate() {
		return _fromDate;
	}

	public void setFromDate(Date fromDate) {
		_fromDate = fromDate;
	}

	public Date getTimeStarted() {
		return _timeStarted;
	}

	public void setTimeStarted(Date timeStarted) {
		_timeStarted = timeStarted;
	}

	public Date getTimeCompleted() {
		return _timeCompleted;
	}

	public void setTimeCompleted(Date timeCompleted) {
		_timeCompleted = timeCompleted;
	}

	public long getRecordsSynced() {
		return _recordsSynced;
	}

	public void setRecordsSynced(long recordsSynced) {
		_recordsSynced = recordsSynced;
	}

	private long _migrationId;
	private Date _fromDate;
	private Date _timeStarted;
	private Date _timeCompleted;
	private long _recordsSynced;
}