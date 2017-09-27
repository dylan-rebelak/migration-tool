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
public class EntityManagerSoap implements Serializable {
	public static EntityManagerSoap toSoapModel(EntityManager model) {
		EntityManagerSoap soapModel = new EntityManagerSoap();

		soapModel.setEntityName(model.getEntityName());
		soapModel.setLastSyncDate(model.getLastSyncDate());

		return soapModel;
	}

	public static EntityManagerSoap[] toSoapModels(EntityManager[] models) {
		EntityManagerSoap[] soapModels = new EntityManagerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EntityManagerSoap[][] toSoapModels(EntityManager[][] models) {
		EntityManagerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EntityManagerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EntityManagerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EntityManagerSoap[] toSoapModels(List<EntityManager> models) {
		List<EntityManagerSoap> soapModels = new ArrayList<EntityManagerSoap>(models.size());

		for (EntityManager model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EntityManagerSoap[soapModels.size()]);
	}

	public EntityManagerSoap() {
	}

	public String getPrimaryKey() {
		return _entityName;
	}

	public void setPrimaryKey(String pk) {
		setEntityName(pk);
	}

	public String getEntityName() {
		return _entityName;
	}

	public void setEntityName(String entityName) {
		_entityName = entityName;
	}

	public Date getLastSyncDate() {
		return _lastSyncDate;
	}

	public void setLastSyncDate(Date lastSyncDate) {
		_lastSyncDate = lastSyncDate;
	}

	private String _entityName;
	private Date _lastSyncDate;
}