CREATE TABLE `log` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`admin_id` INT(20),
	`call_date` DATE,
	`call_time` TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`result` BOOLEAN,
	`error_counts` INT,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=UTF8;