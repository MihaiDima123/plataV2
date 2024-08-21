create table guild(
	id serial primary key,
	name varchar(512) not null,
	owner_id int not null,
	constraint FK_guild_owner foreign key (owner_id) references "user"(id)
);

CREATE INDEX IDX_guild_name ON guild(name);

create table guild_members(
	guild_id int not null,
	user_id int not null
);

create table permission_group(
	id serial PRIMARY key,
	name varchar(32) NOT NULL UNIQUE
);

CREATE TABLE permissions(
	id serial PRIMARY KEY,
	name varchar(32) UNIQUE
);

CREATE TABLE permission_group_permissions(
	permission_group_id int not null REFERENCES permission_group(id) ON DELETE CASCADE,
	permission_id int not null REFERENCES permission_group(id) ON DELETE CASCADE
);

create table user_group(
	user_id int not null references "user"(id) on delete cascade,
	permission_group_id int not null references permission_group(id) on delete cascade
);