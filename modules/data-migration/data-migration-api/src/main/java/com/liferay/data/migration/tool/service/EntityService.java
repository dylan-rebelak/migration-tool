package com.liferay.data.migration.tool.service;

import java.util.Date;
import java.util.List;

/**
 * @author Dylan Rebelak
 */
public interface EntityService {

	public List<Object> getEntitiesModifiedSinceDate(
		Date from, Date to, int batchStart, int batchEnd);

	public void syncEntity(Object entity);

}