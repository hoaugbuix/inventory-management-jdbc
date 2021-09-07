use inventory_management;

drop procedure if EXISTS history_findAll;
DELIMITER $$
CREATE PROCEDURE history_findAll()
begin
	select h.*,p.* from history h inner join product_info p on h.product_id =p.id  where h.active_flag = 1 or h.active_flag = 0 ;
end$$
DELIMITER ;