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
 * This class is a wrapper for {@link Migration}.
 * </p>
 *
 * @author Dylan Rebelak
 * @see Migration
 * @generated
 */
@ProviderType
public class MigrationWrapper implements Migration, ModelWrapper<Migration> {
	public MigrationWrapper(Migration migration) {
		_migration = migration;
	}

	@Override
	public Class<?> getModelClass() {
		return Migration.class;
	}

	@Override
	public String getModelClassName() {
		return Migration.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("migrationId", getMigrationId());
		attributes.put("start", getStart());
		attributes.put("end", getEnd());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long migrationId = (Long)attributes.get("migrationId");

		if (migrationId != null) {
			setMigrationId(migrationId);
		}

		Date start = (Date)attributes.get("start");

		if (start != null) {
			setStart(start);
		}

		Date end = (Date)attributes.get("end");

		if (end != null) {
			setEnd(end);
		}
	}

	@Override
	public Migration toEscapedModel() {
		return new MigrationWrapper(_migration.toEscapedModel());
	}

	@Override
	public Migration toUnescapedModel() {
		return new MigrationWrapper(_migration.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _migration.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _migration.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _migration.isNew();
	}

	@Override
	public com.liferay.data.migration.tool.configuration.MigrationToolConfiguration getConfiguration() {
		return _migration.getConfiguration();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _migration.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Migration> toCacheModel() {
		return _migration.toCacheModel();
	}

	@Override
	public int compareTo(Migration migration) {
		return _migration.compareTo(migration);
	}

	@Override
	public int hashCode() {
		return _migration.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _migration.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new MigrationWrapper((Migration)_migration.clone());
	}

	@Override
	public java.lang.String toString() {
		return _migration.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _migration.toXmlString();
	}

	/**
	* Returns the end of this migration.
	*
	* @return the end of this migration
	*/
	@Override
	public Date getEnd() {
		return _migration.getEnd();
	}

	/**
	* Returns the start of this migration.
	*
	* @return the start of this migration
	*/
	@Override
	public Date getStart() {
		return _migration.getStart();
	}

	/**
	* Returns the migration ID of this migration.
	*
	* @return the migration ID of this migration
	*/
	@Override
	public long getMigrationId() {
		return _migration.getMigrationId();
	}

	/**
	* Returns the primary key of this migration.
	*
	* @return the primary key of this migration
	*/
	@Override
	public long getPrimaryKey() {
		return _migration.getPrimaryKey();
	}

	@Override
	public void persist() {
		_migration.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_migration.setCachedModel(cachedModel);
	}

	@Override
	public void setConfiguration(
		com.liferay.data.migration.tool.configuration.MigrationToolConfiguration configuration) {
		_migration.setConfiguration(configuration);
	}

	/**
	* Sets the end of this migration.
	*
	* @param end the end of this migration
	*/
	@Override
	public void setEnd(Date end) {
		_migration.setEnd(end);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_migration.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_migration.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_migration.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the migration ID of this migration.
	*
	* @param migrationId the migration ID of this migration
	*/
	@Override
	public void setMigrationId(long migrationId) {
		_migration.setMigrationId(migrationId);
	}

	@Override
	public void setNew(boolean n) {
		_migration.setNew(n);
	}

	/**
	* Sets the primary key of this migration.
	*
	* @param primaryKey the primary key of this migration
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_migration.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_migration.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the start of this migration.
	*
	* @param start the start of this migration
	*/
	@Override
	public void setStart(Date start) {
		_migration.setStart(start);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MigrationWrapper)) {
			return false;
		}

		MigrationWrapper migrationWrapper = (MigrationWrapper)obj;

		if (Objects.equals(_migration, migrationWrapper._migration)) {
			return true;
		}

		return false;
	}

	@Override
	public Migration getWrappedModel() {
		return _migration;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _migration.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _migration.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_migration.resetOriginalValues();
	}

	private final Migration _migration;
}