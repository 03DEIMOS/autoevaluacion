/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50500
Source Host           : localhost:3306
Source Database       : autoeval

Target Server Type    : MYSQL
Target Server Version : 50500
File Encoding         : 65001

Date: 2022-06-05 21:44:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for seguimiento
-- ----------------------------
DROP TABLE IF EXISTS `seguimiento`;
CREATE TABLE `seguimiento` (
  `idseguimiento` int(11) NOT NULL AUTO_INCREMENT,
  `oportunidad_mejora_id` int(11) NOT NULL,
  `fecha_programado` varchar(10) DEFAULT NULL,
  `fecha_realizado` varchar(10) DEFAULT NULL,
  `porcentaje_avance` int(11) DEFAULT NULL,
  `avances` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`idseguimiento`),
  KEY `fk_seguimiento_opotunidadMejora1` (`oportunidad_mejora_id`) USING BTREE,
  CONSTRAINT `FK_HALLAZGO_ID` FOREIGN KEY (`oportunidad_mejora_id`) REFERENCES `hallazgo` (`idhallazgo`)
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seguimiento
-- ----------------------------
INSERT INTO `seguimiento` VALUES ('174', '7', '05/06/2022', '05/06/2022', '10', 'Avance de Prueba');
INSERT INTO `seguimiento` VALUES ('175', '7', '05/06/2022', '', '0', 'Avance de prueba 2');
