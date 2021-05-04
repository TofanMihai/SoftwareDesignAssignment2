-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: assignment1sd
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `fighter`
--

DROP TABLE IF EXISTS `fighter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fighter` (
  `idfighter` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `weight` double NOT NULL,
  `inIsolation` varchar(45) NOT NULL,
  `scheduled` varchar(45) DEFAULT '"No"',
  PRIMARY KEY (`idfighter`),
  UNIQUE KEY `idfighter_UNIQUE` (`idfighter`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fighter`
--

LOCK TABLES `fighter` WRITE;
/*!40000 ALTER TABLE `fighter` DISABLE KEYS */;
INSERT INTO `fighter` VALUES (104,'Mike','Thunder',92,'Yes','No'),(105,'Pol','Warren',94,'Yes','No'),(106,'Kurt','Kobain',91,'Yes','No'),(107,'Kol','Paul',82,'Yes','No'),(108,'Kill','Kruss',72,'Yes','No'),(109,'Kiev','Roman',76,'Yes','No'),(110,'Gisel','Murin',82,'Yes','No'),(111,'Pike','Bor',89.5,'Yes','No'),(112,'Russ','Simmons',77,'Yes','No'),(113,'Kar','Khan',74.2,'Yes','No'),(114,'Karius','Mirk',82,'Yes','No'),(115,'Pol','Hugh',81,'Yes','No'),(116,'Kol','Atkins',70,'Yes','No'),(117,'Paul','Mirk',83.5,'Yes','No'),(118,'Jabba','TheHut',67,'Yes','No'),(119,'Thanos','Morus',87,'Yes','No'),(120,'Hugh','Mendes',78.2,'Yes','No'),(121,'Shawn','Mendes',80,'Yes','No');
/*!40000 ALTER TABLE `fighter` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-02  0:29:11
