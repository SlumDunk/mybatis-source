--init user table
insert into user(user_name,svc_num,user_pwd,cust_id,type) values('zhangsan','1234567890','123456',1,1);

--init customer table
insert into customer(cust_name,cert_no) values('sanzhang','isso-9000112');

--init account table
insert into account(pay_name,user_id,bank_no) values('xiaoming',1,'6533299102020345');
insert into account(pay_name,user_id,bank_no) values('xiaodong',1,'6533299102020345');
insert into account(pay_name,user_id,bank_no) values('xiaoxiaotu',1,'6533299102020345');