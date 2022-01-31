use samiestate;

insert into role(code,name) values('ROLE_MANAGER','Quản lý');
insert into role(code,name) values('ROLE_STAFF','Nhân viên');

insert into users(username,password,fullname,status)
values('datnt5334','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','Nguyễn Thành Đạt',1);
insert into users(username,password,fullname,status)
values('username1','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','Đỗ Văn Trung',1);

INSERT INTO user_role(user_id,role_id) VALUES (1,1);
INSERT INTO user_role(user_id,role_id) VALUES (2,2);







