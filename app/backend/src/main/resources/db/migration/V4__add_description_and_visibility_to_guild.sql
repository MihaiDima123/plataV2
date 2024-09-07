alter table guild add column description text;

create type enum_guild_visibility as enum('PUBLIC', 'PRIVATE', 'SECRET');

alter table guild add column visibility enum_guild_visibility default 'PRIVATE';

create index IDX_guild_visibility on guild(visibility);
