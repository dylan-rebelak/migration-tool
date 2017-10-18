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

import com.liferay.data.migration.tool.exception.NoSuchEntityMigrationException;
import com.liferay.data.migration.tool.model.EntityMigration;
import com.liferay.data.migration.tool.model.impl.EntityMigrationImpl;
import com.liferay.data.migration.tool.model.impl.EntityMigrationModelImpl;
import com.liferay.data.migration.tool.service.persistence.EntityMigrationPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the entity migration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Dylan Rebelak
 * @see EntityMigrationPersistence
 * @see com.liferay.data.migration.tool.service.persistence.EntityMigrationUtil
 * @generated
 */
@ProviderType
public class EntityMigrationPersistenceImpl extends BasePersistenceImpl<EntityMigration>
	implements EntityMigrationPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EntityMigrationUtil} to access the entity migration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EntityMigrationImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EntityMigrationModelImpl.ENTITY_CACHE_ENABLED,
			EntityMigrationModelImpl.FINDER_CACHE_ENABLED,
			EntityMigrationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EntityMigrationModelImpl.ENTITY_CACHE_ENABLED,
			EntityMigrationModelImpl.FINDER_CACHE_ENABLED,
			EntityMigrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EntityMigrationModelImpl.ENTITY_CACHE_ENABLED,
			EntityMigrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_MIGRATIONIDANDENTITYNAME =
		new FinderPath(EntityMigrationModelImpl.ENTITY_CACHE_ENABLED,
			EntityMigrationModelImpl.FINDER_CACHE_ENABLED,
			EntityMigrationImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByMigrationIdAndEntityName",
			new String[] { Long.class.getName(), String.class.getName() },
			EntityMigrationModelImpl.MIGRATIONID_COLUMN_BITMASK |
			EntityMigrationModelImpl.ENTITYNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MIGRATIONIDANDENTITYNAME =
		new FinderPath(EntityMigrationModelImpl.ENTITY_CACHE_ENABLED,
			EntityMigrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByMigrationIdAndEntityName",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the entity migration where migrationId = &#63; and entityName = &#63; or throws a {@link NoSuchEntityMigrationException} if it could not be found.
	 *
	 * @param migrationId the migration ID
	 * @param entityName the entity name
	 * @return the matching entity migration
	 * @throws NoSuchEntityMigrationException if a matching entity migration could not be found
	 */
	@Override
	public EntityMigration findByMigrationIdAndEntityName(long migrationId,
		String entityName) throws NoSuchEntityMigrationException {
		EntityMigration entityMigration = fetchByMigrationIdAndEntityName(migrationId,
				entityName);

		if (entityMigration == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("migrationId=");
			msg.append(migrationId);

			msg.append(", entityName=");
			msg.append(entityName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchEntityMigrationException(msg.toString());
		}

		return entityMigration;
	}

	/**
	 * Returns the entity migration where migrationId = &#63; and entityName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param migrationId the migration ID
	 * @param entityName the entity name
	 * @return the matching entity migration, or <code>null</code> if a matching entity migration could not be found
	 */
	@Override
	public EntityMigration fetchByMigrationIdAndEntityName(long migrationId,
		String entityName) {
		return fetchByMigrationIdAndEntityName(migrationId, entityName, true);
	}

	/**
	 * Returns the entity migration where migrationId = &#63; and entityName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param migrationId the migration ID
	 * @param entityName the entity name
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching entity migration, or <code>null</code> if a matching entity migration could not be found
	 */
	@Override
	public EntityMigration fetchByMigrationIdAndEntityName(long migrationId,
		String entityName, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { migrationId, entityName };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_MIGRATIONIDANDENTITYNAME,
					finderArgs, this);
		}

		if (result instanceof EntityMigration) {
			EntityMigration entityMigration = (EntityMigration)result;

			if ((migrationId != entityMigration.getMigrationId()) ||
					!Objects.equals(entityName, entityMigration.getEntityName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ENTITYMIGRATION_WHERE);

			query.append(_FINDER_COLUMN_MIGRATIONIDANDENTITYNAME_MIGRATIONID_2);

			boolean bindEntityName = false;

			if (entityName == null) {
				query.append(_FINDER_COLUMN_MIGRATIONIDANDENTITYNAME_ENTITYNAME_1);
			}
			else if (entityName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MIGRATIONIDANDENTITYNAME_ENTITYNAME_3);
			}
			else {
				bindEntityName = true;

				query.append(_FINDER_COLUMN_MIGRATIONIDANDENTITYNAME_ENTITYNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(migrationId);

				if (bindEntityName) {
					qPos.add(entityName);
				}

				List<EntityMigration> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_MIGRATIONIDANDENTITYNAME,
						finderArgs, list);
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							_log.warn(
								"EntityMigrationPersistenceImpl.fetchByMigrationIdAndEntityName(long, String, boolean) with parameters (" +
								StringUtil.merge(finderArgs) +
								") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					EntityMigration entityMigration = list.get(0);

					result = entityMigration;

					cacheResult(entityMigration);

					if ((entityMigration.getMigrationId() != migrationId) ||
							(entityMigration.getEntityName() == null) ||
							!entityMigration.getEntityName().equals(entityName)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_MIGRATIONIDANDENTITYNAME,
							finderArgs, entityMigration);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_MIGRATIONIDANDENTITYNAME,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (EntityMigration)result;
		}
	}

	/**
	 * Removes the entity migration where migrationId = &#63; and entityName = &#63; from the database.
	 *
	 * @param migrationId the migration ID
	 * @param entityName the entity name
	 * @return the entity migration that was removed
	 */
	@Override
	public EntityMigration removeByMigrationIdAndEntityName(long migrationId,
		String entityName) throws NoSuchEntityMigrationException {
		EntityMigration entityMigration = findByMigrationIdAndEntityName(migrationId,
				entityName);

		return remove(entityMigration);
	}

	/**
	 * Returns the number of entity migrations where migrationId = &#63; and entityName = &#63;.
	 *
	 * @param migrationId the migration ID
	 * @param entityName the entity name
	 * @return the number of matching entity migrations
	 */
	@Override
	public int countByMigrationIdAndEntityName(long migrationId,
		String entityName) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MIGRATIONIDANDENTITYNAME;

		Object[] finderArgs = new Object[] { migrationId, entityName };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ENTITYMIGRATION_WHERE);

			query.append(_FINDER_COLUMN_MIGRATIONIDANDENTITYNAME_MIGRATIONID_2);

			boolean bindEntityName = false;

			if (entityName == null) {
				query.append(_FINDER_COLUMN_MIGRATIONIDANDENTITYNAME_ENTITYNAME_1);
			}
			else if (entityName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MIGRATIONIDANDENTITYNAME_ENTITYNAME_3);
			}
			else {
				bindEntityName = true;

				query.append(_FINDER_COLUMN_MIGRATIONIDANDENTITYNAME_ENTITYNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(migrationId);

				if (bindEntityName) {
					qPos.add(entityName);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_MIGRATIONIDANDENTITYNAME_MIGRATIONID_2 =
		"entityMigration.migrationId = ? AND ";
	private static final String _FINDER_COLUMN_MIGRATIONIDANDENTITYNAME_ENTITYNAME_1 =
		"entityMigration.entityName IS NULL";
	private static final String _FINDER_COLUMN_MIGRATIONIDANDENTITYNAME_ENTITYNAME_2 =
		"entityMigration.entityName = ?";
	private static final String _FINDER_COLUMN_MIGRATIONIDANDENTITYNAME_ENTITYNAME_3 =
		"(entityMigration.entityName IS NULL OR entityMigration.entityName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MIGRATIONID =
		new FinderPath(EntityMigrationModelImpl.ENTITY_CACHE_ENABLED,
			EntityMigrationModelImpl.FINDER_CACHE_ENABLED,
			EntityMigrationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByMigrationId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MIGRATIONID =
		new FinderPath(EntityMigrationModelImpl.ENTITY_CACHE_ENABLED,
			EntityMigrationModelImpl.FINDER_CACHE_ENABLED,
			EntityMigrationImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMigrationId",
			new String[] { Long.class.getName() },
			EntityMigrationModelImpl.MIGRATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MIGRATIONID = new FinderPath(EntityMigrationModelImpl.ENTITY_CACHE_ENABLED,
			EntityMigrationModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMigrationId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the entity migrations where migrationId = &#63;.
	 *
	 * @param migrationId the migration ID
	 * @return the matching entity migrations
	 */
	@Override
	public List<EntityMigration> findByMigrationId(long migrationId) {
		return findByMigrationId(migrationId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<EntityMigration> findByMigrationId(long migrationId, int start,
		int end) {
		return findByMigrationId(migrationId, start, end, null);
	}

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
	@Override
	public List<EntityMigration> findByMigrationId(long migrationId, int start,
		int end, OrderByComparator<EntityMigration> orderByComparator) {
		return findByMigrationId(migrationId, start, end, orderByComparator,
			true);
	}

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
	@Override
	public List<EntityMigration> findByMigrationId(long migrationId, int start,
		int end, OrderByComparator<EntityMigration> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MIGRATIONID;
			finderArgs = new Object[] { migrationId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MIGRATIONID;
			finderArgs = new Object[] { migrationId, start, end, orderByComparator };
		}

		List<EntityMigration> list = null;

		if (retrieveFromCache) {
			list = (List<EntityMigration>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (EntityMigration entityMigration : list) {
					if ((migrationId != entityMigration.getMigrationId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ENTITYMIGRATION_WHERE);

			query.append(_FINDER_COLUMN_MIGRATIONID_MIGRATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EntityMigrationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(migrationId);

				if (!pagination) {
					list = (List<EntityMigration>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EntityMigration>)QueryUtil.list(q,
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
	 * Returns the first entity migration in the ordered set where migrationId = &#63;.
	 *
	 * @param migrationId the migration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entity migration
	 * @throws NoSuchEntityMigrationException if a matching entity migration could not be found
	 */
	@Override
	public EntityMigration findByMigrationId_First(long migrationId,
		OrderByComparator<EntityMigration> orderByComparator)
		throws NoSuchEntityMigrationException {
		EntityMigration entityMigration = fetchByMigrationId_First(migrationId,
				orderByComparator);

		if (entityMigration != null) {
			return entityMigration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("migrationId=");
		msg.append(migrationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntityMigrationException(msg.toString());
	}

	/**
	 * Returns the first entity migration in the ordered set where migrationId = &#63;.
	 *
	 * @param migrationId the migration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching entity migration, or <code>null</code> if a matching entity migration could not be found
	 */
	@Override
	public EntityMigration fetchByMigrationId_First(long migrationId,
		OrderByComparator<EntityMigration> orderByComparator) {
		List<EntityMigration> list = findByMigrationId(migrationId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last entity migration in the ordered set where migrationId = &#63;.
	 *
	 * @param migrationId the migration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entity migration
	 * @throws NoSuchEntityMigrationException if a matching entity migration could not be found
	 */
	@Override
	public EntityMigration findByMigrationId_Last(long migrationId,
		OrderByComparator<EntityMigration> orderByComparator)
		throws NoSuchEntityMigrationException {
		EntityMigration entityMigration = fetchByMigrationId_Last(migrationId,
				orderByComparator);

		if (entityMigration != null) {
			return entityMigration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("migrationId=");
		msg.append(migrationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEntityMigrationException(msg.toString());
	}

	/**
	 * Returns the last entity migration in the ordered set where migrationId = &#63;.
	 *
	 * @param migrationId the migration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching entity migration, or <code>null</code> if a matching entity migration could not be found
	 */
	@Override
	public EntityMigration fetchByMigrationId_Last(long migrationId,
		OrderByComparator<EntityMigration> orderByComparator) {
		int count = countByMigrationId(migrationId);

		if (count == 0) {
			return null;
		}

		List<EntityMigration> list = findByMigrationId(migrationId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the entity migrations before and after the current entity migration in the ordered set where migrationId = &#63;.
	 *
	 * @param entityMigrationId the primary key of the current entity migration
	 * @param migrationId the migration ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next entity migration
	 * @throws NoSuchEntityMigrationException if a entity migration with the primary key could not be found
	 */
	@Override
	public EntityMigration[] findByMigrationId_PrevAndNext(
		long entityMigrationId, long migrationId,
		OrderByComparator<EntityMigration> orderByComparator)
		throws NoSuchEntityMigrationException {
		EntityMigration entityMigration = findByPrimaryKey(entityMigrationId);

		Session session = null;

		try {
			session = openSession();

			EntityMigration[] array = new EntityMigrationImpl[3];

			array[0] = getByMigrationId_PrevAndNext(session, entityMigration,
					migrationId, orderByComparator, true);

			array[1] = entityMigration;

			array[2] = getByMigrationId_PrevAndNext(session, entityMigration,
					migrationId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected EntityMigration getByMigrationId_PrevAndNext(Session session,
		EntityMigration entityMigration, long migrationId,
		OrderByComparator<EntityMigration> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENTITYMIGRATION_WHERE);

		query.append(_FINDER_COLUMN_MIGRATIONID_MIGRATIONID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EntityMigrationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(migrationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(entityMigration);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<EntityMigration> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the entity migrations where migrationId = &#63; from the database.
	 *
	 * @param migrationId the migration ID
	 */
	@Override
	public void removeByMigrationId(long migrationId) {
		for (EntityMigration entityMigration : findByMigrationId(migrationId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(entityMigration);
		}
	}

	/**
	 * Returns the number of entity migrations where migrationId = &#63;.
	 *
	 * @param migrationId the migration ID
	 * @return the number of matching entity migrations
	 */
	@Override
	public int countByMigrationId(long migrationId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MIGRATIONID;

		Object[] finderArgs = new Object[] { migrationId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ENTITYMIGRATION_WHERE);

			query.append(_FINDER_COLUMN_MIGRATIONID_MIGRATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(migrationId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_MIGRATIONID_MIGRATIONID_2 = "entityMigration.migrationId = ?";

	public EntityMigrationPersistenceImpl() {
		setModelClass(EntityMigration.class);
	}

	/**
	 * Caches the entity migration in the entity cache if it is enabled.
	 *
	 * @param entityMigration the entity migration
	 */
	@Override
	public void cacheResult(EntityMigration entityMigration) {
		entityCache.putResult(EntityMigrationModelImpl.ENTITY_CACHE_ENABLED,
			EntityMigrationImpl.class, entityMigration.getPrimaryKey(),
			entityMigration);

		finderCache.putResult(FINDER_PATH_FETCH_BY_MIGRATIONIDANDENTITYNAME,
			new Object[] {
				entityMigration.getMigrationId(),
				entityMigration.getEntityName()
			}, entityMigration);

		entityMigration.resetOriginalValues();
	}

	/**
	 * Caches the entity migrations in the entity cache if it is enabled.
	 *
	 * @param entityMigrations the entity migrations
	 */
	@Override
	public void cacheResult(List<EntityMigration> entityMigrations) {
		for (EntityMigration entityMigration : entityMigrations) {
			if (entityCache.getResult(
						EntityMigrationModelImpl.ENTITY_CACHE_ENABLED,
						EntityMigrationImpl.class,
						entityMigration.getPrimaryKey()) == null) {
				cacheResult(entityMigration);
			}
			else {
				entityMigration.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all entity migrations.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EntityMigrationImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the entity migration.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(EntityMigration entityMigration) {
		entityCache.removeResult(EntityMigrationModelImpl.ENTITY_CACHE_ENABLED,
			EntityMigrationImpl.class, entityMigration.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((EntityMigrationModelImpl)entityMigration, true);
	}

	@Override
	public void clearCache(List<EntityMigration> entityMigrations) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (EntityMigration entityMigration : entityMigrations) {
			entityCache.removeResult(EntityMigrationModelImpl.ENTITY_CACHE_ENABLED,
				EntityMigrationImpl.class, entityMigration.getPrimaryKey());

			clearUniqueFindersCache((EntityMigrationModelImpl)entityMigration,
				true);
		}
	}

	protected void cacheUniqueFindersCache(
		EntityMigrationModelImpl entityMigrationModelImpl) {
		Object[] args = new Object[] {
				entityMigrationModelImpl.getMigrationId(),
				entityMigrationModelImpl.getEntityName()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_MIGRATIONIDANDENTITYNAME,
			args, Long.valueOf(1), false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_MIGRATIONIDANDENTITYNAME,
			args, entityMigrationModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		EntityMigrationModelImpl entityMigrationModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					entityMigrationModelImpl.getMigrationId(),
					entityMigrationModelImpl.getEntityName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MIGRATIONIDANDENTITYNAME,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_MIGRATIONIDANDENTITYNAME,
				args);
		}

		if ((entityMigrationModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_MIGRATIONIDANDENTITYNAME.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					entityMigrationModelImpl.getOriginalMigrationId(),
					entityMigrationModelImpl.getOriginalEntityName()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_MIGRATIONIDANDENTITYNAME,
				args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_MIGRATIONIDANDENTITYNAME,
				args);
		}
	}

	/**
	 * Creates a new entity migration with the primary key. Does not add the entity migration to the database.
	 *
	 * @param entityMigrationId the primary key for the new entity migration
	 * @return the new entity migration
	 */
	@Override
	public EntityMigration create(long entityMigrationId) {
		EntityMigration entityMigration = new EntityMigrationImpl();

		entityMigration.setNew(true);
		entityMigration.setPrimaryKey(entityMigrationId);

		return entityMigration;
	}

	/**
	 * Removes the entity migration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entityMigrationId the primary key of the entity migration
	 * @return the entity migration that was removed
	 * @throws NoSuchEntityMigrationException if a entity migration with the primary key could not be found
	 */
	@Override
	public EntityMigration remove(long entityMigrationId)
		throws NoSuchEntityMigrationException {
		return remove((Serializable)entityMigrationId);
	}

	/**
	 * Removes the entity migration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the entity migration
	 * @return the entity migration that was removed
	 * @throws NoSuchEntityMigrationException if a entity migration with the primary key could not be found
	 */
	@Override
	public EntityMigration remove(Serializable primaryKey)
		throws NoSuchEntityMigrationException {
		Session session = null;

		try {
			session = openSession();

			EntityMigration entityMigration = (EntityMigration)session.get(EntityMigrationImpl.class,
					primaryKey);

			if (entityMigration == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEntityMigrationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(entityMigration);
		}
		catch (NoSuchEntityMigrationException nsee) {
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
	protected EntityMigration removeImpl(EntityMigration entityMigration) {
		entityMigration = toUnwrappedModel(entityMigration);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(entityMigration)) {
				entityMigration = (EntityMigration)session.get(EntityMigrationImpl.class,
						entityMigration.getPrimaryKeyObj());
			}

			if (entityMigration != null) {
				session.delete(entityMigration);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (entityMigration != null) {
			clearCache(entityMigration);
		}

		return entityMigration;
	}

	@Override
	public EntityMigration updateImpl(EntityMigration entityMigration) {
		entityMigration = toUnwrappedModel(entityMigration);

		boolean isNew = entityMigration.isNew();

		EntityMigrationModelImpl entityMigrationModelImpl = (EntityMigrationModelImpl)entityMigration;

		Session session = null;

		try {
			session = openSession();

			if (entityMigration.isNew()) {
				session.save(entityMigration);

				entityMigration.setNew(false);
			}
			else {
				entityMigration = (EntityMigration)session.merge(entityMigration);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !EntityMigrationModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((entityMigrationModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MIGRATIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						entityMigrationModelImpl.getOriginalMigrationId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MIGRATIONID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MIGRATIONID,
					args);

				args = new Object[] { entityMigrationModelImpl.getMigrationId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_MIGRATIONID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MIGRATIONID,
					args);
			}
		}

		entityCache.putResult(EntityMigrationModelImpl.ENTITY_CACHE_ENABLED,
			EntityMigrationImpl.class, entityMigration.getPrimaryKey(),
			entityMigration, false);

		clearUniqueFindersCache(entityMigrationModelImpl, false);
		cacheUniqueFindersCache(entityMigrationModelImpl);

		entityMigration.resetOriginalValues();

		return entityMigration;
	}

	protected EntityMigration toUnwrappedModel(EntityMigration entityMigration) {
		if (entityMigration instanceof EntityMigrationImpl) {
			return entityMigration;
		}

		EntityMigrationImpl entityMigrationImpl = new EntityMigrationImpl();

		entityMigrationImpl.setNew(entityMigration.isNew());
		entityMigrationImpl.setPrimaryKey(entityMigration.getPrimaryKey());

		entityMigrationImpl.setEntityMigrationId(entityMigration.getEntityMigrationId());
		entityMigrationImpl.setMigrationId(entityMigration.getMigrationId());
		entityMigrationImpl.setEntityName(entityMigration.getEntityName());
		entityMigrationImpl.setStart(entityMigration.getStart());
		entityMigrationImpl.setEnd(entityMigration.getEnd());
		entityMigrationImpl.setCount(entityMigration.getCount());
		entityMigrationImpl.setLastCompletion(entityMigration.getLastCompletion());

		return entityMigrationImpl;
	}

	/**
	 * Returns the entity migration with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the entity migration
	 * @return the entity migration
	 * @throws NoSuchEntityMigrationException if a entity migration with the primary key could not be found
	 */
	@Override
	public EntityMigration findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEntityMigrationException {
		EntityMigration entityMigration = fetchByPrimaryKey(primaryKey);

		if (entityMigration == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEntityMigrationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return entityMigration;
	}

	/**
	 * Returns the entity migration with the primary key or throws a {@link NoSuchEntityMigrationException} if it could not be found.
	 *
	 * @param entityMigrationId the primary key of the entity migration
	 * @return the entity migration
	 * @throws NoSuchEntityMigrationException if a entity migration with the primary key could not be found
	 */
	@Override
	public EntityMigration findByPrimaryKey(long entityMigrationId)
		throws NoSuchEntityMigrationException {
		return findByPrimaryKey((Serializable)entityMigrationId);
	}

	/**
	 * Returns the entity migration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the entity migration
	 * @return the entity migration, or <code>null</code> if a entity migration with the primary key could not be found
	 */
	@Override
	public EntityMigration fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(EntityMigrationModelImpl.ENTITY_CACHE_ENABLED,
				EntityMigrationImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		EntityMigration entityMigration = (EntityMigration)serializable;

		if (entityMigration == null) {
			Session session = null;

			try {
				session = openSession();

				entityMigration = (EntityMigration)session.get(EntityMigrationImpl.class,
						primaryKey);

				if (entityMigration != null) {
					cacheResult(entityMigration);
				}
				else {
					entityCache.putResult(EntityMigrationModelImpl.ENTITY_CACHE_ENABLED,
						EntityMigrationImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(EntityMigrationModelImpl.ENTITY_CACHE_ENABLED,
					EntityMigrationImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return entityMigration;
	}

	/**
	 * Returns the entity migration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param entityMigrationId the primary key of the entity migration
	 * @return the entity migration, or <code>null</code> if a entity migration with the primary key could not be found
	 */
	@Override
	public EntityMigration fetchByPrimaryKey(long entityMigrationId) {
		return fetchByPrimaryKey((Serializable)entityMigrationId);
	}

	@Override
	public Map<Serializable, EntityMigration> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, EntityMigration> map = new HashMap<Serializable, EntityMigration>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			EntityMigration entityMigration = fetchByPrimaryKey(primaryKey);

			if (entityMigration != null) {
				map.put(primaryKey, entityMigration);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(EntityMigrationModelImpl.ENTITY_CACHE_ENABLED,
					EntityMigrationImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (EntityMigration)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ENTITYMIGRATION_WHERE_PKS_IN);

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

			for (EntityMigration entityMigration : (List<EntityMigration>)q.list()) {
				map.put(entityMigration.getPrimaryKeyObj(), entityMigration);

				cacheResult(entityMigration);

				uncachedPrimaryKeys.remove(entityMigration.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(EntityMigrationModelImpl.ENTITY_CACHE_ENABLED,
					EntityMigrationImpl.class, primaryKey, nullModel);
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
	 * Returns all the entity migrations.
	 *
	 * @return the entity migrations
	 */
	@Override
	public List<EntityMigration> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<EntityMigration> findAll(int start, int end) {
		return findAll(start, end, null);
	}

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
	@Override
	public List<EntityMigration> findAll(int start, int end,
		OrderByComparator<EntityMigration> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

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
	@Override
	public List<EntityMigration> findAll(int start, int end,
		OrderByComparator<EntityMigration> orderByComparator,
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

		List<EntityMigration> list = null;

		if (retrieveFromCache) {
			list = (List<EntityMigration>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ENTITYMIGRATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ENTITYMIGRATION;

				if (pagination) {
					sql = sql.concat(EntityMigrationModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<EntityMigration>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<EntityMigration>)QueryUtil.list(q,
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
	 * Removes all the entity migrations from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (EntityMigration entityMigration : findAll()) {
			remove(entityMigration);
		}
	}

	/**
	 * Returns the number of entity migrations.
	 *
	 * @return the number of entity migrations
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ENTITYMIGRATION);

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
		return EntityMigrationModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the entity migration persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(EntityMigrationImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ENTITYMIGRATION = "SELECT entityMigration FROM EntityMigration entityMigration";
	private static final String _SQL_SELECT_ENTITYMIGRATION_WHERE_PKS_IN = "SELECT entityMigration FROM EntityMigration entityMigration WHERE entityMigrationId IN (";
	private static final String _SQL_SELECT_ENTITYMIGRATION_WHERE = "SELECT entityMigration FROM EntityMigration entityMigration WHERE ";
	private static final String _SQL_COUNT_ENTITYMIGRATION = "SELECT COUNT(entityMigration) FROM EntityMigration entityMigration";
	private static final String _SQL_COUNT_ENTITYMIGRATION_WHERE = "SELECT COUNT(entityMigration) FROM EntityMigration entityMigration WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "entityMigration.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No EntityMigration exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No EntityMigration exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(EntityMigrationPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"start", "end"
			});
}