package com.liferay.data.migration.tool.portlet.command.render;

import com.liferay.data.migration.tool.constants.MigrationPortletKeys;
import com.liferay.data.migration.tool.model.Migration;
import com.liferay.data.migration.tool.service.MigrationLocalService;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
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
		"mvc.command.name=/", "mvc.command.name=/view"
	},
	service = MVCRenderCommand.class
)
public class ViewMigrationStatisticsMVCRenderCommand
	implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long groupId = themeDisplay.getScopeGroupId();

		PortletURL iteratorURL = renderResponse.createRenderURL();

		iteratorURL.setParameter("groupId", String.valueOf(groupId));

		SearchContainer<Migration> migrationSearchContainer =
			new SearchContainer<>(renderRequest, iteratorURL, null, null);

		List<Migration> migrationList = _migrationLocalService.getMigrations(
			migrationSearchContainer.getStart(),
			migrationSearchContainer.getEnd());

		int totalMigrations = _migrationLocalService.getMigrationsCount();

		migrationSearchContainer.setResults(migrationList);
		migrationSearchContainer.setTotal(totalMigrations);

		renderRequest.setAttribute("backURL", themeDisplay.getURLCurrent());
		renderRequest.setAttribute(
			"migrationSearchContainer", migrationSearchContainer);

		return "/view.jsp";
	}

	@Reference
	private MigrationLocalService _migrationLocalService;

}