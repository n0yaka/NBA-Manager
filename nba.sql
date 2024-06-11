CREATE DATABASE  IF NOT EXISTS `nba` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `nba`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: nba
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contract` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `team` varchar(100) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  `height_cm` double DEFAULT NULL,
  `weight_kg` double DEFAULT NULL,
  `points_per_game` double DEFAULT NULL,
  `rebounds_per_game` double DEFAULT NULL,
  `assists_per_game` double DEFAULT NULL,
  `blocks_per_game` double DEFAULT NULL,
  `steals_per_game` double DEFAULT NULL,
  `salary` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `firstteam`
--

DROP TABLE IF EXISTS `firstteam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `firstteam` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `team` varchar(100) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  `height_cm` double DEFAULT NULL,
  `weight_kg` double DEFAULT NULL,
  `points_per_game` double DEFAULT NULL,
  `rebounds_per_game` double DEFAULT NULL,
  `assists_per_game` double DEFAULT NULL,
  `blocks_per_game` double DEFAULT NULL,
  `steals_per_game` double DEFAULT NULL,
  `salary` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `firstteam`
--

LOCK TABLES `firstteam` WRITE;
/*!40000 ALTER TABLE `firstteam` DISABLE KEYS */;
INSERT INTO `firstteam` VALUES (42,'Nikola Jokic','Denver Nuggets','Center',211,129,28.7,13.4,8.7,0.7,1.4,3100),(44,'Karl-Anthony Towns','Minnesota Timberwolves','Center',213,112,19.1,9,2.6,0.2,0.8,1900),(45,'Shai Gilgeous-Alexander','Oklahoma City Thunder','Guard',198,88,30.1,5.5,6.2,1.7,1.3,3000),(46,'Joel Embiid','Philadelphia 76ers','Forward',213,127,34.7,11,5.6,0.7,1.2,3000),(47,'Luka Doncic','Dallas Mavericks','Forward',201,104,28.8,9.7,8.4,0.4,1.7,3300);
/*!40000 ALTER TABLE `firstteam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `injury`
--

DROP TABLE IF EXISTS `injury`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `injury` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `team` varchar(100) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  `height_cm` double DEFAULT NULL,
  `weight_kg` double DEFAULT NULL,
  `points_per_game` double DEFAULT NULL,
  `rebounds_per_game` double DEFAULT NULL,
  `assists_per_game` double DEFAULT NULL,
  `blocks_per_game` double DEFAULT NULL,
  `steals_per_game` double DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `injury` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `injury`
--

LOCK TABLES `injury` WRITE;
/*!40000 ALTER TABLE `injury` DISABLE KEYS */;
/*!40000 ALTER TABLE `injury` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `players` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `team` varchar(100) NOT NULL,
  `position` varchar(100) DEFAULT NULL,
  `height_cm` int DEFAULT NULL,
  `weight_kg` int DEFAULT NULL,
  `points_per_game` decimal(10,2) DEFAULT NULL,
  `rebounds_per_game` decimal(10,2) DEFAULT NULL,
  `assists_per_game` decimal(10,2) DEFAULT NULL,
  `blocks_per_game` decimal(10,2) DEFAULT NULL,
  `steals_per_game` decimal(10,2) DEFAULT NULL,
  `salary` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` VALUES (39,'Tyrese Maxey','Philadelphia 76ers','Guard',188,91,25.90,3.70,6.20,0.30,0.80,3300),(40,'Donovan Mitchell','Cleveland Cavaliers','Guard',191,98,26.60,5.10,6.10,0.30,1.30,3100),(43,'Anthony Davis','Los Angeles Lakers','Center',208,115,27.80,15.60,4.00,1.60,0.40,3200),(44,'LeBron James','Los Angeles Lakers','Guard',206,113,27.80,6.80,8.80,1.00,2.40,3500),(46,'Franz Wagner','Orlando Magic','Forward',208,100,18.90,6.90,4.40,1.30,0.70,1700),(47,'Jalen Williams','Oklahoma City Thunder','Forward',196,96,18.70,6.80,5.40,0.50,1.70,1800),(48,'Paul George','LA Clippers','Forward',203,100,19.50,6.80,4.80,0.50,1.20,1750),(49,'Brook Lopez','Milwaukee Bucks','Center',216,128,17.70,4.30,1.80,1.50,1.20,1500),(50,'Myles Turner','Indiana Pacers','Center',211,113,17.00,6.60,2.10,1.50,0.50,1300),(51,'Jalen Brunson','New York Knicks','Guard',188,86,28.70,3.60,6.70,0.20,0.80,3100);
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-09 22:14:21
