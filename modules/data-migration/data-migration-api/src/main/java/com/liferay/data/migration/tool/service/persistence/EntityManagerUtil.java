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

import com.liferay.data.migration.tool.model.EntityManager;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the entity manager service. This utility wraps {@link com.liferay.data.migration.tool.service.persistence.impl.EntityManagerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Dylan Rebelak
 * @see EntityManagerPersistence
 * @see com.liferay.data.migration.tool.service.persistence.impl.EntityManagerPersistenceImpl
 * @generated
 */
@ProviderType
public class EntityManagerUtil {
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
	public static void clearCache(EntityManager entityManager) {
		getPersistence().clearCache(entityManager);
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
	public static List<EntityManager> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<EntityManager> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<EntityManager> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<EntityManager> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static EntityManager update(EntityManager entityManager) {
		return getPersistence().update(entityManager);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static EntityManager update(EntityManager entityManager,
		ServiceContext serviceContext) {
		return getPersistence().update(entityManager, serviceContext);
	}

	/**
	* Caches the entity manager in the entity cache if it is enabled.
	*
	* @param entityManager the entity manager
	*/
	public static void cacheResult(EntityManager entityManager) {
		getPersistence().cacheResult(entityManager);
	}

	/**
	* Caches the entity managers in the entity cache if it is enabled.
	*
	* @param entityManagers the entity managers
	*/
	public static void cacheResult(List<EntityManager> entityManagers) {
		getPersistence().cacheResult(entityManagers);
	}

	/**
	* Creates a new entity manager with the primary key. Does not add the entity manager to the database.
	*
	* @param entityName the primary key for the new entity manager
	* @return the new entity manager
	*/
	public static EntityManager create(java.lang.String entityName) {
		return getPersistence().create(entityName);
	}

	/**
	* Removes the entity manager with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entityName the primary key of the entity manager
	* @return the entity manager that was removed
	* @throws NoSuchEntityManagerException if a entity manager with the primary key could not be found
	*/
	public static EntityManager remove(java.lang.String entityName)
		throws com.liferay.data.migration.tool.exception.NoSuchEntityManagerException {
		return getPersistence().remove(entityName);
	}

	public static EntityManager updateImpl(EntityManager entityManager) {
		return getPersistence().updateImpl(entityManager);
	}

	/**
	* Returns the entity manager with the primary key or throws a {@link NoSuchEntityManagerException} if it could not be found.
	*
	* @param entityName the primary key of the entity manager
	* @return the entity manager
	* @throws NoSuchEntityManagerException if a entity manager with the primary key could not be found
	*/
	public static EntityManager findByPrimaryKey(java.lang.String entityName)
		throws com.liferay.data.migration.tool.exception.NoSuchEntityManagerException {
		return getPersistence().findByPrimaryKey(entityName);
	}

	/**
	* Returns the entity manager with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entityName the primary key of the entity manager
	* @return the entity manager, or <code>null</code> if a entity manager with the primary key could not be found
	*/
	public static EntityManager fetchByPrimaryKey(java.lang.String entityName) {
		return getPersistence().fetchByPrimaryKey(entityName);
	}

	public static java.util.Map<java.io.Serializable, EntityManager> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the entity managers.
	*
	* @return the entity managers
	*/
	public static List<EntityManager> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the entity managers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntityManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entity managers
	* @param end the upper bound of the range of entity managers (not inclusive)
	* @return the range of entity managers
	*/
	public static List<EntityManager> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the entity managers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntityManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entity managers
	* @param end the upper bound of the range of entity managers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of entity managers
	*/
	public static List<EntityManager> findAll(int start, int end,
		OrderByComparator<EntityManager> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the entity managers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntityManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entity managers
	* @param end the upper bound of the range of entity managers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of entity managers
	*/
	public static List<EntityManager> findAll(int start, int end,
		OrderByComparator<EntityManager> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the entity managers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of entity managers.
	*
	* @return the number of entity managers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static EntityManagerPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EntityManagerPersistence, EntityManagerPersistence> _serviceTracker =
		ServiceTrackerFactory.open(EntityManagerPersistence.class);
}