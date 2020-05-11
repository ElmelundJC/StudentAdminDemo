-- create the database
DROP DATABASE IF EXISTS administration;
CREATE DATABASE administration;

-- select the database
USE administration;


CREATE TABLE `administration`.`students` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fname` VARCHAR(45) NULL,
  `lname` VARCHAR(45) NULL,
  `enrollmentDate` DATETIME NULL,
  `cpr` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

INSERT INTO `administration`.`students` (`id`,`fname`, `lname`, `enrollmentDate`, `cpr`) 
VALUES (1,'Lars', 'Andersen', '2016-05-15', '1234567890');


