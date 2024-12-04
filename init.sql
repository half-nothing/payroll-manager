CREATE TABLE IF NOT EXISTS `posts`
(
    `id`      int NOT NULL AUTO_INCREMENT,
    `level`   int NOT NULL,
    `payment` int NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `level` (`level`) USING BTREE
);

CREATE TABLE IF NOT EXISTS `users`
(
    `id`       int         NOT NULL AUTO_INCREMENT,
    `username` varchar(64) NOT NULL,
    `password` char(64)    NOT NULL,
    `salt`     char(32)    NOT NULL,
    `post`     varchar(64) NOT NULL,
    `level`    int         NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `username` (`username`) USING HASH,
    CONSTRAINT `level` FOREIGN KEY (`level`) REFERENCES `posts` (`level`) ON DELETE RESTRICT ON UPDATE RESTRICT
);
