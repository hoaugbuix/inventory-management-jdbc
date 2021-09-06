use inventory_management;

drop procedure if EXISTS auth_create;
DELIMITER $$
CREATE PROCEDURE auth_create(
	 in _role_id  INTEGER,
	 in _menu_id INTEGER,
	in _permission INTEGER,
     in _active_flag  INTEGER,
     in _created_date TIMESTAMP,
     in _updated_date TIMESTAMP,
     in _cate_id int
)

body:BEGIN
	declare newid int;
	SET max_sp_recursion_depth=255;
	if(select count(auth.id) from auth where active_flag = 1 or  active_flag = 0) > 0 then
		SET @message_text = CONCAT('auth \'', name, '\' already exists');
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @message_text;
    else
		insert into auth( role_id, menu_id, permission, active_flag, created_date, updated_date, cate_id)
        values (_role_id, _menu_id, _permission, _active_flag, _created_date, _updated_date, _cate_id);
        set newId = last_insert_id();
    end if;
SELECT newId;
END$$
DELIMITER ;

drop procedure if EXISTS auth_update;
DELIMITER $$
CREATE PROCEDURE auth_update(
	in _role_id int,
    in _menu_id int,
     in _permission int,
     in _active_flag  INTEGER,
     in _updated_date TIMESTAMP
)
body:begin
	update auth set  role_id = _role_id, menu_id = _menu_id, permission = _permission, active_flag = _active_flag, updated_date = _updated_date;
END$$
DELIMITER ;


drop procedure if EXISTS auth_findAll;
DELIMITER $$
CREATE PROCEDURE auth_findAll()
begin
	select * from auth;
end$$
DELIMITER ;

drop procedure if EXISTS auth_findByRoleIdAndMenuId;
DELIMITER $$
CREATE PROCEDURE auth_findByRoleIdAndMenuId(in _roleId int, _menuId int)
begin
	select * from auth where active_flag = 1 or  active_flag = 0 and role_id = _roleId and menu_id = _menuId;
end$$
DELIMITER ;