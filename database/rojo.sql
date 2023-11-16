-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema rojo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema rojo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rojo` DEFAULT CHARACTER SET utf8 ;
USE `rojo` ;

-- -----------------------------------------------------
-- Table `rojo`.`jugadores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rojo`.`jugadores` (
  `idjugadores` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(20) NOT NULL,
  `edad` INT NOT NULL,
  `correo` VARCHAR(36) NOT NULL,
  `usuario` VARCHAR(20) NOT NULL,
  `contrasena` VARCHAR(64) NOT NULL,
  `ban` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`idjugadores`),
  UNIQUE INDEX `usuario_UNIQUE` (`usuario` ASC) VISIBLE,
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rojo`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rojo`.`roles` (
  `id_roles` VARCHAR(6) NOT NULL,
  `profesion` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`id_roles`),
  UNIQUE INDEX `profesion_UNIQUE` (`profesion` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rojo`.`nombres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rojo`.`nombres` (
  `id_nombres` INT NOT NULL,
  `nombre` VARCHAR(10) NOT NULL DEFAULT 'Alvaro',
  PRIMARY KEY (`id_nombres`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rojo`.`civilizaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rojo`.`civilizaciones` (
  `id_civilizacion` INT NOT NULL AUTO_INCREMENT,
  `id_jugadores` INT NOT NULL,
  `estado` VARCHAR(8) NOT NULL DEFAULT 'PAZ',
  `time_elapsed` INT NOT NULL DEFAULT '0',
  `days_elapsed` INT NOT NULL DEFAULT '0',
  `alimento_total` INT NOT NULL DEFAULT 0,
  `poblacion_total` INT NOT NULL DEFAULT 0,
  `fuerza_total` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_civilizacion`),
  INDEX `fk_civilizaciones_jugadores1_idx` (`id_jugadores` ASC) VISIBLE,
  CONSTRAINT `fk_civilizaciones_jugadores1`
    FOREIGN KEY (`id_jugadores`)
    REFERENCES `rojo`.`jugadores` (`idjugadores`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rojo`.`muerte`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rojo`.`muerte` (
  `id_muerte` VARCHAR(10) NOT NULL,
  `motivo` VARCHAR(70) NOT NULL,
  PRIMARY KEY (`id_muerte`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rojo`.`personas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rojo`.`personas` (
  `id_personas` INT NOT NULL AUTO_INCREMENT,
  `id_civilizacion` INT NOT NULL,
  `id_roles` VARCHAR(6) NOT NULL,
  `id_nombres` INT NOT NULL,
  `genero` VARCHAR(1) NOT NULL,
  `alimento_dia` INT NOT NULL,
  `moral` INT NOT NULL,
  `fuerza` INT NULL,
  `produce` INT NULL,
  `exiliado` TINYINT NOT NULL DEFAULT 0,
  `alimentado` TINYINT NOT NULL DEFAULT 0,
  `days_alive` INT NOT NULL DEFAULT 0,
  `idmuerte` VARCHAR(10) NOT NULL DEFAULT 'VIVO',
  PRIMARY KEY (`id_personas`),
  INDEX `fk_personas_roles_idx` (`id_roles` ASC) VISIBLE,
  INDEX `fk_personas_nombres1_idx` (`id_nombres` ASC) VISIBLE,
  INDEX `fk_personas_civilizaciones1_idx` (`id_civilizacion` ASC) VISIBLE,
  INDEX `fk_personas_muerte1_idx` (`idmuerte` ASC) VISIBLE,
  CONSTRAINT `fk_personas_roles`
    FOREIGN KEY (`id_roles`)
    REFERENCES `rojo`.`roles` (`id_roles`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personas_nombres1`
    FOREIGN KEY (`id_nombres`)
    REFERENCES `rojo`.`nombres` (`id_nombres`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personas_civilizaciones1`
    FOREIGN KEY (`id_civilizacion`)
    REFERENCES `rojo`.`civilizaciones` (`id_civilizacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personas_muerte1`
    FOREIGN KEY (`idmuerte`)
    REFERENCES `rojo`.`muerte` (`id_muerte`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rojo`.`guerras`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rojo`.`guerras` (
  `id_guerra` INT NOT NULL AUTO_INCREMENT,
  `id_civilizacion` INT NOT NULL,
  `id_oponente` INT NOT NULL,
  `resultado` VARCHAR(12) NOT NULL,
  `rol_guerra` VARCHAR(10) NOT NULL DEFAULT 'VIGENTE',
  PRIMARY KEY (`id_guerra`),
  INDEX `fk_guerras_civilizaciones1_idx` (`id_civilizacion` ASC) VISIBLE,
  CONSTRAINT `fk_guerras_civilizaciones1`
    FOREIGN KEY (`id_civilizacion`)
    REFERENCES `rojo`.`civilizaciones` (`id_civilizacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
