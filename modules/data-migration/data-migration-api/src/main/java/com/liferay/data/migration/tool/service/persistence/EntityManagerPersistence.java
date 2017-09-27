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

import com.liferay.data.migration.tool.exception.NoSuchEntityManagerException;
import com.liferay.data.migration.tool.model.EntityManager;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the entity manager service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Dylan Rebelak
 * @see com.liferay.data.migration.tool.service.persistence.impl.EntityManagerPersistenceImpl
 * @see EntityManagerUtil
 * @generated
 */
@ProviderType
public interface EntityManagerPersistence extends BasePersistence<EntityManager> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EntityManagerUtil} to access the entity manager persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the entity manager in the entity cache if it is enabled.
	*
	* @param entityManager the entity manager
	*/
	public void cacheResult(EntityManager entityManager);

	/**
	* Caches the entity managers in the entity cache if it is enabled.
	*
	* @param entityManagers the entity managers
	*/
	public void cacheResult(java.util.List<EntityManager> entityManagers);

	/**
	* Creates a new entity manager with the primary key. Does not add the entity manager to the database.
	*
	* @param entityName the primary key for the new entity manager
	* @return the new entity manager
	*/
	public EntityManager create(java.lang.String entityName);

	/**
	* Removes the entity manager with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entityName the primary key of the entity manager
	* @return the entity manager that was removed
	* @throws NoSuchEntityManagerException if a entity manager with the primary key could not be found
	*/
	public EntityManager remove(java.lang.String entityName)
		throws NoSuchEntityManagerException;

	public EntityManager updateImpl(EntityManager entityManager);

	/**
	* Returns the entity manager with the primary key or throws a {@link NoSuchEntityManagerException} if it could not be found.
	*
	* @param entityName the primary key of the entity manager
	* @return the entity manager
	* @throws NoSuchEntityManagerException if a entity manager with the primary key could not be found
	*/
	public EntityManager findByPrimaryKey(java.lang.String entityName)
		throws NoSuchEntityManagerException;

	/**
	* Returns the entity manager with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entityName the primary key of the entity manager
	* @return the entity manager, or <code>null</code> if a entity manager with the primary key could not be found
	*/
	public EntityManager fetchByPrimaryKey(java.lang.String entityName);

	@Override
	public java.util.Map<java.io.Serializable, EntityManager> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the entity managers.
	*
	* @return the entity managers
	*/
	public java.util.List<EntityManager> findAll();

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
	public java.util.List<EntityManager> findAll(int start, int end);

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
	public java.util.List<EntityManager> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntityManager> orderByComparator);

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
	public java.util.List<EntityManager> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntityManager> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the entity managers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of entity managers.
	*
	* @return the number of entity managers
	*/
	public int countAll();
}