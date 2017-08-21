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

package com.liferay.data.migration.tool.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.data.migration.tool.model.MigrationManager;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the migration manager service. This utility wraps {@link com.liferay.data.migration.tool.service.persistence.impl.MigrationManagerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Dylan Rebelak
 * @see MigrationManagerPersistence
 * @see com.liferay.data.migration.tool.service.persistence.impl.MigrationManagerPersistenceImpl
 * @generated
 */
@ProviderType
public class MigrationManagerUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(MigrationManager migrationManager) {
		getPersistence().clearCache(migrationManager);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<MigrationManager> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MigrationManager> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MigrationManager> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MigrationManager> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MigrationManager update(MigrationManager migrationManager) {
		return getPersistence().update(migrationManager);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MigrationManager update(MigrationManager migrationManager,
		ServiceContext serviceContext) {
		return getPersistence().update(migrationManager, serviceContext);
	}

	/**
	* Caches the migration manager in the entity cache if it is enabled.
	*
	* @param migrationManager the migration manager
	*/
	public static void cacheResult(MigrationManager migrationManager) {
		getPersistence().cacheResult(migrationManager);
	}

	/**
	* Caches the migration managers in the entity cache if it is enabled.
	*
	* @param migrationManagers the migration managers
	*/
	public static void cacheResult(List<MigrationManager> migrationManagers) {
		getPersistence().cacheResult(migrationManagers);
	}

	/**
	* Creates a new migration manager with the primary key. Does not add the migration manager to the database.
	*
	* @param managerId the primary key for the new migration manager
	* @return the new migration manager
	*/
	public static MigrationManager create(long managerId) {
		return getPersistence().create(managerId);
	}

	/**
	* Removes the migration manager with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param managerId the primary key of the migration manager
	* @return the migration manager that was removed
	* @throws NoSuchMigrationManagerException if a migration manager with the primary key could not be found
	*/
	public static MigrationManager remove(long managerId)
		throws com.liferay.data.migration.tool.exception.NoSuchMigrationManagerException {
		return getPersistence().remove(managerId);
	}

	public static MigrationManager updateImpl(MigrationManager migrationManager) {
		return getPersistence().updateImpl(migrationManager);
	}

	/**
	* Returns the migration manager with the primary key or throws a {@link NoSuchMigrationManagerException} if it could not be found.
	*
	* @param managerId the primary key of the migration manager
	* @return the migration manager
	* @throws NoSuchMigrationManagerException if a migration manager with the primary key could not be found
	*/
	public static MigrationManager findByPrimaryKey(long managerId)
		throws com.liferay.data.migration.tool.exception.NoSuchMigrationManagerException {
		return getPersistence().findByPrimaryKey(managerId);
	}

	/**
	* Returns the migration manager with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param managerId the primary key of the migration manager
	* @return the migration manager, or <code>null</code> if a migration manager with the primary key could not be found
	*/
	public static MigrationManager fetchByPrimaryKey(long managerId) {
		return getPersistence().fetchByPrimaryKey(managerId);
	}

	public static java.util.Map<java.io.Serializable, MigrationManager> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the migration managers.
	*
	* @return the migration managers
	*/
	public static List<MigrationManager> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the migration managers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MigrationManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of migration managers
	* @param end the upper bound of the range of migration managers (not inclusive)
	* @return the range of migration managers
	*/
	public static List<MigrationManager> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the migration managers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MigrationManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of migration managers
	* @param end the upper bound of the range of migration managers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of migration managers
	*/
	public static List<MigrationManager> findAll(int start, int end,
		OrderByComparator<MigrationManager> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the migration managers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MigrationManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of migration managers
	* @param end the upper bound of the range of migration managers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of migration managers
	*/
	public static List<MigrationManager> findAll(int start, int end,
		OrderByComparator<MigrationManager> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the migration managers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of migration managers.
	*
	* @return the number of migration managers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MigrationManagerPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MigrationManagerPersistence, MigrationManagerPersistence> _serviceTracker =
		ServiceTrackerFactory.open(MigrationManagerPersistence.class);
}