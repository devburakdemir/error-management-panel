-------------------------------------------------------------------------------------------------------------
MySQL DB Codes
-------------------------------------------------------------------------------------------------------------

-- migrations_mysql.sql
-- MySQL sürümüne göre uyarlanmış hâli
-- NOT: InnoDB + utf8mb4 kullanılıyor

DROP DATABASE IF EXISTS `ErrorManagementDb`;
CREATE DATABASE `ErrorManagementDb`
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci;
USE `ErrorManagementDb`;

-- USERS tablosu
DROP TABLE IF EXISTS `USERS`;
CREATE TABLE `USERS` (
  `UserID` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,   -- IDENTITY -> AUTO_INCREMENT
  `FirstName` VARCHAR(50) NOT NULL,
  `LastName` VARCHAR(50) NOT NULL,
  `Email` VARCHAR(50) NOT NULL UNIQUE,
  `Phone` VARCHAR(10) NOT NULL UNIQUE,
  `UserRole` VARCHAR(25) NOT NULL,
  CONSTRAINT `CK_UserRole` CHECK (`UserRole` IN ('Administator','StandardUser'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- SECURITYBASE tablosu
DROP TABLE IF EXISTS `SECURITYBASE`;
CREATE TABLE `SECURITYBASE` (
  `SecurityBaseID` INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- yardımcı PK (MySQL'de FK tablosunun PK'sı olması iyi)
  `PasswordHash` VARBINARY(64) NOT NULL,
  `Salt` VARBINARY(16) NOT NULL,
  `LastLogInDate` DATETIME NULL,
  `IsActive` TINYINT(1) DEFAULT 1,
  `IsLocked` TINYINT(1) DEFAULT 0,
  `LoginAttempts` INT DEFAULT 0,
  `LastPasswordChange` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `RegistrationDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `UserID` INT NOT NULL,
  CONSTRAINT `FK_SECURITYBASE_USERS` FOREIGN KEY (`UserID`) REFERENCES `USERS`(`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- SIMAX_ERRORS tablosu
DROP TABLE IF EXISTS `SIMAX_ERRORS`;
CREATE TABLE `SIMAX_ERRORS` (
  `ErrorCode` INT NOT NULL,
  `ErrorDescription` NVARCHAR(500),  -- MySQL NVARCHAR -> use VARCHAR + utf8mb4 handles unicode; keep NVARCHAR for clarity but MySQL maps it
  `ErrorCategory` VARCHAR(50),
  PRIMARY KEY (`ErrorCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- CCT_RESPONSE_CODES tablosu
DROP TABLE IF EXISTS `CCT_RESPONSE_CODES`;
CREATE TABLE `CCT_RESPONSE_CODES` (
  `ResponseCode` INT NOT NULL,
  `ResponseDescription` NVARCHAR(100),
  `ResponseCategory` VARCHAR(25),
  PRIMARY KEY (`ResponseCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



-----------------------------------------------------------------------------------------------------
UPDATED TABLE Codes
-----------------------------------------------------------------------------------------------------

ALTER TABLE simax_errors
modify COLUMN ErrorDescription VARCHAR(500) NOT NULL;

ALTER TABLE cct_response_codes
modify COLUMN ResponseDescription VARCHAR(500) NOT NULL;

CREATE UNIQUE INDEX UX_SECURITYBASE_USER ON SECURITYBASE(UserID);

ALTER TABLE USERS DROP CONSTRAINT CK_UserRole;  -- Varsa kaldır
-- Veya yeniden oluştur:
ALTER TABLE USERS ADD CONSTRAINT CK_UserRole CHECK (`UserRole` IN ('Administrator','StandardUser'));
