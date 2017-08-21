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
 * This class is a wrapper for {@link MigrationManager}.
 * </p>
 *
 * @author Dylan Rebelak
 * @see MigrationManager
 * @generated
 */
@ProviderType
public class MigrationManagerWrapper implements MigrationManager,
	ModelWrapper<MigrationManager> {
	public MigrationManagerWrapper(MigrationManager migrationManager) {
		_migrationManager = migrationManager;
	}

	@Override
	public Class<?> getModelClass() {
		return MigrationManager.class;
	}

	@Override
	public String getModelClassName() {
		return MigrationManager.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("managerId", getManagerId());
		attributes.put("timeStarted", getTimeStarted());
		attributes.put("timeCompleted", getTimeCompleted());
		attributes.put("fromDate", getFromDate());
		attributes.put("recordsSynced", getRecordsSynced());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long managerId = (Long)attributes.get("managerId");

		if (managerId != null) {
			setManagerId(managerId);
		}

		Date timeStarted = (Date)attributes.get("timeStarted");

		if (timeStarted != null) {
			setTimeStarted(timeStarted);
		}

		Date timeCompleted = (Date)attributes.get("timeCompleted");

		if (timeCompleted != null) {
			setTimeCompleted(timeCompleted);
		}

		Date fromDate = (Date)attributes.get("fromDate");

		if (fromDate != null) {
			setFromDate(fromDate);
		}

		Long recordsSynced = (Long)attributes.get("recordsSynced");

		if (recordsSynced != null) {
			setRecordsSynced(recordsSynced);
		}
	}

	@Override
	public MigrationManager toEscapedModel() {
		return new MigrationManagerWrapper(_migrationManager.toEscapedModel());
	}

	@Override
	public MigrationManager toUnescapedModel() {
		return new MigrationManagerWrapper(_migrationManager.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _migrationManager.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _migrationManager.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _migrationManager.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _migrationManager.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MigrationManager> toCacheModel() {
		return _migrationManager.toCacheModel();
	}

	@Override
	public int compareTo(MigrationManager migrationManager) {
		return _migrationManager.compareTo(migrationManager);
	}

	@Override
	public int hashCode() {
		return _migrationManager.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _migrationManager.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new MigrationManagerWrapper((MigrationManager)_migrationManager.clone());
	}

	@Override
	public java.lang.String toString() {
		return _migrationManager.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _migrationManager.toXmlString();
	}

	/**
	* Returns the from date of this migration manager.
	*
	* @return the from date of this migration manager
	*/
	@Override
	public Date getFromDate() {
		return _migrationManager.getFromDate();
	}

	/**
	* Returns the time completed of this migration manager.
	*
	* @return the time completed of this migration manager
	*/
	@Override
	public Date getTimeCompleted() {
		return _migrationManager.getTimeCompleted();
	}

	/**
	* Returns the time started of this migration manager.
	*
	* @return the time started of this migration manager
	*/
	@Override
	public Date getTimeStarted() {
		return _migrationManager.getTimeStarted();
	}

	/**
	* Returns the manager ID of this migration manager.
	*
	* @return the manager ID of this migration manager
	*/
	@Override
	public long getManagerId() {
		return _migrationManager.getManagerId();
	}

	/**
	* Returns the primary key of this migration manager.
	*
	* @return the primary key of this migration manager
	*/
	@Override
	public long getPrimaryKey() {
		return _migrationManager.getPrimaryKey();
	}

	/**
	* Returns the records synced of this migration manager.
	*
	* @return the records synced of this migration manager
	*/
	@Override
	public long getRecordsSynced() {
		return _migrationManager.getRecordsSynced();
	}

	@Override
	public void persist() {
		_migrationManager.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_migrationManager.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_migrationManager.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_migrationManager.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_migrationManager.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the from date of this migration manager.
	*
	* @param fromDate the from date of this migration manager
	*/
	@Override
	public void setFromDate(Date fromDate) {
		_migrationManager.setFromDate(fromDate);
	}

	/**
	* Sets the manager ID of this migration manager.
	*
	* @param managerId the manager ID of this migration manager
	*/
	@Override
	public void setManagerId(long managerId) {
		_migrationManager.setManagerId(managerId);
	}

	@Override
	public void setNew(boolean n) {
		_migrationManager.setNew(n);
	}

	/**
	* Sets the primary key of this migration manager.
	*
	* @param primaryKey the primary key of this migration manager
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_migrationManager.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_migrationManager.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the records synced of this migration manager.
	*
	* @param recordsSynced the records synced of this migration manager
	*/
	@Override
	public void setRecordsSynced(long recordsSynced) {
		_migrationManager.setRecordsSynced(recordsSynced);
	}

	/**
	* Sets the time completed of this migration manager.
	*
	* @param timeCompleted the time completed of this migration manager
	*/
	@Override
	public void setTimeCompleted(Date timeCompleted) {
		_migrationManager.setTimeCompleted(timeCompleted);
	}

	/**
	* Sets the time started of this migration manager.
	*
	* @param timeStarted the time started of this migration manager
	*/
	@Override
	public void setTimeStarted(Date timeStarted) {
		_migrationManager.setTimeStarted(timeStarted);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MigrationManagerWrapper)) {
			return false;
		}

		MigrationManagerWrapper migrationManagerWrapper = (MigrationManagerWrapper)obj;

		if (Objects.equals(_migrationManager,
					migrationManagerWrapper._migrationManager)) {
			return true;
		}

		return false;
	}

	@Override
	public MigrationManager getWrappedModel() {
		return _migrationManager;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _migrationManager.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _migrationManager.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_migrationManager.resetOriginalValues();
	}

	private final MigrationManager _migrationManager;
}