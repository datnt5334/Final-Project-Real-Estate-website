use samiestate;

insert into role(code,name) values('ROLE_MANAGER','Quản lý');
insert into role(code,name) values('ROLE_STAFF','Nhân viên');

insert into users(username,password,employeecode,status,email,phone,gender,fullname)
values('datnt5334','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','QL1001',1,'boyvip01214@gmail.com',
'0339166396',1,'Nguyễn Thành Đạt');

INSERT INTO user_role(user_id,role_id) VALUES (1,1);

insert into district(name,code) values('Quận 1','QUAN_1');
insert into district(name,code) values('Quận 3','QUAN_3');
insert into district(name,code) values('Quận 4','QUAN_4');
insert into district(name,code) values('Quận Bình Tân','QUAN_BINH_TAN');
insert into district(name,code) values('Quận Bình Thạnh','QUAN_BINH_THANH');
insert into district(name,code) values('Quận Gò Vấp','QUAN_GO_VAP');

insert into customer_status(name, code) values('Chưa xử lý','CHUA_XU_LY');
insert into customer_status(name, code) values('Đang xử lý','DANG_XU_LY');
insert into customer_status(name, code) values('Đang cho thuê','DANG_CHO_THUE');
insert into customer_status(name, code) values('Khách hàng cũ','KHC');

insert into transaction_type(name, code) values('Quá trình CSKH','QT_CSKH');
insert into transaction_type(name, code) values('Dẫn đi xem','DAN_DI_XEM');
insert into transaction_type(name, code) values('Kí kết hợp đồng','KI_HOP_DONG');







