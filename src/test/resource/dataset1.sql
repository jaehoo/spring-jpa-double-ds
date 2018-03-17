CREATE SCHEMA IF NOT EXISTS BASEA;
SET SCHEMA BASEA;

create sequence IF NOT EXISTS SQ_CUSTOMER START WITH 4;

create table IF NOT EXISTS CUSTOMER (
      id_customer number(10,0) not null,
      name varchar2(255 char),
      primary key (id_customer)
  );;


INSERT INTO CUSTOMER(id_customer,name) VALUES(1,'Jaehoo');
INSERT INTO CUSTOMER(id_customer,name) VALUES(2,'AB');
INSERT INTO CUSTOMER(id_customer,name) VALUES(3,'BC');


