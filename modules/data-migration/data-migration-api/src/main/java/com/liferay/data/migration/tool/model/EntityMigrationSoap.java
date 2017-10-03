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
public class EntityMigrationSoap implements Serializable {
	public static EntityMigrationSoap toSoapModel(EntityMigration model) {
		EntityMigrationSoap soapModel = new EntityMigrationSoap();

		soapModel.setEntityMigrationId(model.getEntityMigrationId());
		soapModel.setMigrationId(model.getMigrationId());
		soapModel.setEntityName(model.getEntityName());
		soapModel.setStart(model.getStart());
		soapModel.setEnd(model.getEnd());
		soapModel.setCount(model.getCount());
		soapModel.setLastCompletion(model.getLastCompletion());

		return soapModel;
	}

	public static EntityMigrationSoap[] toSoapModels(EntityMigration[] models) {
		EntityMigrationSoap[] soapModels = new EntityMigrationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EntityMigrationSoap[][] toSoapModels(
		EntityMigration[][] models) {
		EntityMigrationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EntityMigrationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EntityMigrationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EntityMigrationSoap[] toSoapModels(
		List<EntityMigration> models) {
		List<EntityMigrationSoap> soapModels = new ArrayList<EntityMigrationSoap>(models.size());

		for (EntityMigration model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EntityMigrationSoap[soapModels.size()]);
	}

	public EntityMigrationSoap() {
	}

	public long getPrimaryKey() {
		return _entityMigrationId;
	}

	public void setPrimaryKey(long pk) {
		setEntityMigrationId(pk);
	}

	public long getEntityMigrationId() {
		return _entityMigrationId;
	}

	public void setEntityMigrationId(long entityMigrationId) {
		_entityMigrationId = entityMigrationId;
	}

	public long getMigrationId() {
		return _migrationId;
	}

	public void setMigrationId(long migrationId) {
		_migrationId = migrationId;
	}

	public String getEntityName() {
		return _entityName;
	}

	public void setEntityName(String entityName) {
		_entityName = entityName;
	}

	public Date getStart() {
		return _start;
	}

	public void setStart(Date start) {
		_start = start;
	}

	public Date getEnd() {
		return _end;
	}

	public void setEnd(Date end) {
		_end = end;
	}

	public long getCount() {
		return _count;
	}

	public void setCount(long count) {
		_count = count;
	}

	public Date getLastCompletion() {
		return _lastCompletion;
	}

	public void setLastCompletion(Date lastCompletion) {
		_lastCompletion = lastCompletion;
	}

	private long _entityMigrationId;
	private long _migrationId;
	private String _entityName;
	private Date _start;
	private Date _end;
	private long _count;
	private Date _lastCompletion;
}