package com.liferay.data.migration.tool.portlet.command.render;

import com.liferay.data.migration.tool.constants.MigrationPortletKeys;
import com.liferay.data.migration.tool.model.EntityMigration;
import com.liferay.data.migration.tool.service.EntityMigrationLocalService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Dylan Rebelak
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + MigrationPortletKeys.MIGRATION_STATISTICS,
		"mvc.command.name=/migration/view_entities"
	},
	service = MVCRenderCommand.class
)
public class ViewMigrationEntityStatisticsMVCRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long groupId = themeDisplay.getScopeGroupId();

		long migrationId = ParamUtil.getLong(renderRequest, "migrationId");
		String backURL = ParamUtil.getString(renderRequest, "backURL");

		PortletURL iteratorURL = renderResponse.createRenderURL();

		iteratorURL.setParameter("groupId", String.valueOf(groupId));

		SearchContainer<EntityMigration> entityMigrationSearchContainer =
			new SearchContainer<>(renderRequest, iteratorURL, null, null);

		List<EntityMigration> entityMigrationList =
			_entityMigrationLocalService.findByMigrationId(
				migrationId, entityMigrationSearchContainer.getStart(),
				entityMigrationSearchContainer.getEnd());

		int totalEntityMigrations =
			_entityMigrationLocalService.countByMigrationId(migrationId);

		entityMigrationSearchContainer.setResults(entityMigrationList);
		entityMigrationSearchContainer.setTotal(totalEntityMigrations);

		renderRequest.setAttribute("backURL", backURL);
		renderRequest.setAttribute(
			"migrationSearchContainer", entityMigrationSearchContainer);

		return "/view_entities.jsp";
	}

	@Reference
	private EntityMigrationLocalService _entityMigrationLocalService;

}