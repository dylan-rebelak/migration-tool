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

import com.liferay.data.migration.tool.exception.NoSuchMigrationException;
import com.liferay.data.migration.tool.model.Migration;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the migration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Dylan Rebelak
 * @see com.liferay.data.migration.tool.service.persistence.impl.MigrationPersistenceImpl
 * @see MigrationUtil
 * @generated
 */
@ProviderType
public interface MigrationPersistence extends BasePersistence<Migration> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MigrationUtil} to access the migration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the migration in the entity cache if it is enabled.
	*
	* @param migration the migration
	*/
	public void cacheResult(Migration migration);

	/**
	* Caches the migrations in the entity cache if it is enabled.
	*
	* @param migrations the migrations
	*/
	public void cacheResult(java.util.List<Migration> migrations);

	/**
	* Creates a new migration with the primary key. Does not add the migration to the database.
	*
	* @param migrationId the primary key for the new migration
	* @return the new migration
	*/
	public Migration create(long migrationId);

	/**
	* Removes the migration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param migrationId the primary key of the migration
	* @return the migration that was removed
	* @throws NoSuchMigrationException if a migration with the primary key could not be found
	*/
	public Migration remove(long migrationId) throws NoSuchMigrationException;

	public Migration updateImpl(Migration migration);

	/**
	* Returns the migration with the primary key or throws a {@link NoSuchMigrationException} if it could not be found.
	*
	* @param migrationId the primary key of the migration
	* @return the migration
	* @throws NoSuchMigrationException if a migration with the primary key could not be found
	*/
	public Migration findByPrimaryKey(long migrationId)
		throws NoSuchMigrationException;

	/**
	* Returns the migration with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param migrationId the primary key of the migration
	* @return the migration, or <code>null</code> if a migration with the primary key could not be found
	*/
	public Migration fetchByPrimaryKey(long migrationId);

	@Override
	public java.util.Map<java.io.Serializable, Migration> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the migrations.
	*
	* @return the migrations
	*/
	public java.util.List<Migration> findAll();

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
	public java.util.List<Migration> findAll(int start, int end);

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
	public java.util.List<Migration> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Migration> orderByComparator);

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
	public java.util.List<Migration> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Migration> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the migrations from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of migrations.
	*
	* @return the number of migrations
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}