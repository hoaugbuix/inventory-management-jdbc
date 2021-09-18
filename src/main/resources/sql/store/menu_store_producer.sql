use inventory_management;

drop procedure if EXISTS menu_create;
DELIMITER $$
CREATE PROCEDURE menu_create(
    in _parent_id INTEGER,
    in _url varchar(255),
    in _name VARCHAR(255),
    in _order_index INTEGER,
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
                select count(menu.id)
                from menu
                where menu.name) > 0 then
        SET @message_text = CONCAT('menu \'', name, '\' already exists');
        SIGNAL
            SQLSTATE '45000' SET MESSAGE_TEXT = @message_text;
    else
        insert into menu(parent_id, url, name, order_index, active_flag, created_date, updated_date, cate_id)
        values (_parent_id, _url, _name, _order_index, _active_flag, _created_date, _updated_date, _cate_id);
        set
            newId = last_insert_id();
    end if;
    SELECT newId;
END$$
DELIMITER ;

drop procedure if EXISTS menu_update;
DELIMITER $$
CREATE PROCEDURE menu_update(
    in _parent_id INTEGER,
    in _url varchar(255),
    in _name VARCHAR(255),
    in _order_index INTEGER,
    in _active_flag INTEGER,
    in _updated_date TIMESTAMP
)
body:
begin
    update menu
    set parent_id    = _parent_id,
        url          =_url,
        name         = _name,
        order_index  = _order_index,
        active_flag  = _active_flag,
        updated_date = _updated_date;
END$$
DELIMITER ;


drop procedure if EXISTS menuFindAll;
DELIMITER $$
CREATE PROCEDURE menuFindAll()
begin
    select n.*, a.role_id, a.permission, r.id
    from menu n
             inner join auth a on a.menu_id = n.id
             inner join role r on a.role_id = r.id
    where n.active_flag = 1
       or n.active_flag = 0;
end$$
DELIMITER ;
