CREATE DATABASE  IF NOT EXISTS `test`;
USE `test`;
--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(256) DEFAULT NULL,
  `name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `users_email_fk_1` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
