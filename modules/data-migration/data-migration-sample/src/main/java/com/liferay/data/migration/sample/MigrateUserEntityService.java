package com.liferay.data.migration.sample;

import com.liferay.data.migration.tool.service.MigrationEntity;
import com.liferay.data.migration.tool.service.MigrationEntityService;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Dylan Rebelak
 */
@Component(immediate = true, service = MigrationEntityService.class)
public class MigrateUserEntityService implements MigrationEntityService {

	@Override
	public DynamicQuery dynamicQuery() {

		return _userLocalService.dynamicQuery();
	}

	@Override
	public long dynamicQueryCount(DynamicQuery query) {

		return _userLocalService.dynamicQueryCount(query);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<MigrationEntity> getEntitiesModifiedSinceDate(
		Date sinceDate, int start, int end)
	{

		if (sinceDate.getTime() == 0) {
			return _asMigrationEntities(_userLocalService.getUsers(start, end));
		}

		DynamicQuery dynamicQuery = dynamicQuery();

		Criterion criterion = RestrictionsFactoryUtil.between(
			"modifiedDate", sinceDate, new Date());

		dynamicQuery.add(criterion);

		List<User> users = _userLocalService.dynamicQuery(
			dynamicQuery, start, end);

		if (_log.isDebugEnabled()) {
			_log.debug("Found " + users.size() + " updated users.");
		}

		return _asMigrationEntities(users);
	}

	private List<MigrationEntity> _asMigrationEntities(List<User> users) {
		return users.stream().filter(
			Objects::nonNull).map(
				this::_asMigrationEntity).collect(Collectors.toList());
	}

	private MigrationEntity _asMigrationEntity(User user) {
		return (MigrationEntity)user;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MigrateUserEntityService.class);

	@Override
	public void syncEntity(MigrationEntity entity) {
		User user = (User)entity;

		_log.info("Migrated user: " + user.getFullName());
	}

	@Reference
	private UserLocalService _userLocalService;
}