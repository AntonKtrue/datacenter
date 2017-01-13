CREATE DATABASE  IF NOT EXISTS `regft` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `regft`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: regft
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `dict_department`
--

DROP TABLE IF EXISTS `dict_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `shortName` varchar(45) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `structure_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict_department`
--

LOCK TABLES `dict_department` WRITE;
/*!40000 ALTER TABLE `dict_department` DISABLE KEYS */;
INSERT INTO `dict_department` VALUES (1,'Главный отдел 1','Главный 1',NULL,3),(2,'Подотдел 1','Подотдел 1',1,3),(3,'Подотдел 2','Подотдел 2',1,3),(4,'Главный отдел 2','Главный 2',NULL,4),(5,'Подотдел 3','Подотдел3',4,4),(6,'Подотдел4','Подотдел4',4,4),(7,'Подотдел 5','Подотдел5',9,4),(8,'Подотдел6','Подотдел6',9,4),(9,'Главный отдел 3','главотд3',NULL,4);
/*!40000 ALTER TABLE `dict_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dict_employee`
--

DROP TABLE IF EXISTS `dict_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `patroName` varchar(45) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `rank_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict_employee`
--

LOCK TABLES `dict_employee` WRITE;
/*!40000 ALTER TABLE `dict_employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `dict_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dict_nps`
--

DROP TABLE IF EXISTS `dict_nps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_nps` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `shortname` varchar(45) NOT NULL,
  `umn` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `shortname_UNIQUE` (`shortname`),
  KEY `umn_idx` (`umn`),
  CONSTRAINT `umn` FOREIGN KEY (`umn`) REFERENCES `dict_umn` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict_nps`
--

LOCK TABLES `dict_nps` WRITE;
/*!40000 ALTER TABLE `dict_nps` DISABLE KEYS */;
INSERT INTO `dict_nps` VALUES (1,'01','НПС «Азнакаево-1»','НПС «Азнакаево-1»',1),(2,'02','НПС «Азнакаево-2»','НПС «Азнакаево-2»',1),(3,'03','НПС «Белая»','НПС «Белая»',1),(4,'04','НПС «Карабаш»','НПС «Карабаш»',1),(5,'05','НПС «Михайловка-2»','НПС «Михайловка-2»',1),(6,'06','НПС «Михайловка-3»','НПС «Михайловка-3»',1),(7,'07','НПС «Муслюмово»','НПС «Муслюмово»',1),(8,'08','НПС «Набережные Челны-1»','НПС «Набережные Челны-1»',1),(9,'09','НПС «Набережные Челны-2»','НПС «Набережные Челны-2»',1),(10,'10','НПС-3 н/н №1','НПС-3 н/н №1',1),(11,'11','НПС-3 н/н №2','НПС-3 н/н №2',1),(12,'12','ПСП «Нижнекамск» № 495','ПСП «Нижнекамск» № 495',1),(13,'13','ПСП «Нижнекамск» НПЗ','ПСП «Нижнекамск» НПЗ',1),(14,'01','НПС «Ковали-2»','НПС «Ковали-2»',2),(15,'02','НПС «Ковали-3»','НПС «Ковали-3»',2),(16,'03','НПС «Лазарево-1»','НПС «Лазарево-1»',2),(17,'04','НПС «Лазарево-2»','НПС «Лазарево-2»',2),(18,'05','НПС «Студенец»','НПС «Студенец»',2),(19,'06','НПС «Тиньговатово-2»','НПС «Тиньговатово-2»',2),(20,'07','НПС «Тиньговатово-3»','НПС «Тиньговатово-3»',2),(21,'01','НПС «Арбатская-1»','НПС «Арбатская-1»',3),(22,'02','НПС «Арбатская-2»','НПС «Арбатская-2»',3),(23,'03','НПС «Бисер»','НПС «Бисер»',3),(24,'04','НПС«Лысьва-1»','НПС«Лысьва-1»',3),(25,'05','НПС«Лысьва-2»','НПС«Лысьва-2»',3),(26,'06',' НПС «Мостовая»',' НПС «Мостовая»',3),(27,'07','НПС «Оса-1»','НПС «Оса-1»',3),(28,'08','НПС «Оса-2»','НПС «Оса-2»',3),(29,'09',' НПС «Пермь-1»',' НПС «Пермь-1»',3),(30,'10',' НПС «Пермь-2»',' НПС «Пермь-2»',3),(31,'11',' НПС «Пермь-3»',' НПС «Пермь-3»',3),(32,'12',' НПС «Пермь-4»',' НПС «Пермь-4»',3),(33,'13','НПС «Платина-1»','НПС «Платина-1»',3),(34,'14','НПС «Платина-2»','НПС «Платина-2»',3),(35,'15','НПС «Полазна»','НПС «Полазна»',3),(36,'16','НПС «Уральская»','НПС «Уральская»',3),(37,'01','НПС «Байтуган»','НПС «Байтуган»',4),(38,'02','НПС «Елизаветинка-1»','НПС «Елизаветинка-1»',4),(39,'03','НПС «Елизаветинка-2»','НПС «Елизаветинка-2»',4),(40,'04','НПС «Елизаветинка-3»','НПС «Елизаветинка-3»',4),(41,'05','НПС «Елизаветинка-4»','НПС «Елизаветинка-4»',4),(42,'06','НПС «Калейкино-1»','НПС «Калейкино-1»',4),(43,'07','НПС «Калейкино-2»','НПС «Калейкино-2»',4),(44,'08','НПС «Калейкино-3»','НПС «Калейкино-3»',4),(45,'09','НПС «Калейкино-4»','НПС «Калейкино-4»',4),(46,'10','НПС «Калейкино-5»','НПС «Калейкино-5»',4),(47,'11','НПС «Калиновый Ключ»','НПС «Калиновый Ключ»',4),(48,'01','НПС «Арлеть»','НПС «Арлеть»',5),(49,'02','НПС «Большая Соснова»','НПС «Большая Соснова»',5),(50,'03','НПС «Дебесы»','НПС «Дебесы»',5),(51,'04','НПС «Киенгоп»','НПС «Киенгоп»',5),(52,'05','НПС «Малая Пурга»','НПС «Малая Пурга»',5),(53,'06','НПС «Сюмси»','НПС «Сюмси»',5);
/*!40000 ALTER TABLE `dict_nps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dict_ost`
--

DROP TABLE IF EXISTS `dict_ost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_ost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `shortname` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `shortname_UNIQUE` (`shortname`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict_ost`
--

LOCK TABLES `dict_ost` WRITE;
/*!40000 ALTER TABLE `dict_ost` DISABLE KEYS */;
INSERT INTO `dict_ost` VALUES (1,'10','АО \"Транснефть - Прикамье\"','ТПК');
/*!40000 ALTER TABLE `dict_ost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dict_rank`
--

DROP TABLE IF EXISTS `dict_rank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_rank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `shortName` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict_rank`
--

LOCK TABLES `dict_rank` WRITE;
/*!40000 ALTER TABLE `dict_rank` DISABLE KEYS */;
INSERT INTO `dict_rank` VALUES (1,'Генеральный директор','Ген.директор');
/*!40000 ALTER TABLE `dict_rank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dict_states`
--

DROP TABLE IF EXISTS `dict_states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_states` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `short_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict_states`
--

LOCK TABLES `dict_states` WRITE;
/*!40000 ALTER TABLE `dict_states` DISABLE KEYS */;
/*!40000 ALTER TABLE `dict_states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dict_structure`
--

DROP TABLE IF EXISTS `dict_structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_structure` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `shortName` varchar(45) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `type` enum('AK','OST','UMN','NPS') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict_structure`
--

LOCK TABLES `dict_structure` WRITE;
/*!40000 ALTER TABLE `dict_structure` DISABLE KEYS */;
INSERT INTO `dict_structure` VALUES (1,'ПАО \"Транснефть\"','ПАО \"Транснефть\"',NULL,'AK'),(2,'АО \"Транснефть - Прикамье\"','ТПК',1,'OST'),(3,'Альметьевское РНУ','АРНУ',2,'UMN'),(4,'Казанское РНУ','КРНУ',2,'UMN'),(5,'Пермское РНУ','ПРНУ',2,'UMN'),(6,'Ромашкинское РНУ','РРНУ',2,'UMN'),(7,'Удмуртское РНУ','УРНУ',2,'UMN');
/*!40000 ALTER TABLE `dict_structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dict_umn`
--

DROP TABLE IF EXISTS `dict_umn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dict_umn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `shortname` varchar(45) NOT NULL,
  `ost` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `shortname_UNIQUE` (`shortname`),
  KEY `ost_idx` (`ost`),
  CONSTRAINT `ost` FOREIGN KEY (`ost`) REFERENCES `dict_ost` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict_umn`
--

LOCK TABLES `dict_umn` WRITE;
/*!40000 ALTER TABLE `dict_umn` DISABLE KEYS */;
INSERT INTO `dict_umn` VALUES (1,'01','Альметьевское РНУ','АРНУ',1),(2,'02','Казанское РНУ','КРНУ',1),(3,'03','Пермское РНУ','РРНУ',1),(4,'04','Ромашкинское РНУ','ПРНУ',1),(5,'05','Удмуртское РНУ','УРНУ',1);
/*!40000 ALTER TABLE `dict_umn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ft`
--

DROP TABLE IF EXISTS `ft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ft` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ost` varchar(8) NOT NULL,
  `umn` varchar(8) NOT NULL,
  `nps` varchar(8) NOT NULL,
  `data` json DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ft`
--

LOCK TABLES `ft` WRITE;
/*!40000 ALTER TABLE `ft` DISABLE KEYS */;
INSERT INTO `ft` VALUES (1,'10','03','13',NULL),(2,'10','02','04',NULL);
/*!40000 ALTER TABLE `ft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mercadoria`
--

DROP TABLE IF EXISTS `mercadoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mercadoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(45) DEFAULT NULL,
  `quantidade` int(11) NOT NULL,
  `preco` double NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mercadoria`
--

LOCK TABLES `mercadoria` WRITE;
/*!40000 ALTER TABLE `mercadoria` DISABLE KEYS */;
INSERT INTO `mercadoria` VALUES (1,'werqwe','werweert',1,2,1);
/*!40000 ALTER TABLE `mercadoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prepare_ft`
--

DROP TABLE IF EXISTS `prepare_ft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prepare_ft` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ost` varchar(8) NOT NULL,
  `umn` varchar(8) NOT NULL,
  `nps` varchar(8) NOT NULL,
  `data` json DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prepare_ft`
--

LOCK TABLES `prepare_ft` WRITE;
/*!40000 ALTER TABLE `prepare_ft` DISABLE KEYS */;
INSERT INTO `prepare_ft` VALUES (1,'10','02','04','{\"desc\": \"werwer\", \"cause\": [{\"num\": \"1\", \"val\": \"Обоснование которое было добавленно ранее\"}, {\"num\": \"2\", \"val\": \"Обоснование которое было добавленно намного опзднее\"}, {\"num\": \"3\", \"val\": \"werwerwer\"}], \"target\": \"ft\", \"function\": \"step2\"}'),(2,'10','01','09','{\"desc\": \"erweqwefsdqfw\", \"cause\": [{\"num\": \"1\", \"val\": \"Обоснование которое было добавленно ранее\"}, {\"num\": \"2\", \"val\": \"Обоснование которое было добавленно намного опзднее\"}, {\"num\": \"3\", \"val\": \"asdasda\"}, {\"num\": \"4\", \"val\": \"wedwqeqwe\"}], \"target\": \"ft\", \"function\": \"step2\"}');
/*!40000 ALTER TABLE `prepare_ft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registry`
--

DROP TABLE IF EXISTS `registry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(45) NOT NULL,
  `object` json NOT NULL,
  `docs` json DEFAULT NULL,
  `dates` json DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number_UNIQUE` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registry`
--

LOCK TABLES `registry` WRITE;
/*!40000 ALTER TABLE `registry` DISABLE KEYS */;
INSERT INTO `registry` VALUES (1,'046','{\"nps\": \"37\", \"num\": \"046\", \"ost\": \"1\", \"umn\": \"4\", \"year\": \"2013\", \"month\": \"январь\"}','{\"cat\": {\"fr\": {\"FT\": {\"1\": \"FT_TPK-Romashkinskoe RNU-10-04-01-2013_yanvar-046_1.doc\", \"count\": 1}, \"ACT\": {\"1\": \"ACT_TPK-Romashkinskoe RNU-10-04-01-2013_yanvar-046_1.doc\", \"2\": \"ACT_TPK-Romashkinskoe RNU-10-04-01-2013_yanvar-046_2.doc\", \"count\": 2}, \"ASI\": {\"1\": \"ASI_TPK-Romashkinskoe RNU-10-04-01-2013_yanvar-046_1.doc\", \"2\": \"ASI_TPK-Romashkinskoe RNU-10-04-01-2013_yanvar-046_2.doc\", \"count\": 2}, \"PMI\": {\"1\": \"PMI_TPK-Romashkinskoe RNU-10-04-01-2013_yanvar-046_1.doc\", \"2\": \"PMI_TPK-Romashkinskoe RNU-10-04-01-2013_yanvar-046_2.doc\", \"count\": 2}, \"PSI\": {\"1\": \"PSI_TPK-Romashkinskoe RNU-10-04-01-2013_yanvar-046_1.doc\", \"2\": \"PSI_TPK-Romashkinskoe RNU-10-04-01-2013_yanvar-046_2.doc\", \"count\": 2}, \"PROTOKOL\": {\"1\": \"PROTOKOL_TPK-Romashkinskoe RNU-10-04-01-2013_yanvar-046_1.doc\", \"2\": \"PROTOKOL_TPK-Romashkinskoe RNU-10-04-01-2013_yanvar-046_2.doc\", \"count\": 2}, \"RATIONALE\": {\"1\": \"RATIONALE_TPK-Romashkinskoe RNU-10-04-01-2013_yanvar-046_1.pdf\", \"count\": 1}}}, \"raw\": [], \"count\": 12}',NULL,1),(2,'345','{\"nps\": \"43\", \"num\": \"345\", \"ost\": \"1\", \"umn\": \"4\", \"year\": \"2015\", \"month\": \"январь\"}','{\"cat\": {\"fr\": {\"ASI\": {\"1\": \"ASI_TPK-Romashkinskoe RNU-10-04-07-2015_yanvar-345_1.doc\", \"count\": 1}, \"PMI\": {\"1\": \"PMI_TPK-Romashkinskoe RNU-10-04-07-2015_yanvar-345_1.doc\", \"2\": \"PMI_TPK-Romashkinskoe RNU-10-04-07-2015_yanvar-345_2.doc\", \"count\": 2}}, \"scan\": {\"FT\": {\"1\": \"FT_TPK-Romashkinskoe RNU-10-04-07-2015_yanvar-345_1.pdf\", \"2\": \"FT_TPK-Romashkinskoe RNU-10-04-07-2015_yanvar-345_2.doc\", \"3\": \"FT_TPK-Romashkinskoe RNU-10-04-07-2015_yanvar-345_3.doc\", \"4\": \"FT_TPK-Romashkinskoe RNU-10-04-07-2015_yanvar-345_4.doc\", \"count\": 4}, \"ACT\": {\"1\": \"ACT_TPK-Romashkinskoe RNU-10-04-07-2015_yanvar-345_1.doc\", \"count\": 1}, \"ASI\": {\"1\": \"ASI_TPK-Romashkinskoe RNU-10-04-07-2015_yanvar-345_1.doc\", \"count\": 1}, \"PMI\": {\"1\": \"PMI_TPK-Romashkinskoe RNU-10-04-07-2015_yanvar-345_1.doc\", \"2\": \"PMI_TPK-Romashkinskoe RNU-10-04-07-2015_yanvar-345_2.doc\", \"count\": 2}, \"PSI\": {\"1\": \"PSI_TPK-Romashkinskoe RNU-10-04-07-2015_yanvar-345_1.doc\", \"count\": 1}}}, \"raw\": {\"13\": {\"id\": 13, \"tmpname\": \"6616dd3e6512b0a3058d9a5f58fffa8c\", \"uplname\": \"3BSE006712R0001_A_en.pdf\"}, \"14\": {\"id\": 14, \"tmpname\": \"4cdd004dd3357425a752296474abcd73\", \"uplname\": \"3BSE000506-600_-_en_Advant_Fieldbus_100_User_Manual.pdf\"}, \"15\": {\"id\": 15, \"tmpname\": \"89bdd3a1bab4623f90ba79be4c475e1a\", \"uplname\": \"3BUR000874R301-users-guide-for-ac70.pdf\"}}, \"count\": 15}',NULL,1),(3,'324','{\"nps\": \"39\", \"num\": \"324\", \"ost\": \"1\", \"umn\": \"4\", \"year\": \"2015\", \"month\": \"январь\"}','{\"cat\": {\"fr\": {\"ACT\": {\"1\": \"ACT_TPK-Romashkinskoe RNU-10-04-03-2015_yanvar-324_1.doc\", \"2\": \"ACT_TPK-Romashkinskoe RNU-10-04-03-2015_yanvar-324_2.doc\", \"3\": \"ACT_TPK-Romashkinskoe RNU-10-04-03-2015_yanvar-324_3.doc\", \"count\": 3}, \"PMI\": {\"1\": \"PMI_TPK-Romashkinskoe RNU-10-04-03-2015_yanvar-324_1.doc\", \"count\": 1}, \"PSI\": {\"1\": \"PSI_TPK-Romashkinskoe RNU-10-04-03-2015_yanvar-324_1.doc\", \"count\": 1}, \"OTHER\": {\"1\": \"OTHER_TPK-Romashkinskoe RNU-10-04-03-2015_yanvar-324_1.vsd\", \"2\": \"OTHER_TPK-Romashkinskoe RNU-10-04-03-2015_yanvar-324_2.doc\", \"count\": 2}, \"PROTOKOL\": {\"1\": \"PROTOKOL_TPK-Romashkinskoe RNU-10-04-03-2015_yanvar-324_1.doc\", \"count\": 1}}, \"scan\": {\"PMI\": {\"1\": \"PMI_TPK-Romashkinskoe RNU-10-04-03-2015_yanvar-324_1.doc\", \"count\": 1}}}, \"raw\": [], \"count\": 9}',NULL,1),(4,'456','{\"nps\": \"52\", \"num\": \"456\", \"ost\": \"1\", \"umn\": \"5\", \"year\": \"2015\", \"month\": \"январь\"}','{\"cat\": {\"fr\": {\"FT\": {\"1\": \"FT_TPK-Udmurtskoe RNU-10-05-05-2015_yanvar-456_1.doc\", \"count\": 1}, \"ASI\": {\"1\": \"ASI_TPK-Udmurtskoe RNU-10-05-05-2015_yanvar-456_1.doc\", \"count\": 1}, \"PMI\": {\"1\": \"PMI_TPK-Udmurtskoe RNU-10-05-05-2015_yanvar-456_1.doc\", \"count\": 1}, \"PSI\": {\"1\": \"PSI_TPK-Udmurtskoe RNU-10-05-05-2015_yanvar-456_1.vsd\", \"count\": 1}}, \"scan\": {\"FT\": {\"1\": \"FT_TPK-Udmurtskoe RNU-10-05-05-2015_yanvar-456_1.doc\", \"count\": 1}, \"ACT\": {\"1\": \"ACT_TPK-Udmurtskoe RNU-10-05-05-2015_yanvar-456_1.doc\", \"count\": 1}, \"ASI\": {\"1\": \"ASI_TPK-Udmurtskoe RNU-10-05-05-2015_yanvar-456_1.doc\", \"2\": \"ASI_TPK-Udmurtskoe RNU-10-05-05-2015_yanvar-456_2.doc\", \"count\": 2}, \"NOTITY\": {\"1\": \"NOTITY_TPK-Udmurtskoe RNU-10-05-05-2015_yanvar-456_1.doc\", \"count\": 1}}}, \"raw\": [], \"count\": 9}',NULL,1),(5,'867','{\"nps\": \"41\", \"num\": \"867\", \"ost\": \"1\", \"umn\": \"4\", \"year\": \"2015\", \"month\": \"январь\"}','{\"cat\": {\"fr\": {\"ACT\": {\"1\": \"ACT_TPK-Romashkinskoe RNU-10-04-05-2015_yanvar-867_1.doc\", \"count\": 1}, \"ASI\": {\"1\": \"ASI_TPK-Romashkinskoe RNU-10-04-05-2015_yanvar-867_1.vsd\", \"2\": \"ASI_TPK-Romashkinskoe RNU-10-04-05-2015_yanvar-867_2.doc\", \"3\": \"ASI_TPK-Romashkinskoe RNU-10-04-05-2015_yanvar-867_3.doc\", \"4\": \"ASI_TPK-Romashkinskoe RNU-10-04-05-2015_yanvar-867_4.doc\", \"count\": 4}, \"PMI\": {\"1\": \"PMI_TPK-Romashkinskoe RNU-10-04-05-2015_yanvar-867_1.doc\", \"count\": 1}, \"ACTPP\": {\"1\": \"ACTPP_TPK-Romashkinskoe RNU-10-04-05-2015_yanvar-867_1.doc\", \"2\": \"ACTPP_TPK-Romashkinskoe RNU-10-04-05-2015_yanvar-867_2.doc\", \"count\": 2}}, \"scan\": {\"PMI\": {\"1\": \"PMI_TPK-Romashkinskoe RNU-10-04-05-2015_yanvar-867_1.doc\", \"count\": 1}}}, \"raw\": [], \"count\": 9}',NULL,1),(6,'978','{\"nps\": \"53\", \"num\": \"978\", \"ost\": \"1\", \"umn\": \"5\", \"year\": \"2015\", \"month\": \"январь\"}','{\"cat\": {\"fr\": {\"FT\": {\"1\": \"FT_TPK-Udmurtskoe RNU-10-05-06-2015_yanvar-978_1.doc\", \"count\": 1}, \"ASI\": {\"1\": \"ASI_TPK-Udmurtskoe RNU-10-05-06-2015_yanvar-978_1.doc\", \"count\": 1}, \"PSI\": {\"1\": \"PSI_TPK-Udmurtskoe RNU-10-05-06-2015_yanvar-978_1.doc\", \"count\": 1}, \"ACTPP\": {\"1\": \"ACTPP_TPK-Udmurtskoe RNU-10-05-06-2015_yanvar-978_1.doc\", \"count\": 1}, \"OTHER\": {\"1\": \"OTHER_TPK-Udmurtskoe RNU-10-05-06-2015_yanvar-978_1.doc\", \"count\": 1}, \"RATIONALE\": {\"1\": \"RATIONALE_TPK-Udmurtskoe RNU-10-05-06-2015_yanvar-978_1.doc\", \"count\": 1}}, \"scan\": {\"FT\": {\"1\": \"FT_TPK-Udmurtskoe RNU-10-05-06-2015_yanvar-978_1.doc\", \"count\": 1}, \"PMI\": {\"1\": \"PMI_TPK-Udmurtskoe RNU-10-05-06-2015_yanvar-978_1.doc\", \"2\": \"PMI_TPK-Udmurtskoe RNU-10-05-06-2015_yanvar-978_2.vsd\", \"count\": 2}}}, \"raw\": [], \"count\": 9}',NULL,1),(7,'678','{\"nps\": \"17\", \"num\": \"678\", \"ost\": \"1\", \"umn\": \"2\", \"year\": \"2014\", \"month\": \"сентябрь\"}','{\"cat\": {\"fr\": {\"ASI\": {\"1\": \"ASI_TPK-Kazanskoe RNU-10-02-04-2014_sentyabr-678_1.doc\", \"2\": \"ASI_TPK-Kazanskoe RNU-10-02-04-2014_sentyabr-678_2.vsd\", \"count\": 2}}, \"scan\": {\"PMI\": {\"1\": \"PMI_TPK-Kazanskoe RNU-10-02-04-2014_sentyabr-678_1.doc\", \"count\": 1}, \"PROTOKOL\": {\"1\": \"PROTOKOL_TPK-Kazanskoe RNU-10-02-04-2014_sentyabr-678_1.doc\", \"count\": 1}}}, \"raw\": {\"1\": {\"id\": 1, \"tmpname\": \"6e30276c5cc34b7a65ba55ba53fefdd1\", \"uplname\": \"Акт работ Елизаветинка 14_05_14.doc\"}, \"3\": {\"id\": 3, \"tmpname\": \"282036b9c42c8f2a298bf0f9eff6fc28\", \"uplname\": \"Акт_внедрения_ПО.doc\"}, \"5\": {\"id\": 5, \"tmpname\": \"76fe84f64eee4c2e3aaf9463d964b720\", \"uplname\": \"ПМИ.doc\"}, \"6\": {\"id\": 6, \"tmpname\": \"9b0d4340b77cea54caa1b43ec405ce88\", \"uplname\": \"Протокол_внедрения.doc\"}, \"7\": {\"id\": 7, \"tmpname\": \"cfcaafe5a1b9fdeaa4a83cc496da3efd\", \"uplname\": \"Протокол_стенд_испытаний.doc\"}}, \"count\": 9}',NULL,1),(8,'9986','{\"nps\": \"22\", \"num\": \"9986\", \"ost\": \"1\", \"umn\": \"3\", \"year\": \"2015\", \"month\": \"январь\"}',NULL,NULL,1),(9,'111','{\"nps\": \"1\", \"num\": \"111\", \"ost\": \"1\", \"umn\": \"1\", \"year\": \"2015\", \"month\": \"январь\"}',NULL,NULL,1),(10,'11','{\"nps\": \"1\", \"num\": \"11\", \"ost\": \"1\", \"umn\": \"1\", \"year\": \"2015\", \"month\": \"январь\"}',NULL,NULL,1),(11,'9988','{\"nps\": \"53\", \"num\": \"9988\", \"ost\": \"1\", \"umn\": \"5\", \"year\": \"2016\", \"month\": \"ноябрь\"}','{\"cat\": {\"fr\": {\"FT\": {\"1\": \"FT_TPK-Udmurtskoe RNU-10-05-06-2016_noyabr-9988_1.doc\", \"count\": 1}, \"PMI\": {\"1\": \"PMI_TPK-Udmurtskoe RNU-10-05-06-2016_noyabr-9988_1.doc\", \"2\": \"PMI_TPK-Udmurtskoe RNU-10-05-06-2016_noyabr-9988_2.doc\", \"3\": \"PMI_TPK-Udmurtskoe RNU-10-05-06-2016_noyabr-9988_3.vsd\", \"4\": \"PMI_TPK-Udmurtskoe RNU-10-05-06-2016_noyabr-9988_4.doc\", \"5\": \"PMI_TPK-Udmurtskoe RNU-10-05-06-2016_noyabr-9988_5.doc\", \"6\": \"PMI_TPK-Udmurtskoe RNU-10-05-06-2016_noyabr-9988_6.doc\", \"7\": \"PMI_TPK-Udmurtskoe RNU-10-05-06-2016_noyabr-9988_7.doc\", \"count\": 7}}, \"scan\": {\"FT\": {\"1\": \"FT_TPK-Udmurtskoe RNU-10-05-06-2016_noyabr-9988_1.doc\", \"count\": 1}}}, \"raw\": [], \"count\": 9}',NULL,1),(12,'11111','{\"nps\": \"1\", \"num\": \"11111\", \"ost\": \"1\", \"umn\": \"1\", \"year\": \"2015\", \"month\": \"январь\"}','{\"cat\": {\"fr\": {\"ASI\": {\"1\": \"ASI_TPK-Almetevskoe RNU-10-01-01-2015_yanvar-11111_1.doc\", \"count\": 1}, \"PMI\": {\"1\": \"PMI_TPK-Almetevskoe RNU-10-01-01-2015_yanvar-11111_1.doc\", \"count\": 1}, \"PROTOKOL\": {\"1\": \"PROTOKOL_TPK-Almetevskoe RNU-10-01-01-2015_yanvar-11111_1.doc\", \"count\": 1}}, \"scan\": {\"FT\": {\"1\": \"FT_TPK-Almetevskoe RNU-10-01-01-2015_yanvar-11111_1.doc\", \"count\": 1}, \"ACT\": {\"1\": \"ACT_TPK-Almetevskoe RNU-10-01-01-2015_yanvar-11111_1.doc\", \"2\": \"ACT_TPK-Almetevskoe RNU-10-01-01-2015_yanvar-11111_2.doc\", \"count\": 2}, \"ASI\": {\"1\": \"ASI_TPK-Almetevskoe RNU-10-01-01-2015_yanvar-11111_1.doc\", \"count\": 1}, \"PSI\": {\"1\": \"PSI_TPK-Almetevskoe RNU-10-01-01-2015_yanvar-11111_1.doc\", \"count\": 1}}}, \"raw\": {\"4\": {\"id\": 4, \"tmpname\": \"fa1cff55c5c4e5a278e2266c1c1f8df0\", \"uplname\": \"Акт_стендовых_испытаний.doc\"}, \"5\": {\"id\": 5, \"tmpname\": \"62a8e6dc45777bc857db7991cc3294b7\", \"uplname\": \"ПМИ.doc\"}, \"6\": {\"id\": 6, \"tmpname\": \"ca50095f3314c14f5cb7f9de8354d98a\", \"uplname\": \"Протокол_внедрения.doc\"}, \"8\": {\"id\": 8, \"tmpname\": \"b682893272a06f3d2eb2dc1ad80604b5\", \"uplname\": \"статья 1.doc\"}, \"9\": {\"id\": 9, \"tmpname\": \"3802318b35d96ad891e603dfa791090a\", \"uplname\": \"структурка_стенд_ОСПАС_белая.vsd\"}, \"11\": {\"id\": 11, \"tmpname\": \"89ae0804193c55d93acab87daf392082\", \"uplname\": \"Акт работ калейкино-29-08-2014.doc\"}, \"12\": {\"id\": 12, \"tmpname\": \"51d2965ac7c00586dc77e429acef953e\", \"uplname\": \"Акт_внедрения_ПО.doc\"}, \"13\": {\"id\": 13, \"tmpname\": \"754723d4854896935418d7522f91472f\", \"uplname\": \"Акт_стендовых_испытаний.doc\"}, \"14\": {\"id\": 14, \"tmpname\": \"755a9ec38124c70db623d07b73b35bfd\", \"uplname\": \"ПМИ.doc\"}, \"16\": {\"id\": 16, \"tmpname\": \"59e70d8f90e6188593c0e2f109d5206b\", \"uplname\": \"Протокол_стенд_испытаний.doc\"}, \"17\": {\"id\": 17, \"tmpname\": \"7066341a62298b239a50302d5918cb26\", \"uplname\": \"статья 1.doc\"}, \"18\": {\"id\": 18, \"tmpname\": \"c336833dff2d4ebba6ff8cf175f61e7c\", \"uplname\": \"структурка_стенд_ОСПАС_белая.vsd\"}, \"20\": {\"id\": 20, \"tmpname\": \"73b03471eb72630581937582de643e67\", \"uplname\": \"Акт работ калейкино-29-08-2014.doc\"}, \"21\": {\"id\": 21, \"tmpname\": \"795e81f4b2f073b6c46a48d62d988e06\", \"uplname\": \"Акт_внедрения_ПО.doc\"}, \"22\": {\"id\": 22, \"tmpname\": \"a4dc19dec5a37d835f8ef8aff44d6603\", \"uplname\": \"Акт_стендовых_испытаний.doc\"}, \"23\": {\"id\": 23, \"tmpname\": \"060658e591145674bd5191a722f8b95d\", \"uplname\": \"ПМИ.doc\"}, \"24\": {\"id\": 24, \"tmpname\": \"dda587f88d552e48f642a348ab53759b\", \"uplname\": \"Протокол_внедрения.doc\"}, \"26\": {\"id\": 26, \"tmpname\": \"5720bf82664c8938b22260d7698bc448\", \"uplname\": \"статья 1.doc\"}, \"27\": {\"id\": 27, \"tmpname\": \"36e5761b5a7ba7aa6ed0cce55e64208a\", \"uplname\": \"структурка_стенд_ОСПАС_белая.vsd\"}}, \"count\": 27}',NULL,1),(13,'42345','{\"nps\": \"1\", \"num\": \"42345\", \"ost\": \"1\", \"umn\": \"1\", \"year\": \"2013\", \"month\": \"июль\"}','{\"cat\": {\"fr\": {\"FT\": {\"1\": \"FT_TPK-Almetevskoe RNU-10-01-01-2013_iyul-42345_1.doc\", \"count\": 1}, \"ACT\": {\"1\": \"ACT_TPK-Almetevskoe RNU-10-01-01-2013_iyul-42345_1.doc\", \"count\": 1}, \"PMI\": {\"1\": \"PMI_TPK-Almetevskoe RNU-10-01-01-2013_iyul-42345_1.doc\", \"count\": 1}, \"NOTITY\": {\"1\": \"NOTITY_TPK-Almetevskoe RNU-10-01-01-2013_iyul-42345_1.doc\", \"count\": 1}}, \"scan\": {\"ACT\": {\"1\": \"ACT_TPK-Almetevskoe RNU-10-01-01-2013_iyul-42345_1.doc\", \"count\": 1}, \"PMI\": {\"1\": \"PMI_TPK-Almetevskoe RNU-10-01-01-2013_iyul-42345_1.doc\", \"count\": 1}, \"PROTOKOL\": {\"1\": \"PROTOKOL_TPK-Almetevskoe RNU-10-01-01-2013_iyul-42345_1.doc\", \"count\": 1}}}, \"raw\": {\"6\": {\"id\": 6, \"tmpname\": \"e460500dc60e5e7e4d6f8e368ad8c177\", \"uplname\": \"Протокол_внедрения.doc\"}, \"9\": {\"id\": 9, \"tmpname\": \"21dfd1d1b2cd78d6db2621dbea3795dc\", \"uplname\": \"структурка_стенд_ОСПАС_белая.vsd\"}}, \"count\": 9}',NULL,1);
/*!40000 ALTER TABLE `registry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `structs`
--

DROP TABLE IF EXISTS `structs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `structs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(45) DEFAULT NULL,
  `data` json NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `structs`
--

LOCK TABLES `structs` WRITE;
/*!40000 ALTER TABLE `structs` DISABLE KEYS */;
INSERT INTO `structs` VALUES (1,'структура ТПК','{\"code\": \"10\", \"href\": \"javascript:changeList(\\\"АО Транснефть - Прикамье\\\");\", \"text\": \"АО Транснефть - Прикамье\", \"nodes\": [{\"code\": \"01\", \"href\": \"javascript:changeList(\\\"Альметьевское РНУ\\\");\", \"text\": \"Альметьевское РНУ\", \"nodes\": [{\"code\": \"01\", \"href\": \"javascript:changeList(\\\"НПС Азнакаево-1\\\");\", \"text\": \"НПС Азнакаево-1\"}, {\"code\": \"02\", \"href\": \"javascript:changeList(\\\"НПС Азнакаево-2\\\")\", \"text\": \"НПС Азнакаево-2\"}, {\"code\": \"03\", \"href\": \"javascript:changeList(\\\"НПС Белая\\\")\", \"text\": \"НПС Белая\"}, {\"code\": \"04\", \"href\": \"javascript:changeList(\\\"НПС Карабаш\\\")\", \"text\": \"НПС Карабаш\"}, {\"code\": \"05\", \"href\": \"javascript:changeList(\\\"НПС Михайловка-2\\\")\", \"text\": \"НПС Михайловка-2\"}, {\"code\": \"06\", \"href\": \"javascript:changeList(\\\"НПС Михайловка-3\\\")\", \"text\": \"НПС Михайловка-3\"}, {\"code\": \"07\", \"href\": \"javascript:changeList(\\\"НПС Муслюмово\\\")\", \"text\": \"НПС Муслюмово\"}, {\"code\": \"08\", \"href\": \"javascript:changeList(\\\"НПС Набережные Челны-1\\\")\", \"text\": \"НПС Набережные Челны-1\"}, {\"code\": \"09\", \"href\": \"javascript:changeList(\\\"НПС Набережные Челны-2\\\")\", \"text\": \"НПС Набережные Челны-2\"}, {\"code\": \"10\", \"href\": \"javascript:changeList(\\\"НПС-3 н/н №1\\\")\", \"text\": \"НПС-3 н/н №1\"}, {\"code\": \"11\", \"href\": \"javascript:changeList(\\\"НПС-3 н/н №2\\\")\", \"text\": \"НПС-3 н/н №2\"}, {\"code\": \"12\", \"href\": \"javascript:changeList(\'ПСП \\\"Нижнекамск\\\" №495\');\", \"text\": \"ПСП \\\"Нижнекамск\\\" №495\"}, {\"code\": \"13\", \"href\": \"javascript:changeList(\'ПСП \\\"Нижнекамск\\\" НПЗ\');\", \"text\": \"ПСП \\\"Нижнекамск\\\" НПЗ\"}]}, {\"code\": \"02\", \"href\": \"javascript:changeList(\'Казанское РНУ\');\", \"text\": \"Казанское РНУ\", \"nodes\": [{\"code\": \"01\", \"href\": \"javascript:changeList(\'НПС Ковали-2\');\", \"text\": \"НПС Ковали-2\"}, {\"code\": \"02\", \"href\": \"javascript:changeList(\'НПС Ковали-3\');\", \"text\": \"НПС Ковали-3\"}, {\"code\": \"03\", \"href\": \"javascript:changeList(\'НПС Лазарево-1\');\", \"text\": \"НПС Лазарево-1\"}, {\"code\": \"04\", \"href\": \"javascript:changeList(\'НПС Лазарево-2\');\", \"text\": \"НПС Лазарево-2\"}, {\"code\": \"05\", \"href\": \"javascript:changeList(\'НПС Студенец\');\", \"text\": \"НПС Студенец\"}, {\"code\": \"06\", \"href\": \"javascript:changeList(\'НПС Тиньговатово-2\');\", \"text\": \"НПС Тиньговатово-2\"}, {\"code\": \"07\", \"href\": \"javascript:changeList(\'НПС Тиньговатово-3\');\", \"text\": \"НПС Тиньговатово-3\"}]}, {\"code\": \"03\", \"href\": \"javascript:changeList(\'Пермское РНУ\');\", \"text\": \"Пермское РНУ\", \"nodes\": [{\"code\": \"01\", \"href\": \"javascript:changeList(\'НПС Арбатская-1\');\", \"text\": \"НПС Арбатская-1\"}, {\"code\": \"02\", \"href\": \"javascript:changeList(\'НПС Арбатская-2\');\", \"text\": \"НПС Арбатская-2\"}, {\"code\": \"03\", \"href\": \"javascript:changeList(\'НПС Арбатская-2\');\", \"text\": \"НПС Бисер\"}, {\"code\": \"04\", \"href\": \"javascript:changeList(\'НПС Лысьва-1\');\", \"text\": \"НПС Лысьва-1\"}, {\"code\": \"05\", \"href\": \"javascript:changeList(\'НПС Лысьва-2\');\", \"text\": \"НПС Лысьва-2\"}, {\"code\": \"06\", \"href\": \"javascript:changeList(\'НПС Мостовая\');\", \"text\": \"НПС Мостовая\"}, {\"code\": \"07\", \"href\": \"javascript:changeList(\'НПС Оса-1\');\", \"text\": \"НПС Оса-1\"}, {\"code\": \"08\", \"href\": \"javascript:changeList(\'НПС Оса-2\');\", \"text\": \"НПС Оса-2\"}, {\"code\": \"09\", \"href\": \"javascript:changeList(\'НПС Пермь-1\');\", \"text\": \"НПС Пермь-1\"}, {\"code\": \"10\", \"href\": \"javascript:changeList(\'НПС Пермь-2\');\", \"text\": \"НПС Пермь-2\"}, {\"code\": \"11\", \"href\": \"javascript:changeList(\'НПС Пермь-3\');\", \"text\": \"НПС Пермь-3\"}, {\"code\": \"12\", \"href\": \"javascript:changeList(\'НПС Пермь-4\');\", \"text\": \"НПС Пермь-4\"}, {\"code\": \"13\", \"href\": \"javascript:changeList(\'НПС Платина-1\');\", \"text\": \"НПС Платина-1\"}, {\"code\": \"14\", \"href\": \"javascript:changeList(\'НПС Платина-2\');\", \"text\": \"НПС Платина-2\"}, {\"code\": \"15\", \"href\": \"javascript:changeList(\'НПС Полазна\');\", \"text\": \"НПС Полазна\"}, {\"code\": \"16\", \"href\": \"javascript:changeList(\'НПС Полазна\');\", \"text\": \"НПС Уральская\"}]}, {\"code\": \"04\", \"href\": \"javascript:changeList(\'Ромашкинское РНУ\');\", \"text\": \"Ромашкинское РНУ\", \"nodes\": [{\"code\": \"01\", \"href\": \"javascript:changeList(\'НПС Байтуган\');\", \"text\": \"НПС Байтуган\"}, {\"code\": \"02\", \"href\": \"javascript:changeList(\'НПС Елизаветинка-1\');\", \"text\": \"НПС Елизаветинка-1\"}, {\"code\": \"03\", \"href\": \"javascript:changeList(\'НПС Елизаветинка-2\');\", \"text\": \"НПС Елизаветинка-2\"}, {\"code\": \"04\", \"href\": \"javascript:changeList(\'НПС Елизаветинка-3\');\", \"text\": \"НПС Елизаветинка-3\"}, {\"code\": \"05\", \"href\": \"javascript:changeList(\'НПС Елизаветинка-4\');\", \"text\": \"НПС Елизаветинка-4\"}, {\"code\": \"06\", \"href\": \"javascript:changeList(\'НПС Калейкино-1\');\", \"text\": \"НПС Калейкино-1\"}, {\"code\": \"07\", \"href\": \"javascript:changeList(\'НПС Калейкино-2\');\", \"text\": \"НПС Калейкино-2\"}, {\"code\": \"08\", \"href\": \"javascript:changeList(\'НПС Калейкино-3\');\", \"text\": \"НПС Калейкино-3\"}, {\"code\": \"09\", \"href\": \"javascript:changeList(\'НПС Калейкино-4\');\", \"text\": \"НПС Калейкино-4\"}, {\"code\": \"10\", \"href\": \"javascript:changeList(\'НПС Калейкино-5\');\", \"text\": \"НПС Калейкино-5\"}, {\"code\": \"11\", \"href\": \"javascript:changeList(\'НПС Калиновый ключ\');\", \"text\": \"НПС Калиновый ключ\"}]}, {\"code\": \"05\", \"href\": \"javascript:changeList(\'Удмуртское РНУ\');\", \"text\": \"Удмуртское РНУ\", \"nodes\": [{\"code\": \"01\", \"href\": \"javascript:changeList(\'НПС Артель\');\", \"text\": \"НПС Артель\"}, {\"code\": \"02\", \"href\": \"javascript:changeList(\'НПС Большая Соснова\');\", \"text\": \"НПС Большая Соснова\"}, {\"code\": \"03\", \"href\": \"javascript:changeList(\'НПС Дебесы\');\", \"text\": \"НПС Дебесы\"}, {\"code\": \"04\", \"href\": \"javascript:changeList(\'НПС Киенгоп\');\", \"text\": \"НПС Киенгоп\"}, {\"code\": \"05\", \"href\": \"javascript:changeList(\'НПС Малая Пурга\');\", \"text\": \"НПС Малая Пурга\"}, {\"code\": \"06\", \"href\": \"javascript:changeList(\'НПС Сюмси\');\", \"text\": \"НПС Сюмси\"}]}]}');
/*!40000 ALTER TABLE `structs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id_idx` (`parent_id`),
  CONSTRAINT `parent_id` FOREIGN KEY (`parent_id`) REFERENCES `test` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tn_objects`
--

DROP TABLE IF EXISTS `tn_objects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tn_objects` (
  `code` varchar(8) NOT NULL,
  `struct` json NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tn_objects`
--

LOCK TABLES `tn_objects` WRITE;
/*!40000 ALTER TABLE `tn_objects` DISABLE KEYS */;
/*!40000 ALTER TABLE `tn_objects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `password` varchar(32) NOT NULL,
  `hash` varchar(32) DEFAULT NULL,
  `employee` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'ospas','9886293b94403edb2fb1670a0017be95','7499d30a6a2aa4155af7767cb5c4c56e',NULL);
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

-- Dump completed on 2017-01-13 15:04:49
