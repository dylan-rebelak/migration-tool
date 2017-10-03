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

import com.liferay.data.migration.tool.model.Migration;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the migration service. This utility wraps {@link com.liferay.data.migration.tool.service.persistence.impl.MigrationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Dylan Rebelak
 * @see MigrationPersistence
 * @see com.liferay.data.migration.tool.service.persistence.impl.MigrationPersistenceImpl
 * @generated
 */
@ProviderType
public class MigrationUtil {
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
	public static void clearCache(Migration migration) {
		getPersistence().clearCache(migration);
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
	public static List<Migration> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Migration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Migration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Migration> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Migration update(Migration migration) {
		return getPersistence().update(migration);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Migration update(Migration migration,
		ServiceContext serviceContext) {
		return getPersistence().update(migration, serviceContext);
	}

	/**
	* Caches the migration in the entity cache if it is enabled.
	*
	* @param migration the migration
	*/
	public static void cacheResult(Migration migration) {
		getPersistence().cacheResult(migration);
	}

	/**
	* Caches the migrations in the entity cache if it is enabled.
	*
	* @param migrations the migrations
	*/
	public static void cacheResult(List<Migration> migrations) {
		getPersistence().cacheResult(migrations);
	}

	/**
	* Creates a new migration with the primary key. Does not add the migration to the database.
	*
	* @param migrationId the primary key for the new migration
	* @return the new migration
	*/
	public static Migration create(long migrationId) {
		return getPersistence().create(migrationId);
	}

	/**
	* Removes the migration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param migrationId the primary key of the migration
	* @return the migration that was removed
	* @throws NoSuchMigrationException if a migration with the primary key could not be found
	*/
	public static Migration remove(long migrationId)
		throws com.liferay.data.migration.tool.exception.NoSuchMigrationException {
		return getPersistence().remove(migrationId);
	}

	public static Migration updateImpl(Migration migration) {
		return getPersistence().updateImpl(migration);
	}

	/**
	* Returns the migration with the primary key or throws a {@link NoSuchMigrationException} if it could not be found.
	*
	* @param migrationId the primary key of the migration
	* @return the migration
	* @throws NoSuchMigrationException if a migration with the primary key could not be found
	*/
	public static Migration findByPrimaryKey(long migrationId)
		throws com.liferay.data.migration.tool.exception.NoSuchMigrationException {
		return getPersistence().findByPrimaryKey(migrationId);
	}

	/**
	* Returns the migration with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param migrationId the primary key of the migration
	* @return the migration, or <code>null</code> if a migration with the primary key could not be found
	*/
	public static Migration fetchByPrimaryKey(long migrationId) {
		return getPersistence().fetchByPrimaryKey(migrationId);
	}

	public static java.util.Map<java.io.Serializable, Migration> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the migrations.
	*
	* @return the migrations
	*/
	public static List<Migration> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the migrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of migrations
	* @param end the upper bound of the range of migrations (not inclusive)
	* @return the range of migrations
	*/
	public static List<Migration> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the migrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of migrations
	* @param end the upper bound of the range of migrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of migrations
	*/
	public static List<Migration> findAll(int start, int end,
		OrderByComparator<Migration> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the migrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of migrations
	* @param end the upper bound of the range of migrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of migrations
	*/
	public static List<Migration> findAll(int start, int end,
		OrderByComparator<Migration> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the migrations from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of migrations.
	*
	* @return the number of migrations
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static MigrationPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MigrationPersistence, MigrationPersistence> _serviceTracker =
		ServiceTrackerFactory.open(MigrationPersistence.class);
}