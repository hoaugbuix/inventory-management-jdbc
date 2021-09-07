use inventory_management;

drop procedure if EXISTS invoice_findAll;
DELIMITER $$
CREATE PROCEDURE invoice_findAll(in  _type int)
begin
select * from invoice where active_flag = 1 or  active_flag = 0 and type= _type;
end$$
DELIMITER ;