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

package com.liferay.data.migration.tool.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.data.migration.tool.exception.NoSuchEntityManagerException;
import com.liferay.data.migration.tool.model.EntityManager;
import com.liferay.data.migration.tool.model.impl.EntityManagerImpl;
import com.liferay.data.migration.tool.model.impl.EntityManagerModelImpl;
import com.liferay.data.migration.tool.service.persistence.EntityManagerPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the entity manager service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Dylan Rebelak
 * @see EntityManagerPersistence
 * @see com.liferay.data.migration.tool.service.persistence.EntityManagerUtil
 * @generated
 */
@ProviderType
public class EntityManagerPersistenceImpl extends BasePersistenceImpl<EntityManager>
	implements EntityManagerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EntityManagerUtil} to access the entity manager persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EntityManagerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EntityManagerModelImpl.ENTITY_CACHE_ENABLED,
			EntityManagerModelImpl.FINDER_CACHE_ENABLED,
			EntityManagerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EntityManagerModelImpl.ENTITY_CACHE_ENABLED,
			EntityManagerModelImpl.FINDER_CACHE_ENABLED,
			EntityManagerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EntityManagerModelImpl.ENTITY_CACHE_ENABLED,
			EntityManagerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public EntityManagerPersistenceImpl() {
		setModelClass(EntityManager.class);
	}

	/**
	 * Caches the entity manager in the entity cache if it is enabled.
	 *
	 * @param entityManager the entity manager
	 */
	@Override
	public void cacheResult(EntityManager entityManager) {
		entityCache.putResult(EntityManagerModelImpl.ENTITY_CACHE_ENABLED,
			EntityManagerImpl.class, entityManager.getPrimaryKey(),
			entityManager);

		entityManager.resetOriginalValues();
	}

	/**
	 * Caches the entity managers in the entity cache if it is enabled.
	 *
	 * @param entityManagers the entity managers
	 */
	@Override
	public void cacheResult(List<EntityManager> entityManagers) {
		for (EntityManager entityManager : entityManagers) {
			if (entityCache.getResult(
						EntityManagerModelImpl.ENTITY_CACHE_ENABLED,
						EntityManagerImpl.class, entityManager.getPrimaryKey()) == null) {
				cacheResult(entityManager);
			}
			else {
				entityManager.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all entity managers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EntityManagerImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the entity manager.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EntityManager entityManager) {
		entityCache.removeResult(EntityManagerModelImpl.ENTITY_CACHE_ENABLED,
			EntityManagerImpl.class, entityManager.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<EntityManager> entityManagers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (EntityManager entityManager : entityManagers) {
			entityCache.removeResult(EntityManagerModelImpl.ENTITY_CACHE_ENABLED,
				EntityManagerImpl.class, entityManager.getPrimaryKey());
		}
	}

	/**
	 * Creates a new entity manager with the primary key. Does not add the entity manager to the database.
	 *
	 * @param entityName the primary key for the new entity manager
	 * @return the new entity manager
	 */
	@Override
	public EntityManager create(String entityName) {
		EntityManager entityManager = new EntityManagerImpl();

		entityManager.setNew(true);
		entityManager.setPrimaryKey(entityName);

		return entityManager;
	}

	/**
	 * Removes the entity manager with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entityName the primary key of the entity manager
	 * @return the entity manager that was removed
	 * @throws NoSuchEntityManagerException if a entity manager with the primary key could not be found
	 */
	@Override
	public EntityManager remove(String entityName)
		throws NoSuchEntityManagerException {
		return remove((Serializable)entityName);
	}

	/**
	 * Removes the entity manager with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the entity manager
	 * @return the entity manager that was removed
	 * @throws NoSuchEntityManagerException if a entity manager with the primary key could not be found
	 */
	@Override
	public EntityManager remove(Serializable primaryKey)
		throws NoSuchEntityManagerException {
		Session session = null;

		try {
			session = openSession();

			EntityManager entityManager = (EntityManager)session.get(EntityManagerImpl.class,
					primaryKey);

			if (entityManager == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntityManagerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(entityManager);
		}
		catch (NoSuchEntityManagerException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected EntityManager removeImpl(EntityManager entityManager) {
		entityManager = toUnwrappedModel(entityManager);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(entityManager)) {
				entityManager = (EntityManager)session.get(EntityManagerImpl.class,
						entityManager.getPrimaryKeyObj());
			}

			if (entityManager != null) {
				session.delete(entityManager);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (entityManager != null) {
			clearCache(entityManager);
		}

		return entityManager;
	}

	@Override
	public EntityManager updateImpl(EntityManager entityManager) {
		entityManager = toUnwrappedModel(entityManager);

		boolean isNew = entityManager.isNew();

		Session session = null;

		try {
			session = openSession();

			if (entityManager.isNew()) {
				session.save(entityManager);

				entityManager.setNew(false);
			}
			else {
				entityManager = (EntityManager)session.merge(entityManager);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		entityCache.putResult(EntityManagerModelImpl.ENTITY_CACHE_ENABLED,
			EntityManagerImpl.class, entityManager.getPrimaryKey(),
			entityManager, false);

		entityManager.resetOriginalValues();

		return entityManager;
	}

	protected EntityManager toUnwrappedModel(EntityManager entityManager) {
		if (entityManager instanceof EntityManagerImpl) {
			return entityManager;
		}

		EntityManagerImpl entityManagerImpl = new EntityManagerImpl();

		entityManagerImpl.setNew(entityManager.isNew());
		entityManagerImpl.setPrimaryKey(entityManager.getPrimaryKey());

		entityManagerImpl.setEntityName(entityManager.getEntityName());
		entityManagerImpl.setLastSyncDate(entityManager.getLastSyncDate());

		return entityManagerImpl;
	}

	/**
	 * Returns the entity manager with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the entity manager
	 * @return the entity manager
	 * @throws NoSuchEntityManagerException if a entity manager with the primary key could not be found
	 */
	@Override
	public EntityManager findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntityManagerException {
		EntityManager entityManager = fetchByPrimaryKey(primaryKey);

		if (entityManager == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntityManagerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return entityManager;
	}

	/**
	 * Returns the entity manager with the primary key or throws a {@link NoSuchEntityManagerException} if it could not be found.
	 *
	 * @param entityName the primary key of the entity manager
	 * @return the entity manager
	 * @throws NoSuchEntityManagerException if a entity manager with the primary key could not be found
	 */
	@Override
	public EntityManager findByPrimaryKey(String entityName)
		throws NoSuchEntityManagerException {
		return findByPrimaryKey((Serializable)entityName);
	}

	/**
	 * Returns the entity manager with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the entity manager
	 * @return the entity manager, or <code>null</code> if a entity manager with the primary key could not be found
	 */
	@Override
	public EntityManager fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(EntityManagerModelImpl.ENTITY_CACHE_ENABLED,
				EntityManagerImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		EntityManager entityManager = (EntityManager)serializable;

		if (entityManager == null) {
			Session session = null;

			try {
				session = openSession();

				entityManager = (EntityManager)session.get(EntityManagerImpl.class,
						primaryKey);

				if (entityManager != null) {
					cacheResult(entityManager);
				}
				else {
					entityCache.putResult(EntityManagerModelImpl.ENTITY_CACHE_ENABLED,
						EntityManagerImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(EntityManagerModelImpl.ENTITY_CACHE_ENABLED,
					EntityManagerImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return entityManager;
	}

	/**
	 * Returns the entity manager with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entityName the primary key of the entity manager
	 * @return the entity manager, or <code>null</code> if a entity manager with the primary key could not be found
	 */
	@Override
	public EntityManager fetchByPrimaryKey(String entityName) {
		return fetchByPrimaryKey((Serializable)entityName);
	}

	@Override
	public Map<Serializable, EntityManager> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, EntityManager> map = new HashMap<Serializable, EntityManager>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			EntityManager entityManager = fetchByPrimaryKey(primaryKey);

			if (entityManager != null) {
				map.put(primaryKey, entityManager);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(EntityManagerModelImpl.ENTITY_CACHE_ENABLED,
					EntityManagerImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (EntityManager)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 4) +
				1);

		query.append(_SQL_SELECT_ENTITYMANAGER_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(StringPool.APOSTROPHE);
			query.append((String)primaryKey);
			query.append(StringPool.APOSTROPHE);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (EntityManager entityManager : (List<EntityManager>)q.list()) {
				map.put(entityManager.getPrimaryKeyObj(), entityManager);

				cacheResult(entityManager);

				uncachedPrimaryKeys.remove(entityManager.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(EntityManagerModelImpl.ENTITY_CACHE_ENABLED,
					EntityManagerImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the entity managers.
	 *
	 * @return the entity managers
	 */
	@Override
	public List<EntityManager> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<EntityManager> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<EntityManager> findAll(int start, int end,
		OrderByComparator<EntityManager> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<EntityManager> findAll(int start, int end,
		OrderByComparator<EntityManager> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<EntityManager> list = null;

		if (retrieveFromCache) {
			list = (List<EntityManager>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ENTITYMANAGER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ENTITYMANAGER;

				if (pagination) {
					sql = sql.concat(EntityManagerModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<EntityManager>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EntityManager>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the entity managers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EntityManager entityManager : findAll()) {
			remove(entityManager);
		}
	}

	/**
	 * Returns the number of entity managers.
	 *
	 * @return the number of entity managers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ENTITYMANAGER);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return EntityManagerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the entity manager persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(EntityManagerImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ENTITYMANAGER = "SELECT entityManager FROM EntityManager entityManager";
	private static final String _SQL_SELECT_ENTITYMANAGER_WHERE_PKS_IN = "SELECT entityManager FROM EntityManager entityManager WHERE entityName IN (";
	private static final String _SQL_COUNT_ENTITYMANAGER = "SELECT COUNT(entityManager) FROM EntityManager entityManager";
	private static final String _ORDER_BY_ENTITY_ALIAS = "entityManager.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No EntityManager exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(EntityManagerPersistenceImpl.class);
}