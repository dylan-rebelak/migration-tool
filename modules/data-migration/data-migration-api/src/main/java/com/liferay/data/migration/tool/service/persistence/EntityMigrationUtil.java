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

import com.liferay.data.migration.tool.model.EntityMigration;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the entity migration service. This utility wraps {@link com.liferay.data.migration.tool.service.persistence.impl.EntityMigrationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Dylan Rebelak
 * @see EntityMigrationPersistence
 * @see com.liferay.data.migration.tool.service.persistence.impl.EntityMigrationPersistenceImpl
 * @generated
 */
@ProviderType
public class EntityMigrationUtil {
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
	public static void clearCache(EntityMigration entityMigration) {
		getPersistence().clearCache(entityMigration);
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
	public static List<EntityMigration> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EntityMigration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EntityMigration> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<EntityMigration> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static EntityMigration update(EntityMigration entityMigration) {
		return getPersistence().update(entityMigration);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static EntityMigration update(EntityMigration entityMigration,
		ServiceContext serviceContext) {
		return getPersistence().update(entityMigration, serviceContext);
	}

	/**
	* Returns the entity migration where migrationId = &#63; and entityName = &#63; or throws a {@link NoSuchEntityMigrationException} if it could not be found.
	*
	* @param migrationId the migration ID
	* @param entityName the entity name
	* @return the matching entity migration
	* @throws NoSuchEntityMigrationException if a matching entity migration could not be found
	*/
	public static EntityMigration findByMigrationIdAndEntityName(
		long migrationId, java.lang.String entityName)
		throws com.liferay.data.migration.tool.exception.NoSuchEntityMigrationException {
		return getPersistence()
				   .findByMigrationIdAndEntityName(migrationId, entityName);
	}

	/**
	* Returns the entity migration where migrationId = &#63; and entityName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param migrationId the migration ID
	* @param entityName the entity name
	* @return the matching entity migration, or <code>null</code> if a matching entity migration could not be found
	*/
	public static EntityMigration fetchByMigrationIdAndEntityName(
		long migrationId, java.lang.String entityName) {
		return getPersistence()
				   .fetchByMigrationIdAndEntityName(migrationId, entityName);
	}

	/**
	* Returns the entity migration where migrationId = &#63; and entityName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param migrationId the migration ID
	* @param entityName the entity name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching entity migration, or <code>null</code> if a matching entity migration could not be found
	*/
	public static EntityMigration fetchByMigrationIdAndEntityName(
		long migrationId, java.lang.String entityName, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByMigrationIdAndEntityName(migrationId, entityName,
			retrieveFromCache);
	}

	/**
	* Removes the entity migration where migrationId = &#63; and entityName = &#63; from the database.
	*
	* @param migrationId the migration ID
	* @param entityName the entity name
	* @return the entity migration that was removed
	*/
	public static EntityMigration removeByMigrationIdAndEntityName(
		long migrationId, java.lang.String entityName)
		throws com.liferay.data.migration.tool.exception.NoSuchEntityMigrationException {
		return getPersistence()
				   .removeByMigrationIdAndEntityName(migrationId, entityName);
	}

	/**
	* Returns the number of entity migrations where migrationId = &#63; and entityName = &#63;.
	*
	* @param migrationId the migration ID
	* @param entityName the entity name
	* @return the number of matching entity migrations
	*/
	public static int countByMigrationIdAndEntityName(long migrationId,
		java.lang.String entityName) {
		return getPersistence()
				   .countByMigrationIdAndEntityName(migrationId, entityName);
	}

	/**
	* Returns all the entity migrations where migrationId = &#63;.
	*
	* @param migrationId the migration ID
	* @return the matching entity migrations
	*/
	public static List<EntityMigration> findByMigrationId(long migrationId) {
		return getPersistence().findByMigrationId(migrationId);
	}

	/**
	* Returns a range of all the entity migrations where migrationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntityMigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param migrationId the migration ID
	* @param start the lower bound of the range of entity migrations
	* @param end the upper bound of the range of entity migrations (not inclusive)
	* @return the range of matching entity migrations
	*/
	public static List<EntityMigration> findByMigrationId(long migrationId,
		int start, int end) {
		return getPersistence().findByMigrationId(migrationId, start, end);
	}

	/**
	* Returns an ordered range of all the entity migrations where migrationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntityMigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param migrationId the migration ID
	* @param start the lower bound of the range of entity migrations
	* @param end the upper bound of the range of entity migrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entity migrations
	*/
	public static List<EntityMigration> findByMigrationId(long migrationId,
		int start, int end, OrderByComparator<EntityMigration> orderByComparator) {
		return getPersistence()
				   .findByMigrationId(migrationId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the entity migrations where migrationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntityMigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param migrationId the migration ID
	* @param start the lower bound of the range of entity migrations
	* @param end the upper bound of the range of entity migrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching entity migrations
	*/
	public static List<EntityMigration> findByMigrationId(long migrationId,
		int start, int end,
		OrderByComparator<EntityMigration> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByMigrationId(migrationId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first entity migration in the ordered set where migrationId = &#63;.
	*
	* @param migrationId the migration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entity migration
	* @throws NoSuchEntityMigrationException if a matching entity migration could not be found
	*/
	public static EntityMigration findByMigrationId_First(long migrationId,
		OrderByComparator<EntityMigration> orderByComparator)
		throws com.liferay.data.migration.tool.exception.NoSuchEntityMigrationException {
		return getPersistence()
				   .findByMigrationId_First(migrationId, orderByComparator);
	}

	/**
	* Returns the first entity migration in the ordered set where migrationId = &#63;.
	*
	* @param migrationId the migration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entity migration, or <code>null</code> if a matching entity migration could not be found
	*/
	public static EntityMigration fetchByMigrationId_First(long migrationId,
		OrderByComparator<EntityMigration> orderByComparator) {
		return getPersistence()
				   .fetchByMigrationId_First(migrationId, orderByComparator);
	}

	/**
	* Returns the last entity migration in the ordered set where migrationId = &#63;.
	*
	* @param migrationId the migration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entity migration
	* @throws NoSuchEntityMigrationException if a matching entity migration could not be found
	*/
	public static EntityMigration findByMigrationId_Last(long migrationId,
		OrderByComparator<EntityMigration> orderByComparator)
		throws com.liferay.data.migration.tool.exception.NoSuchEntityMigrationException {
		return getPersistence()
				   .findByMigrationId_Last(migrationId, orderByComparator);
	}

	/**
	* Returns the last entity migration in the ordered set where migrationId = &#63;.
	*
	* @param migrationId the migration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entity migration, or <code>null</code> if a matching entity migration could not be found
	*/
	public static EntityMigration fetchByMigrationId_Last(long migrationId,
		OrderByComparator<EntityMigration> orderByComparator) {
		return getPersistence()
				   .fetchByMigrationId_Last(migrationId, orderByComparator);
	}

	/**
	* Returns the entity migrations before and after the current entity migration in the ordered set where migrationId = &#63;.
	*
	* @param entityMigrationId the primary key of the current entity migration
	* @param migrationId the migration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entity migration
	* @throws NoSuchEntityMigrationException if a entity migration with the primary key could not be found
	*/
	public static EntityMigration[] findByMigrationId_PrevAndNext(
		long entityMigrationId, long migrationId,
		OrderByComparator<EntityMigration> orderByComparator)
		throws com.liferay.data.migration.tool.exception.NoSuchEntityMigrationException {
		return getPersistence()
				   .findByMigrationId_PrevAndNext(entityMigrationId,
			migrationId, orderByComparator);
	}

	/**
	* Removes all the entity migrations where migrationId = &#63; from the database.
	*
	* @param migrationId the migration ID
	*/
	public static void removeByMigrationId(long migrationId) {
		getPersistence().removeByMigrationId(migrationId);
	}

	/**
	* Returns the number of entity migrations where migrationId = &#63;.
	*
	* @param migrationId the migration ID
	* @return the number of matching entity migrations
	*/
	public static int countByMigrationId(long migrationId) {
		return getPersistence().countByMigrationId(migrationId);
	}

	/**
	* Caches the entity migration in the entity cache if it is enabled.
	*
	* @param entityMigration the entity migration
	*/
	public static void cacheResult(EntityMigration entityMigration) {
		getPersistence().cacheResult(entityMigration);
	}

	/**
	* Caches the entity migrations in the entity cache if it is enabled.
	*
	* @param entityMigrations the entity migrations
	*/
	public static void cacheResult(List<EntityMigration> entityMigrations) {
		getPersistence().cacheResult(entityMigrations);
	}

	/**
	* Creates a new entity migration with the primary key. Does not add the entity migration to the database.
	*
	* @param entityMigrationId the primary key for the new entity migration
	* @return the new entity migration
	*/
	public static EntityMigration create(long entityMigrationId) {
		return getPersistence().create(entityMigrationId);
	}

	/**
	* Removes the entity migration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entityMigrationId the primary key of the entity migration
	* @return the entity migration that was removed
	* @throws NoSuchEntityMigrationException if a entity migration with the primary key could not be found
	*/
	public static EntityMigration remove(long entityMigrationId)
		throws com.liferay.data.migration.tool.exception.NoSuchEntityMigrationException {
		return getPersistence().remove(entityMigrationId);
	}

	public static EntityMigration updateImpl(EntityMigration entityMigration) {
		return getPersistence().updateImpl(entityMigration);
	}

	/**
	* Returns the entity migration with the primary key or throws a {@link NoSuchEntityMigrationException} if it could not be found.
	*
	* @param entityMigrationId the primary key of the entity migration
	* @return the entity migration
	* @throws NoSuchEntityMigrationException if a entity migration with the primary key could not be found
	*/
	public static EntityMigration findByPrimaryKey(long entityMigrationId)
		throws com.liferay.data.migration.tool.exception.NoSuchEntityMigrationException {
		return getPersistence().findByPrimaryKey(entityMigrationId);
	}

	/**
	* Returns the entity migration with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entityMigrationId the primary key of the entity migration
	* @return the entity migration, or <code>null</code> if a entity migration with the primary key could not be found
	*/
	public static EntityMigration fetchByPrimaryKey(long entityMigrationId) {
		return getPersistence().fetchByPrimaryKey(entityMigrationId);
	}

	public static java.util.Map<java.io.Serializable, EntityMigration> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the entity migrations.
	*
	* @return the entity migrations
	*/
	public static List<EntityMigration> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the entity migrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntityMigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entity migrations
	* @param end the upper bound of the range of entity migrations (not inclusive)
	* @return the range of entity migrations
	*/
	public static List<EntityMigration> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the entity migrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntityMigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entity migrations
	* @param end the upper bound of the range of entity migrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of entity migrations
	*/
	public static List<EntityMigration> findAll(int start, int end,
		OrderByComparator<EntityMigration> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the entity migrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntityMigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entity migrations
	* @param end the upper bound of the range of entity migrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of entity migrations
	*/
	public static List<EntityMigration> findAll(int start, int end,
		OrderByComparator<EntityMigration> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the entity migrations from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of entity migrations.
	*
	* @return the number of entity migrations
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static EntityMigrationPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EntityMigrationPersistence, EntityMigrationPersistence> _serviceTracker =
		ServiceTrackerFactory.open(EntityMigrationPersistence.class);
}