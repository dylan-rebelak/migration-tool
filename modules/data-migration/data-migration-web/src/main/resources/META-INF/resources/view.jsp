<%@ include file="/init.jsp" %>

<liferay-ui:search-container emptyResultsMessage="no-migration-found" searchContainer="${migrationSearchContainer}">
	<liferay-ui:search-container-row
		className="com.liferay.data.migration.tool.model.Migration"
		escapedModel="true"
		keyProperty="migrationId"
		modelVar="migration"
	>
		<portlet:renderURL var="entitiesURL">
			<portlet:param name="mvcRenderCommandName" value="/migration/view_entities" />
			<portlet:param name="migrationId" value="${migration.migrationId}" />
			<portlet:param name="backURL" value="${backURL}" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text
			href="${entitiesURL}"
			name="migration-id"
			value="${migration.migrationId}"
		/>

		<liferay-ui:search-container-column-text
			name="start"
			value="${migration.start}"
		/>

		<liferay-ui:search-container-column-text
			name="end"
			value="${migration.end}"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator markupView="lexicon" />
</liferay-ui:search-container>