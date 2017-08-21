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

import com.liferay.data.migration.tool.exception.NoSuchMigrationManagerException;
import com.liferay.data.migration.tool.model.MigrationManager;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the migration manager service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Dylan Rebelak
 * @see com.liferay.data.migration.tool.service.persistence.impl.MigrationManagerPersistenceImpl
 * @see MigrationManagerUtil
 * @generated
 */
@ProviderType
public interface MigrationManagerPersistence extends BasePersistence<MigrationManager> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MigrationManagerUtil} to access the migration manager persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the migration manager in the entity cache if it is enabled.
	*
	* @param migrationManager the migration manager
	*/
	public void cacheResult(MigrationManager migrationManager);

	/**
	* Caches the migration managers in the entity cache if it is enabled.
	*
	* @param migrationManagers the migration managers
	*/
	public void cacheResult(java.util.List<MigrationManager> migrationManagers);

	/**
	* Creates a new migration manager with the primary key. Does not add the migration manager to the database.
	*
	* @param managerId the primary key for the new migration manager
	* @return the new migration manager
	*/
	public MigrationManager create(long managerId);

	/**
	* Removes the migration manager with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param managerId the primary key of the migration manager
	* @return the migration manager that was removed
	* @throws NoSuchMigrationManagerException if a migration manager with the primary key could not be found
	*/
	public MigrationManager remove(long managerId)
		throws NoSuchMigrationManagerException;

	public MigrationManager updateImpl(MigrationManager migrationManager);

	/**
	* Returns the migration manager with the primary key or throws a {@link NoSuchMigrationManagerException} if it could not be found.
	*
	* @param managerId the primary key of the migration manager
	* @return the migration manager
	* @throws NoSuchMigrationManagerException if a migration manager with the primary key could not be found
	*/
	public MigrationManager findByPrimaryKey(long managerId)
		throws NoSuchMigrationManagerException;

	/**
	* Returns the migration manager with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param managerId the primary key of the migration manager
	* @return the migration manager, or <code>null</code> if a migration manager with the primary key could not be found
	*/
	public MigrationManager fetchByPrimaryKey(long managerId);

	@Override
	public java.util.Map<java.io.Serializable, MigrationManager> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the migration managers.
	*
	* @return the migration managers
	*/
	public java.util.List<MigrationManager> findAll();

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
	public java.util.List<MigrationManager> findAll(int start, int end);

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
	public java.util.List<MigrationManager> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MigrationManager> orderByComparator);

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
	public java.util.List<MigrationManager> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MigrationManager> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the migration managers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of migration managers.
	*
	* @return the number of migration managers
	*/
	public int countAll();
}