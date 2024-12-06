CREATE TABLE IF NOT EXISTS `levels`
(
    `id`      int     NOT NULL AUTO_INCREMENT,
    `level`   char(8) NOT NULL,
    `payment` double  NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `level` (`level`) USING BTREE
);

CREATE TABLE IF NOT EXISTS `posts`
(
    `id`         int         NOT NULL AUTO_INCREMENT,
    `post_name`  varchar(64) NOT NULL,
    `post_level` char(8)     NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `post_level` FOREIGN KEY (`post_level`) REFERENCES `levels` (`level`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE IF NOT EXISTS `users`
(
    `id`       int         NOT NULL AUTO_INCREMENT,
    `nickname` varchar(64) NOT NULL,
    `username` varchar(64) NOT NULL,
    `password` char(64)    NOT NULL,
    `salt`     char(32)    NOT NULL,
    `admin`    tinyint(1)  NOT NULL DEFAULT FALSE,
    `post`     int         NOT NULL,
    `real_pay` double      NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `username` (`username`) USING HASH,
    CONSTRAINT `post` FOREIGN KEY (`post`) REFERENCES `posts` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

INSERT INTO `levels` (`level`, `payment`)
VALUES ('M1', 10000),
       ('M2', 15000),
       ('M3', 20000),
       ('M4', 30000),
       ('M5', 35000),
       ('M6', 50000),
       ('P1', 3000),
       ('P2', 6000),
       ('P3', 10000),
       ('P4', 20000);

INSERT INTO `posts` (`post_name`, `post_level`)
VALUES ('主管', 'M1'),
       ('经理', 'M2'),
       ('总监', 'M3'),
       ('副总裁', 'M4'),
       ('副董事长', 'M5'),
       ('董事长', 'M6'),
       ('初级工程师', 'P1'),
       ('中级工程师', 'P2'),
       ('高级工程师', 'P3'),
       ('专家', 'P4');

INSERT INTO `users` (`nickname`, `username`, `password`, `salt`, `admin`, `post`, `real_pay`)
VALUES ('严俊玉', 'Half_nothing', 'e810c36b167a1edfebad63b5157457540cf834afbd522099b3975883fd1f5ef4',
        '4LwnxzGGAzZtJH1zA4Fxc27wZ8xQjWop', TRUE, 7, 3500),
       ('季又蓝', 'jiyoulan', 'e810c36b167a1edfebad63b5157457540cf834afbd522099b3975883fd1f5ef4',
        '4LwnxzGGAzZtJH1zA4Fxc27wZ8xQjWop', FALSE, 10, null),
       ('苟升', 'gousheng', 'e810c36b167a1edfebad63b5157457540cf834afbd522099b3975883fd1f5ef4',
        '4LwnxzGGAzZtJH1zA4Fxc27wZ8xQjWop', FALSE, 9, 15000),
       ('焦冠玉', 'jiaoguanyu', 'e810c36b167a1edfebad63b5157457540cf834afbd522099b3975883fd1f5ef4',
        '4LwnxzGGAzZtJH1zA4Fxc27wZ8xQjWop', FALSE, 3, null),
       ('桥幼白', 'qiaoyoubai', 'e810c36b167a1edfebad63b5157457540cf834afbd522099b3975883fd1f5ef4',
        '4LwnxzGGAzZtJH1zA4Fxc27wZ8xQjWop', FALSE, 6, null);
