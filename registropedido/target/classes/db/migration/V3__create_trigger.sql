USE db_bibliotech_pedidos;
DELIMITER //

CREATE TRIGGER despues_insert_pedido

AFTER INSERT ON pedido

FOR EACH ROW

BEGIN

  INSERT INTO pedido_backup (rut, codigo, nombre, fechaini, fechadevo, titulo)

  VALUES (NEW.rut, NEW.codigo, NEW.nombre, NEW.fechaini, NEW.fechadevo, NEW.titulo);

END//

DELIMITER ;

