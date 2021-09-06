use sakila;

DELIMITER $$
CREATE PROCEDURE my_procedure(IN search_var VARCHAR(255))

BEGIN
DECLARE exit handler for SQLEXCEPTION
 BEGIN
  GET DIAGNOSTICS CONDITION 1 @sqlstate = RETURNED_SQLSTATE, 
   @errno = MYSQL_ERRNO, @text = MESSAGE_TEXT;
  SET @full_error = CONCAT("ERROR ", @errno, " (", @sqlstate, "): ", @text);
  SELECT @full_error;
 END;
 if (search_var != 0) then
	SIGNAL SQLSTATE '45000'
	SET MESSAGE_TEXT = 'Order No not found in orders table';
	SELECT * FROM city WHERE city_id = search_var;
end if;
END$$
DELIMITER ;