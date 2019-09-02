CREATE TABLE IF NOT EXISTS pass_user(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name CHAR(16) COMMENT '姓名',
    age INT,
    gender VARCHAR(8),
    birthday BIGINT,
    create_time BIGINT,
    update_time BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';