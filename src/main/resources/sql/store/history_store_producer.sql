use inventory_management;

drop procedure if EXISTS history_create;
DELIMITER $$
CREATE PROCEDURE history_create(
    in _action_name VARCHAR(255),
    in _type int,
    in _product_id int,
    in _qty int,
    in _price decimal(50, 2),
    in _active_flag INTEGER,
    in _created_date TIMESTAMP,
    in _updated_date TIMESTAMP
)
body:
BEGIN
    declare
        newId int;
    SET
        max_sp_recursion_depth = 255;
    if
            (
                select count(h.id)
                from history h
                where h.action_name = _action_name) > 0 then
        SET @message_text = CONCAT('product_info \'', name, '\' already exists');
        SIGNAL
            SQLSTATE '45000' SET MESSAGE_TEXT = @message_text;
    else
        insert into history(action_name, type, product_id, qty, price, active_flag, created_date, updated_date)
        values (_action_name, _type, _product_id, _qty, _price, _active_flag, _created_date, _updated_date);
        set
            newId = last_insert_id();
    end if;
    select newId;
END$$
DELIMITER ;

drop procedure if EXISTS history_findAll;
DELIMITER $$
CREATE PROCEDURE history_findAll()
begin
    select h.*, p.*
    from history h
             inner join product_info p on h.product_id = p.id
    where h.active_flag = 1
       or h.active_flag = 0;
end$$
DELIMITER ;