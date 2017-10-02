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

package com.liferay.data.migration.tool.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MigrationLocalService}.
 *
 * @author Dylan Rebelak
 * @see MigrationLocalService
 * @generated
 */
@ProviderType
public class MigrationLocalServiceWrapper implements MigrationLocalService,
	ServiceWrapper<MigrationLocalService> {
	public MigrationLocalServiceWrapper(
		MigrationLocalService migrationLocalService) {
		_migrationLocalService = migrationLocalService;
	}

	/**
	* Adds the migration to the database. Also notifies the appropriate model listeners.
	*
	* @param migration the migration
	* @return the migration that was added
	*/
	@Override
	public com.liferay.data.migration.tool.model.Migration addMigration(
		com.liferay.data.migration.tool.model.Migration migration) {
		return _migrationLocalService.addMigration(migration);
	}

	/**
	* Creates a new migration with the primary key. Does not add the migration to the database.
	*
	* @param migrationId the primary key for the new migration
	* @return the new migration
	*/
	@Override
	public com.liferay.data.migration.tool.model.Migration createMigration(
		long migrationId) {
		return _migrationLocalService.createMigration(migrationId);
	}

	/**
	* Deletes the migration from the database. Also notifies the appropriate model listeners.
	*
	* @param migration the migration
	* @return the migration that was removed
	*/
	@Override
	public com.liferay.data.migration.tool.model.Migration deleteMigration(
		com.liferay.data.migration.tool.model.Migration migration) {
		return _migrationLocalService.deleteMigration(migration);
	}

	/**
	* Deletes the migration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param migrationId the primary key of the migration
	* @return the migration that was removed
	* @throws PortalException if a migration with the primary key could not be found
	*/
	@Override
	public com.liferay.data.migration.tool.model.Migration deleteMigration(
		long migrationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _migrationLocalService.deleteMigration(migrationId);
	}

	@Override
	public com.liferay.data.migration.tool.model.Migration fetchMigration(
		long migrationId) {
		return _migrationLocalService.fetchMigration(migrationId);
	}

	@Override
	public com.liferay.data.migration.tool.model.Migration getLastMigration() {
		return _migrationLocalService.getLastMigration();
	}

	/**
	* Returns the migration with the primary key.
	*
	* @param migrationId the primary key of the migration
	* @return the migration
	* @throws PortalException if a migration with the primary key could not be found
	*/
	@Override
	public com.liferay.data.migration.tool.model.Migration getMigration(
		long migrationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _migrationLocalService.getMigration(migrationId);
	}

	/**
	* Updates the migration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param migration the migration
	* @return the migration that was updated
	*/
	@Override
	public com.liferay.data.migration.tool.model.Migration updateMigration(
		com.liferay.data.migration.tool.model.Migration migration) {
		return _migrationLocalService.updateMigration(migration);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _migrationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _migrationLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _migrationLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _migrationLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _migrationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of migrations.
	*
	* @return the number of migrations
	*/
	@Override
	public int getMigrationsCount() {
		return _migrationLocalService.getMigrationsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _migrationLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _migrationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.migration.tool.model.impl.MigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _migrationLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.migration.tool.model.impl.MigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _migrationLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the migrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.migration.tool.model.impl.MigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of migrations
	* @param end the upper bound of the range of migrations (not inclusive)
	* @return the range of migrations
	*/
	@Override
	public java.util.List<com.liferay.data.migration.tool.model.Migration> getMigrations(
		int start, int end) {
		return _migrationLocalService.getMigrations(start, end);
	}

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link MigrationLocalServiceUtil} to access the migration local service.
	*/
	@Override
	public java.util.concurrent.atomic.AtomicLong runEntityService(
		MigrationEntityService entityService, java.util.Date startDate,
		java.util.concurrent.atomic.AtomicLong count) {
		return _migrationLocalService.runEntityService(entityService,
			startDate, count);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _migrationLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _migrationLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public long migrateEntityBatch(MigrationEntityService entityService,
		java.util.List<java.lang.Object> batch) {
		return _migrationLocalService.migrateEntityBatch(entityService, batch);
	}

	@Override
	public void addMigration(java.util.Date fromDate,
		java.util.Date timeStarted, long count) {
		_migrationLocalService.addMigration(fromDate, timeStarted, count);
	}

	@Override
	public MigrationLocalService getWrappedService() {
		return _migrationLocalService;
	}

	@Override
	public void setWrappedService(MigrationLocalService migrationLocalService) {
		_migrationLocalService = migrationLocalService;
	}

	private MigrationLocalService _migrationLocalService;
}