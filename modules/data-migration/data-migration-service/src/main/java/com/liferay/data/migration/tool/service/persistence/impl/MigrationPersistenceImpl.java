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

import com.liferay.data.migration.tool.exception.NoSuchMigrationException;
import com.liferay.data.migration.tool.model.Migration;
import com.liferay.data.migration.tool.model.impl.MigrationImpl;
import com.liferay.data.migration.tool.model.impl.MigrationModelImpl;
import com.liferay.data.migration.tool.service.persistence.MigrationPersistence;

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
import com.liferay.portal.kernel.util.SetUtil;
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
 * The persistence implementation for the migration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Dylan Rebelak
 * @see MigrationPersistence
 * @see com.liferay.data.migration.tool.service.persistence.MigrationUtil
 * @generated
 */
@ProviderType
public class MigrationPersistenceImpl extends BasePersistenceImpl<Migration>
	implements MigrationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MigrationUtil} to access the migration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MigrationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MigrationModelImpl.ENTITY_CACHE_ENABLED,
			MigrationModelImpl.FINDER_CACHE_ENABLED, MigrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MigrationModelImpl.ENTITY_CACHE_ENABLED,
			MigrationModelImpl.FINDER_CACHE_ENABLED, MigrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MigrationModelImpl.ENTITY_CACHE_ENABLED,
			MigrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public MigrationPersistenceImpl() {
		setModelClass(Migration.class);
	}

	/**
	 * Caches the migration in the entity cache if it is enabled.
	 *
	 * @param migration the migration
	 */
	@Override
	public void cacheResult(Migration migration) {
		entityCache.putResult(MigrationModelImpl.ENTITY_CACHE_ENABLED,
			MigrationImpl.class, migration.getPrimaryKey(), migration);

		migration.resetOriginalValues();
	}

	/**
	 * Caches the migrations in the entity cache if it is enabled.
	 *
	 * @param migrations the migrations
	 */
	@Override
	public void cacheResult(List<Migration> migrations) {
		for (Migration migration : migrations) {
			if (entityCache.getResult(MigrationModelImpl.ENTITY_CACHE_ENABLED,
						MigrationImpl.class, migration.getPrimaryKey()) == null) {
				cacheResult(migration);
			}
			else {
				migration.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all migrations.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MigrationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the migration.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Migration migration) {
		entityCache.removeResult(MigrationModelImpl.ENTITY_CACHE_ENABLED,
			MigrationImpl.class, migration.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Migration> migrations) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Migration migration : migrations) {
			entityCache.removeResult(MigrationModelImpl.ENTITY_CACHE_ENABLED,
				MigrationImpl.class, migration.getPrimaryKey());
		}
	}

	/**
	 * Creates a new migration with the primary key. Does not add the migration to the database.
	 *
	 * @param migrationId the primary key for the new migration
	 * @return the new migration
	 */
	@Override
	public Migration create(long migrationId) {
		Migration migration = new MigrationImpl();

		migration.setNew(true);
		migration.setPrimaryKey(migrationId);

		return migration;
	}

	/**
	 * Removes the migration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param migrationId the primary key of the migration
	 * @return the migration that was removed
	 * @throws NoSuchMigrationException if a migration with the primary key could not be found
	 */
	@Override
	public Migration remove(long migrationId) throws NoSuchMigrationException {
		return remove((Serializable)migrationId);
	}

	/**
	 * Removes the migration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the migration
	 * @return the migration that was removed
	 * @throws NoSuchMigrationException if a migration with the primary key could not be found
	 */
	@Override
	public Migration remove(Serializable primaryKey)
		throws NoSuchMigrationException {
		Session session = null;

		try {
			session = openSession();

			Migration migration = (Migration)session.get(MigrationImpl.class,
					primaryKey);

			if (migration == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMigrationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(migration);
		}
		catch (NoSuchMigrationException nsee) {
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
	protected Migration removeImpl(Migration migration) {
		migration = toUnwrappedModel(migration);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(migration)) {
				migration = (Migration)session.get(MigrationImpl.class,
						migration.getPrimaryKeyObj());
			}

			if (migration != null) {
				session.delete(migration);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (migration != null) {
			clearCache(migration);
		}

		return migration;
	}

	@Override
	public Migration updateImpl(Migration migration) {
		migration = toUnwrappedModel(migration);

		boolean isNew = migration.isNew();

		Session session = null;

		try {
			session = openSession();

			if (migration.isNew()) {
				session.save(migration);

				migration.setNew(false);
			}
			else {
				migration = (Migration)session.merge(migration);
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

		entityCache.putResult(MigrationModelImpl.ENTITY_CACHE_ENABLED,
			MigrationImpl.class, migration.getPrimaryKey(), migration, false);

		migration.resetOriginalValues();

		return migration;
	}

	protected Migration toUnwrappedModel(Migration migration) {
		if (migration instanceof MigrationImpl) {
			return migration;
		}

		MigrationImpl migrationImpl = new MigrationImpl();

		migrationImpl.setNew(migration.isNew());
		migrationImpl.setPrimaryKey(migration.getPrimaryKey());

		migrationImpl.setMigrationId(migration.getMigrationId());
		migrationImpl.setStart(migration.getStart());
		migrationImpl.setEnd(migration.getEnd());

		return migrationImpl;
	}

	/**
	 * Returns the migration with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the migration
	 * @return the migration
	 * @throws NoSuchMigrationException if a migration with the primary key could not be found
	 */
	@Override
	public Migration findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMigrationException {
		Migration migration = fetchByPrimaryKey(primaryKey);

		if (migration == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMigrationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return migration;
	}

	/**
	 * Returns the migration with the primary key or throws a {@link NoSuchMigrationException} if it could not be found.
	 *
	 * @param migrationId the primary key of the migration
	 * @return the migration
	 * @throws NoSuchMigrationException if a migration with the primary key could not be found
	 */
	@Override
	public Migration findByPrimaryKey(long migrationId)
		throws NoSuchMigrationException {
		return findByPrimaryKey((Serializable)migrationId);
	}

	/**
	 * Returns the migration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the migration
	 * @return the migration, or <code>null</code> if a migration with the primary key could not be found
	 */
	@Override
	public Migration fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MigrationModelImpl.ENTITY_CACHE_ENABLED,
				MigrationImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Migration migration = (Migration)serializable;

		if (migration == null) {
			Session session = null;

			try {
				session = openSession();

				migration = (Migration)session.get(MigrationImpl.class,
						primaryKey);

				if (migration != null) {
					cacheResult(migration);
				}
				else {
					entityCache.putResult(MigrationModelImpl.ENTITY_CACHE_ENABLED,
						MigrationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MigrationModelImpl.ENTITY_CACHE_ENABLED,
					MigrationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return migration;
	}

	/**
	 * Returns the migration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param migrationId the primary key of the migration
	 * @return the migration, or <code>null</code> if a migration with the primary key could not be found
	 */
	@Override
	public Migration fetchByPrimaryKey(long migrationId) {
		return fetchByPrimaryKey((Serializable)migrationId);
	}

	@Override
	public Map<Serializable, Migration> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Migration> map = new HashMap<Serializable, Migration>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Migration migration = fetchByPrimaryKey(primaryKey);

			if (migration != null) {
				map.put(primaryKey, migration);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MigrationModelImpl.ENTITY_CACHE_ENABLED,
					MigrationImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Migration)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MIGRATION_WHERE_PKS_IN);

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

			for (Migration migration : (List<Migration>)q.list()) {
				map.put(migration.getPrimaryKeyObj(), migration);

				cacheResult(migration);

				uncachedPrimaryKeys.remove(migration.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MigrationModelImpl.ENTITY_CACHE_ENABLED,
					MigrationImpl.class, primaryKey, nullModel);
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
	 * Returns all the migrations.
	 *
	 * @return the migrations
	 */
	@Override
	public List<Migration> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<Migration> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<Migration> findAll(int start, int end,
		OrderByComparator<Migration> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<Migration> findAll(int start, int end,
		OrderByComparator<Migration> orderByComparator,
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

		List<Migration> list = null;

		if (retrieveFromCache) {
			list = (List<Migration>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MIGRATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MIGRATION;

				if (pagination) {
					sql = sql.concat(MigrationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Migration>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Migration>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the migrations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Migration migration : findAll()) {
			remove(migration);
		}
	}

	/**
	 * Returns the number of migrations.
	 *
	 * @return the number of migrations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MIGRATION);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return MigrationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the migration persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MigrationImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MIGRATION = "SELECT migration FROM Migration migration";
	private static final String _SQL_SELECT_MIGRATION_WHERE_PKS_IN = "SELECT migration FROM Migration migration WHERE migrationId IN (";
	private static final String _SQL_COUNT_MIGRATION = "SELECT COUNT(migration) FROM Migration migration";
	private static final String _ORDER_BY_ENTITY_ALIAS = "migration.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Migration exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(MigrationPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"start", "end"
			});
}