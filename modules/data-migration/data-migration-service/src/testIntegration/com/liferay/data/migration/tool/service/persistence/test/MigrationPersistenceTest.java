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

import com.liferay.data.migration.tool.exception.NoSuchMigrationException;
import com.liferay.data.migration.tool.model.Migration;
import com.liferay.data.migration.tool.service.MigrationLocalServiceUtil;
import com.liferay.data.migration.tool.service.persistence.MigrationPersistence;
import com.liferay.data.migration.tool.service.persistence.MigrationUtil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
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
public class MigrationPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.data.migration.tool.service"));

	@Before
	public void setUp() {
		_persistence = MigrationUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<Migration> iterator = _migrations.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Migration migration = _persistence.create(pk);

		Assert.assertNotNull(migration);

		Assert.assertEquals(migration.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		Migration newMigration = addMigration();

		_persistence.remove(newMigration);

		Migration existingMigration = _persistence.fetchByPrimaryKey(newMigration.getPrimaryKey());

		Assert.assertNull(existingMigration);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMigration();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Migration newMigration = _persistence.create(pk);

		newMigration.setStart(RandomTestUtil.nextDate());

		newMigration.setEnd(RandomTestUtil.nextDate());

		_migrations.add(_persistence.update(newMigration));

		Migration existingMigration = _persistence.findByPrimaryKey(newMigration.getPrimaryKey());

		Assert.assertEquals(existingMigration.getMigrationId(),
			newMigration.getMigrationId());
		Assert.assertEquals(Time.getShortTimestamp(existingMigration.getStart()),
			Time.getShortTimestamp(newMigration.getStart()));
		Assert.assertEquals(Time.getShortTimestamp(existingMigration.getEnd()),
			Time.getShortTimestamp(newMigration.getEnd()));
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		Migration newMigration = addMigration();

		Migration existingMigration = _persistence.findByPrimaryKey(newMigration.getPrimaryKey());

		Assert.assertEquals(existingMigration, newMigration);
	}

	@Test(expected = NoSuchMigrationException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<Migration> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("MIG_Migration",
			"migrationId", true, "start", true, "end", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		Migration newMigration = addMigration();

		Migration existingMigration = _persistence.fetchByPrimaryKey(newMigration.getPrimaryKey());

		Assert.assertEquals(existingMigration, newMigration);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Migration missingMigration = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMigration);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		Migration newMigration1 = addMigration();
		Migration newMigration2 = addMigration();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMigration1.getPrimaryKey());
		primaryKeys.add(newMigration2.getPrimaryKey());

		Map<Serializable, Migration> migrations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, migrations.size());
		Assert.assertEquals(newMigration1,
			migrations.get(newMigration1.getPrimaryKey()));
		Assert.assertEquals(newMigration2,
			migrations.get(newMigration2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, Migration> migrations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(migrations.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		Migration newMigration = addMigration();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMigration.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, Migration> migrations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, migrations.size());
		Assert.assertEquals(newMigration,
			migrations.get(newMigration.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, Migration> migrations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(migrations.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		Migration newMigration = addMigration();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMigration.getPrimaryKey());

		Map<Serializable, Migration> migrations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, migrations.size());
		Assert.assertEquals(newMigration,
			migrations.get(newMigration.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = MigrationLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<Migration>() {
				@Override
				public void performAction(Migration migration) {
					Assert.assertNotNull(migration);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		Migration newMigration = addMigration();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Migration.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("migrationId",
				newMigration.getMigrationId()));

		List<Migration> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Migration existingMigration = result.get(0);

		Assert.assertEquals(existingMigration, newMigration);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Migration.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("migrationId",
				RandomTestUtil.nextLong()));

		List<Migration> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		Migration newMigration = addMigration();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Migration.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("migrationId"));

		Object newMigrationId = newMigration.getMigrationId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("migrationId",
				new Object[] { newMigrationId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingMigrationId = result.get(0);

		Assert.assertEquals(existingMigrationId, newMigrationId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(Migration.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("migrationId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("migrationId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected Migration addMigration() throws Exception {
		long pk = RandomTestUtil.nextLong();

		Migration migration = _persistence.create(pk);

		migration.setStart(RandomTestUtil.nextDate());

		migration.setEnd(RandomTestUtil.nextDate());

		_migrations.add(_persistence.update(migration));

		return migration;
	}

	private List<Migration> _migrations = new ArrayList<Migration>();
	private MigrationPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}