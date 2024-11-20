
INSERT INTO role ( name,description)
VALUES
('ADMIN', 'Quản trị viên có toàn quyền trên hệ thống') ,
('USER', 'Người dùng thường với quyền hạn hạn chế') ;


INSERT INTO user( role_id, name, dob, phone, email, password, avatar, bio, cover_image, created_at, updated_at, deleted_at, unblocked_at, status)
VALUES
( 1, 'Nguyễn Ngọc Ánh', '1990-01-01', '0912000001', 'nnanh124@gmail.com', '12345', 'hinh1.jpg', 'Lập trình viên', 'hinh1-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'ONLINE'),
( 2, 'Trần Thị Kim Liên', '1992-02-02', '0912000002', 'ttkl92@gmail.com', '12345', 'hinh2.jpg', 'Nhà thiết kế', 'hinh2-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'ONLINE'),

(2, 'Phạm Thị Mỹ Tiên', '1994-04-04', '0912000004', 'ptmtien@gmail.com', '12345', 'hinh3.jpg', 'Kế toán viên', 'hinh3-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'ONLINE'),
( 2, 'Trần Phúc Minh', '1995-05-05', '0912000005', 'tpminh87@gmail.com', '12345', 'hinh4.jpg', 'Giáo viên', 'hinh4-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'OFFLINE'),
( 2, 'Lê Minh Khái', '1996-06-06', '0912000006', 'lmkhai@gmail.com', '12345', 'hinh5.jpg', 'Bác sĩ', 'hinh5-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'OFFLINE'),
(2, 'Trần Văn Bối', '1997-07-07', '0912000007', 'tvboi840@gmail.com', '12345', 'hinh6.jpg', 'Luật sư', 'hinh6-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'OFFLINE'),

( 2, 'Nguyễn Trần Thanh Thúy', '1999-09-09', '0912000009', 'ntthuy@gmail.com', '12345', 'hinh7.jpg', 'Nông dân', 'hinh7-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'OFFLINE'),
(2, 'Huỳnh Ngọc Hiền', '1990-10-10', '0912000010', 'hnhien@gmail.com', '12345', 'hinh8.jpg', 'Kỹ sư xây dựng', 'hinh8-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'OFFLINE'),
( 2, 'Trần Mạnh', '1991-11-11', '0912000011', 'tranmanh8h7@gmail.com', '12345', 'hinh9.jpg', 'Nhà báo', 'hinh9-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'OFFLINE'),
(2, 'John Nguyễn', '1992-12-12', '0912000012', 'John87g@gmail.com', '12345', 'hinh10.jpg', 'Nhà nghiên cứu', 'hinh10-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'OFFLINE'),
( 2, 'Selena', '1993-01-13', '0912000013', 'slena517@gmail.com', '12345', 'hinh11.jpg', 'Nhân viên IT', 'hinh11-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'OFFLINE'),
(2, 'Ánh Minh', '1994-02-14', '0912000014', 'minh@gmail.com', '12345', 'hinh12.jpg', 'Kỹ sư phần mềm', 'hinh12-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'OFFLINE'),
(2, 'Hoàng Hùng', '1995-03-15', '0912000015', 'hung123@gmail.com', '12345', 'hinh13.jpg', 'Kỹ thuật viên', 'hinh13-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'OFFLINE'),
(2, 'Phan Tran My Truc', '1996-04-16', '0912000016', 'ptmytruc834@gmail.com', '12345', 'hinh14.jpg', 'Bác sĩ phẫu thuật', 'hinh14-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'OFFLINE'),
(2, 'Văn Nguyên', '1997-05-17', '0912000017', 'nguyenvan872@gmail.com', '12345', 'hinh15.jpg', 'Chuyên viên bảo mật', 'hinh15-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'OFFLINE'),
( 2, 'Lê Ngọc', '1998-06-18', '0912000018', 'lengoc77w@gmail.com', '12345', 'hinh16.jpg', 'Quản lý dự án', 'hinh16-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'OFFLINE'),
(2, 'Trần Sáng', '1999-07-19', '0912000019', 'sangtran881@gmail.com', '12345', 'hinh17.jpg', 'Kỹ sư điện', 'hinh17-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'OFFLINE'),
(2, 'Pham Uyển', '2000-08-20', '0912000020', 'phamuyen@gmail.com', '12345', 'hinh18.jpg', 'Chuyên viên nhân sự', 'hinh18-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'OFFLINE'),
( 1, 'Nguyễn Ngọc Trân', '1990-01-01', '0912000001', 'nnanh124aa@gmail.com', '12345', 'hinh19.jpg', 'Lập trình viên', 'hinh19-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'OFFLINE'),
( 1, 'Trần Ngọc Ánh', '1990-01-01', '0912000001', 'tnanh124@gmail.com', '12345', 'hinh20.jpg', 'Lập trình viên', 'hinh20-cover.png', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, NULL, 'OFFLINE');

INSERT INTO post ( content, created_at, updated_at, deleted_at, user_id)
VALUES
( 'Bài viết đầu tiên của tôi!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 1),
( 'Xin chào mọi người!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 2),
('Hôm nay là một ngày tuyệt vời!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 3),
('Ai cũng có thể làm được!', '2024-02-21 20:34:00', '2024-02-21 20:35:00', NULL, 1),
( 'Cố gắng lên!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 5),
( 'Mọi thứ sẽ ổn thôi.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 6),
( 'Sáng nay trời đẹp quá!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 7),
( 'Tôi rất thích lập trình.', '2024-09-29 23:34:00', CURRENT_TIMESTAMP, NULL, 8),
( 'Thử thách là cơ hội để phát triển.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 9),
( 'Hãy luôn lạc quan.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 10),
('Tôi yêu công nghệ.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 11),
( 'Bạn có thể làm được!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 12),
( 'Mọi thứ đều có lý do.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 13),
( 'Thành công không đến từ may mắn.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 14),
( 'Cần cù bù thông minh.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 15),
( 'Hãy tận hưởng cuộc sống.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 14),
( 'Luôn luôn học hỏi.', '2022-09-22 22:00:00', CURRENT_TIMESTAMP, NULL, 18),
( 'Đừng bao giờ bỏ cuộc.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 20),
( 'Mỗi ngày là một cơ hội mới.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 17),
( 'Hãy sống như không có ngày mai.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL, 10);

INSERT INTO comment ( parent_comment_id, post_id, user_id, content, created_at, updated_at, deleted_at)
VALUES
(NULL, 1, 1, 'Bài viết rất hay!', '2023-11-15 09:15:00', '2024-01-15 09:15:00', '2024-07-15 09:15:00' ),
( NULL, 2, 2, 'Cảm ơn bạn đã chia sẻ!', '2024-01-15 09:15:00', '2024-07-18 18:15:00', NULL),
(NULL, 3, 3, 'Tôi đồng ý với bạn.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
( NULL, 4, 4, 'Rất hữu ích!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
( NULL, 5, 5, 'Tuyệt vời quá!', '2022-01-15 09:15:00', '2023-07-15 09:15:00', NULL),
( NULL, 6, 6, 'Tôi sẽ thử xem.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
( NULL, 7, 7, 'Cảm ơn bạn!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
( NULL, 8, 8, 'Điều này rất thú vị.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
( NULL, 9, 9, 'Rất đồng ý!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
( NULL, 10, 10, 'Tôi đã thử và rất tốt!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
( NULL, 11, 11, 'Bài viết tuyệt lắm.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
( NULL, 12, 12, 'Cảm ơn bạn.', '2023-12-15 09:15:00', '2023-12-16 01:15:00', NULL),
( NULL, 13, 13, 'Tôi sẽ áp dụng ngay.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
( NULL, 14, 14, 'Đúng là như vậy.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
(NULL, 15, 15, 'Bài viết của bạn rất hay.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
( NULL, 16, 16, 'Tôi cũng nghĩ vậy.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
( NULL, 17, 17, 'Hữu ích thật.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
( NULL, 18, 18, 'Tuyệt vời.', '2023-12-15 09:15:00', '2023-12-16 01:15:00', NULL),
( NULL, 19, 19, 'Cảm ơn bạn đã chia sẻ.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL),
( NULL, 20, 20, 'Bài viết rất ý nghĩa.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL);

INSERT INTO reaction ( name)
VALUES
('Thích'),
('Yêu thích'),
( 'Ha ha'),
('Ngạc nhiên'),
( 'Giận dữ'),
( 'Buồn'),
( 'Yêu thương'),
( 'Không thích');


INSERT INTO `post_like` (user_id, post_id, reaction_id, created_at)
VALUES
(1, 1, 1, '2024-09-29 23:34:00'),
(2, 2, 1, '2023-06-26 21:09:00'),
(3, 3, 2, '2023-05-22 20:23:00'),
(4, 4, 2, '2024-08-26 11:17:00'),
(5, 5, 1, '2023-07-27 09:17:00'),
(6, 6, 3, '2024-06-28 18:08:00'),
(7, 7, 2, '2024-05-18 12:10:00'),
(8, 8, 1, '2024-04-30 07:10:00'),
(9, 9, 3, '2024-02-22 12:40:00'),
(10, 10, 1, '2023-10-22 16:03:00'),
(11, 11, 2, '2024-11-22 17:07:00'),
(12, 12, 1, '2024-12-22 19:00:00'),
(13, 13, 2, '2023-08-01 11:06:00'),
(14, 14, 1, '2024-06-22 10:05:00'),
(15, 15, 3, '2023-05-22 09:01:00'),
(16, 16, 1, '2024-04-22 12:09:00'),
(17, 17, 2, '2022-01-22 10:05:00'),
(18, 18, 1, '2024-02-22 12:40:00'),
(19, 19, 3, '2022-07-22 13:00:00'),
(20, 20, 1, '2022-09-22 22:00:00');

INSERT INTO room (theme, created_at, deleted_at)
VALUES
( 'Phòng họp nhóm lập trình', '2024-09-22 12:00:00', NULL),
( 'Phòng thảo luận thiết kế', '2024-10-02 17:00:00', NULL),
( 'Phòng giải trí', '2023-11-05 14:30:00', NULL),
( 'Phòng hội thảo bảo mật', '2024-01-15 19:15:10', NULL),
( 'Phòng dự án ABC', '2023-09-15 20:15:00', NULL),
( 'Phòng tư vấn pháp lý', '2024-03-25 09:15:00', NULL),
( 'Phòng phát triển sản phẩm', '2022-07-15 09:15:00', NULL),
( 'Phòng tổ chức sự kiện', '2024-01-15 20:18:00', NULL),
( 'Phòng học trực tuyến', '2021-01-15 09:46:00', NULL),
( 'Phòng thư giãn', '2024-12-05 11:14:00', NULL),
( 'Phòng hỗ trợ khách hàng', '2024-01-23 02:21:00', NULL),
( 'Phòng sáng tạo ý tưởng', '2022-11-13 19:45:10', NULL),
( 'Phòng chia sẻ kiến thức', '2023-09-12 23:15:00', NULL),
('Phòng dự án XYZ', '2024-01-15 09:15:00', NULL),
( 'Phòng lập kế hoạch', '2024-03-17 06:55:00', NULL),
('Phòng học lập trình nâng cao', '2022-02-26 19:13:00', NULL),
( 'Phòng phân tích dữ liệu', '2023-11-15 09:15:00', '2024-01-15 09:15:00'),
( 'Phòng đào tạo nội bộ', '2023-12-15 05:55:00', '2024-02-15 09:15:00'),
( 'Phòng chăm sóc sức khỏe', '2023-01-15 09:15:00', NULL),
( 'Phòng nghiên cứu khoa học', '2023-01-15 09:15:00', '2024-01-15 09:15:00');

INSERT INTO room_call ( user_id, room_id, status, started_at, ended_at)
VALUES
( 1, 1, 'Không bắt máy', CURRENT_TIMESTAMP, NULL),
( 2, 1, 'Hoàn tất', '2024-09-25 10:00:00', '2024-09-25 11:00:00'),
( 3, 2, 'Không bắt máy', CURRENT_TIMESTAMP, NULL),
( 4, 2, 'Hoàn tất', '2024-09-25 09:00:00', '2024-09-25 10:00:00'),
( 5, 3, 'Hoàn tất', '2024-09-25 08:00:00', '2024-09-25 09:00:00'),
( 6, 3, 'Không bắt máy', CURRENT_TIMESTAMP, NULL),
(7, 4, 'Không bắt máy', CURRENT_TIMESTAMP, NULL),
( 8, 4, 'Hoàn tất', '2024-09-24 15:00:00', '2024-09-24 16:00:00'),
( 9, 5, 'Hoàn tất', '2024-09-23 14:00:00', '2024-09-23 15:00:00'),
( 10, 5, 'Hoàn tất', '2024-09-23 13:00:00', '2024-09-23 14:00:00'),
( 11, 6, 'Không bắt máy', CURRENT_TIMESTAMP, NULL),
(12, 6, 'Hoàn tất', '2024-09-22 12:00:00', '2024-09-22 13:00:00'),
( 13, 7, 'Hoàn tất', '2024-09-21 11:00:00', '2024-09-21 12:00:00'),
( 14, 7, 'Hoàn tất', '2024-09-20 10:00:00', '2024-09-20 11:00:00'),
( 15, 8, 'Hoàn tất', '2024-09-19 09:00:00', '2024-09-19 10:00:00'),
( 16, 8, 'Hoàn tất', '2024-09-18 08:00:00', '2024-09-18 09:00:00'),
( 17, 9, 'Hoàn tất', '2024-09-17 07:00:00', '2024-09-17 08:00:00'),
( 18, 9, 'Hoàn tất', '2024-09-16 06:00:00', '2024-09-16 07:00:00'),
( 19, 10, 'Hoàn tất', '2024-09-15 05:00:00', '2024-09-15 06:00:00'),
( 20, 10, 'Hoàn tất', '2024-09-14 04:00:00', '2024-09-14 05:00:00');


INSERT INTO message( room_id, user_id, content, sent_at, is_read)
VALUES
(1, 1, 'Xin chào các bạn!', '2024-09-14 04:00:00', 1),
(1, 2, 'Chào bạn!', '2024-09-14 04:10:00', 1),
( 2, 3, 'Mọi người đã chuẩn bị xong chưa?', '2024-01-14 02:07:00', 1),
( 2, 4, 'Tôi đã xong rồi!', '2024-01-14 04:00:00', 1),
( 3, 5, 'Có ai muốn chơi game không?', '2024-04-04 04:00:00', 1),
( 3, 6, 'Chắc chắn rồi!', '2024-04-14 14:28:00', 1),
( 4, 7, 'Chúng ta sẽ thảo luận về vấn đề gì?', '2022-09-19 08:16:34', 1),
( 4, 8, 'Vấn đề bảo mật hệ thống.', '2022-09-20 04:00:00', 1),
( 5, 9, 'Tôi đã gửi tài liệu cho mọi người.', CURRENT_TIMESTAMP, 1),
( 5, 10, 'Cảm ơn bạn!', CURRENT_TIMESTAMP, 1),
( 6, 11, 'Mọi thứ đã chuẩn bị xong.', CURRENT_TIMESTAMP, 1),
(6, 12, 'Tốt lắm.', CURRENT_TIMESTAMP, 1),
( 7, 13, 'Dự án đã tiến triển như thế nào?', CURRENT_TIMESTAMP, 1),
( 7, 14, 'Đang đi đúng kế hoạch.', CURRENT_TIMESTAMP, 1),
(8, 15, 'Khi nào sự kiện sẽ bắt đầu?', CURRENT_TIMESTAMP, 1),
( 8, 16, 'Bắt đầu lúc 10h sáng mai.', CURRENT_TIMESTAMP, 1),
( 9, 17, 'Tôi sẽ tham gia buổi học trực tuyến.', CURRENT_TIMESTAMP, 1),
(9, 18, 'Tôi cũng vậy.', CURRENT_TIMESTAMP, 1),
(10, 19, 'Tôi thích không gian thư giãn ở đây.', CURRENT_TIMESTAMP, 1),
( 10, 20, 'Cảm ơn bạn!', CURRENT_TIMESTAMP, 1);

INSERT INTO relationship_type ( name)
VALUES
( 'Bạn bè'),
('Yêu cầu'),
('Đang chờ xác nhận');

INSERT INTO relationship (user_owner_id, user_referenced_id, relationship_type_id, updated_at)
VALUES
(1, 2, 1, '2022-09-19 08:16:34'),
(2, 1, 2, CURRENT_TIMESTAMP);

INSERT INTO user_room (user_id, room_id)
VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(5, 3),
(6, 3),
(7, 4),
(8, 4),
(9, 5),
(10, 5),
(11, 6),
(12, 6),
(13, 7),
(14, 7),
(15, 8),
(16, 8),
(17, 9),
(18, 9),
(19, 10),
(20, 10);

