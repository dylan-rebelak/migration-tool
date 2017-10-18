package com.liferay.data.migration.tool.portlet.panel;

import com.liferay.application.list.BasePanelCategory;
import com.liferay.application.list.PanelCategory;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.data.migration.tool.constants.MigrationPortletKeys;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Dylan Rebelak
 */
@Component(
	immediate = true,
	property = {
		"panel.category.key=" + PanelCategoryKeys.CONTROL_PANEL,
		"panel.category.order:Integer=95"
	},
	service = PanelCategory.class
)
public class MigrationPanelCategory extends BasePanelCategory {

	@Override
	public String getKey() {
		return MigrationPortletKeys.CONTROL_PANEL_CATEGORY;
	}

	@Override
	public String getLabel(Locale locale) {
		return "Migration";
	}

}