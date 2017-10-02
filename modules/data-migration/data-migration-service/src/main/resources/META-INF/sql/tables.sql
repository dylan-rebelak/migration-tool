create table SYNC_EntityManager (
	entityName VARCHAR(75) not null primary key,
	lastSyncDate DATE null
);

create table SYNC_Migration (
	migrationId LONG not null primary key,
	fromDate DATE null,
	timeStarted DATE null,
	timeCompleted DATE null,
	recordsSynced LONG
);