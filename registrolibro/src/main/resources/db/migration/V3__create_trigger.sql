USE db_bibliotech_libro;
DELIMITER //

CREATE TRIGGER despues_insert_libro

AFTER INSERT ON libro

FOR EACH ROW

BEGIN

  INSERT INTO libro_backup (codigo, titulo, autor, fecha, isbn, disponible)

  VALUES (NEW.codigo, NEW.titulo, NEW.autor, NEW.fecha, NEW.isbn, NEW.disponible);

END//

DELIMITER ;

