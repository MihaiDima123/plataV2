alter table guild drop column visibility;

drop type if exists enum_guild_visibility;

alter table guild add column visibility varchar(10) default 'PRIVATE';

create index IDX_guild_visibility on guild(visibility);
