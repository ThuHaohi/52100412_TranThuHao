create database phone

CREATE TABLE Manufacture (
    ID INT PRIMARY KEY,
    employee INT NOT NULL
);

CREATE TABLE MobilePhone (
    ID INT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    Color VARCHAR(50) NOT NULL
);

