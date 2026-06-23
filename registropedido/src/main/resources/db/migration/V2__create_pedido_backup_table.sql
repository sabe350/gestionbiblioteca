USE db_bibliotech_pedidos;
CREATE TABLE `pedido_backup` (
	`codigo` INT NOT NULL,
	`fechadevo` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`fechaini` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`nombre` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`rut` INT NOT NULL,
	`titulo` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	PRIMARY KEY (`codigo`) USING BTREE
);