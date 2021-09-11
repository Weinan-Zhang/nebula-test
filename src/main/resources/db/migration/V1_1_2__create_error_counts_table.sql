CREATE TABLE `error_counts` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`api_name` VARCHAR(50),
	`error_counts` INT,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=UTF8;