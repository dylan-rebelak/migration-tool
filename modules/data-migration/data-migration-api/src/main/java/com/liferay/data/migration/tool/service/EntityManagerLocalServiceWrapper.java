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
 * Provides a wrapper for {@link EntityManagerLocalService}.
 *
 * @author Dylan Rebelak
 * @see EntityManagerLocalService
 * @generated
 */
@ProviderType
public class EntityManagerLocalServiceWrapper
	implements EntityManagerLocalService,
		ServiceWrapper<EntityManagerLocalService> {
	public EntityManagerLocalServiceWrapper(
		EntityManagerLocalService entityManagerLocalService) {
		_entityManagerLocalService = entityManagerLocalService;
	}

	/**
	* Adds the entity manager to the database. Also notifies the appropriate model listeners.
	*
	* @param entityManager the entity manager
	* @return the entity manager that was added
	*/
	@Override
	public com.liferay.data.migration.tool.model.EntityManager addEntityManager(
		com.liferay.data.migration.tool.model.EntityManager entityManager) {
		return _entityManagerLocalService.addEntityManager(entityManager);
	}

	/**
	* Creates a new entity manager with the primary key. Does not add the entity manager to the database.
	*
	* @param entityName the primary key for the new entity manager
	* @return the new entity manager
	*/
	@Override
	public com.liferay.data.migration.tool.model.EntityManager createEntityManager(
		java.lang.String entityName) {
		return _entityManagerLocalService.createEntityManager(entityName);
	}

	/**
	* Deletes the entity manager from the database. Also notifies the appropriate model listeners.
	*
	* @param entityManager the entity manager
	* @return the entity manager that was removed
	*/
	@Override
	public com.liferay.data.migration.tool.model.EntityManager deleteEntityManager(
		com.liferay.data.migration.tool.model.EntityManager entityManager) {
		return _entityManagerLocalService.deleteEntityManager(entityManager);
	}

	/**
	* Deletes the entity manager with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entityName the primary key of the entity manager
	* @return the entity manager that was removed
	* @throws PortalException if a entity manager with the primary key could not be found
	*/
	@Override
	public com.liferay.data.migration.tool.model.EntityManager deleteEntityManager(
		java.lang.String entityName)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entityManagerLocalService.deleteEntityManager(entityName);
	}

	@Override
	public com.liferay.data.migration.tool.model.EntityManager fetchEntityManager(
		java.lang.String entityName) {
		return _entityManagerLocalService.fetchEntityManager(entityName);
	}

	/**
	* Returns the entity manager with the primary key.
	*
	* @param entityName the primary key of the entity manager
	* @return the entity manager
	* @throws PortalException if a entity manager with the primary key could not be found
	*/
	@Override
	public com.liferay.data.migration.tool.model.EntityManager getEntityManager(
		java.lang.String entityName)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entityManagerLocalService.getEntityManager(entityName);
	}

	/**
	* Updates the entity manager in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param entityManager the entity manager
	* @return the entity manager that was updated
	*/
	@Override
	public com.liferay.data.migration.tool.model.EntityManager updateEntityManager(
		com.liferay.data.migration.tool.model.EntityManager entityManager) {
		return _entityManagerLocalService.updateEntityManager(entityManager);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _entityManagerLocalService.dynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entityManagerLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _entityManagerLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of entity managers.
	*
	* @return the number of entity managers
	*/
	@Override
	public int getEntityManagersCount() {
		return _entityManagerLocalService.getEntityManagersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _entityManagerLocalService.getOSGiServiceIdentifier();
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
		return _entityManagerLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.migration.tool.model.impl.EntityManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _entityManagerLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.migration.tool.model.impl.EntityManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _entityManagerLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the entity managers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.data.migration.tool.model.impl.EntityManagerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entity managers
	* @param end the upper bound of the range of entity managers (not inclusive)
	* @return the range of entity managers
	*/
	@Override
	public java.util.List<com.liferay.data.migration.tool.model.EntityManager> getEntityManagers(
		int start, int end) {
		return _entityManagerLocalService.getEntityManagers(start, end);
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
		return _entityManagerLocalService.dynamicQueryCount(dynamicQuery);
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
		return _entityManagerLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public EntityManagerLocalService getWrappedService() {
		return _entityManagerLocalService;
	}

	@Override
	public void setWrappedService(
		EntityManagerLocalService entityManagerLocalService) {
		_entityManagerLocalService = entityManagerLocalService;
	}

	private EntityManagerLocalService _entityManagerLocalService;
}