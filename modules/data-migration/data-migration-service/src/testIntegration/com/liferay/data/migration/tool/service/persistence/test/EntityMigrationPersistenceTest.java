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

import com.liferay.data.migration.tool.exception.NoSuchEntityMigrationException;
import com.liferay.data.migration.tool.model.EntityMigration;
import com.liferay.data.migration.tool.service.EntityMigrationLocalServiceUtil;
import com.liferay.data.migration.tool.service.persistence.EntityMigrationPersistence;
import com.liferay.data.migration.tool.service.persistence.EntityMigrationUtil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
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
import java.util.Objects;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class EntityMigrationPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.data.migration.tool.service"));

	@Before
	public void setUp() {
		_persistence = EntityMigrationUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<EntityMigration> iterator = _entityMigrations.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		EntityMigration entityMigration = _persistence.create(pk);

		Assert.assertNotNull(entityMigration);

		Assert.assertEquals(entityMigration.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		EntityMigration newEntityMigration = addEntityMigration();

		_persistence.remove(newEntityMigration);

		EntityMigration existingEntityMigration = _persistence.fetchByPrimaryKey(newEntityMigration.getPrimaryKey());

		Assert.assertNull(existingEntityMigration);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addEntityMigration();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		EntityMigration newEntityMigration = _persistence.create(pk);

		newEntityMigration.setMigrationId(RandomTestUtil.nextLong());

		newEntityMigration.setEntityName(RandomTestUtil.randomString());

		newEntityMigration.setStart(RandomTestUtil.nextDate());

		newEntityMigration.setEnd(RandomTestUtil.nextDate());

		newEntityMigration.setCount(RandomTestUtil.nextLong());

		newEntityMigration.setLastCompletion(RandomTestUtil.nextDate());

		_entityMigrations.add(_persistence.update(newEntityMigration));

		EntityMigration existingEntityMigration = _persistence.findByPrimaryKey(newEntityMigration.getPrimaryKey());

		Assert.assertEquals(existingEntityMigration.getEntityMigrationId(),
			newEntityMigration.getEntityMigrationId());
		Assert.assertEquals(existingEntityMigration.getMigrationId(),
			newEntityMigration.getMigrationId());
		Assert.assertEquals(existingEntityMigration.getEntityName(),
			newEntityMigration.getEntityName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingEntityMigration.getStart()),
			Time.getShortTimestamp(newEntityMigration.getStart()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingEntityMigration.getEnd()),
			Time.getShortTimestamp(newEntityMigration.getEnd()));
		Assert.assertEquals(existingEntityMigration.getCount(),
			newEntityMigration.getCount());
		Assert.assertEquals(Time.getShortTimestamp(
				existingEntityMigration.getLastCompletion()),
			Time.getShortTimestamp(newEntityMigration.getLastCompletion()));
	}

	@Test
	public void testCountByMigrationIdAndEntityName() throws Exception {
		_persistence.countByMigrationIdAndEntityName(RandomTestUtil.nextLong(),
			StringPool.BLANK);

		_persistence.countByMigrationIdAndEntityName(0L, StringPool.NULL);

		_persistence.countByMigrationIdAndEntityName(0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		EntityMigration newEntityMigration = addEntityMigration();

		EntityMigration existingEntityMigration = _persistence.findByPrimaryKey(newEntityMigration.getPrimaryKey());

		Assert.assertEquals(existingEntityMigration, newEntityMigration);
	}

	@Test(expected = NoSuchEntityMigrationException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<EntityMigration> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("MIG_EntityMigration",
			"entityMigrationId", true, "migrationId", true, "entityName", true,
			"start", true, "end", true, "count", true, "lastCompletion", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		EntityMigration newEntityMigration = addEntityMigration();

		EntityMigration existingEntityMigration = _persistence.fetchByPrimaryKey(newEntityMigration.getPrimaryKey());

		Assert.assertEquals(existingEntityMigration, newEntityMigration);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		EntityMigration missingEntityMigration = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingEntityMigration);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		EntityMigration newEntityMigration1 = addEntityMigration();
		EntityMigration newEntityMigration2 = addEntityMigration();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newEntityMigration1.getPrimaryKey());
		primaryKeys.add(newEntityMigration2.getPrimaryKey());

		Map<Serializable, EntityMigration> entityMigrations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, entityMigrations.size());
		Assert.assertEquals(newEntityMigration1,
			entityMigrations.get(newEntityMigration1.getPrimaryKey()));
		Assert.assertEquals(newEntityMigration2,
			entityMigrations.get(newEntityMigration2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, EntityMigration> entityMigrations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(entityMigrations.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		EntityMigration newEntityMigration = addEntityMigration();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newEntityMigration.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, EntityMigration> entityMigrations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, entityMigrations.size());
		Assert.assertEquals(newEntityMigration,
			entityMigrations.get(newEntityMigration.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, EntityMigration> entityMigrations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(entityMigrations.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		EntityMigration newEntityMigration = addEntityMigration();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newEntityMigration.getPrimaryKey());

		Map<Serializable, EntityMigration> entityMigrations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, entityMigrations.size());
		Assert.assertEquals(newEntityMigration,
			entityMigrations.get(newEntityMigration.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = EntityMigrationLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<EntityMigration>() {
				@Override
				public void performAction(EntityMigration entityMigration) {
					Assert.assertNotNull(entityMigration);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		EntityMigration newEntityMigration = addEntityMigration();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(EntityMigration.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("entityMigrationId",
				newEntityMigration.getEntityMigrationId()));

		List<EntityMigration> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		EntityMigration existingEntityMigration = result.get(0);

		Assert.assertEquals(existingEntityMigration, newEntityMigration);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(EntityMigration.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("entityMigrationId",
				RandomTestUtil.nextLong()));

		List<EntityMigration> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		EntityMigration newEntityMigration = addEntityMigration();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(EntityMigration.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"entityMigrationId"));

		Object newEntityMigrationId = newEntityMigration.getEntityMigrationId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("entityMigrationId",
				new Object[] { newEntityMigrationId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingEntityMigrationId = result.get(0);

		Assert.assertEquals(existingEntityMigrationId, newEntityMigrationId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(EntityMigration.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"entityMigrationId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("entityMigrationId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		EntityMigration newEntityMigration = addEntityMigration();

		_persistence.clearCache();

		EntityMigration existingEntityMigration = _persistence.findByPrimaryKey(newEntityMigration.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingEntityMigration.getMigrationId()),
			ReflectionTestUtil.<Long>invoke(existingEntityMigration,
				"getOriginalMigrationId", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(
				existingEntityMigration.getEntityName(),
				ReflectionTestUtil.invoke(existingEntityMigration,
					"getOriginalEntityName", new Class<?>[0])));
	}

	protected EntityMigration addEntityMigration() throws Exception {
		long pk = RandomTestUtil.nextLong();

		EntityMigration entityMigration = _persistence.create(pk);

		entityMigration.setMigrationId(RandomTestUtil.nextLong());

		entityMigration.setEntityName(RandomTestUtil.randomString());

		entityMigration.setStart(RandomTestUtil.nextDate());

		entityMigration.setEnd(RandomTestUtil.nextDate());

		entityMigration.setCount(RandomTestUtil.nextLong());

		entityMigration.setLastCompletion(RandomTestUtil.nextDate());

		_entityMigrations.add(_persistence.update(entityMigration));

		return entityMigration;
	}

	private List<EntityMigration> _entityMigrations = new ArrayList<EntityMigration>();
	private EntityMigrationPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}