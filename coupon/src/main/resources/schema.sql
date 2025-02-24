-- ✅ 먼저 member 테이블 생성
CREATE TABLE member (
                        member_id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- ✅ 기존 'id'를 'member_id'로 변경
                        email VARCHAR(255) UNIQUE NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        phone VARCHAR(20)
);

-- ✅ 그다음 coupon 테이블 생성 (member 테이블을 참조)
CREATE TABLE coupon (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        member_id BIGINT NOT NULL,
                        FOREIGN KEY (member_id) REFERENCES member(member_id) ON DELETE CASCADE
);


