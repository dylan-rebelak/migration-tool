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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for EntityMigration. This utility wraps
 * {@link com.liferay.data.migration.tool.service.impl.EntityMigrationLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Dylan Rebelak
 * @see EntityMigrationLocalService
 * @see com.liferay.data.migration.tool.service.base.EntityMigrationLocalServiceBaseImpl
 * @see com.liferay.data.migration.tool.service.impl.EntityMigrationLocalServiceImpl
 * @generated
 */
@ProviderType
public class EntityMigrationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.data.migration.tool.service.impl.EntityMigrationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the entity migration to the database. Also notifies the appropriate model listeners.
	*
	* @param entityMigration the entity migration
	* @return the entity migration that was added
	*/
	public static com.liferay.data.migration.tool.model.EntityMigration addEntityMigration(
		com.liferay.data.migration.tool.model.EntityMigration entityMigration) {
		return getService().addEntityMigration(entityMigration);
	}

	public static com.liferay.data.migration.tool.model.EntityMigration addEntityMigration(
		long migrationId, java.lang.String entityName) {
		return getService().addEntityMigration(migrationId, entityName);
	}

	/**
	* Creates a new entity migration with the primary key. Does not add the entity migration to the database.
	*
	* @param entityMigrationId the primary key for the new entity migration
	* @return the new entity migration
	*/
	public static com.liferay.data.migration.tool.model.EntityMigration createEntityMigration(
		long entityMigrationId) {
		return getService().createEntityMigration(entityMigrationId);
	}

	/**
	* Deletes the entity migration from the database. Also notifies the appropriate model listeners.
	*
	* @param entityMigration the entity migration
	* @return the entity migration that was removed
	*/
	public static com.liferay.data.migration.tool.model.EntityMigration deleteEntityMigration(
		com.liferay.data.migration.tool.model.EntityMigration entityMigration) {
		return getService().deleteEntityMigration(entityMigration);
	}

	/**
	* Deletes the entity migration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entityMigrationId the primary key of the entity migration
	* @return the entity migration that was removed
	* @throws PortalException if a entity migration with the primary key could not be found
	*/
	public static com.liferay.data.migration.tool.model.EntityMigration deleteEntityMigration(
		long entityMigrationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteEntityMigration(entityMigrationId);
	}

	public static com.liferay.data.migration.tool.model.EntityMigration fetchEntityMigration(
		long entityMigrationId) {
		return getService().fetchEntityMigration(entityMigrationId);
	}

	public static com.liferay.data.migration.tool.model.EntityMigration fetchLastEntityMigration(
		long parentId, java.lang.String entityName) {
		return getService().fetchLastEntityMigration(parentId, entityName);
	}

	/**
	* Returns the entity migration with the primary key.
	*
	* @param entityMigrationId the primary key of the entity migration
	* @return the entity migration
	* @throws PortalException if a entity migration with the primary key could not be found
	*/
	public static com.liferay.data.migration.tool.model.EntityMigration getEntityMigration(
		long entityMigrationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEntityMigration(entityMigrationId);
	}

	/**
	* Updates the entity migration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param entityMigration the entity migration
	* @return the entity migration that was updated
	*/
	public static com.liferay.data.migration.tool.model.EntityMigration updateEntityMigration(
		com.liferay.data.migration.tool.model.EntityMigration entityMigration) {
		return getService().updateEntityMigration(entityMigration);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of entity migrations.
	*
	* @return the number of entity migrations
	*/
	public static int getEntityMigrationsCount() {
		return getService().getEntityMigrationsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
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
	public static java.util.List<com.liferay.data.migration.tool.model.EntityMigration> getEntityMigrations(
		int start, int end) {
		return getService().getEntityMigrations(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static EntityMigrationLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EntityMigrationLocalService, EntityMigrationLocalService> _serviceTracker =
		ServiceTrackerFactory.open(EntityMigrationLocalService.class);
}