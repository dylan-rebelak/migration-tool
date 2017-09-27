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

package com.liferay.data.migration.tool.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.data.migration.tool.exception.NoSuchEntityManagerException;
import com.liferay.data.migration.tool.model.EntityManager;
import com.liferay.data.migration.tool.service.persistence.EntityManagerPersistence;
import com.liferay.data.migration.tool.service.persistence.EntityManagerUtil;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class EntityManagerPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.data.migration.tool.service"));

	@Before
	public void setUp() {
		_persistence = EntityManagerUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<EntityManager> iterator = _entityManagers.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		String pk = RandomTestUtil.randomString();

		EntityManager entityManager = _persistence.create(pk);

		Assert.assertNotNull(entityManager);

		Assert.assertEquals(entityManager.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		EntityManager newEntityManager = addEntityManager();

		_persistence.remove(newEntityManager);

		EntityManager existingEntityManager = _persistence.fetchByPrimaryKey(newEntityManager.getPrimaryKey());

		Assert.assertNull(existingEntityManager);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addEntityManager();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		String pk = RandomTestUtil.randomString();

		EntityManager newEntityManager = _persistence.create(pk);

		newEntityManager.setLastSyncDate(RandomTestUtil.nextDate());

		_entityManagers.add(_persistence.update(newEntityManager));

		EntityManager existingEntityManager = _persistence.findByPrimaryKey(newEntityManager.getPrimaryKey());

		Assert.assertEquals(existingEntityManager.getEntityName(),
			newEntityManager.getEntityName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingEntityManager.getLastSyncDate()),
			Time.getShortTimestamp(newEntityManager.getLastSyncDate()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		EntityManager newEntityManager = addEntityManager();

		EntityManager existingEntityManager = _persistence.findByPrimaryKey(newEntityManager.getPrimaryKey());

		Assert.assertEquals(existingEntityManager, newEntityManager);
	}

	@Test(expected = NoSuchEntityManagerException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		String pk = RandomTestUtil.randomString();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<EntityManager> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("SYNC_EntityManager",
			"entityName", true, "lastSyncDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		EntityManager newEntityManager = addEntityManager();

		EntityManager existingEntityManager = _persistence.fetchByPrimaryKey(newEntityManager.getPrimaryKey());

		Assert.assertEquals(existingEntityManager, newEntityManager);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		String pk = RandomTestUtil.randomString();

		EntityManager missingEntityManager = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingEntityManager);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		EntityManager newEntityManager1 = addEntityManager();
		EntityManager newEntityManager2 = addEntityManager();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newEntityManager1.getPrimaryKey());
		primaryKeys.add(newEntityManager2.getPrimaryKey());

		Map<Serializable, EntityManager> entityManagers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, entityManagers.size());
		Assert.assertEquals(newEntityManager1,
			entityManagers.get(newEntityManager1.getPrimaryKey()));
		Assert.assertEquals(newEntityManager2,
			entityManagers.get(newEntityManager2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		String pk1 = RandomTestUtil.randomString();

		String pk2 = RandomTestUtil.randomString();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, EntityManager> entityManagers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(entityManagers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		EntityManager newEntityManager = addEntityManager();

		String pk = RandomTestUtil.randomString();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newEntityManager.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, EntityManager> entityManagers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, entityManagers.size());
		Assert.assertEquals(newEntityManager,
			entityManagers.get(newEntityManager.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, EntityManager> entityManagers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(entityManagers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		EntityManager newEntityManager = addEntityManager();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newEntityManager.getPrimaryKey());

		Map<Serializable, EntityManager> entityManagers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, entityManagers.size());
		Assert.assertEquals(newEntityManager,
			entityManagers.get(newEntityManager.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		EntityManager newEntityManager = addEntityManager();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(EntityManager.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("entityName",
				newEntityManager.getEntityName()));

		List<EntityManager> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		EntityManager existingEntityManager = result.get(0);

		Assert.assertEquals(existingEntityManager, newEntityManager);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(EntityManager.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("entityName",
				RandomTestUtil.randomString()));

		List<EntityManager> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		EntityManager newEntityManager = addEntityManager();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(EntityManager.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("entityName"));

		Object newEntityName = newEntityManager.getEntityName();

		dynamicQuery.add(RestrictionsFactoryUtil.in("entityName",
				new Object[] { newEntityName }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingEntityName = result.get(0);

		Assert.assertEquals(existingEntityName, newEntityName);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(EntityManager.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("entityName"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("entityName",
				new Object[] { RandomTestUtil.randomString() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected EntityManager addEntityManager() throws Exception {
		String pk = RandomTestUtil.randomString();

		EntityManager entityManager = _persistence.create(pk);

		entityManager.setLastSyncDate(RandomTestUtil.nextDate());

		_entityManagers.add(_persistence.update(entityManager));

		return entityManager;
	}

	private List<EntityManager> _entityManagers = new ArrayList<EntityManager>();
	private EntityManagerPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}