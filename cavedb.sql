-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: localhost    Database: cavedb
-- ------------------------------------------------------
-- Server version	5.6.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `username` varchar(60) NOT NULL,
  `authority` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES ('Admin','ROLE_ADMIN'),('CreatedChen','ROLE_USER'),('CreatedChen2','ROLE_USER'),('CreatedChen3','ROLE_USER'),('createdJohn','ROLE_USER'),('CreatedMay','ROLE_USER'),('EncryptedAdmin','ROLE_ADMIN'),('John','ROLE_USER'),('testEncryptedPassword','ROLE_USER'),('testEncryptedPassword2','ROLE_USER'),('testTileUser','ROLE_USER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offers`
--

DROP TABLE IF EXISTS `offers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(60) NOT NULL,
  `text` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offers`
--

LOCK TABLES `offers` WRITE;
/*!40000 ALTER TABLE `offers` DISABLE KEYS */;
INSERT INTO `offers` VALUES (2,'Mike','mike@nowhereatall.com','Web design, very cheap'),(3,'Sue','sue@nowhereatall.com','PHP coding'),(4,'Dave','Dave@mun.ca','Coding Java'),(5,'Dave','Dave@mun.ca','Coding Java'),(6,'DaveUpdate','Dave@nocland.com','Full stack'),(7,'Dave','Dave@mun.ca','Coding Java'),(12,'Joe','joe@caveofprogramming.com','Elegant web design'),(13,'Steve','steve@caveofprogramming.com','Cash for software.'),(14,'Steve','steve@caveofprogramming.com','Cash for software.'),(15,'Joe','joe@caveofprogramming.com','Elegant web design'),(16,'Steve','steve@caveofprogramming.com','Cash for software.'),(17,'Joe','joe@caveofprogramming.com','Elegant web design'),(18,'Steve','steve@caveofprogramming.com','Cash for software.'),(20,'John Lennon','John@Lennon.com','When I went to school, they asked me what I wanted to be when I grew up. I wrote down ?happy?. They told me I didn?t understand the assignment, and I told them they didn?t understand life.'),(21,'John Lennon','John@Lennon.com','Make a dent in the universe. ');
/*!40000 ALTER TABLE `offers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(60) NOT NULL,
  `password` varchar(80) DEFAULT NULL,
  `enabled` blob,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('Admin','hello','1','admin@chenli.com'),('CreatedChen','hello','0','chen.li@mun.ca'),('CreatedChen2','hello','1','chen.li@mun.ca'),('CreatedChen3','hello','1','chen.li@mun.ca'),('createdJohn','hello','1','John@Lennon.com'),('CreatedMay','hello','0','yekiki@gmail.com'),('EncryptedAdmin','9d0c7a8af0e83ceee95e30021b406f21e903b01f5c717e52cfb931f8ca08034dfbe62dafaaa0a202','1','Hecker@HelloWorld.com'),('John','hello','1',NULL),('testEncryptedPassword','c496becac3d9903a691df9ec306a6ee1d086ea7751983eb700abad88e938332108bb988395da3faa','1','chen.li@mun.ca'),('testEncryptedPassword2','47d887e30dc316a3c8921ec2c2f7f926deb73e819fa4dea785c143e734756009aa46fa9cba8bcdb6','1','lulugeo.li@gmail.com'),('testTileUser','e4b9fe9f615648619648e059cc171f225028e1dfa5ece2e0ef90cb2b177955c0be320b017079fe26','1','chen.li@mun.ca');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-01-23 12:55:07
