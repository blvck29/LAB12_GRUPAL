-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema rojo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema rojo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rojo` DEFAULT CHARACTER SET utf8mb3 ;
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
  `ban` TINYINT NOT NULL DEFAULT '0',
  PRIMARY KEY (`idjugadores`),
  UNIQUE INDEX `usuario_UNIQUE` (`usuario` ASC) VISIBLE,
  UNIQUE INDEX `correo_UNIQUE` (`correo` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb3;


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
    REFERENCES `rojo`.`jugadores` (`idjugadores`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `rojo`.`guerra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rojo`.`guerra` (
  `id_guerra` INT NOT NULL AUTO_INCREMENT,
  `id_civilizacion_atacante` INT NOT NULL,
  `id_civilizacion_defensora` INT NOT NULL,
  `estado_guerra` VARCHAR(2) NOT NULL,
  `dia_atacante` INT NOT NULL,
  `dia_defensor` INT NOT NULL,
  PRIMARY KEY (`id_guerra`),
  INDEX `fk_Guerra_civilizaciones1_idx` (`id_civilizacion_atacante` ASC) VISIBLE,
  INDEX `fk_Guerra_civilizaciones2_idx` (`id_civilizacion_defensora` ASC) VISIBLE,
  CONSTRAINT `fk_Guerra_civilizaciones1`
    FOREIGN KEY (`id_civilizacion_atacante`)
    REFERENCES `rojo`.`civilizaciones` (`id_civilizacion`),
  CONSTRAINT `fk_Guerra_civilizaciones2`
    FOREIGN KEY (`id_civilizacion_defensora`)
    REFERENCES `rojo`.`civilizaciones` (`id_civilizacion`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `rojo`.`personas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rojo`.`personas` (
  `id_personas` INT NOT NULL AUTO_INCREMENT,
  `id_civilizacion` INT NOT NULL,
  `genero` VARCHAR(1) NOT NULL,
  `alimento_dia` INT NOT NULL,
  `moral` INT NOT NULL,
  `fuerza` INT NULL DEFAULT NULL,
  `produce` INT NULL DEFAULT NULL,
  `alimentado` TINYINT NOT NULL DEFAULT '0',
  `days_alive` INT NOT NULL DEFAULT '0',
  `nombre` VARCHAR(10) NOT NULL,
  `profesion` VARCHAR(12) NOT NULL,
  `muerto` TINYINT NOT NULL DEFAULT '0',
  `motivoMuerte` VARCHAR(45) NULL DEFAULT NULL,
  `dia_muerte` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_personas`),
  INDEX `fk_personas_civilizaciones1_idx` (`id_civilizacion` ASC) VISIBLE,
  CONSTRAINT `fk_personas_civilizaciones1`
    FOREIGN KEY (`id_civilizacion`)
    REFERENCES `rojo`.`civilizaciones` (`id_civilizacion`))
ENGINE = InnoDB
AUTO_INCREMENT = 30
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;




#Llenado de datos


#Usuarios

insert into jugadores(nombre,edad,correo,usuario,contrasena) values("Alex",16,"sol@gmail.com","Ares","dinosaurio");
insert into jugadores(nombre,edad,correo,usuario,contrasena) values("Maria",19,"alo@gmail.com","Sdeath","solymarns");
insert into jugadores(nombre,edad,correo,usuario,contrasena) values("Sonia",15,"son@gmail.com","Arlet","manilamia");
insert into jugadores(nombre,edad,correo,usuario,contrasena) values("Diego",27,"di@gmail.com","Disaster","nocturno1");

UPDATE jugadores SET contrasena = SHA2(contrasena, 256) where idjugadores>=1;

#Civilizaciones
insert into civilizaciones(id_jugadores,nombre) values (1,"La mancha");
insert into civilizaciones(id_jugadores,nombre) values (2,"Narnia");
insert into civilizaciones(id_jugadores,nombre) values (3,"Zagreb");
insert into civilizaciones(id_jugadores,nombre) values (4,"Helheim");

#Personas
insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion)
values (1,"O",16,20,null,160,"Mario","Granjero");
insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion)
values (1,"F",40,40,null,null,"Mariana","Ninguna");

insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion)
values (2,"M",70,40,20,20,"Lancelot","Soldado");
insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion)
values (2,"F",30,50,null,null,"Luciana","Ninguna");

insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion)
values (3,"M",60,30,10,18,"Bob","Constructor");
insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion)
values (3,"M",45,46,null,null,"Arturo","Ninguna");


insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion)
values (4,"F",70,40,16,20,"Lina","Constructor");
insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion)
values (4,"F",38,50,null,null,"Alice","Ninguna");
insert into personas(id_civilizacion,genero,alimento_dia,moral,fuerza,produce,nombre,profesion)
values (4,"M",46,40,null,null,"Alonso","Ninguna");

#Guerra
insert into guerra(id_civilizacion_atacante,id_civilizacion_defensora,estado_guerra,dia_atacante,dia_defensor)
values(2,1,"VA",1,0);

insert into guerra(id_civilizacion_atacante,id_civilizacion_defensora,estado_guerra,dia_atacante,dia_defensor)
values(3,1,"VD",1,1);

