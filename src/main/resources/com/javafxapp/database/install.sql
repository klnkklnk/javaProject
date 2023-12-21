CREATE TABLE IF NOT EXISTS user
(
    login varchar(32) PRIMARY KEY UNIQUE NOT NULL,
    password varchar(200) NOT NULL,
    registeredAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);