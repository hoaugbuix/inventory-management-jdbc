use inventory_management;

drop procedure if EXISTS invoice_create;
DELIMITER $$
CREATE PROCEDURE invoice_create(
    in _code VARCHAR(100),
    in _type int,
    in _product_id int,
    in _qty int,
    in _price decimal(50, 2),
    in _to_date TIMESTAMP,
    in _from_date TIMESTAMP,
    in _active_flag INTEGER,
    in _created_date TIMESTAMP,
    in _updated_date TIMESTAMP)
body:
BEGIN
    declare newid int;
    SET max_sp_recursion_depth = 255;
    if
            (
                select count(invoice.id)
                from invoice
                where invoice.code = _code) > 0 then
        SET @message_text = CONCAT('Catgory \'', _code, '\' already exists');
        SIGNAL
            SQLSTATE '45000' SET MESSAGE_TEXT = @message_text;
    else
        insert into invoice(code, type, product_id, qty, price, to_date, from_date, active_flag, created_date,
                            updated_date)
        values (_code, _type, _product_id, _qty, _price, _to_date, _from_date, _active_flag, _created_date,
                _updated_date);
        set newId = last_insert_id();
    end if;
    select newId;
END$$
DELIMITER ;

-- update
drop procedure if EXISTS invoice_update;
DELIMITER $$
CREATE PROCEDURE invoice_update(
    in _code VARCHAR(100),
    in _type int,
    in _product_id int,
    in _qty int,
    in _price decimal(5, 2),
    in _to_date TIMESTAMP,
    in _from_date TIMESTAMP,
    in _description VARCHAR(255),
    in _active_flag INTEGER,
    in _updated_date TIMESTAMP
)
body:
begin
    update invoice
    set code         = _code,
        type= _type,
        product_id   =_product_id,
        qty          = _qty,
        price= _price,
        to_date= _to_date,
        from_date=_from_date,
        active_flag  = _active_flag,
        updated_date = _updated_date;
END$$
DELIMITER ;

drop procedure if EXISTS invoice_findAll;
DELIMITER $$
CREATE PROCEDURE invoice_findAll(in _type int)
begin
    select *
    from invoice
    where type = _type
      and (active_flag = 1
        or active_flag = 0);
end$$
DELIMITER ;

drop procedure if EXISTS invoice_findById;
DELIMITER $$
CREATE PROCEDURE invoice_findById(in _id int)
begin
    select *
    from invoice
    where id = _id
      and (active_flag = 1
        or active_flag = 0);
end$$
DELIMITER ;

drop procedure if EXISTS invoice_findByCode;
DELIMITER $$
CREATE PROCEDURE invoice_findByCode(in _code nvarchar(50))
begin
    select i.*
    from invoice i
    where i.code = _code
      and (active_flag = 1
        or active_flag = 0);
end$$
DELIMITER ;