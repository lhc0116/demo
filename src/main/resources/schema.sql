CREATE TABLE IF NOT EXISTS pass_user(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(16) COMMENT '姓名',
    age INT NOT NULL ,
    gender VARCHAR(8) NOT NULL ,
    birthday DATETIME NOT NULL,
    create_time TIMESTAMP NOT NULL DEFAULT current_timestamp,
    update_time TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';