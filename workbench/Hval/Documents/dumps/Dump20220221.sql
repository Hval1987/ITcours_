-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: park
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `approved_order`
--

DROP TABLE IF EXISTS `approved_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `approved_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_assigned_car` int NOT NULL,
  `status` varchar(45) NOT NULL,
  `id_manager` int NOT NULL,
  `id_driver` int NOT NULL,
  `id_employer` varchar(45) NOT NULL,
  `id_order` int NOT NULL,
  `date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_approved-order_car1_idx` (`id_assigned_car`),
  KEY `fk_approved-order_users1_idx` (`id_manager`),
  KEY `fk_approved-order_users2_idx` (`id_driver`),
  KEY `fk_id_order_idx` (`id_order`),
  CONSTRAINT `fk_approved-order_users1` FOREIGN KEY (`id_manager`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_approved-order_users2` FOREIGN KEY (`id_driver`) REFERENCES `user` (`id`),
  CONSTRAINT `fp_assigned_car` FOREIGN KEY (`id_assigned_car`) REFERENCES `car` (`id`),
  CONSTRAINT `fp_id_order` FOREIGN KEY (`id_order`) REFERENCES `order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `approved_order`
--

LOCK TABLES `approved_order` WRITE;
/*!40000 ALTER TABLE `approved_order` DISABLE KEYS */;
INSERT INTO `approved_order` VALUES (1,1,'COMPLITED',6,15,'5',1,'23.5.2021'),(2,4,'COMPLITED',6,4,'2',2,'23.5.2021'),(3,3,'COMPLITED',6,15,'4',3,'2023-06-29'),(5,2,'dg',2,1,'3',2,'23.5.2021'),(6,3,'in ',5,2,'1',3,'2023-06-29'),(8,1,'in progress',1,4,'1',3,'2023-06-29'),(9,5,'in progress',1,4,'1',3,'23.5.2021'),(10,1,'in progress',1,4,'2',4,'12.3.3634'),(11,1,'in progress',1,4,'1',3,'2023-06-29'),(12,1,'in progress',1,4,'1',3,'23.5.2021'),(13,1,'in progress',1,4,'1',6,'12.3.3634'),(14,1,'in progress',1,4,'2',4,'23.5.2021'),(15,1,'in progress',1,4,'2',4,'23.5.2021'),(16,1,'in progress',1,4,'1',3,'23.5.2021'),(17,4,'in progress',1,4,'2',2,'2023-06-29'),(18,2,'in progress',1,4,'1',1,'2023-06-29'),(19,1,'in progress',1,4,'1',3,'2023-06-29'),(20,4,'in progress',1,4,'2',2,'12.3.3634'),(21,1,'in progress',1,4,'1',3,'23.5.2021'),(22,4,'in progress',1,4,'2',2,'2022-01-20'),(23,2,'in progress',1,1,'1',1,'12.3.3634'),(24,32,'COMPLITED',15,15,'2',4,'23.5.2021'),(25,8,'IN_PROCESS',15,15,'2',37,'2022-01-20'),(26,2,'IN_PROCESS',15,1,'1',1,'23.5.2021'),(27,2,'IN_PROCESS',18,1,'1',1,NULL),(28,4,'IN_PROCESS',1,15,'2',2,NULL),(30,2,'IN_PROCESS',20,1,'1',1,NULL),(34,8,'IN_PROCESS',20,15,'2',37,NULL),(35,130,'IN_PROCESS',20,5,'1',6,NULL),(36,8,'IN_PROCESS',20,15,'2',37,NULL),(37,84,'IN_PROCESS',20,5,'1',1,NULL),(38,1,'IN_PROCESS',20,15,'1',6,NULL),(39,32,'IN_PROCESS',20,15,'1',6,NULL),(40,128,'IN_PROCESS',20,5,'1',1,NULL);
/*!40000 ALTER TABLE `approved_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `id` int NOT NULL AUTO_INCREMENT,
  `transport_type` varchar(45) NOT NULL,
  `driverId` tinyint NOT NULL,
  `regNumber` varchar(45) NOT NULL,
  `available` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `regNumber_UNIQUE` (`regNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (1,'truck',15,'АН 5115-5','SERVISEABLE'),(2,'bus',1,'АК 7494-5','SERVISEABLE'),(3,'mixer',1,'АВ 1432-5','FAULTY'),(4,'passenger_car',15,'АВ 0847-5','FAULTY'),(5,'truck',15,'АР 2315-5','SERVISEABLE'),(8,'car',15,'АП 4555-5','FAULTY'),(32,'truck',15,'ВП 7845-5','SERVISEABLE'),(84,'bus',5,'ОО 012345-0','FAULTY'),(127,'truck',4,'4352 ВП-6','SERVISEABLE'),(128,'bus',5,'3465 ОЛ-6','SERVISEABLE'),(129,'truck',17,'2637 ФВ-2','SERVISEABLE'),(130,'truck',5,'1334 ПА-5','SERVISEABLE');
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `damage`
--

DROP TABLE IF EXISTS `damage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `damage` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(45) DEFAULT NULL,
  `car_idNumber` int NOT NULL,
  `users_id` int NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_поломка_car1_idx` (`car_idNumber`),
  KEY `fk_поломка_users1_idx` (`users_id`),
  CONSTRAINT `fk_поломка_car1` FOREIGN KEY (`car_idNumber`) REFERENCES `car` (`id`),
  CONSTRAINT `fk_поломка_users1` FOREIGN KEY (`users_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `damage`
--

LOCK TABLES `damage` WRITE;
/*!40000 ALTER TABLE `damage` DISABLE KEYS */;
/*!40000 ALTER TABLE `damage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `transport_type` varchar(45) NOT NULL,
  `employer_id` int NOT NULL,
  `date` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_users1_idx` (`employer_id`),
  CONSTRAINT `fk_order_users1` FOREIGN KEY (`employer_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'bus',1,'12.4.2424'),(2,'passenger_car',2,'12.3.3634'),(3,'truck',1,'23.5.2021'),(4,'truck',2,'23.5.2022'),(6,'truck',1,'2021-12-01'),(37,'car',2,'2022-01-20'),(50,'truck',1,'2022-02-24');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tittle` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Employer'),(2,'driver'),(3,'Dispatcher'),(4,'Admin'),(5,'Admin2');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `roles_id` int NOT NULL,
  `email` varchar(45) NOT NULL,
  `passportNumber` varchar(45) DEFAULT NULL,
  `userPassword` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `fk_users_roles1_idx` (`roles_id`),
  CONSTRAINT `fk_users_roles1` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'John',1,'john@gmail.com','МС 4258545','1','John'),(2,'Mike',1,'mike@ya.ru','МС 7854165','935785','Mike'),(3,'Ivan',2,'vanua@ya.ru','МС 4583212','347347','Ival'),(4,'Leonid',2,'lenya@ya.ru','МО 7126578','377257','Lenya'),(5,'Sergey',2,'serega@tut.by','МС 1257545','837445','Sergey'),(6,'Evgeniy',3,'evg@mail.ru','МА 3254578','525634','Evgeniy'),(15,'Valery',2,'hval2006@ya.ru','MC 1233333','0','Valera'),(17,'valera',2,'h','John','2','Valera'),(18,'Диана',3,'В','John','4','Diana'),(20,'admin',3,'adm@ya.ru','1332412412gh','admin','Admin'),(141,'Hvalerka11',3,'adm@ya.ru','MD1355725','admin','Валерий');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-21 19:35:05
