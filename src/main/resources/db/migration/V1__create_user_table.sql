CREATE TABLE IF NOT EXISTS `user` (
    `id` BINARY(16) NOT NULL PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) UNIQUE NOT NULL,
    `role` ENUM('USER','ADMIN') NOT NULL,
    `password` VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `card` (
    `id` BINARY(16) NOT NULL PRIMARY KEY,
    `number` VARCHAR(255) UNIQUE NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `status` BOOLEAN NOT NULL,
    `type` ENUM('COMUM','ESTUDANTE','TRABALHADOR') NOT NULL,
    `user_id` BINARY(16) NOT NULL,

    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);

INSERT INTO `user` (`id`, `name`, `email`, `role`, `password`)
VALUES (
           UNHEX(REPLACE(UUID(), '-', '')),
           'Thiagao',
           'admin.teste@gmail.com',
           'ADMIN',
           '$2a$12$KNp0lxhVxeNUfhI9h5Tw8Oh/JBNeUyKmC3DlxQmS6zhcUQGD/H2Xe'
       );