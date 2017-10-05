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

	public List<Object> getEntitiesModifiedSinceDate(
		Date from, Date to, int batchStart, int batchEnd);

	public default String getEntityName() {
		Class<? extends EntityService> localServiceClass = this.getClass();

		return StringUtil.removeSubstring(
			localServiceClass.getSimpleName(), LOCAL_SERVICE_IMPL_SUFFIX);
	}

	public void syncEntity(Object entity);

}