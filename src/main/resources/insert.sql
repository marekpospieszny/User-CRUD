CREATE DATABASE workshop2 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `workshop2`;
CREATE TABLE users (
    id int(11) NOT NULL auto_increment,
    email varchar(255) NOT NULL UNIQUE,
    username varchar(255) NOT NULL,
    password varchar(60) NOT NULL,
    PRIMARY KEY (id)
);