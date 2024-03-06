DROP table IF EXISTS employee;

create table employee
(
    id long PRIMARY KEY auto_increment,
    empName         	varchar(255),
    empDeptName         varchar(255),
    empTelNo         varchar(20),
    empMail         varchar(25)
);