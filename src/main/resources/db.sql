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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict_department`
--

LOCK TABLES `dict_department` WRITE;
/*!40000 ALTER TABLE `dict_department` DISABLE KEYS */;
INSERT INTO `dict_department` VALUES (1,'Отдел сопровождения программно-аппаратных средств МПСА и ТМ','ОСПАС',NULL,2),(2,'Отдел автоматизирванны средств управления технологическим процессом','О АСУТП',NULL,2),(3,'Отдел автоматизированных средств управления технологическим процессом','О АСУТП',NULL,6),(4,'Участок ремонта и технического обслуживания систем автоматики','УРТОСА',3,6),(5,'Участок ремонта и технического обслуживания телемеханики','УРТОТМ',3,6),(6,'Сектор программирования','Сектор программирования',1,2),(7,'Сектор документирования','Сектор документирования',1,2),(8,'Участок эксплуатации систем автоматизации','УЭСА',3,19),(9,'wqq','wqwq',3,19),(10,'УЭСА','УЭСА',3,6),(11,'УЭСА','УЭСА',NULL,19),(12,'УЭСА','УЭСА',NULL,20);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict_employee`
--

LOCK TABLES `dict_employee` WRITE;
/*!40000 ALTER TABLE `dict_employee` DISABLE KEYS */;
INSERT INTO `dict_employee` VALUES (1,'Китов','Антон','Александрович',6,5),(2,'Ракипов','Марат','Марсельевич',6,5),(3,'Бородин','Сергей','Николаевич',7,7);
/*!40000 ALTER TABLE `dict_employee` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict_rank`
--

LOCK TABLES `dict_rank` WRITE;
/*!40000 ALTER TABLE `dict_rank` DISABLE KEYS */;
INSERT INTO `dict_rank` VALUES (1,'Генеральный директор','Ген.директор'),(2,'Главный инженер','Гл.инженер'),(3,'Начальник','Начальник'),(4,'Заместитель начальника','Зам.начальника'),(5,'Ведущий инженер программист','Вед.инженер программист'),(6,'инженер программист 1 категории','инженер программист 1 кат.'),(7,'инженер по АСУП','инженер АСУП'),(8,'инженер по документированию','инженер по документированию');
/*!40000 ALTER TABLE `dict_rank` ENABLE KEYS */;
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
  `type` enum('AK','OST','UMN','NPS','OBJ') DEFAULT NULL,
  `code` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dict_structure`
--

LOCK TABLES `dict_structure` WRITE;
/*!40000 ALTER TABLE `dict_structure` DISABLE KEYS */;
INSERT INTO `dict_structure` VALUES (1,'ПАО \"Транснефть\"','ПАО \"Транснефть\"',NULL,'AK',NULL),(2,'АО \"Транснефть - Прикамье\"','ТПК',1,'OST','10'),(3,'Альметьевское РНУ','АРНУ',2,'UMN','01'),(4,'Казанское РНУ','КРНУ',2,'UMN','02'),(5,'Пермское РНУ','ПРНУ',2,'UMN','03'),(6,'Ромашкинское РНУ','РРНУ',2,'UMN','04'),(7,'Удмуртское РНУ','УРНУ',2,'UMN','05'),(8,'НПС \"Байтуган\"','НПС Байтуган',20,'OBJ','01'),(9,'НПС \"Елизаветинка-1\"','НПС Елизаветинка-1',20,'OBJ','02'),(10,'НПС \"Елизаветинка-2\"','НПС Елизаветинка-2',20,'OBJ','03'),(11,'НПС \"Елизаветинка-3\"','НПС Елизаветинка-3',20,'OBJ','04'),(12,'НПС \"Елизаветинка-4\"','НПС Елизаветинка-4',20,'OBJ','05'),(13,'НПС \"Калейкино-1\"','НПС Калейкино-1',19,'OBJ','06'),(14,'НПС \"Калейкино-2\"','НПС Калейкино-2',19,'OBJ','07'),(15,'НПС \"Калейкино-3\"','НПС Калейкино-3',19,'OBJ','08'),(16,'НПС \"Калейкино-4\"','НПС Калейкино-4',19,'OBJ','09'),(17,'НПС \"Калейкино-5\"','НПС Калейкино-5',19,'OBJ','10'),(18,'НПС \"Калиновый Ключ\"','НПС Калиновый Ключ',6,'NPS','11'),(19,'НПС \"Калейкино\"','НПС Калейкино',6,'NPS',NULL),(20,'НПС \"Елизаветинка\"','НПС Елизаветинка',6,'NPS',NULL),(23,'НПС \"Калиновый Ключ\"','НПС Калиновый Ключ',18,'OBJ','11'),(24,'НПС-3','НПС-3',3,'NPS',''),(25,'НПС-3','НПС-3',24,'OBJ','04');
/*!40000 ALTER TABLE `dict_structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_acceptor`
--

DROP TABLE IF EXISTS `fr_acceptor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_acceptor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL,
  `rank_id` int(11) NOT NULL,
  `department_id` int(11) NOT NULL,
  `funcrequirement_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_acceptor`
--

LOCK TABLES `fr_acceptor` WRITE;
/*!40000 ALTER TABLE `fr_acceptor` DISABLE KEYS */;
/*!40000 ALTER TABLE `fr_acceptor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_agreementor`
--

DROP TABLE IF EXISTS `fr_agreementor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_agreementor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL,
  `rank_id` int(11) NOT NULL,
  `department_id` int(11) NOT NULL,
  `order` int(11) NOT NULL,
  `funcRequirement_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_agreementor`
--

LOCK TABLES `fr_agreementor` WRITE;
/*!40000 ALTER TABLE `fr_agreementor` DISABLE KEYS */;
/*!40000 ALTER TABLE `fr_agreementor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_attachment`
--

DROP TABLE IF EXISTS `fr_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text,
  `filePath` varchar(255) DEFAULT NULL,
  `cause_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_attachment`
--

LOCK TABLES `fr_attachment` WRITE;
/*!40000 ALTER TABLE `fr_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `fr_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_cause`
--

DROP TABLE IF EXISTS `fr_cause`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_cause` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order` int(11) NOT NULL,
  `description` text NOT NULL,
  `funcrequirement_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_cause`
--

LOCK TABLES `fr_cause` WRITE;
/*!40000 ALTER TABLE `fr_cause` DISABLE KEYS */;
/*!40000 ALTER TABLE `fr_cause` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_checklevels_corrector`
--

DROP TABLE IF EXISTS `fr_checklevels_corrector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_checklevels_corrector` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `executorType_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_checklevels_corrector`
--

LOCK TABLES `fr_checklevels_corrector` WRITE;
/*!40000 ALTER TABLE `fr_checklevels_corrector` DISABLE KEYS */;
/*!40000 ALTER TABLE `fr_checklevels_corrector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_description`
--

DROP TABLE IF EXISTS `fr_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_description` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order` int(11) NOT NULL,
  `text` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_description`
--

LOCK TABLES `fr_description` WRITE;
/*!40000 ALTER TABLE `fr_description` DISABLE KEYS */;
/*!40000 ALTER TABLE `fr_description` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_executor_type`
--

DROP TABLE IF EXISTS `fr_executor_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_executor_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_executor_type`
--

LOCK TABLES `fr_executor_type` WRITE;
/*!40000 ALTER TABLE `fr_executor_type` DISABLE KEYS */;
INSERT INTO `fr_executor_type` VALUES (1,'силами специалистов ОСПАС'),(2,'силами специалистов АО \"Транснефть - Прикамье\"'),(3,'не требуется'),(4,'силами сторонней организации');
/*!40000 ALTER TABLE `fr_executor_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_funcrequirement`
--

DROP TABLE IF EXISTS `fr_funcrequirement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_funcrequirement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `date` date NOT NULL,
  `structure_id` int(11) NOT NULL,
  `shortDescription` varchar(255) DEFAULT NULL,
  `acceptor_id` int(11) DEFAULT NULL,
  `workExecutors_id` int(11) DEFAULT NULL,
  `notice_id` int(11) DEFAULT NULL,
  `limitation_id` int(11) DEFAULT NULL,
  `docs_id` int(11) DEFAULT NULL,
  `cause_id` int(11) DEFAULT NULL,
  `executors_id` int(11) DEFAULT NULL,
  `developmentDate` date DEFAULT NULL,
  `standTestDate` date DEFAULT NULL,
  `implementationDate` date DEFAULT NULL,
  `frFilePath` varchar(255) DEFAULT NULL,
  `pmiFilePath` varchar(255) DEFAULT NULL,
  `asiFilePath` varchar(255) DEFAULT NULL,
  `psiFilePath` varchar(255) DEFAULT NULL,
  `actFilePath` varchar(255) DEFAULT NULL,
  `protFilePath` varchar(255) DEFAULT NULL,
  `exchangeActFilePath` varchar(255) DEFAULT NULL,
  `noticeFilePath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_funcrequirement`
--

LOCK TABLES `fr_funcrequirement` WRITE;
/*!40000 ALTER TABLE `fr_funcrequirement` DISABLE KEYS */;
INSERT INTO `fr_funcrequirement` VALUES (1,13,'2017-01-23',13,'werqwewe',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2017-01-12','2017-01-23','2017-01-14',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,21312,'2017-01-13',15,'null12e12e1w',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,213,'2017-01-25',23,'1232131',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,213213,'2017-01-05',25,'цуйцуцйу',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `fr_funcrequirement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_kdcorrector`
--

DROP TABLE IF EXISTS `fr_kdcorrector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_kdcorrector` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `executorType_id` int(11) NOT NULL,
  `workExecutors_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_kdcorrector`
--

LOCK TABLES `fr_kdcorrector` WRITE;
/*!40000 ALTER TABLE `fr_kdcorrector` DISABLE KEYS */;
/*!40000 ALTER TABLE `fr_kdcorrector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_mpsacorrector`
--

DROP TABLE IF EXISTS `fr_mpsacorrector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_mpsacorrector` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `executorType_id` int(11) NOT NULL,
  `workExecutors_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_mpsacorrector`
--

LOCK TABLES `fr_mpsacorrector` WRITE;
/*!40000 ALTER TABLE `fr_mpsacorrector` DISABLE KEYS */;
/*!40000 ALTER TABLE `fr_mpsacorrector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_newtechdocsexecutor`
--

DROP TABLE IF EXISTS `fr_newtechdocsexecutor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_newtechdocsexecutor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `executorType_id` int(11) NOT NULL,
  `workExecutors_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_newtechdocsexecutor`
--

LOCK TABLES `fr_newtechdocsexecutor` WRITE;
/*!40000 ALTER TABLE `fr_newtechdocsexecutor` DISABLE KEYS */;
/*!40000 ALTER TABLE `fr_newtechdocsexecutor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_notice`
--

DROP TABLE IF EXISTS `fr_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_notice` (
  `id` int(11) NOT NULL,
  `funcrequirement_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_notice`
--

LOCK TABLES `fr_notice` WRITE;
/*!40000 ALTER TABLE `fr_notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `fr_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_noticetype`
--

DROP TABLE IF EXISTS `fr_noticetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_noticetype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_noticetype`
--

LOCK TABLES `fr_noticetype` WRITE;
/*!40000 ALTER TABLE `fr_noticetype` DISABLE KEYS */;
INSERT INTO `fr_noticetype` VALUES (1,'извещение об изменениях'),(2,'изменения в математическом обеспечении'),(3,'изменения в эксплуатационной документации'),(4,'контрольные экземпляры откорректированного ПО с указанием версии ПО, содержащий доработки');
/*!40000 ALTER TABLE `fr_noticetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_sdkucorrector`
--

DROP TABLE IF EXISTS `fr_sdkucorrector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_sdkucorrector` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `executorType_id` int(11) NOT NULL,
  `workExecutors_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_sdkucorrector`
--

LOCK TABLES `fr_sdkucorrector` WRITE;
/*!40000 ALTER TABLE `fr_sdkucorrector` DISABLE KEYS */;
/*!40000 ALTER TABLE `fr_sdkucorrector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_topagreementor`
--

DROP TABLE IF EXISTS `fr_topagreementor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_topagreementor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL,
  `rank_id` int(11) NOT NULL,
  `department_id` int(11) NOT NULL,
  `order` int(11) NOT NULL,
  `funcrequirement_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_topagreementor`
--

LOCK TABLES `fr_topagreementor` WRITE;
/*!40000 ALTER TABLE `fr_topagreementor` DISABLE KEYS */;
/*!40000 ALTER TABLE `fr_topagreementor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_workexecutors`
--

DROP TABLE IF EXISTS `fr_workexecutors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_workexecutors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `funcrequirement_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_workexecutors`
--

LOCK TABLES `fr_workexecutors` WRITE;
/*!40000 ALTER TABLE `fr_workexecutors` DISABLE KEYS */;
/*!40000 ALTER TABLE `fr_workexecutors` ENABLE KEYS */;
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

-- Dump completed on 2017-01-20 17:23:04
