SELECT * FROM juice.stock;
create database Juice;
use Juice;
create table stock(id int,juice_name varchar(50),qty int,price double precision);
select * from stock;
insert into stock(id,juice_name,qty,price)values(1,'Fresh Juice',1,40.0);

create database Juice;
use Juice;
create table stock(id int,juice_name varchar(50),qty int,price double precision);
select * from stock;
insert into stock(id,juice_name,qty,price)values(1,'Fresh Juice',1,40.0);
create table salseman(id int,salseman_name varchar(50));
insert into salseman(id,salseman_name)values(2,'Vimal');
select * from salseman;
create table tempbill(salseman_name varchar(50),juice_name varchar(50),qty int,price double precision,netvalue double precision,date date,id int);
drop table tempbill;
insert into tempbill(salseman_name,juice_name,qty,price,netvalue,date,id) values('raja','orange juice',1,40.0,40.0,'2025-3-01',1);
insert into tempbill(salseman_name,juice_name,qty,price,netvalue,date,id) values(?,?,?,?,?,?,?);
select * from tempbill; 
select price from stock;
select sum(netvalue) from tempbill; 
truncate tempbill;

create table bill_orginal(billno int,customer_name varchar(255),mobileno bigint);

insert into bill_orginal(billno,customer_name,mobileno) values(2,'dinesh',98968476);


select * from bill_orginal;
select * from bill_orginal where billno=5;
select billno from bill_orginal;
truncate bill_orginal;
alter table bill_orginal add column salseman_name varchar(50);
alter table bill_orginal drop column id;
alter table bill_orginal add column juice_name varchar(50) after salseman_name;
alter table bill_orginal add column id int after netvalue;
alter table bill_orginal add column price double precision;
alter table bill_orginal add column netvalue double precision;
alter table bill_orginal add column date date;
alter table bill_orginal add column id int;
alter table bill_orginal add column qty int after juice_name;

create table bill_orginal(billno int,customer_name varchar(255),mobileno bigint,salseman_name varchar(50),juice_name varchar(50),qty int,price double precision,netvalue double precision,date date,id int);


create table tempbill(salseman_name varchar(50),juice_name varchar(50),qty int,price double precision,netvalue double precision,date date,id int);

alter table tempbill add column billno int;

alter table tempbill add column customer_name varchar(255);
alter table tempbill add column mobileno bigint;
select * from tempbill;
alter table tempbill drop column billno;
select * from bill_orginal;
truncate bill_orginal;
create table orginal_datas(billno int,customer_name varchar(255),mobileno bigint,salseman_name varchar(50),juice_name varchar(50),qty int,price double precision,netvalue double precision,date date,id int);
select * from orginal_datas;
truncate tempbill;
truncate bill_orginal;
describe bill_orginal;

insert into tempbill(salseman_name,juice_name,qty,price,netvalue,date,id,customer_name,mobileno,billno) values('mapla','orange',1,80.0,80.0,'2025-01-22',1,'hari',987786769,1);

DELIMITER //

-- ✅ INSERT Trigger: Moves all data from tempbill to orginal_datas
CREATE TRIGGER after_insert_tempbill
AFTER INSERT ON tempbill
FOR EACH ROW
BEGIN
    INSERT INTO orginal_datas (billno, customer_name, mobileno, salseman_name, juice_name, qty, price, netvalue, date, id)
    VALUES (NEW.billno, NEW.customer_name, NEW.mobileno, NEW.salseman_name, NEW.juice_name, NEW.qty, NEW.price, NEW.netvalue, NEW.date, NEW.id);
END;
//

-- ✅ UPDATE Trigger: Updates orginal_datas when tempbill is updated
CREATE TRIGGER after_update_tempbill
AFTER UPDATE ON tempbill
FOR EACH ROW
BEGIN
    UPDATE orginal_datas
    SET billno = NEW.billno,
        customer_name = NEW.customer_name,
        mobileno = NEW.mobileno,
        salseman_name = NEW.salseman_name,
        juice_name = NEW.juice_name,
        qty = NEW.qty,
        price = NEW.price,
        netvalue = NEW.netvalue,
        date = NEW.date,
        id = NEW.id
    WHERE id = OLD.id;
END;
//

-- ✅ DELETE Trigger: Deletes corresponding row from orginal_datas when tempbill row is deleted
CREATE TRIGGER after_delete_tempbill
AFTER DELETE ON tempbill
FOR EACH ROW
BEGIN
    DELETE FROM orginal_datas WHERE id = OLD.id;
END;
//

DELIMITER ;
DROP TRIGGER IF EXISTS after_insert_tempbill;
DROP TRIGGER IF EXISTS after_update_tempbill;
DROP TRIGGER IF EXISTS after_delete_tempbill;


truncate tempbill;
select * from orginal_datas;
select * from orginal_datas where date = '2025-03-05';
truncate orginal_datas;
SELECT * FROM orginal_datas 
WHERE date BETWEEN '2025-03-01' AND '2025-03-05';

