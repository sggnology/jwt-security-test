CREATE TABLE IF NOT EXISTS MEMBER(
    id INT PRIMARY KEY auto_increment,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    authority VARCHAR(20) DEFAULT 'user' NOT NULL
);