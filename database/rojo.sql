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
-- Table `rojo`.`civilizaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rojo`.`civilizaciones` (
  `id_civilizacion` INT NOT NULL AUTO_INCREMENT,
  `id_jugadores` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(8) NOT NULL DEFAULT 'PAZ',
  `time_elapsed` INT NOT NULL DEFAULT '0',
  `days_elapsed` INT NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_civilizacion`),
  INDEX `fk_civilizaciones_jugadores1_idx` (`id_jugadores` ASC) VISIBLE,
  CONSTRAINT `fk_civilizaciones_jugadores1`
    FOREIGN KEY (`id_jugadores`)
    REFERENCES `rojo`.`jugadores` (`idjugadores`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rojo`.`personas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rojo`.`personas` (
  `id_personas` INT NOT NULL AUTO_INCREMENT,
  `id_civilizacion` INT NOT NULL,
  `genero` VARCHAR(1) NOT NULL,
  `alimento_dia` INT NOT NULL,
  `moral` INT NOT NULL,
  `fuerza` INT NULL,
  `produce` INT NULL,
  `alimentado` TINYINT NOT NULL DEFAULT 0,
  `days_alive` INT NOT NULL DEFAULT 0,
  `nombre` VARCHAR(10) NOT NULL,
  `profesion` VARCHAR(12) NOT NULL,
  `muerto` TINYINT NOT NULL DEFAULT 0,
  `motivoMuerte` VARCHAR(45) NULL,
  `dia_muerte` INT NULL,
  PRIMARY KEY (`id_personas`),
  INDEX `fk_personas_civilizaciones1_idx` (`id_civilizacion` ASC) VISIBLE,
  CONSTRAINT `fk_personas_civilizaciones1`
    FOREIGN KEY (`id_civilizacion`)
    REFERENCES `rojo`.`civilizaciones` (`id_civilizacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rojo`.`Guerra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rojo`.`Guerra` (
  `id_guerra` INT NOT NULL AUTO_INCREMENT,
  `id_civilzacion_atacante` INT NOT NULL,
  `id_civilizacion_defensora` INT NOT NULL,
  `estado_guerra` VARCHAR(2) NOT NULL,
  `dia_atacante` INT NOT NULL,
  `dia_defensor` INT NOT NULL,
  PRIMARY KEY (`id_guerra`),
  INDEX `fk_Guerra_civilizaciones1_idx` (`id_civilzacion_atacante` ASC) VISIBLE,
  INDEX `fk_Guerra_civilizaciones2_idx` (`id_civilizacion_defensora` ASC) VISIBLE,
  CONSTRAINT `fk_Guerra_civilizaciones1`
    FOREIGN KEY (`id_civilzacion_atacante`)
    REFERENCES `rojo`.`civilizaciones` (`id_civilizacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Guerra_civilizaciones2`
    FOREIGN KEY (`id_civilizacion_defensora`)
    REFERENCES `rojo`.`civilizaciones` (`id_civilizacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
