CREATE TABLE `phone` (
    `id` long unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(255),
    `color` varchar(255),
    `price` double,
    `country` varchar(255),
    `created_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
    `modified_at` datetime DEFAULT NULL ON UPDATE current_timestamp(),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
