USE db_bibliotech_libro_dev;
CREATE TABLE `libro` (
	`codigo` INT NOT NULL,
	`autor` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`disponible` INT NOT NULL,
	`fecha` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`isbn` INT NOT NULL,
	`titulo` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	PRIMARY KEY (`codigo`) USING BTREE
);

CREATE TABLE `libro_backup` (
	`codigo` INT NOT NULL,
	`autor` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`disponible` INT NOT NULL,
	`fecha` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`isbn` INT NOT NULL,
	`titulo` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	PRIMARY KEY (`codigo`) USING BTREE
);
