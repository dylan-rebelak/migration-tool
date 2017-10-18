package com.liferay.data.migration.tool.portlet.panel;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.data.migration.tool.constants.MigrationPortletKeys;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Dylan Rebelak
 */
@Component(
	immediate = true,
	property = {
		"panel.category.key=" + MigrationPortletKeys.CONTROL_PANEL_CATEGORY,
		"panel.category.order:Integer=1"
	},
	service = PanelApp.class
)
public class MigrationPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return MigrationPortletKeys.MIGRATION_STATISTICS;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + MigrationPortletKeys.MIGRATION_STATISTICS + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}