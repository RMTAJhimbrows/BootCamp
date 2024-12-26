-- MySQL dump 10.13  Distrib 8.4.3, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: empleados_db
-- ------------------------------------------------------
-- Server version	8.4.3

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
CREATE TABLE `empleados` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `cargo` varchar(255) DEFAULT NULL,
  `salario` double DEFAULT NULL,
  `fechaInicio` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;

INSERT INTO `empleados` VALUES (2,'María','Valdivia','Sub Gerente General',19000,'2024-01-01'),(3,'Natalia','Carazo','Recursos Humanos',15000,'2024-01-01'),(4,'Luis','Medina','Jefe de Proyecto',15000,'2024-01-01'),(5,'Jorge','Caceres','Jefe de Almacén',14000,'2024-01-01'),(6,'Carmen','Perez','Jefe',23000,'2022-12-03'),(8,'Fulano','Rodriguez','Obrero de planta',20000,'1990-02-01'),(9,'Fulano','Rodriguez','Obrero de planta',20000,'1990-02-01'),(11,'Fulano','Rodriguez','Obrero de planta',20000,'1990-02-01'),(13,'coco','fuli','jefe de ventas',23000,'2022-05-03'),(14,'david','fulo','obrero',800,'2012-11-06'),(15,'Maria','Sanchez','Secretaria',15000,'2022-09-03'),(16,'miguel','machicado','repartidor',12000,'2021-09-13'),(17,'manuel','turizo','cantante',12000,'2023-12-04'),(18,'fernando','salas','chofer designado',123000,'2014-04-24'),(19,'RAFAEL','GALLARDO','PANADERO',300000,'2012-10-08'),(20,'george','washintong','presidente de los estados unidos',200000,'1997-09-12'),(22,'juan','kike','hostelero',12000,'2021-12-21');

UNLOCK TABLES;

