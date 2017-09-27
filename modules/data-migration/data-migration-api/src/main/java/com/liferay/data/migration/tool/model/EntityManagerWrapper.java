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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link EntityManager}.
 * </p>
 *
 * @author Dylan Rebelak
 * @see EntityManager
 * @generated
 */
@ProviderType
public class EntityManagerWrapper implements EntityManager,
	ModelWrapper<EntityManager> {
	public EntityManagerWrapper(EntityManager entityManager) {
		_entityManager = entityManager;
	}

	@Override
	public Class<?> getModelClass() {
		return EntityManager.class;
	}

	@Override
	public String getModelClassName() {
		return EntityManager.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("entityName", getEntityName());
		attributes.put("lastSyncDate", getLastSyncDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String entityName = (String)attributes.get("entityName");

		if (entityName != null) {
			setEntityName(entityName);
		}

		Date lastSyncDate = (Date)attributes.get("lastSyncDate");

		if (lastSyncDate != null) {
			setLastSyncDate(lastSyncDate);
		}
	}

	@Override
	public EntityManager toEscapedModel() {
		return new EntityManagerWrapper(_entityManager.toEscapedModel());
	}

	@Override
	public EntityManager toUnescapedModel() {
		return new EntityManagerWrapper(_entityManager.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _entityManager.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _entityManager.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _entityManager.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _entityManager.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<EntityManager> toCacheModel() {
		return _entityManager.toCacheModel();
	}

	@Override
	public int compareTo(EntityManager entityManager) {
		return _entityManager.compareTo(entityManager);
	}

	@Override
	public int hashCode() {
		return _entityManager.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _entityManager.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new EntityManagerWrapper((EntityManager)_entityManager.clone());
	}

	/**
	* Returns the entity name of this entity manager.
	*
	* @return the entity name of this entity manager
	*/
	@Override
	public java.lang.String getEntityName() {
		return _entityManager.getEntityName();
	}

	/**
	* Returns the primary key of this entity manager.
	*
	* @return the primary key of this entity manager
	*/
	@Override
	public java.lang.String getPrimaryKey() {
		return _entityManager.getPrimaryKey();
	}

	@Override
	public java.lang.String toString() {
		return _entityManager.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _entityManager.toXmlString();
	}

	/**
	* Returns the last sync date of this entity manager.
	*
	* @return the last sync date of this entity manager
	*/
	@Override
	public Date getLastSyncDate() {
		return _entityManager.getLastSyncDate();
	}

	@Override
	public void persist() {
		_entityManager.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_entityManager.setCachedModel(cachedModel);
	}

	/**
	* Sets the entity name of this entity manager.
	*
	* @param entityName the entity name of this entity manager
	*/
	@Override
	public void setEntityName(java.lang.String entityName) {
		_entityManager.setEntityName(entityName);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_entityManager.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_entityManager.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_entityManager.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the last sync date of this entity manager.
	*
	* @param lastSyncDate the last sync date of this entity manager
	*/
	@Override
	public void setLastSyncDate(Date lastSyncDate) {
		_entityManager.setLastSyncDate(lastSyncDate);
	}

	@Override
	public void setNew(boolean n) {
		_entityManager.setNew(n);
	}

	/**
	* Sets the primary key of this entity manager.
	*
	* @param primaryKey the primary key of this entity manager
	*/
	@Override
	public void setPrimaryKey(java.lang.String primaryKey) {
		_entityManager.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_entityManager.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EntityManagerWrapper)) {
			return false;
		}

		EntityManagerWrapper entityManagerWrapper = (EntityManagerWrapper)obj;

		if (Objects.equals(_entityManager, entityManagerWrapper._entityManager)) {
			return true;
		}

		return false;
	}

	@Override
	public EntityManager getWrappedModel() {
		return _entityManager;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityManager.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _entityManager.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_entityManager.resetOriginalValues();
	}

	private final EntityManager _entityManager;
}