CREATE TABLE Organization (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    master TINYINT,
);

CREATE TABLE Admin(
   id VARCHAR(255) PRIMARY KEY,
   firstName VARCHAR(255) NOT NULL,
   lastName VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   password VARCHAR(255),
   organizationId VARCHAR(255) FOREIGN KEY REFERENCES Organization(id)
);

CREATE TABLE Role (
     id VARCHAR(255) PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     organizationId VARCHAR(255) FOREIGN KEY REFERENCES Organization(id)
);

CREATE TABLE Permission (
     id VARCHAR(255) PRIMARY KEY,
     entity TINYINT NOT NULL,
     operation TINYINT NOT NULL,
     roleId VARCHAR(255) FOREIGN KEY REFERENCES Role(id)
);

CREATE TABLE AdminRole (
     id VARCHAR(255) PRIMARY KEY,
     adminId VARCHAR(255) FOREIGN KEY REFERENCES Admin(id),
     roleId VARCHAR(255) FOREIGN KEY REFERENCES Role(id)
);

CREATE TABLE Product (
     id VARCHAR(255) PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     description VARCHAR(255),
     price FLOAT,
     quantity BIGINT,
     organizationId VARCHAR(255) FOREIGN KEY REFERENCES Organization(id)
);

CREATE TABLE ProductSharingStatement (
     id VARCHAR(255) PRIMARY KEY,
     sharingOrgId VARCHAR(255)  FOREIGN KEY REFERENCES Organization(id),
     accessingOrgId VARCHAR(255)  FOREIGN KEY REFERENCES Organization(id),
     quantity BIGINT,
     price FLOAT,
     relation TINYINT,
     operation TINYINT,
     approved TINYINT
);



