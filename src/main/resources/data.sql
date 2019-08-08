--Script automatically executed on startup by Hibernate
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS library;

CREATE TABLE author (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255),
    lastname varchar(16),
    PRIMARY KEY (id)
);

CREATE TABLE book (
    id int  AUTO_INCREMENT,
    title varchar(255),
    published int,
    author_id int,
    --PRIMARY KEY (id), TODO: error on purpose - id is not a key, multiplicated ids allowed
    FOREIGN KEY (author_id) REFERENCES author(id)
);

CREATE TABLE library (
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    address varchar(255)
);

CREATE TABLE external_library(
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    address varchar(255)
);

INSERT INTO author(id, lastname, name) VALUES (1001, 'Mickiewicz', 'Adas');
INSERT INTO author(lastname, name) VALUES('Homer', '');
INSERT INTO author(lastname, name) VALUES('Shakespeare', 'William');
INSERT INTO author(lastname, name) VALUES('Martin', 'George R.R.');
INSERT INTO author(lastname, name) VALUES('Lucas', 'George');
INSERT INTO author(lastname, name) VALUES('Rej', 'Mikolaj');
INSERT INTO author(lastname, name) VALUES('Strugacki', 'A.A.');
INSERT INTO author(lastname, name) VALUES('Tolkien', 'John');
INSERT INTO author(lastname, name) VALUES('Slowacki', 'Juliusz');

INSERT INTO book(id, title, published, author_id) VALUES (2001,  'Pan Tadeusz', 1977, 1001);
INSERT INTO book(title, published, author_id) VALUES ('Star Wars', 1997, 1005);
INSERT INTO book(title, published, author_id) VALUES ( 'Lord of the Rings', 1997, 1008);
INSERT INTO book(title, published, author_id) VALUES ( 'Song of Ice and Fire', 1997, 1004);
INSERT INTO book(title, published, author_id) VALUES ( 'Pan Beniowski', 1997, 1009);
INSERT INTO book(title, published, author_id) VALUES ('Hobbit', 1988, 1008);
INSERT INTO book(title, published, author_id) VALUES ( 'Dziady 1', 1997, 1001);
INSERT INTO book(title, published, author_id) VALUES ( 'Sonety Krymske', 1988, 1001);
INSERT INTO book(title, published, author_id) VALUES ( 'Kordian', 1988, 1009);
INSERT INTO book(title, published, author_id) VALUES ( 'Dziady 2', 1997, 1001);
INSERT INTO book(title, published, author_id) VALUES ( 'Dziady 3', 1997, 1001);
INSERT INTO book(title, published, author_id) VALUES ('Konrad Wallenrod', 1988, 1001);
INSERT INTO book(title, published, author_id) VALUES ('Księgi narodu polskiego i pielgrzymstwa polskiego', 1988, 1001);
INSERT INTO book(title, published, author_id) VALUES ('Liryki lozańskie', 1994, 1001);
INSERT INTO book(title, published, author_id) VALUES ( 'Balladyna', 1994, 1009);
INSERT INTO book(title, published, author_id) VALUES ('Fantazy', 1994, 1009);
INSERT INTO book(title, published, author_id) VALUES ('Mazepa', 2001, 1009);
INSERT INTO book(title, published, author_id) VALUES ('Ksiadz Marek', 2010, 1009);
INSERT INTO book(title, published, author_id) VALUES ('A Clash of Kings', 1999, 1004);
INSERT INTO book(title, published, author_id) VALUES ('A Storm of Swords', 2000, 1004);
INSERT INTO book(title, published, author_id) VALUES ('A Feast for Crows', 2006, 1004);
INSERT INTO book(title, published, author_id) VALUES ('A Dance with Dragons', 2011, 1004);
INSERT INTO book(title, published, author_id) VALUES ('Oddysey', 1988, 1002);
INSERT INTO book(title, published, author_id) VALUES ('Illiada', 1988, 1002);
INSERT INTO book(title, published, author_id) VALUES ('Zywot czlowieka poczciwego', 1988, 1006);

INSERT INTO library VALUES (3001, 'Wroclaw');
INSERT INTO library(address) VALUES ('Warszawa');
INSERT INTO library(address) VALUES ('Poznan');
INSERT INTO library(address) VALUES ('Pcim Dolny');
INSERT INTO library(address) VALUES ('Bielsko Biala');
INSERT INTO external_library VALUES (3006, 'Munich');
INSERT INTO external_library (address) VALUES ('Prague');
INSERT INTO external_library (address) VALUES ('Bratislava');
INSERT INTO external_library (address) VALUES ('Budapest');
INSERT INTO external_library (address) VALUES ('Ostrava');
