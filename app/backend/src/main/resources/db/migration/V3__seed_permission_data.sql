
insert into permission_group(id,name) values (1,'user');
insert into permission_group(id,name) values (2,'premium-user');
insert into permission_group(id,name) values (3,'admin');

insert into permissions(id, name) values (1,'guild:read-guild');
insert into permissions(id, name) values (2,'guild:join-guild');
insert into permissions(id, name) values (3,'guild:create-guild');

insert into permission_group_permissions(permission_group_id, permission_id) values (1,1);
insert into permission_group_permissions(permission_group_id, permission_id) values (1,2);

insert into permission_group_permissions(permission_group_id, permission_id) values (2,1);
insert into permission_group_permissions(permission_group_id, permission_id) values (2,2);
insert into permission_group_permissions(permission_group_id, permission_id) values (2,3);

insert into permission_group_permissions(permission_group_id, permission_id) values (3,1);
insert into permission_group_permissions(permission_group_id, permission_id) values (3,2);
insert into permission_group_permissions(permission_group_id, permission_id) values (3,3);
