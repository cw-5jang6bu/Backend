CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       userid VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL
);

CREATE TABLE coupons (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         user_id BIGINT NOT NULL,
                         issued BOOLEAN NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES users(id)
);
