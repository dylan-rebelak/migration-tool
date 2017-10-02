create table SYNC_EntityManager (
	entityName VARCHAR(75) not null primary key,
	lastSyncDate DATE null
);

create table SYNC_Migration (
	migrationId LONG not null primary key,
	timeStarted DATE null,
	timeCompleted DATE null,
	fromDate DATE null,
	recordsSynced LONG
);