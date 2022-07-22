CREATE TABLE `t_user`
(
    `id`            varchar(64) NOT NULL,
    `username`      varchar(32)  DEFAULT NULL,
    `age`           int(11) DEFAULT NULL,
    `email`         varchar(64)  DEFAULT NULL,
    `address`       varchar(255) DEFAULT NULL,
    `gmt_create`    datetime     DEFAULT NULL,
    `gmt_modify`    datetime     DEFAULT NULL,
    `gmt_delete`    datetime     DEFAULT NULL,
    `creator`       varchar(64)  DEFAULT NULL,
    `creator_name`  varchar(32)  DEFAULT NULL,
    `modifier`      varchar(64)  DEFAULT NULL,
    `modifier_name` varchar(32)  DEFAULT NULL,
    `is_valid`      tinyint(2) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;