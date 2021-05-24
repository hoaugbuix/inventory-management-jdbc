use inventory_management;

drop procedure if EXISTS role_create;
DELIMITER $$
CREATE PROCEDURE role_create(
     in _name   VARCHAR(255),
     in _code    VARCHAR(100) ,
     in _description  VARCHAR(255) ,
     in _active_flag  INTEGER,
     in _created_date TIMESTAMP,
     in _updated_date TIMESTAMP
)

body:BEGIN
	declare newid int;
	SET max_sp_recursion_depth=255;
	if(select count(category.id) from category where category.name and category.code) > 0 then
		SET @message_text = CONCAT('Catgory \'', name, '\' already exists');
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @message_text;
    else
		insert into category(name, code, description, active_flag, created_date, updated_date)
        values (_name, _code, _description, _active_flag, _created_date, _updated_date);
        set newId = last_insert_id();
    end if;
    select newId;
END$$
DELIMITER ;

drop procedure if EXISTS findByRoleName;
DELIMITER $$
CREATE PROCEDURE findByRoleName(in _roleName varchar(100))
begin
	select * from role where active_flag = 1 AND role_name = _roleName;
end$$
DELIMITER ;