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
 * Provides a wrapper for {@link EntityMigrationLocalService}.
 *
 * @author Dylan Rebelak
 * @see EntityMigrationLocalService
 * @generated
 */
@ProviderType
public class EntityMigrationLocalServiceWrapper
	implements EntityMigrationLocalService,
		ServiceWrapper<EntityMigrationLocalService> {
	public EntityMigrationLocalServiceWrapper(
		EntityMigrationLocalService entityMigrationLocalService) {
		_entityMigrationLocalService = entityMigrationLocalService;
	}

	/**
	* Adds the entity migration to the database. Also notifies the appropriate model listeners.
	*
	* @param entityMigration the entity migration
	* @return the entity migration that was added
	*/
	@Override
	public com.liferay.data.migration.tool.model.EntityMigration addEntityMigration(
		com.liferay.data.migration.tool.model.EntityMigration entityMigration) {
		return _entityMigrationLocalService.addEntityMigration(entityMigration);
	}

	@Override
	public com.liferay.data.migration.tool.model.EntityMigration addEntityMigration(
		long migrationId, java.lang.String entityName) {
		return _entityMigrationLocalService.addEntityMigration(migrationId,
			entityName);
	}

	/**
	* Creates a new entity migration with the primary key. Does not add the entity migration to the database.
	*
	* @param entityMigrationId the primary key for the new entity migration
	* @return the new entity migration
	*/
	@Override
	public com.liferay.data.migration.tool.model.EntityMigration createEntityMigration(
		long entityMigrationId) {
		return _entityMigrationLocalService.createEntityMigration(entityMigrationId);
	}

	/**
	* Deletes the entity migration from the database. Also notifies the appropriate model listeners.
	*
	* @param entityMigration the entity migration
	* @return the entity migration that was removed
	*/
	@Override
	public com.liferay.data.migration.tool.model.EntityMigration deleteEntityMigration(
		com.liferay.data.migration.tool.model.EntityMigration entityMigration) {
		return _entityMigrationLocalService.deleteEntityMigration(entityMigration);
	}

	/**
	* Deletes the entity migration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entityMigrationId the primary key of the entity migration
	* @return the entity migration that was removed
	* @throws PortalException if a entity migration with the primary key could not be found
	*/
	@Override
	public com.liferay.data.migration.tool.model.EntityMigration deleteEntityMigration(
		long entityMigrationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entityMigrationLocalService.deleteEntityMigration(entityMigrationId);
	}

	@Override
	public com.liferay.data.migration.tool.model.EntityMigration fetchEntityMigration(
		long entityMigrationId) {
		return _entityMigrationLocalService.fetchEntityMigration(entityMigrationId);
	}

	@Override
	public com.liferay.data.migration.tool.model.EntityMigration fetchLastEntityMigration(
		long parentId, java.lang.String entityName) {
		return _entityMigrationLocalService.fetchLastEntityMigration(parentId,
			entityName);
	}

	/**
	* Returns the entity migration with the primary key.
	*
	* @param entityMigrationId the primary key of the entity migration
	* @return the entity migration
	* @throws PortalException if a entity migration with the primary key could not be found
	*/
	@Override
	public com.liferay.data.migration.tool.model.EntityMigration getEntityMigration(
		long entityMigrationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entityMigrationLocalService.getEntityMigration(entityMigrationId);
	}

	/**
	* Updates the entity migration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param entityMigration the entity migration
	* @return the entity migration that was updated
	*/
	@Override
	public com.liferay.data.migration.tool.model.EntityMigration updateEntityMigration(
		com.liferay.data.migration.tool.model.EntityMigration entityMigration) {
		return _entityMigrationLocalService.updateEntityMigration(entityMigration);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _entityMigrationLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _entityMigrationLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _entityMigrationLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entityMigrationLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entityMigrationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of entity migrations.
	*
	* @return the number of entity migrations
	*/
	@Override
	public int getEntityMigrationsCount() {
		return _entityMigrationLocalService.getEntityMigrationsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _entityMigrationLocalService.getOSGiServiceIdentifier();
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
		return _entityMigrationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.migration.tool.model.impl.EntityMigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _entityMigrationLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.migration.tool.model.impl.EntityMigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _entityMigrationLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the entity migrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.migration.tool.model.impl.EntityMigrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entity migrations
	* @param end the upper bound of the range of entity migrations (not inclusive)
	* @return the range of entity migrations
	*/
	@Override
	public java.util.List<com.liferay.data.migration.tool.model.EntityMigration> getEntityMigrations(
		int start, int end) {
		return _entityMigrationLocalService.getEntityMigrations(start, end);
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
		return _entityMigrationLocalService.dynamicQueryCount(dynamicQuery);
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
		return _entityMigrationLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public EntityMigrationLocalService getWrappedService() {
		return _entityMigrationLocalService;
	}

	@Override
	public void setWrappedService(
		EntityMigrationLocalService entityMigrationLocalService) {
		_entityMigrationLocalService = entityMigrationLocalService;
	}

	private EntityMigrationLocalService _entityMigrationLocalService;
}