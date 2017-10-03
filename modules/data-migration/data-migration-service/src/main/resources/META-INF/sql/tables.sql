create table MIG_EntityMigration (
	entityMigrationId LONG not null primary key,
	migrationId LONG,
	entityName VARCHAR(75) null,
	start_ DATE null,
	end_ DATE null,
	count LONG,
	lastCompletion DATE null
);

create table MIG_Migration (
	migrationId LONG not null primary key,
	start_ DATE null,
	end_ DATE null
);