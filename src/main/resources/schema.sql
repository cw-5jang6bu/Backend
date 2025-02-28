CREATE TABLE IF NOT EXISTS member (
                                      member_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20)
    );

CREATE TABLE IF NOT EXISTS coupon (
                                      coupon_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      member_id BIGINT NOT NULL,
                                      issued BOOLEAN NOT NULL DEFAULT FALSE,
                                      FOREIGN KEY (member_id) REFERENCES member(member_id)
    );
