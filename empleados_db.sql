-- Crea la base de datos empleados_db
CREATE SCHEMA IF NOT EXISTS `empleados_db` ;

-- Cambia al esquema empleados_db
USE `empleados_db`;

-- Crea la tabla empleados con su estructura
CREATE TABLE IF NOT EXISTS `empleados` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `cargo` VARCHAR(45) NOT NULL,
  `salario` DOUBLE NOT NULL,
  `fecha_inicio` DATETIME(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
);

-- Inserta datos iniciales en la tabla empleados
INSERT INTO `empleados` (`nombre`, `apellido`, `cargo`, `salario`, `fecha_inicio`) VALUES 
('José', 'Suarez', 'Gerente General', 22000, '2006-05-20 00:00:00.000000'),
('María', 'Valdivia', 'Sub Gerente General', 19000, '2016-03-22 00:00:00.000000'),
('Natalia', 'Carazo', 'Recursos Humanos', 15000, '2012-05-20 00:00:00.000000'),
('Luis', 'Medina', 'Jefe de Proyecto', 15000, '2010-05-25 00:00:00.000000'),
('Jorge', 'Caceres', 'Jefe de Almacén', 14000, '2014-07-21 00:00:00.000000');

