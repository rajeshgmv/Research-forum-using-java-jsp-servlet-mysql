-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema nbad3
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema nbad3
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nbad3` DEFAULT CHARACTER SET utf8 ;
USE `nbad3` ;

-- -----------------------------------------------------
-- Table `nbad3`.`answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nbad3`.`answer` (
  `StudyID` VARCHAR(40) NOT NULL DEFAULT '',
  `QuestionID` VARCHAR(40) NOT NULL DEFAULT '',
  `UserName` VARCHAR(40) NOT NULL DEFAULT '',
  `Choice` VARCHAR(40) NULL DEFAULT NULL,
  `DateSubmitted` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`StudyID`, `QuestionID`, `UserName`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `nbad3`.`forgotpassword`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nbad3`.`forgotpassword` (
  `email` VARCHAR(50) NOT NULL,
  `token` VARCHAR(100) NULL DEFAULT NULL,
  `expirationdate` VARCHAR(50) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `nbad3`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nbad3`.`user` (
  `Username` VARCHAR(50) NOT NULL DEFAULT '',
  `Password` VARCHAR(100) NULL DEFAULT NULL,
  `Type` VARCHAR(50) NULL DEFAULT NULL,
  `Studies` INT(15) NULL DEFAULT NULL,
  `Participation` INT(15) NULL DEFAULT NULL,
  `Coins` INT(15) NULL DEFAULT NULL,
  `name` VARCHAR(30) NOT NULL DEFAULT '',
  `salt` VARCHAR(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`Username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `nbad3`.`study`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nbad3`.`study` (
  `StudyID` VARCHAR(40) NOT NULL DEFAULT '',
  `StudyName` VARCHAR(40) NULL DEFAULT NULL,
  `Description` VARCHAR(50) NULL DEFAULT NULL,
  `Username` VARCHAR(50) NULL DEFAULT NULL,
  `DateCreated` DATETIME NULL DEFAULT NULL,
  `ImageURL` VARCHAR(50) NULL DEFAULT NULL,
  `ReqParticipants` INT(15) NULL DEFAULT NULL,
  `ActParticipants` INT(15) NULL DEFAULT NULL,
  `SStatus` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`StudyID`),
  INDEX `Username` (`Username` ASC),
  CONSTRAINT `study_ibfk_1`
    FOREIGN KEY (`Username`)
    REFERENCES `nbad3`.`user` (`Username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `nbad3`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nbad3`.`question` (
  `QuestionID` VARCHAR(40) NOT NULL DEFAULT '',
  `StudyID` VARCHAR(40) NULL DEFAULT NULL,
  `Question` VARCHAR(50) NULL DEFAULT NULL,
  `AnswerType` VARCHAR(10) NULL DEFAULT NULL,
  `Option1` VARCHAR(40) NULL DEFAULT NULL,
  `Option2` VARCHAR(40) NULL DEFAULT NULL,
  `Option3` VARCHAR(40) NULL DEFAULT NULL,
  `Option4` VARCHAR(40) NULL DEFAULT NULL,
  `Option5` VARCHAR(40) NULL DEFAULT NULL,
  PRIMARY KEY (`QuestionID`),
  INDEX `StudyID` (`StudyID` ASC),
  CONSTRAINT `question_ibfk_1`
    FOREIGN KEY (`StudyID`)
    REFERENCES `nbad3`.`study` (`StudyID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `nbad3`.`reported`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nbad3`.`reported` (
  `StudyID` VARCHAR(40) NOT NULL DEFAULT '',
  `QuestionID` VARCHAR(255) NOT NULL,
  `Username` VARCHAR(50) NOT NULL DEFAULT '',
  `Date` DATETIME NULL DEFAULT NULL,
  `NumParticipants` INT(15) NULL DEFAULT NULL,
  `Status` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`StudyID`, `QuestionID`),
  INDEX `QuestionID` (`QuestionID` ASC),
  CONSTRAINT `reported_ibfk_1`
    FOREIGN KEY (`QuestionID`)
    REFERENCES `nbad3`.`question` (`QuestionID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `reported_ibfk_2`
    FOREIGN KEY (`StudyID`)
    REFERENCES `nbad3`.`study` (`StudyID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `nbad3`.`tempuser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nbad3`.`tempuser` (
  `uname` VARCHAR(50) NOT NULL DEFAULT '',
  `email` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NULL DEFAULT NULL,
  `token` VARCHAR(100) NULL DEFAULT NULL,
  `salt` VARCHAR(100) NULL DEFAULT NULL,
  `issueDate` VARCHAR(50) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
