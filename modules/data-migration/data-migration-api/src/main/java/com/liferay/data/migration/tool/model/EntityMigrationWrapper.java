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
 * This class is a wrapper for {@link EntityMigration}.
 * </p>
 *
 * @author Dylan Rebelak
 * @see EntityMigration
 * @generated
 */
@ProviderType
public class EntityMigrationWrapper implements EntityMigration,
	ModelWrapper<EntityMigration> {
	public EntityMigrationWrapper(EntityMigration entityMigration) {
		_entityMigration = entityMigration;
	}

	@Override
	public Class<?> getModelClass() {
		return EntityMigration.class;
	}

	@Override
	public String getModelClassName() {
		return EntityMigration.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("entityMigrationId", getEntityMigrationId());
		attributes.put("migrationId", getMigrationId());
		attributes.put("entityName", getEntityName());
		attributes.put("start", getStart());
		attributes.put("end", getEnd());
		attributes.put("count", getCount());
		attributes.put("lastCompletion", getLastCompletion());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long entityMigrationId = (Long)attributes.get("entityMigrationId");

		if (entityMigrationId != null) {
			setEntityMigrationId(entityMigrationId);
		}

		Long migrationId = (Long)attributes.get("migrationId");

		if (migrationId != null) {
			setMigrationId(migrationId);
		}

		String entityName = (String)attributes.get("entityName");

		if (entityName != null) {
			setEntityName(entityName);
		}

		Date start = (Date)attributes.get("start");

		if (start != null) {
			setStart(start);
		}

		Date end = (Date)attributes.get("end");

		if (end != null) {
			setEnd(end);
		}

		Long count = (Long)attributes.get("count");

		if (count != null) {
			setCount(count);
		}

		Date lastCompletion = (Date)attributes.get("lastCompletion");

		if (lastCompletion != null) {
			setLastCompletion(lastCompletion);
		}
	}

	@Override
	public EntityMigration toEscapedModel() {
		return new EntityMigrationWrapper(_entityMigration.toEscapedModel());
	}

	@Override
	public EntityMigration toUnescapedModel() {
		return new EntityMigrationWrapper(_entityMigration.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _entityMigration.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _entityMigration.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _entityMigration.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _entityMigration.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<EntityMigration> toCacheModel() {
		return _entityMigration.toCacheModel();
	}

	@Override
	public int compareTo(EntityMigration entityMigration) {
		return _entityMigration.compareTo(entityMigration);
	}

	@Override
	public int hashCode() {
		return _entityMigration.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _entityMigration.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new EntityMigrationWrapper((EntityMigration)_entityMigration.clone());
	}

	/**
	* Returns the entity name of this entity migration.
	*
	* @return the entity name of this entity migration
	*/
	@Override
	public java.lang.String getEntityName() {
		return _entityMigration.getEntityName();
	}

	@Override
	public java.lang.String toString() {
		return _entityMigration.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _entityMigration.toXmlString();
	}

	/**
	* Returns the end of this entity migration.
	*
	* @return the end of this entity migration
	*/
	@Override
	public Date getEnd() {
		return _entityMigration.getEnd();
	}

	/**
	* Returns the last completion of this entity migration.
	*
	* @return the last completion of this entity migration
	*/
	@Override
	public Date getLastCompletion() {
		return _entityMigration.getLastCompletion();
	}

	/**
	* Returns the start of this entity migration.
	*
	* @return the start of this entity migration
	*/
	@Override
	public Date getStart() {
		return _entityMigration.getStart();
	}

	/**
	* Returns the count of this entity migration.
	*
	* @return the count of this entity migration
	*/
	@Override
	public long getCount() {
		return _entityMigration.getCount();
	}

	/**
	* Returns the entity migration ID of this entity migration.
	*
	* @return the entity migration ID of this entity migration
	*/
	@Override
	public long getEntityMigrationId() {
		return _entityMigration.getEntityMigrationId();
	}

	/**
	* Returns the migration ID of this entity migration.
	*
	* @return the migration ID of this entity migration
	*/
	@Override
	public long getMigrationId() {
		return _entityMigration.getMigrationId();
	}

	/**
	* Returns the primary key of this entity migration.
	*
	* @return the primary key of this entity migration
	*/
	@Override
	public long getPrimaryKey() {
		return _entityMigration.getPrimaryKey();
	}

	@Override
	public void persist() {
		_entityMigration.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_entityMigration.setCachedModel(cachedModel);
	}

	/**
	* Sets the count of this entity migration.
	*
	* @param count the count of this entity migration
	*/
	@Override
	public void setCount(long count) {
		_entityMigration.setCount(count);
	}

	/**
	* Sets the end of this entity migration.
	*
	* @param end the end of this entity migration
	*/
	@Override
	public void setEnd(Date end) {
		_entityMigration.setEnd(end);
	}

	/**
	* Sets the entity migration ID of this entity migration.
	*
	* @param entityMigrationId the entity migration ID of this entity migration
	*/
	@Override
	public void setEntityMigrationId(long entityMigrationId) {
		_entityMigration.setEntityMigrationId(entityMigrationId);
	}

	/**
	* Sets the entity name of this entity migration.
	*
	* @param entityName the entity name of this entity migration
	*/
	@Override
	public void setEntityName(java.lang.String entityName) {
		_entityMigration.setEntityName(entityName);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_entityMigration.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_entityMigration.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_entityMigration.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the last completion of this entity migration.
	*
	* @param lastCompletion the last completion of this entity migration
	*/
	@Override
	public void setLastCompletion(Date lastCompletion) {
		_entityMigration.setLastCompletion(lastCompletion);
	}

	/**
	* Sets the migration ID of this entity migration.
	*
	* @param migrationId the migration ID of this entity migration
	*/
	@Override
	public void setMigrationId(long migrationId) {
		_entityMigration.setMigrationId(migrationId);
	}

	@Override
	public void setNew(boolean n) {
		_entityMigration.setNew(n);
	}

	/**
	* Sets the primary key of this entity migration.
	*
	* @param primaryKey the primary key of this entity migration
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_entityMigration.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_entityMigration.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the start of this entity migration.
	*
	* @param start the start of this entity migration
	*/
	@Override
	public void setStart(Date start) {
		_entityMigration.setStart(start);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EntityMigrationWrapper)) {
			return false;
		}

		EntityMigrationWrapper entityMigrationWrapper = (EntityMigrationWrapper)obj;

		if (Objects.equals(_entityMigration,
					entityMigrationWrapper._entityMigration)) {
			return true;
		}

		return false;
	}

	@Override
	public EntityMigration getWrappedModel() {
		return _entityMigration;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityMigration.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _entityMigration.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_entityMigration.resetOriginalValues();
	}

	private final EntityMigration _entityMigration;
}