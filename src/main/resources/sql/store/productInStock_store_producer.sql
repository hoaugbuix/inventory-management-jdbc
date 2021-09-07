use inventory_management;

drop procedure if EXISTS productInStock_create;
DELIMITER $$
CREATE PROCEDURE productInStock_create(
    in _productId int,
    in _qty int,
    in _price DECIMAL (15, 2),
    in _active_flag INTEGER,
    in _created_date TIMESTAMP,
    in _updated_date TIMESTAMP
        ) body:
BEGIN
	declare
newid int;
	SET
max_sp_recursion_depth=255;
	if
(
select count(p.id)
from product_in_stock as p) > 0 then
SET @message_text = CONCAT('product_in_stock \'', name, '\' already exists');
SIGNAL
sqlstate '45000' SET MESSAGE_TEXT = @message_text;
else
		insert into product_in_stock(product_id, qty, price,  active_flag, created_date, updated_date)
        values (_productId, _qty, _price, _active_flag, _created_date, _updated_date);
        -- on DUPLICATE KEY UPDATE id = newId;
        set
newId = last_insert_id();
end if;
select newId;
END$$
DELIMITER ;

drop procedure if EXISTS productInStock_findAll;
DELIMITER $$
CREATE PROCEDURE productInStock_findAll()
begin
select *
from product_in_stock
where active_flag = 1;
end$$
DELIMITER ;