DROP DATABASE IF EXISTS webserverdatabase;
CREATE DATABASE webserverdatabase;

use webserverdatabase;

CREATE TABLE users (
	id int NOT NULL AUTO_INCREMENT,
    uname varchar(20) UNIQUE NOT NULL,
    passwd varchar(64) NOT NULL,
    email varchar(64) UNIQUE NOT NULL,
    ts timestamp DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE questions (
	id int NOT NULL AUTO_INCREMENT,
    title varchar(100) NOT NULL,
    body varchar(1000) NOT NULL,
    author int NOT NULL,
    rating int DEFAULT 0,
    ts timestamp DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (author) references users(id)
);

CREATE TABLE answers (
	id int NOT NULL AUTO_INCREMENT,
    question int NOT NULL,
    body varchar(1000) NOT NULL,
    codeBody varchar(1000) NOT NULL,
    rating int DEFAULT 0,
    author int NOT NULL,
    ts timestamp DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (question) REFERENCES questions(id),
    FOREIGN KEY (author) references users(id)
);

CREATE TABLE rates (
	answer int NOT NULL,
    rater int NOT NULL,
    up boolean NOT NULL,
    FOREIGN KEY (answer) REFERENCES answers(id),
    FOREIGN KEY (rater) REFERENCES users(id)
);