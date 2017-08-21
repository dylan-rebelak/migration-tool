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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for MigrationManager. This utility wraps
 * {@link com.liferay.data.migration.tool.service.impl.MigrationManagerLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Dylan Rebelak
 * @see MigrationManagerLocalService
 * @see com.liferay.data.migration.tool.service.base.MigrationManagerLocalServiceBaseImpl
 * @see com.liferay.data.migration.tool.service.impl.MigrationManagerLocalServiceImpl
 * @generated
 */
@ProviderType
public class MigrationManagerLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.data.migration.tool.service.impl.MigrationManagerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the migration manager to the database. Also notifies the appropriate model listeners.
	*
	* @param migrationManager the migration manager
	* @return the migration manager that was added
	*/
	public static com.liferay.data.migration.tool.model.MigrationManager addMigrationManager(
		com.liferay.data.migration.tool.model.MigrationManager migrationManager) {
		return getService().addMigrationManager(migrationManager);
	}

	/**
	* Creates a new migration manager with the primary key. Does not add the migration manager to the database.
	*
	* @param managerId the primary key for the new migration manager
	* @return the new migration manager
	*/
	public static com.liferay.data.migration.tool.model.MigrationManager createMigrationManager(
		long managerId) {
		return getService().createMigrationManager(managerId);
	}

	/**
	* Deletes the migration manager from the database. Also notifies the appropriate model listeners.
	*
	* @param migrationManager the migration manager
	* @return the migration manager that was removed
	*/
	public static com.liferay.data.migration.tool.model.MigrationManager deleteMigrationManager(
		com.liferay.data.migration.tool.model.MigrationManager migrationManager) {
		return getService().deleteMigrationManager(migrationManager);
	}

	/**
	* Deletes the migration manager with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param managerId the primary key of the migration manager
	* @return the migration manager that was removed
	* @throws PortalException if a migration manager with the primary key could not be found
	*/
	public static com.liferay.data.migration.tool.model.MigrationManager deleteMigrationManager(
		long managerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteMigrationManager(managerId);
	}

	public static com.liferay.data.migration.tool.model.MigrationManager fetchMigrationManager(
		long managerId) {
		return getService().fetchMigrationManager(managerId);
	}

	/**
	* Returns the migration manager with the primary key.
	*
	* @param managerId the primary key of the migration manager
	* @return the migration manager
	* @throws PortalException if a migration manager with the primary key could not be found
	*/
	public static com.liferay.data.migration.tool.model.MigrationManager getMigrationManager(
		long managerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMigrationManager(managerId);
	}

	/**
	* Updates the migration manager in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param migrationManager the migration manager
	* @return the migration manager that was updated
	*/
	public static com.liferay.data.migration.tool.model.MigrationManager updateMigrationManager(
		com.liferay.data.migration.tool.model.MigrationManager migrationManager) {
		return getService().updateMigrationManager(migrationManager);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of migration managers.
	*
	* @return the number of migration managers
	*/
	public static int getMigrationManagersCount() {
		return getService().getMigrationManagersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.migration.tool.model.impl.MigrationManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.migration.tool.model.impl.MigrationManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the migration managers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.migration.tool.model.impl.MigrationManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of migration managers
	* @param end the upper bound of the range of migration managers (not inclusive)
	* @return the range of migration managers
	*/
	public static java.util.List<com.liferay.data.migration.tool.model.MigrationManager> getMigrationManagers(
		int start, int end) {
		return getService().getMigrationManagers(start, end);
	}

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link MigrationManagerLocalServiceUtil} to access the migration manager local service.
	*/
	public static java.util.concurrent.atomic.AtomicLong runEntityService(
		MigrationEntityService entityService, java.util.Date startDate,
		java.util.concurrent.atomic.AtomicLong count) {
		return getService().runEntityService(entityService, startDate, count);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static long migrateEntityBatch(
		com.liferay.data.migration.tool.MigrationEntityService entityService,
		java.util.List<com.liferay.data.migration.tool.MigrationEntity> batch) {
		return getService().migrateEntityBatch(entityService, batch);
	}

	public static void recordMigrationStatistics(java.util.Date fromDate,
		java.util.Date timeStarted, long count) {
		getService().recordMigrationStatistics(fromDate, timeStarted, count);
	}

	public static MigrationManagerLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MigrationManagerLocalService, MigrationManagerLocalService> _serviceTracker =
		ServiceTrackerFactory.open(MigrationManagerLocalService.class);
}