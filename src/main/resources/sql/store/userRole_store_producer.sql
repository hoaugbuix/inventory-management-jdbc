use inventory_management;

drop procedure if EXISTS userRole_create;
DELIMITER $$
CREATE PROCEDURE userRole_create(
    in _userId int,
    in _roleId int
)
body:
BEGIN
    declare newId int;
    SET max_sp_recursion_depth = 255;
   --  if
--             (
--                 select count(user_role.id)
--                 from user_role
--                 where user_role.id is not null) > 0 then
--         SET @message_text = CONCAT('Catgory \'', _name, '\' already exists');
--         SIGNAL
--             SQLSTATE '45000' SET MESSAGE_TEXT = @message_text;
--     else
        insert into user_role(user_id, role_id, active_flag, created_date, updated_date)
        values (_userId, _roleId, 1, now(), now());
        set newId = last_insert_id();
    -- end if;
    select newId;
END$$
DELIMITER ;

drop procedure if EXISTS userRole_findAll;
DELIMITER $$
CREATE PROCEDURE userRole_findAll()
begin
    select * from user_role where (active_flag = 1 or active_flag = 0);
end$$
DELIMITER ;