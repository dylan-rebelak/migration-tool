create table SYNC_MigrationManager (
	managerId LONG not null primary key,
	timeStarted DATE null,
	timeCompleted DATE null,
	fromDate DATE null,
	recordsSynced LONG
);