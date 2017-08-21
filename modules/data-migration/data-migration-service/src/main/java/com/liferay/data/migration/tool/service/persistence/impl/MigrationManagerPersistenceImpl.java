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

import com.liferay.data.migration.tool.exception.NoSuchMigrationManagerException;
import com.liferay.data.migration.tool.model.MigrationManager;
import com.liferay.data.migration.tool.model.impl.MigrationManagerImpl;
import com.liferay.data.migration.tool.model.impl.MigrationManagerModelImpl;
import com.liferay.data.migration.tool.service.persistence.MigrationManagerPersistence;

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
 * The persistence implementation for the migration manager service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Dylan Rebelak
 * @see MigrationManagerPersistence
 * @see com.liferay.data.migration.tool.service.persistence.MigrationManagerUtil
 * @generated
 */
@ProviderType
public class MigrationManagerPersistenceImpl extends BasePersistenceImpl<MigrationManager>
	implements MigrationManagerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MigrationManagerUtil} to access the migration manager persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MigrationManagerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MigrationManagerModelImpl.ENTITY_CACHE_ENABLED,
			MigrationManagerModelImpl.FINDER_CACHE_ENABLED,
			MigrationManagerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MigrationManagerModelImpl.ENTITY_CACHE_ENABLED,
			MigrationManagerModelImpl.FINDER_CACHE_ENABLED,
			MigrationManagerImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MigrationManagerModelImpl.ENTITY_CACHE_ENABLED,
			MigrationManagerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public MigrationManagerPersistenceImpl() {
		setModelClass(MigrationManager.class);
	}

	/**
	 * Caches the migration manager in the entity cache if it is enabled.
	 *
	 * @param migrationManager the migration manager
	 */
	@Override
	public void cacheResult(MigrationManager migrationManager) {
		entityCache.putResult(MigrationManagerModelImpl.ENTITY_CACHE_ENABLED,
			MigrationManagerImpl.class, migrationManager.getPrimaryKey(),
			migrationManager);

		migrationManager.resetOriginalValues();
	}

	/**
	 * Caches the migration managers in the entity cache if it is enabled.
	 *
	 * @param migrationManagers the migration managers
	 */
	@Override
	public void cacheResult(List<MigrationManager> migrationManagers) {
		for (MigrationManager migrationManager : migrationManagers) {
			if (entityCache.getResult(
						MigrationManagerModelImpl.ENTITY_CACHE_ENABLED,
						MigrationManagerImpl.class,
						migrationManager.getPrimaryKey()) == null) {
				cacheResult(migrationManager);
			}
			else {
				migrationManager.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all migration managers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MigrationManagerImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the migration manager.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MigrationManager migrationManager) {
		entityCache.removeResult(MigrationManagerModelImpl.ENTITY_CACHE_ENABLED,
			MigrationManagerImpl.class, migrationManager.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MigrationManager> migrationManagers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MigrationManager migrationManager : migrationManagers) {
			entityCache.removeResult(MigrationManagerModelImpl.ENTITY_CACHE_ENABLED,
				MigrationManagerImpl.class, migrationManager.getPrimaryKey());
		}
	}

	/**
	 * Creates a new migration manager with the primary key. Does not add the migration manager to the database.
	 *
	 * @param managerId the primary key for the new migration manager
	 * @return the new migration manager
	 */
	@Override
	public MigrationManager create(long managerId) {
		MigrationManager migrationManager = new MigrationManagerImpl();

		migrationManager.setNew(true);
		migrationManager.setPrimaryKey(managerId);

		return migrationManager;
	}

	/**
	 * Removes the migration manager with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param managerId the primary key of the migration manager
	 * @return the migration manager that was removed
	 * @throws NoSuchMigrationManagerException if a migration manager with the primary key could not be found
	 */
	@Override
	public MigrationManager remove(long managerId)
		throws NoSuchMigrationManagerException {
		return remove((Serializable)managerId);
	}

	/**
	 * Removes the migration manager with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the migration manager
	 * @return the migration manager that was removed
	 * @throws NoSuchMigrationManagerException if a migration manager with the primary key could not be found
	 */
	@Override
	public MigrationManager remove(Serializable primaryKey)
		throws NoSuchMigrationManagerException {
		Session session = null;

		try {
			session = openSession();

			MigrationManager migrationManager = (MigrationManager)session.get(MigrationManagerImpl.class,
					primaryKey);

			if (migrationManager == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMigrationManagerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(migrationManager);
		}
		catch (NoSuchMigrationManagerException nsee) {
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
	protected MigrationManager removeImpl(MigrationManager migrationManager) {
		migrationManager = toUnwrappedModel(migrationManager);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(migrationManager)) {
				migrationManager = (MigrationManager)session.get(MigrationManagerImpl.class,
						migrationManager.getPrimaryKeyObj());
			}

			if (migrationManager != null) {
				session.delete(migrationManager);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (migrationManager != null) {
			clearCache(migrationManager);
		}

		return migrationManager;
	}

	@Override
	public MigrationManager updateImpl(MigrationManager migrationManager) {
		migrationManager = toUnwrappedModel(migrationManager);

		boolean isNew = migrationManager.isNew();

		Session session = null;

		try {
			session = openSession();

			if (migrationManager.isNew()) {
				session.save(migrationManager);

				migrationManager.setNew(false);
			}
			else {
				migrationManager = (MigrationManager)session.merge(migrationManager);
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

		entityCache.putResult(MigrationManagerModelImpl.ENTITY_CACHE_ENABLED,
			MigrationManagerImpl.class, migrationManager.getPrimaryKey(),
			migrationManager, false);

		migrationManager.resetOriginalValues();

		return migrationManager;
	}

	protected MigrationManager toUnwrappedModel(
		MigrationManager migrationManager) {
		if (migrationManager instanceof MigrationManagerImpl) {
			return migrationManager;
		}

		MigrationManagerImpl migrationManagerImpl = new MigrationManagerImpl();

		migrationManagerImpl.setNew(migrationManager.isNew());
		migrationManagerImpl.setPrimaryKey(migrationManager.getPrimaryKey());

		migrationManagerImpl.setManagerId(migrationManager.getManagerId());
		migrationManagerImpl.setTimeStarted(migrationManager.getTimeStarted());
		migrationManagerImpl.setTimeCompleted(migrationManager.getTimeCompleted());
		migrationManagerImpl.setFromDate(migrationManager.getFromDate());
		migrationManagerImpl.setRecordsSynced(migrationManager.getRecordsSynced());

		return migrationManagerImpl;
	}

	/**
	 * Returns the migration manager with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the migration manager
	 * @return the migration manager
	 * @throws NoSuchMigrationManagerException if a migration manager with the primary key could not be found
	 */
	@Override
	public MigrationManager findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMigrationManagerException {
		MigrationManager migrationManager = fetchByPrimaryKey(primaryKey);

		if (migrationManager == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMigrationManagerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return migrationManager;
	}

	/**
	 * Returns the migration manager with the primary key or throws a {@link NoSuchMigrationManagerException} if it could not be found.
	 *
	 * @param managerId the primary key of the migration manager
	 * @return the migration manager
	 * @throws NoSuchMigrationManagerException if a migration manager with the primary key could not be found
	 */
	@Override
	public MigrationManager findByPrimaryKey(long managerId)
		throws NoSuchMigrationManagerException {
		return findByPrimaryKey((Serializable)managerId);
	}

	/**
	 * Returns the migration manager with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the migration manager
	 * @return the migration manager, or <code>null</code> if a migration manager with the primary key could not be found
	 */
	@Override
	public MigrationManager fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MigrationManagerModelImpl.ENTITY_CACHE_ENABLED,
				MigrationManagerImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MigrationManager migrationManager = (MigrationManager)serializable;

		if (migrationManager == null) {
			Session session = null;

			try {
				session = openSession();

				migrationManager = (MigrationManager)session.get(MigrationManagerImpl.class,
						primaryKey);

				if (migrationManager != null) {
					cacheResult(migrationManager);
				}
				else {
					entityCache.putResult(MigrationManagerModelImpl.ENTITY_CACHE_ENABLED,
						MigrationManagerImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MigrationManagerModelImpl.ENTITY_CACHE_ENABLED,
					MigrationManagerImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return migrationManager;
	}

	/**
	 * Returns the migration manager with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param managerId the primary key of the migration manager
	 * @return the migration manager, or <code>null</code> if a migration manager with the primary key could not be found
	 */
	@Override
	public MigrationManager fetchByPrimaryKey(long managerId) {
		return fetchByPrimaryKey((Serializable)managerId);
	}

	@Override
	public Map<Serializable, MigrationManager> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MigrationManager> map = new HashMap<Serializable, MigrationManager>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MigrationManager migrationManager = fetchByPrimaryKey(primaryKey);

			if (migrationManager != null) {
				map.put(primaryKey, migrationManager);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MigrationManagerModelImpl.ENTITY_CACHE_ENABLED,
					MigrationManagerImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MigrationManager)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MIGRATIONMANAGER_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (MigrationManager migrationManager : (List<MigrationManager>)q.list()) {
				map.put(migrationManager.getPrimaryKeyObj(), migrationManager);

				cacheResult(migrationManager);

				uncachedPrimaryKeys.remove(migrationManager.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MigrationManagerModelImpl.ENTITY_CACHE_ENABLED,
					MigrationManagerImpl.class, primaryKey, nullModel);
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
	 * Returns all the migration managers.
	 *
	 * @return the migration managers
	 */
	@Override
	public List<MigrationManager> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<MigrationManager> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<MigrationManager> findAll(int start, int end,
		OrderByComparator<MigrationManager> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<MigrationManager> findAll(int start, int end,
		OrderByComparator<MigrationManager> orderByComparator,
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

		List<MigrationManager> list = null;

		if (retrieveFromCache) {
			list = (List<MigrationManager>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MIGRATIONMANAGER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MIGRATIONMANAGER;

				if (pagination) {
					sql = sql.concat(MigrationManagerModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MigrationManager>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MigrationManager>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the migration managers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MigrationManager migrationManager : findAll()) {
			remove(migrationManager);
		}
	}

	/**
	 * Returns the number of migration managers.
	 *
	 * @return the number of migration managers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MIGRATIONMANAGER);

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
		return MigrationManagerModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the migration manager persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MigrationManagerImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MIGRATIONMANAGER = "SELECT migrationManager FROM MigrationManager migrationManager";
	private static final String _SQL_SELECT_MIGRATIONMANAGER_WHERE_PKS_IN = "SELECT migrationManager FROM MigrationManager migrationManager WHERE managerId IN (";
	private static final String _SQL_COUNT_MIGRATIONMANAGER = "SELECT COUNT(migrationManager) FROM MigrationManager migrationManager";
	private static final String _ORDER_BY_ENTITY_ALIAS = "migrationManager.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MigrationManager exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(MigrationManagerPersistenceImpl.class);
}