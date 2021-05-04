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
-- Table structure for table `covidtest`
--

DROP TABLE IF EXISTS `covidtest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `covidtest` (
  `idCovidTests` int NOT NULL AUTO_INCREMENT,
  `fighterFirstName` varchar(45) NOT NULL,
  `fighterLastName` varchar(45) NOT NULL,
  `arrivalTest` varchar(45) NOT NULL,
  `arrivalTestDate` varchar(45) NOT NULL,
  `secondTest` varchar(45) DEFAULT NULL,
  `secondTestDate` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCovidTests`),
  UNIQUE KEY `idcovidTests_UNIQUE` (`idCovidTests`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `covidtest`
--

LOCK TABLES `covidtest` WRITE;
/*!40000 ALTER TABLE `covidtest` DISABLE KEYS */;
INSERT INTO `covidtest` VALUES (92,'Mike','Thunder','Negative','2021-05-11',NULL,NULL),(93,'Pol','Warren','Negative','2021-05-11',NULL,NULL),(94,'Kurt','Kobain','Negative','2021-05-11',NULL,NULL),(95,'Kol','Paul','Negative','2021-05-11',NULL,NULL),(96,'Kill','Kruss','Negative','2021-05-11',NULL,NULL),(97,'Kiev','Roman','Negative','2021-05-11',NULL,NULL),(98,'Gisel','Murin','Negative','2021-05-11',NULL,NULL),(99,'Pike','Bor','Negative','2021-05-11',NULL,NULL),(100,'Russ','Simmons','Negative','2021-05-11',NULL,NULL),(101,'Kar','Khan','Negative','2021-05-11',NULL,NULL),(102,'Karius','Mirk','Negative','2021-05-11',NULL,NULL),(103,'Pol','Hugh','Negative','2021-05-11',NULL,NULL),(104,'Kol','Atkins','Negative','2021-05-11',NULL,NULL),(105,'Paul','Mirk','Negative','2021-05-11',NULL,NULL),(106,'Jabba','TheHut','Negative','2021-05-11',NULL,NULL),(107,'Thanos','Morus','Negative','2021-05-11',NULL,NULL),(108,'Hugh','Mendes','Negative','2021-05-11',NULL,NULL),(109,'Shawn','Mendes','Negative','2021-05-11',NULL,NULL);
/*!40000 ALTER TABLE `covidtest` ENABLE KEYS */;
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
