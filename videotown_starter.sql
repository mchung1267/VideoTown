-- MySQL dump 10.13  Distrib 9.0.1, for macos14 (arm64)
--
-- Host: 127.0.0.1    Database: videotown
-- ------------------------------------------------------
-- Server version	9.0.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adRevClass`
--

DROP TABLE IF EXISTS `adRevClass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adRevClass` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `revenue` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adRevClass`
--

LOCK TABLES `adRevClass` WRITE;
/*!40000 ALTER TABLE `adRevClass` DISABLE KEYS */;
INSERT INTO `adRevClass` (`id`, `revenue`) VALUES (1,10),(2,12),(3,15),(4,20);
/*!40000 ALTER TABLE `adRevClass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advertisement`
--

DROP TABLE IF EXISTS `advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `advertisement` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `advertiserId` bigint DEFAULT NULL,
  `originId` bigint DEFAULT NULL,
  `views` bigint DEFAULT NULL,
  `adClass` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisement`
--

LOCK TABLES `advertisement` WRITE;
/*!40000 ALTER TABLE `advertisement` DISABLE KEYS */;
INSERT INTO `advertisement` (`id`, `advertiserId`, `originId`, `views`, `adClass`) VALUES (1,1,4,0,1),(2,1,4,0,1),(3,1,5,0,1),(4,1,NULL,0,1),(5,1,NULL,0,1);
/*!40000 ALTER TABLE `advertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adWatchHistory`
--

DROP TABLE IF EXISTS `adWatchHistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adWatchHistory` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `adId` bigint DEFAULT NULL,
  `viewerId` bigint DEFAULT NULL,
  `creatorId` bigint DEFAULT NULL,
  `viewedAt` datetime DEFAULT NULL,
  `fullyWatched` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adWatchHistory`
--

LOCK TABLES `adWatchHistory` WRITE;
/*!40000 ALTER TABLE `adWatchHistory` DISABLE KEYS */;
INSERT INTO `adWatchHistory` (`id`, `adId`, `viewerId`, `creatorId`, `viewedAt`, `fullyWatched`) VALUES (1,3,2,1,'2024-11-11 01:53:55',1);
/*!40000 ALTER TABLE `adWatchHistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dailyRevenue`
--

DROP TABLE IF EXISTS `dailyRevenue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dailyRevenue` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `creatorId` bigint DEFAULT NULL,
  `videoId` bigint DEFAULT NULL,
  `advertisementId` bigint DEFAULT NULL,
  `totalRevenue` double DEFAULT NULL,
  `effectiveDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dailyRevenue_members_id_fk` (`creatorId`),
  KEY `dailyRevenue_advertisement_id_fk` (`advertisementId`),
  KEY `dailyRevenue_videos_id_fk` (`videoId`),
  CONSTRAINT `dailyRevenue_advertisement_id_fk` FOREIGN KEY (`advertisementId`) REFERENCES `advertisement` (`id`),
  CONSTRAINT `dailyRevenue_members_id_fk` FOREIGN KEY (`creatorId`) REFERENCES `members` (`id`),
  CONSTRAINT `dailyRevenue_videos_id_fk` FOREIGN KEY (`videoId`) REFERENCES `videos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dailyRevenue`
--

LOCK TABLES `dailyRevenue` WRITE;
/*!40000 ALTER TABLE `dailyRevenue` DISABLE KEYS */;
/*!40000 ALTER TABLE `dailyRevenue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `calcPolicy` varchar(20) DEFAULT 'monthly',
  `type` varchar(20) DEFAULT 'user',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` (`id`, `email`, `password`, `name`, `calcPolicy`, `type`) VALUES (1,'meow@kitten.com','$2a$10$Cpvbp9pgDMUJavneOOZnLOFQ1SP4/5pbwNo84vogAb1h2T0ks1/PW','MeowHub','monthly','user'),(2,'tester@kitten.com','$2a$10$POu6qTkNvdF/Ga..sYZIyOZEm/wti8Nq/g7ezGGbN5m.MsX1toN0q','Kitten Channel 2','monthly','user');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revenueReport`
--

DROP TABLE IF EXISTS `revenueReport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revenueReport` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `userId` bigint DEFAULT NULL,
  `effectiveDate` date DEFAULT NULL,
  `revenue` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `revenueReport_members_id_fk` (`userId`),
  CONSTRAINT `revenueReport_members_id_fk` FOREIGN KEY (`userId`) REFERENCES `members` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revenueReport`
--

LOCK TABLES `revenueReport` WRITE;
/*!40000 ALTER TABLE `revenueReport` DISABLE KEYS */;
/*!40000 ALTER TABLE `revenueReport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videoClass`
--

DROP TABLE IF EXISTS `videoClass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videoClass` (
  `id` int NOT NULL AUTO_INCREMENT,
  `revenuePerWatch` decimal(10,1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videoClass`
--

LOCK TABLES `videoClass` WRITE;
/*!40000 ALTER TABLE `videoClass` DISABLE KEYS */;
INSERT INTO `videoClass` (`id`, `revenuePerWatch`) VALUES (1,1.0),(2,1.1),(3,1.3),(4,1.5);
/*!40000 ALTER TABLE `videoClass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos`
--

DROP TABLE IF EXISTS `videos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `creatorId` bigint NOT NULL,
  `revenueClass` bigint NOT NULL,
  `views` bigint NOT NULL,
  `length` bigint NOT NULL,
  `totalLength` bigint DEFAULT NULL,
  `adCoordination` varchar(255) DEFAULT NULL,
  `uploadDate` datetime NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos`
--

LOCK TABLES `videos` WRITE;
/*!40000 ALTER TABLE `videos` DISABLE KEYS */;
INSERT INTO `videos` (`id`, `creatorId`, `revenueClass`, `views`, `length`, `totalLength`, `adCoordination`, `uploadDate`, `title`) VALUES (1,1,1,0,250,250,NULL,'2024-11-04 18:27:30','Cats purring for 2 hours'),(2,1,1,0,450,465,'0 ','2024-11-06 01:04:26','My cat purring'),(3,1,1,0,750,780,'0 315','2024-11-06 01:05:09','My cat jumping around the room'),(4,1,1,0,620,650,'0 315','2024-11-09 14:32:47','Cats meowing compliliation'),(5,1,1,0,420,435,'0','2024-11-09 15:34:45','Test Video 2'),(6,1,1,0,1000,1030,'0 315','2024-11-10 18:37:57','Test Video 3');
/*!40000 ALTER TABLE `videos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `watchHistory`
--

DROP TABLE IF EXISTS `watchHistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `watchHistory` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `videoId` bigint DEFAULT NULL,
  `viewerId` bigint DEFAULT NULL,
  `creatorId` bigint DEFAULT NULL,
  `viewedAt` date DEFAULT NULL,
  `beginsAt` bigint DEFAULT NULL,
  `lastStamp` bigint DEFAULT NULL,
  `viewDone` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `watchHistory_videos_id_fk` (`videoId`),
  KEY `watchHistory_members_id_fk` (`viewerId`),
  CONSTRAINT `watchHistory_members_id_fk` FOREIGN KEY (`viewerId`) REFERENCES `members` (`id`),
  CONSTRAINT `watchHistory_videos_id_fk` FOREIGN KEY (`videoId`) REFERENCES `videos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `watchHistory`
--

LOCK TABLES `watchHistory` WRITE;
/*!40000 ALTER TABLE `watchHistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `watchHistory` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-16 13:33:49
