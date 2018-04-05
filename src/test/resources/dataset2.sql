 CREATE SCHEMA IF NOT EXISTS BASEB;
 SET SCHEMA BASEB;

create sequence IF NOT EXISTS SQ_PROD START WITH 1;

create table IF NOT EXISTS PRODUCT (
      id_product number(10,0) not null,
      name varchar2(255 char),
      price double precision,
      primary key (id_product)
  ) ;;

INSERT INTO PRODUCT(id_product,name,price) VALUES(1,'something', 10.0);

