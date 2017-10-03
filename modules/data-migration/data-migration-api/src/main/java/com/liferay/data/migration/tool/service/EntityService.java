package com.liferay.data.migration.tool.service;

import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Dylan Rebelak
 */
public interface EntityService {

	public static final String LOCAL_SERVICE_IMPL_SUFFIX = "LocalServiceImpl";

	public default long countEntities(Date startDate, Date now) {
		DynamicQuery query = this.dynamicQuery();

		if (startDate.getTime() != 0) {
			Criterion criterion = RestrictionsFactoryUtil.between(
				"modifiedDate", startDate, now);

			query.add(criterion);
		}

		return this.dynamicQueryCount(query);
	}

	public abstract DynamicQuery dynamicQuery();

	public abstract long dynamicQueryCount(DynamicQuery query);

	public List<Object> getEntitiesModifiedSinceDate(
		Date sinceDate, Date now, int start, int end);

	public default String getEntityName() {
		Class<? extends EntityService> localServiceClass = this.getClass();

		return StringUtil.removeSubstring(
			localServiceClass.getSimpleName(), LOCAL_SERVICE_IMPL_SUFFIX);
	}

	public void syncEntity(Object entity);

}