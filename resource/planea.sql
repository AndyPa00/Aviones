DROP TABLE IF EXISTS `Puntuacion`;
DROP TABLE IF EXISTS `Piloto`;
DROP TABLE IF EXISTS `Grupo`;
DROP TABLE IF EXISTS `Manga`;
DROP TABLE IF EXISTS `Prueba`;
DROP TABLE IF EXISTS `Credencial`;

DROP TABLE IF EXISTS `Competicion`;
CREATE TABLE `Competicion` (
  `idCompeticion` int(11) NOT NULL AUTO_INCREMENT,
  `fechaInscripcion` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idCompeticion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Prueba` (
  `idPrueba` int(11) NOT NULL AUTO_INCREMENT,
  `idCompeticion` int(11) NOT NULL,
  `fechaPrueba` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idPrueba`),
  KEY `fk_idCompeticion_idx` (`idCompeticion`),
  CONSTRAINT `fk_idCompeticion` FOREIGN KEY (`idCompeticion`) REFERENCES `Competicion` (`idCompeticion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Manga` (
  `idManga` int(11) NOT NULL AUTO_INCREMENT,
  `idPrueba` int(11) NOT NULL,
  PRIMARY KEY (`idManga`),
  KEY `fk_idPrueba_idx` (`idPrueba`),
  CONSTRAINT `fk_idPrueba` FOREIGN KEY (`idPrueba`) REFERENCES `Prueba` (`idPrueba`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Grupo` (
  `idGrupo` int(11) NOT NULL AUTO_INCREMENT,
  `idManga` int(11) NOT NULL,
  PRIMARY KEY (`idGrupo`),
  KEY `fk_idManga_idx` (`idManga`),
  CONSTRAINT `fk_idManga` FOREIGN KEY (`idManga`) REFERENCES `Manga` (`idManga`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Credencial` (
  `usuario` varchar(45) NOT NULL,
  `contrasena` varchar(45) NOT NULL,
  `numLicencia` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido1` varchar(45) NOT NULL,
  `apellido2` varchar(45) NOT NULL,
  PRIMARY KEY (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Piloto` (
  `idPiloto` int(11) NOT NULL AUTO_INCREMENT,
  `idGrupo` int(11) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  PRIMARY KEY (`idPiloto`),
  KEY `fk_idGrupo_idx` (`idGrupo`),
  KEY `fk_usuario_idx` (`usuario`),
  CONSTRAINT `fk_idGrupo` FOREIGN KEY (`idGrupo`) REFERENCES `Grupo` (`idGrupo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario` FOREIGN KEY (`usuario`) REFERENCES `Credencial` (`usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Puntuacion` (
  `idPiloto` int(11) NOT NULL,
  `tiempoVuelo` int(11) NOT NULL,
  `distancia` int(11) NOT NULL,
  `altura` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  KEY `fk_idPiloto_idx` (`idPiloto`),
  CONSTRAINT `fk_idPiloto` FOREIGN KEY (`idPiloto`) REFERENCES `Piloto` (`idPiloto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




