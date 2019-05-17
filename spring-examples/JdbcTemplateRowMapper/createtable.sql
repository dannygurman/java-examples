create schema examples ;

use examples;

drop table `employee`;

create table employee(
id int(10),
name varchar(100),
salary int(10)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO employee (id, name, salary) VALUES (1, 'dan', 123);