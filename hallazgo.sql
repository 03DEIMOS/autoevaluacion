/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50500
Source Host           : localhost:3306
Source Database       : autoeval

Target Server Type    : MYSQL
Target Server Version : 50500
File Encoding         : 65001

Date: 2022-05-15 21:20:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hallazgo
-- ----------------------------
DROP TABLE IF EXISTS `hallazgo`;
CREATE TABLE `hallazgo` (
  `idhallazgo` int(11) NOT NULL AUTO_INCREMENT,
  `hallazgo` varchar(3000) DEFAULT NULL,
  `factor` varchar(45) DEFAULT NULL,
  `proceso_id` int(11) NOT NULL,
  `caracteristica_id` int(11) NOT NULL,
  `eje` varchar(50) DEFAULT NULL,
  `linea_accion` varchar(255) DEFAULT NULL,
  `estado` varchar(40) DEFAULT NULL,
  `responsable` varchar(255) DEFAULT NULL,
  `fecha_inicio` varchar(10) DEFAULT NULL,
  `fecha_fin` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idhallazgo`),
  KEY `fk_hallazgo_proceso1` (`proceso_id`),
  KEY `fk_hallazgo_caracteristica1` (`caracteristica_id`),
  CONSTRAINT `fk_hallazgo_caracteristica1` FOREIGN KEY (`caracteristica_id`) REFERENCES `caracteristica` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hallazgo_proceso1` FOREIGN KEY (`proceso_id`) REFERENCES `proceso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hallazgo
-- ----------------------------
INSERT INTO `hallazgo` VALUES ('2', 'Ampliar los procesos de divulgación del PDI para lograr una mayor apropiación y participación de la comunidad educativa en el logro de sus objetivos.', '1', '1', '172', 'Eje 4', 'Fortalecer la participación de la comunidad educativa en los organismos previstos en la normatividad institucional y ampliar los mecanismos para consultar sus percepciones como un medio para alimentar la toma de decisiones', 'A', 'Dirección de Planeación y Gestión de la Calidad ', null, null);
INSERT INTO `hallazgo` VALUES ('3', 'Evaluar la pertinencia del Proyecto Educativo Institucional- PEI de cara a los ajustes curriculares realizados a los programas académicos', '1', '2', '172', 'Eje 2', 'Adoptar políticas que regulen la práctica académica para sostener una alta calidad de los programas y garantizar la suficiencia de recursos académicos para su desarrollo.', 'A', 'Vicerrectoría Académica', null, null);
