use inventory_management;

drop procedure if EXISTS productInfo_create;
DELIMITER $$
CREATE PROCEDURE productInfo_create(
    in _code VARCHAR (100),
    in _name VARCHAR (255),
    in _description VARCHAR (255),
    in _img_url varchar (255),
    in _active_flag INTEGER,
    in _created_date TIMESTAMP,
    in _updated_date TIMESTAMP,
    in _cate_id int
        ) body:
BEGIN
	declare
newid int;
	SET
max_sp_recursion_depth=255;
	if
(
select count(product_info.id)
from product_info
where product_info.name
  and product_info.code) > 0 then
SET @message_text = CONCAT('product_info \'', name, '\' already exists');
SIGNAL
SQLSTATE '45000' SET MESSAGE_TEXT = @message_text;
else
		insert into product_info( code,name, description, img_url, active_flag, created_date, updated_date, cate_id)
        values (_code,_name, _description, _img_url, _active_flag, _created_date, _updated_date, _cate_id);
        set
newId = last_insert_id();
end if;
select newId;
END$$
DELIMITER ;

drop procedure if EXISTS productInfo_update;
DELIMITER $$
CREATE PROCEDURE productInfo_update(
    in _code VARCHAR (100),
    in _name VARCHAR (255),
    in _description Text,
    in _img_url varchar (255),
    in _cate_id int,
    in _active_flag INTEGER,
    in _updated_date TIMESTAMP
        ) body:
begin
update product_info
set code = _code, name = _name, description= _description, img_url = _img_url, cate_id = _cate_id, active_flag = _active_flag, updated_date = _updated_date;
END$$
DELIMITER ;

drop procedure if EXISTS productInfo_findProductInfoAll;
DELIMITER $$
CREATE PROCEDURE productInfo_findProductInfoAll()
begin
select *
from product_info;
end$$
DELIMITER ;


drop procedure if EXISTS productInfo_findProductInfoById;
DELIMITER $$
CREATE PROCEDURE productInfo_findProductInfoById(in _id int)
begin
select p.*
from product_info p inner join category c on p.cate_id = c.id
where p.active_flag = 1
   or p.active_flag = 0 and p.id = _id;
end$$
DELIMITER ;

drop procedure if EXISTS productInfo_findProductInfoByCode;
DELIMITER $$
CREATE PROCEDURE productInfo_findProductInfoByCode(in _code varchar (100))
begin
select *
from product_info
where active_flag = 1
   or active_flag = 0 and code = _code;
end$$
DELIMITER ;