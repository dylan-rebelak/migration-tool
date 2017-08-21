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

import com.liferay.data.migration.tool.exception.NoSuchMigrationManagerException;
import com.liferay.data.migration.tool.model.MigrationManager;
import com.liferay.data.migration.tool.service.MigrationManagerLocalServiceUtil;
import com.liferay.data.migration.tool.service.persistence.MigrationManagerPersistence;
import com.liferay.data.migration.tool.service.persistence.MigrationManagerUtil;

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
public class MigrationManagerPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.data.migration.tool.service"));

	@Before
	public void setUp() {
		_persistence = MigrationManagerUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<MigrationManager> iterator = _migrationManagers.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		MigrationManager migrationManager = _persistence.create(pk);

		Assert.assertNotNull(migrationManager);

		Assert.assertEquals(migrationManager.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		MigrationManager newMigrationManager = addMigrationManager();

		_persistence.remove(newMigrationManager);

		MigrationManager existingMigrationManager = _persistence.fetchByPrimaryKey(newMigrationManager.getPrimaryKey());

		Assert.assertNull(existingMigrationManager);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMigrationManager();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		MigrationManager newMigrationManager = _persistence.create(pk);

		newMigrationManager.setTimeStarted(RandomTestUtil.nextDate());

		newMigrationManager.setTimeCompleted(RandomTestUtil.nextDate());

		newMigrationManager.setFromDate(RandomTestUtil.nextDate());

		newMigrationManager.setRecordsSynced(RandomTestUtil.nextLong());

		_migrationManagers.add(_persistence.update(newMigrationManager));

		MigrationManager existingMigrationManager = _persistence.findByPrimaryKey(newMigrationManager.getPrimaryKey());

		Assert.assertEquals(existingMigrationManager.getManagerId(),
			newMigrationManager.getManagerId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingMigrationManager.getTimeStarted()),
			Time.getShortTimestamp(newMigrationManager.getTimeStarted()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingMigrationManager.getTimeCompleted()),
			Time.getShortTimestamp(newMigrationManager.getTimeCompleted()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingMigrationManager.getFromDate()),
			Time.getShortTimestamp(newMigrationManager.getFromDate()));
		Assert.assertEquals(existingMigrationManager.getRecordsSynced(),
			newMigrationManager.getRecordsSynced());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		MigrationManager newMigrationManager = addMigrationManager();

		MigrationManager existingMigrationManager = _persistence.findByPrimaryKey(newMigrationManager.getPrimaryKey());

		Assert.assertEquals(existingMigrationManager, newMigrationManager);
	}

	@Test(expected = NoSuchMigrationManagerException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<MigrationManager> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("SYNC_MigrationManager",
			"managerId", true, "timeStarted", true, "timeCompleted", true,
			"fromDate", true, "recordsSynced", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		MigrationManager newMigrationManager = addMigrationManager();

		MigrationManager existingMigrationManager = _persistence.fetchByPrimaryKey(newMigrationManager.getPrimaryKey());

		Assert.assertEquals(existingMigrationManager, newMigrationManager);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		MigrationManager missingMigrationManager = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMigrationManager);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		MigrationManager newMigrationManager1 = addMigrationManager();
		MigrationManager newMigrationManager2 = addMigrationManager();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMigrationManager1.getPrimaryKey());
		primaryKeys.add(newMigrationManager2.getPrimaryKey());

		Map<Serializable, MigrationManager> migrationManagers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, migrationManagers.size());
		Assert.assertEquals(newMigrationManager1,
			migrationManagers.get(newMigrationManager1.getPrimaryKey()));
		Assert.assertEquals(newMigrationManager2,
			migrationManagers.get(newMigrationManager2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, MigrationManager> migrationManagers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(migrationManagers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		MigrationManager newMigrationManager = addMigrationManager();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMigrationManager.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, MigrationManager> migrationManagers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, migrationManagers.size());
		Assert.assertEquals(newMigrationManager,
			migrationManagers.get(newMigrationManager.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, MigrationManager> migrationManagers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(migrationManagers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		MigrationManager newMigrationManager = addMigrationManager();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newMigrationManager.getPrimaryKey());

		Map<Serializable, MigrationManager> migrationManagers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, migrationManagers.size());
		Assert.assertEquals(newMigrationManager,
			migrationManagers.get(newMigrationManager.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = MigrationManagerLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<MigrationManager>() {
				@Override
				public void performAction(MigrationManager migrationManager) {
					Assert.assertNotNull(migrationManager);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		MigrationManager newMigrationManager = addMigrationManager();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MigrationManager.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("managerId",
				newMigrationManager.getManagerId()));

		List<MigrationManager> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		MigrationManager existingMigrationManager = result.get(0);

		Assert.assertEquals(existingMigrationManager, newMigrationManager);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MigrationManager.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("managerId",
				RandomTestUtil.nextLong()));

		List<MigrationManager> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		MigrationManager newMigrationManager = addMigrationManager();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MigrationManager.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("managerId"));

		Object newManagerId = newMigrationManager.getManagerId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("managerId",
				new Object[] { newManagerId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingManagerId = result.get(0);

		Assert.assertEquals(existingManagerId, newManagerId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MigrationManager.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("managerId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("managerId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected MigrationManager addMigrationManager() throws Exception {
		long pk = RandomTestUtil.nextLong();

		MigrationManager migrationManager = _persistence.create(pk);

		migrationManager.setTimeStarted(RandomTestUtil.nextDate());

		migrationManager.setTimeCompleted(RandomTestUtil.nextDate());

		migrationManager.setFromDate(RandomTestUtil.nextDate());

		migrationManager.setRecordsSynced(RandomTestUtil.nextLong());

		_migrationManagers.add(_persistence.update(migrationManager));

		return migrationManager;
	}

	private List<MigrationManager> _migrationManagers = new ArrayList<MigrationManager>();
	private MigrationManagerPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}