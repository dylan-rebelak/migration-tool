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

import com.liferay.data.migration.tool.exception.NoSuchEntityMigrationException;
import com.liferay.data.migration.tool.model.EntityMigration;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the entity migration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Dylan Rebelak
 * @see com.liferay.data.migration.tool.service.persistence.impl.EntityMigrationPersistenceImpl
 * @see EntityMigrationUtil
 * @generated
 */
@ProviderType
public interface EntityMigrationPersistence extends BasePersistence<EntityMigration> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EntityMigrationUtil} to access the entity migration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the entity migration where migrationId = &#63; and entityName = &#63; or throws a {@link NoSuchEntityMigrationException} if it could not be found.
	*
	* @param migrationId the migration ID
	* @param entityName the entity name
	* @return the matching entity migration
	* @throws NoSuchEntityMigrationException if a matching entity migration could not be found
	*/
	public EntityMigration findByMigrationIdAndEntityName(long migrationId,
		java.lang.String entityName) throws NoSuchEntityMigrationException;

	/**
	* Returns the entity migration where migrationId = &#63; and entityName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param migrationId the migration ID
	* @param entityName the entity name
	* @return the matching entity migration, or <code>null</code> if a matching entity migration could not be found
	*/
	public EntityMigration fetchByMigrationIdAndEntityName(long migrationId,
		java.lang.String entityName);

	/**
	* Returns the entity migration where migrationId = &#63; and entityName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param migrationId the migration ID
	* @param entityName the entity name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching entity migration, or <code>null</code> if a matching entity migration could not be found
	*/
	public EntityMigration fetchByMigrationIdAndEntityName(long migrationId,
		java.lang.String entityName, boolean retrieveFromCache);

	/**
	* Removes the entity migration where migrationId = &#63; and entityName = &#63; from the database.
	*
	* @param migrationId the migration ID
	* @param entityName the entity name
	* @return the entity migration that was removed
	*/
	public EntityMigration removeByMigrationIdAndEntityName(long migrationId,
		java.lang.String entityName) throws NoSuchEntityMigrationException;

	/**
	* Returns the number of entity migrations where migrationId = &#63; and entityName = &#63;.
	*
	* @param migrationId the migration ID
	* @param entityName the entity name
	* @return the number of matching entity migrations
	*/
	public int countByMigrationIdAndEntityName(long migrationId,
		java.lang.String entityName);

	/**
	* Returns all the entity migrations where migrationId = &#63;.
	*
	* @param migrationId the migration ID
	* @return the matching entity migrations
	*/
	public java.util.List<EntityMigration> findByMigrationId(long migrationId);

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
	public java.util.List<EntityMigration> findByMigrationId(long migrationId,
		int start, int end);

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
	public java.util.List<EntityMigration> findByMigrationId(long migrationId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntityMigration> orderByComparator);

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
	public java.util.List<EntityMigration> findByMigrationId(long migrationId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntityMigration> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first entity migration in the ordered set where migrationId = &#63;.
	*
	* @param migrationId the migration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entity migration
	* @throws NoSuchEntityMigrationException if a matching entity migration could not be found
	*/
	public EntityMigration findByMigrationId_First(long migrationId,
		com.liferay.portal.kernel.util.OrderByComparator<EntityMigration> orderByComparator)
		throws NoSuchEntityMigrationException;

	/**
	* Returns the first entity migration in the ordered set where migrationId = &#63;.
	*
	* @param migrationId the migration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entity migration, or <code>null</code> if a matching entity migration could not be found
	*/
	public EntityMigration fetchByMigrationId_First(long migrationId,
		com.liferay.portal.kernel.util.OrderByComparator<EntityMigration> orderByComparator);

	/**
	* Returns the last entity migration in the ordered set where migrationId = &#63;.
	*
	* @param migrationId the migration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entity migration
	* @throws NoSuchEntityMigrationException if a matching entity migration could not be found
	*/
	public EntityMigration findByMigrationId_Last(long migrationId,
		com.liferay.portal.kernel.util.OrderByComparator<EntityMigration> orderByComparator)
		throws NoSuchEntityMigrationException;

	/**
	* Returns the last entity migration in the ordered set where migrationId = &#63;.
	*
	* @param migrationId the migration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entity migration, or <code>null</code> if a matching entity migration could not be found
	*/
	public EntityMigration fetchByMigrationId_Last(long migrationId,
		com.liferay.portal.kernel.util.OrderByComparator<EntityMigration> orderByComparator);

	/**
	* Returns the entity migrations before and after the current entity migration in the ordered set where migrationId = &#63;.
	*
	* @param entityMigrationId the primary key of the current entity migration
	* @param migrationId the migration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entity migration
	* @throws NoSuchEntityMigrationException if a entity migration with the primary key could not be found
	*/
	public EntityMigration[] findByMigrationId_PrevAndNext(
		long entityMigrationId, long migrationId,
		com.liferay.portal.kernel.util.OrderByComparator<EntityMigration> orderByComparator)
		throws NoSuchEntityMigrationException;

	/**
	* Removes all the entity migrations where migrationId = &#63; from the database.
	*
	* @param migrationId the migration ID
	*/
	public void removeByMigrationId(long migrationId);

	/**
	* Returns the number of entity migrations where migrationId = &#63;.
	*
	* @param migrationId the migration ID
	* @return the number of matching entity migrations
	*/
	public int countByMigrationId(long migrationId);

	/**
	* Caches the entity migration in the entity cache if it is enabled.
	*
	* @param entityMigration the entity migration
	*/
	public void cacheResult(EntityMigration entityMigration);

	/**
	* Caches the entity migrations in the entity cache if it is enabled.
	*
	* @param entityMigrations the entity migrations
	*/
	public void cacheResult(java.util.List<EntityMigration> entityMigrations);

	/**
	* Creates a new entity migration with the primary key. Does not add the entity migration to the database.
	*
	* @param entityMigrationId the primary key for the new entity migration
	* @return the new entity migration
	*/
	public EntityMigration create(long entityMigrationId);

	/**
	* Removes the entity migration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entityMigrationId the primary key of the entity migration
	* @return the entity migration that was removed
	* @throws NoSuchEntityMigrationException if a entity migration with the primary key could not be found
	*/
	public EntityMigration remove(long entityMigrationId)
		throws NoSuchEntityMigrationException;

	public EntityMigration updateImpl(EntityMigration entityMigration);

	/**
	* Returns the entity migration with the primary key or throws a {@link NoSuchEntityMigrationException} if it could not be found.
	*
	* @param entityMigrationId the primary key of the entity migration
	* @return the entity migration
	* @throws NoSuchEntityMigrationException if a entity migration with the primary key could not be found
	*/
	public EntityMigration findByPrimaryKey(long entityMigrationId)
		throws NoSuchEntityMigrationException;

	/**
	* Returns the entity migration with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entityMigrationId the primary key of the entity migration
	* @return the entity migration, or <code>null</code> if a entity migration with the primary key could not be found
	*/
	public EntityMigration fetchByPrimaryKey(long entityMigrationId);

	@Override
	public java.util.Map<java.io.Serializable, EntityMigration> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the entity migrations.
	*
	* @return the entity migrations
	*/
	public java.util.List<EntityMigration> findAll();

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
	public java.util.List<EntityMigration> findAll(int start, int end);

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
	public java.util.List<EntityMigration> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntityMigration> orderByComparator);

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
	public java.util.List<EntityMigration> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<EntityMigration> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the entity migrations from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of entity migrations.
	*
	* @return the number of entity migrations
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}