-- 회원 데이터 삽입
INSERT INTO member (member_id, email, password, phone) VALUES (1, 'test@example.com', 'password123', '010-1234-5678');
INSERT INTO member (member_id, email, password, phone) VALUES (2, 'user@example.com', 'securepass', '010-5678-1234');

-- 쿠폰 데이터 삽입 (1번 회원만 쿠폰 발급)
INSERT INTO coupon (member_id, issued) VALUES (1, true);
