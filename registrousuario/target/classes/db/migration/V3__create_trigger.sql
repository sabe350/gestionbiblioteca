USE db_bibliotech_usuario;
DELIMITER //

CREATE TRIGGER despues_insert_usuario

AFTER INSERT ON usuario

FOR EACH ROW

BEGIN

  INSERT INTO usuario_backup (rut, nombre, apellido, password, email)

  VALUES (NEW.rut, NEW.nombre, NEW.apellido, NEW.password, NEW.email);

END//

DELIMITER ;

