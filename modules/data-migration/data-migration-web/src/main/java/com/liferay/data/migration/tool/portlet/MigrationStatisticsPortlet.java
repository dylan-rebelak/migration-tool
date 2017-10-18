package com.liferay.data.migration.tool.portlet;

import com.liferay.data.migration.tool.constants.MigrationPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author drebelak
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=migration",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Migration Statistics Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=",
		"javax.portlet.resource-bundle=content.Language" + MigrationPortletKeys.MIGRATION_STATISTICS
	},
	service = Portlet.class
)
public class MigrationStatisticsPortlet extends MVCPortlet {
}