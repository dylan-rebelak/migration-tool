<%@ include file="/init.jsp" %>

<liferay-ui:search-container emptyResultsMessage="no-migration-found" searchContainer="${migrationSearchContainer}">
	<liferay-ui:search-container-row
		className="com.liferay.data.migration.tool.model.EntityMigration"
		escapedModel="true"
		keyProperty="entityMigrationId"
		modelVar="entityMigration"
	>
		<liferay-ui:search-container-column-text
			name="entity-name"
			value="${entityMigration.entityName}"
		/>

		<liferay-ui:search-container-column-text
			name="start"
			value="${entityMigration.start}"
		/>

		<liferay-ui:search-container-column-text
			name="end"
			value="${entityMigration.end}"
		/>

		<liferay-ui:search-container-column-text
			name="count"
			value="${entityMigration.count}"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator markupView="lexicon" />
</liferay-ui:search-container>

<aui:button-row>
	<aui:button cssClass="btn-lg" href="${backURL}" value="back" />
</aui:button-row>