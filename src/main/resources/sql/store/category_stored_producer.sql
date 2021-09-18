use inventory_management;

drop procedure if EXISTS category_create;
DELIMITER $$
CREATE PROCEDURE category_create(
    in _name VARCHAR(255),
    in _code VARCHAR(100),
    in _description VARCHAR(255),
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
                select count(category.id)
                from category
                where category.name
                  and category.code) > 0 then
        SET @message_text = CONCAT('Catgory \'', '', '\' already exists');
        SIGNAL
            SQLSTATE '45000' SET MESSAGE_TEXT = @message_text;
    else
        insert into category(name, code, description, active_flag, created_date, updated_date)
        values (_name, _code, _description, _active_flag, _created_date, _updated_date);
        set
            newId = last_insert_id();
    end if;
    select newId;
END$$
DELIMITER ;

-- update
drop procedure if EXISTS category_update;
DELIMITER $$
CREATE PROCEDURE category_update(
    in _name VARCHAR(255),
    in _code VARCHAR(100),
    in _description Text,
    in _active_flag INTEGER,
    in _updated_date TIMESTAMP
)
body:
begin
    update category
    set name         = _name,
        code         = _code,
        description  = _description,
        active_flag  = _active_flag,
        updated_date = _updated_date;
END$$
DELIMITER ;
--

drop procedure if EXISTS category_findAll;
DELIMITER $$
CREATE PROCEDURE category_findAll()
begin
    select *
    from category
    where active_flag = 1
       or active_flag = 0;
end$$
DELIMITER ;


drop procedure if EXISTS category_findCategoryById;
DELIMITER $$
CREATE PROCEDURE category_findCategoryById(inout _id int)
begin
    select *
    from category
    where active_flag = 1
      and id = _id
    order by name;
end$$
DELIMITER ;

drop procedure if EXISTS category_findCategoryByCode;
DELIMITER $$
CREATE PROCEDURE category_findCategoryByCode(in _code varchar(100))
begin
    select *
    from category
    where active_flag = 1
      and code = _code;
end$$
DELIMITER ;


drop procedure if EXISTS category_checkProductInCategory;
DELIMITER $$
CREATE PROCEDURE category_checkProductInCategory(in _id int)
begin
    select 1
    from product_info
    where cate_id = _id;
end$$
DELIMITER ;