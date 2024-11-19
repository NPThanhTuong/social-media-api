package com.one.social_media.config;

import com.one.social_media.entity.*;
import com.one.social_media.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository,
                                        RoleRepository roleRepository,
                                        PostRepository postRepository,
                                        CommentRepository commentRepository,
                                        ReactionRepository reactionRepository,
                                        LikeRepository likeRepository,
                                        RoomRepository roomRepository,
                                        MessageRepository messageRepository,
                                        RelationshipTypeRepository relationshipTypeRepository,
                                        RelationshipRepository relationshipRepository,
                                        UserRoomRepository userRoomRepository
    ) {
        return args -> {
            // Tạo Role
            if (roleRepository.count() == 0) {
                roleRepository.saveAll(List.of(
                        new Role("ADMIN", "Quản trị viên có toàn quyền trên hệ thống"),
                        new Role("USER", "Người dùng thường với quyền hạn hạn chế")
                ));
            }

            var adminRole = roleRepository.findByName("ADMIN").orElseThrow();
            var userRole = roleRepository.findByName("USER").orElseThrow();

            // Tạo user admin
            if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
                var user = User.builder()
                        .email("admin@gmail.com")
                        .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1729995552/no-avatar_nj3kwi.png")
                        .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1729995553/no-image_ow1ois.jpg")
                        .name("Administrator")
                        .phone("0928746784")
                        .role(adminRole)
                        .password(passwordEncoder.encode("admin12345"))
                        .build();

                userRepository.save(user);
                log.info("Admin user created: {}", user.getEmail());
            }

            List<User> users = List.of(
                    User.builder()
                            .email("user@gmail.com")
                            .name("User")
                            .dob(java.sql.Date.valueOf("1990-01-01"))
                            .phone("0912000001")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1729995552/no-avatar_nj3kwi.png")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1729995553/no-image_ow1ois.jpg")
                            .bio("Lập trình viên")
                            .role(userRole)
                            .password(passwordEncoder.encode("user12345"))
                            .build(),
                    User.builder()
                            .email("ttkl92@gmail.com")
                            .name("Trần Thị Kim Liên")
                            .dob(java.sql.Date.valueOf("1992-02-02"))
                            .phone("0912000002")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840200/mtw5iprak3i2bocijieh.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731841048/wacdxwdbbf7pjklsgkfo.jpg")
                            .bio("Nhà thiết kế")
                            .role(userRole)
                            .password(passwordEncoder.encode("12345"))
                            .build(),
                    User.builder()
                            .email("ptmtien@gmail.com")
                            .name("Phạm Thị Mỹ Tiên")
                            .dob(java.sql.Date.valueOf("1994-04-04"))
                            .phone("0912000004")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840229/lbbnok4w6fqug8gqdlq7.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731841087/o9knsbwjevkbclrckmy7.jpg")
                            .bio("Kế toán viên")
                            .role(userRole)
                            .password(passwordEncoder.encode("12345"))
                            .build(),
                    User.builder()
                            .email("tpminh87@gmail.com")
                            .name("Trần Phúc Minh")
                            .dob(java.sql.Date.valueOf("1995-05-05"))
                            .phone("0912000005")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840145/owgw5qd51vwmnewcl7i7.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731841388/ue7axsza92ocswt23pg9.jpg")
                            .bio("Giáo viên")
                            .role(userRole)
                            .password(passwordEncoder.encode("12345"))
                            .build(),
                    User.builder()
                            .email("lmkhai@gmail.com")
                            .name("Lê Minh Khái")
                            .dob(java.sql.Date.valueOf("1996-06-06"))
                            .phone("0912000006")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840262/u4klbcltg4pk4h6chuc0.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731841430/gsdasojgoj50nsllrhfw.jpg")
                            .bio("Bác sĩ")
                            .role(userRole)
                            .password(passwordEncoder.encode("12345"))
                            .build(),
                    User.builder()
                            .email("tvbao840@gmail.com")
                            .name("Trần Văn Bảo")
                            .dob(java.sql.Date.valueOf("1997-07-07"))
                            .phone("0912000007")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840301/eghwou5qrmwbp7p9hmcd.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731841465/j4q4rwrwh1gqvnttfvsf.jpg")
                            .bio("Luật sư")
                            .role(userRole)
                            .password(passwordEncoder.encode("12345"))
                            .build(),
                    User.builder()
                            .email("ntthuy@gmail.com")
                            .name("Nguyễn Trần Thanh Thúy")
                            .dob(java.sql.Date.valueOf("1999-09-09"))
                            .phone("0912000009")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840324/eubp2i16ujken2sqmzcr.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731841523/ntkvy5oqu7rjowqbatam.jpg")
                            .role(userRole)
                            .password(passwordEncoder.encode("12345"))
                            .build(),
                    User.builder()
                            .email("hnhien@gmail.com")
                            .name("Huỳnh Ngọc Hiền")
                            .dob(java.sql.Date.valueOf("1990-10-10"))
                            .phone("0912000010")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840346/ydfvqvztbzrrkf1k8ihy.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731841561/w3p97p0ryyzeyrhkd46g.jpg")
                            .bio("Kỹ sư xây dựng")
                            .role(userRole)
                            .password(passwordEncoder.encode("12345"))
                            .build(),
                    User.builder()
                            .email("tranmanh8h7@gmail.com")
                            .name("Trần Mạnh")
                            .dob(java.sql.Date.valueOf("1991-11-11"))
                            .phone("0912000011")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840373/hkjafxhxios7vxnpsckn.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731841625/mhwcthbrt9eqab2xisug.jpg")
                            .bio("Nhà báo")
                            .role(userRole)
                            .password(passwordEncoder.encode("12345"))
                            .build(),
                    User.builder()
                            .email("John87g@gmail.com")
                            .name("John Nguyễn")
                            .dob(java.sql.Date.valueOf("1992-12-12"))
                            .phone("0912000012")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840401/hv48cmfpduybgxdqykw5.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731841706/xzg03hzjdiqlo26knv5b.jpg")
                            .bio("Nhà nghiên cứu")
                            .role(userRole)
                            .password(passwordEncoder.encode("12345"))
                            .build(),
                    User.builder()
                            .email("slena517@gmail.com")
                            .name("Selena")
                            .dob(java.sql.Date.valueOf("1993-01-13"))
                            .phone("0912000013")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840427/rspmxmpq9y7rnvfd7gck.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731841763/kme33os6px85kydnduy9.jpg")
                            .bio("Nhân viên IT")
                            .role(userRole)
                            .password(passwordEncoder.encode("12345"))
                            .build(),
                    User.builder()
                            .email("minh@gmail.com")
                            .name("Ánh Minh")
                            .dob(java.sql.Date.valueOf("1994-02-14"))
                            .phone("0912000014")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840452/hbz9srzxqtv1zumswbca.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731841812/dkgf5h1drpontkxtzhvx.jpg")
                            .bio("Kỹ sư phần mềm")
                            .role(userRole)
                            .password(passwordEncoder.encode("12345"))
                            .build(),
                    User.builder()
                            .email("hung123@gmail.com")
                            .name("Hoàng Hùng")
                            .dob(java.sql.Date.valueOf("1995-03-15"))
                            .phone("0912000015")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840475/czs6sbucn4bsgupn56og.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731841851/bsgj3vllwdmq7tqt5lu1.jpg")
                            .bio("Kỹ thuật viên")
                            .role(userRole)
                            .password(passwordEncoder.encode("12345"))
                            .build(),
                    User.builder()
                            .email("ptmytruc834@gmail.com")
                            .name("Phan Trần Mỹ Trúc")
                            .dob(java.sql.Date.valueOf("1996-04-16"))
                            .phone("0912000016")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840505/ckns68mn0xsbp8yasqzv.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731842020/debs09uy0qu0ymuknv2m.jpg")
                            .bio("Bác sĩ phẫu thuật")
                            .role(userRole)
                            .password(passwordEncoder.encode("12345"))
                            .build(),
                    User.builder()
                            .email("nguyenvan872@gmail.com")
                            .name("Văn Nguyên")
                            .dob(java.sql.Date.valueOf("1997-05-17"))
                            .phone("0912000017")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840582/nlr3jjtsb5qctdlxozhm.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731842115/j7wdchdr1limooy1mvke.jpg")
                            .bio("Chuyên viên bảo mật")
                            .role(userRole)
                            .password(passwordEncoder.encode("12345"))
                            .build(),
                    User.builder()
                            .email("lengoc77w@gmail.com")
                            .name("Lê Ngọc")
                            .dob(java.sql.Date.valueOf("1998-06-18"))
                            .phone("0912000018")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840742/wctdeunwa2ozjd81ek09.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731842144/dfdericc41skpuvktoaw.jpg")
                            .bio("Quản lý dự án")
                            .role(userRole)
                            .password(passwordEncoder.encode("12345"))
                            .build(),
                    User.builder()
                            .email("sangtran881@gmail.com")
                            .name("Trần Sáng")
                            .dob(java.sql.Date.valueOf("1999-07-19"))
                            .phone("0912000019")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840770/rcjvjl3hoaqzzntjmhls.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731842171/dwva1uokaq9tordigqi0.jpg")
                            .bio("Kỹ sư điện")
                            .role(userRole)
                            .password(passwordEncoder.encode("12345"))
                            .build(),
                    User.builder()
                            .email("phamuyen@gmail.com")
                            .name("Phạm Uyển")
                            .dob(java.sql.Date.valueOf("2000-08-20"))
                            .phone("0912000020")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840843/tpsssbz3xf55ggiwjazf.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731842204/n92yxdgcaywtrfllbjuo.jpg")
                            .bio("Chuyên viên nhân sự")
                            .role(userRole)
                            .password(passwordEncoder.encode("12345"))
                            .build(),
                    User.builder()
                            .email("nnanh124aa@gmail.com")
                            .name("Nguyễn Ngọc Trân")
                            .dob(java.sql.Date.valueOf("1990-01-01"))
                            .phone("0912000001")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840885/pexlwmxvon9gidlblm0f.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731842231/diofj7fhozdpdttmai53.jpg")
                            .bio("Lập trình viên")
                            .role(adminRole)
                            .password(passwordEncoder.encode("12345"))
                            .build(),
                    User.builder()
                            .email("tnanh124@gmail.com")
                            .name("Trần Ngọc Ánh")
                            .dob(java.sql.Date.valueOf("1990-01-01"))
                            .phone("0912000001")
                            .avatar("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731840933/hx7lutu1ytmsi3i5jsh3.jpg")
                            .coverImage("https://res.cloudinary.com/dmcqr73g4/image/upload/v1731842259/uavm4afleh65gkrjr4di.jpg")
                            .bio("Lập trình viên")
                            .role(adminRole)
                            .password(passwordEncoder.encode("12345"))
                            .build()

            );

            users.forEach(user -> {
                if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
                    userRepository.save(user);
                    log.info("User created: {}", user.getEmail());
                }
            });


            // Tạo Post
            if (postRepository.count() == 0) {
                List<Post> posts = List.of(
                        new Post("Bài viết đầu tiên của tôi!", new Date(), new Date(), null, new User(1L)),
                        new Post("Xin chào mọi người!", new Date(), new Date(), null, new User(2L)),
                        new Post("Hôm nay là một ngày tuyệt vời!", new Date(), new Date(), null, new User(3L)),
                        new Post("Ai cũng có thể làm được!", java.sql.Timestamp.valueOf("2024-02-21 20:34:00"), java.sql.Timestamp.valueOf("2024-02-21 20:35:00"), null, new User(1L)),
                        new Post("Cố gắng lên!", new Date(), new Date(), null, new User(5L)),
                        new Post("Mọi thứ sẽ ổn thôi.", new Date(), new Date(), null, new User(6L)),
                        new Post("Sáng nay trời đẹp quá!", new Date(), new Date(), null, new User(7L)),
                        new Post("Tôi rất thích lập trình.", java.sql.Timestamp.valueOf("2024-09-29 23:34:00"), new Date(), null, new User(8L)),
                        new Post("Thử thách là cơ hội để phát triển.", new Date(), new Date(), null, new User(9L)),
                        new Post("Hãy luôn lạc quan.", new Date(), new Date(), null, new User(10L)),
                        new Post("Tôi yêu công nghệ.", new Date(), new Date(), null, new User(11L)),
                        new Post("Bạn có thể làm được!", new Date(), new Date(), null, new User(12L)),
                        new Post("Mọi thứ đều có lý do.", new Date(), new Date(), null, new User(13L)),
                        new Post("Thành công không đến từ may mắn.", new Date(), new Date(), null, new User(14L)),
                        new Post("Cần cù bù thông minh.", new Date(), new Date(), null, new User(15L)),
                        new Post("Hãy tận hưởng cuộc sống.", new Date(), new Date(), null, new User(14L)),
                        new Post("Luôn luôn học hỏi.", java.sql.Timestamp.valueOf("2022-09-22 22:00:00"), new Date(), null, new User(18L)),
                        new Post("Đừng bao giờ bỏ cuộc.", new Date(), new Date(), null, new User(20L)),
                        new Post("Mỗi ngày là một cơ hội mới.", new Date(), new Date(), null, new User(17L)),
                        new Post("Hãy sống như không có ngày mai.", new Date(), new Date(), null, new User(10L)),
                        new Post("Cuộc sống là một món quà.", new Date(), new Date(), null, new User(3L)),
                        new Post("Học lập trình mỗi ngày.", new Date(), new Date(), null, new User(5L)),
                        new Post("Tôi thích uống cà phê sáng.", new Date(), new Date(), null, new User(6L))
                );

                // Thêm hình ảnh liên kết với mỗi bài viết
                posts.get(0).getImages().add(new Image(null, "https://res.cloudinary.com/dmcqr73g4/image/upload/v1731842326/gdypzdgp0bd4iiyq1qup.jpg", posts.get(0)));
                posts.get(0).getImages().add(new Image(null, "https://res.cloudinary.com/dmcqr73g4/image/upload/v1731842362/fuc7a4qtrippmlrikllm.jpg", posts.get(0)));
                posts.get(1).getImages().add(new Image(null, "https://res.cloudinary.com/dmcqr73g4/image/upload/v1731842407/ik6p5muylj3nlshf0nkt.jpg", posts.get(1)));
                posts.get(2).getImages().add(new Image(null, "https://res.cloudinary.com/dmcqr73g4/image/upload/v1731842462/tmelr5uisjov5qmpgekb.jpg", posts.get(2)));
                posts.get(3).getImages().add(new Image(null, "https://res.cloudinary.com/dmcqr73g4/image/upload/v1731842512/sbdqwtbysjt3g7h5iytx.jpg", posts.get(3)));

                // Lưu tất cả bài viết cùng hình ảnh
                postRepository.saveAll(posts);
                log.info("Posts and images have been initialized.");
            }


            //Tạo comment
            if (commentRepository.count() == 0) {
                List<Comment> topLevelComments = List.of(
                        new Comment(
                                "Bài viết rất hay!",
                                new Date(java.sql.Timestamp.valueOf("2023-11-15 09:15:00").getTime()),
                                new Date(java.sql.Timestamp.valueOf("2024-01-15 09:15:00").getTime()),
                                new Date(java.sql.Timestamp.valueOf("2024-07-15 09:15:00").getTime()),
                                postRepository.findById(1L).orElseThrow(),
                                userRepository.findById(1L).orElseThrow(),
                                null
                        ),
                        new Comment(
                                "Cảm ơn bạn đã chia sẻ!",
                                new Date(java.sql.Timestamp.valueOf("2024-01-15 09:15:00").getTime()),
                                new Date(java.sql.Timestamp.valueOf("2024-07-18 18:15:00").getTime()),
                                null,
                                postRepository.findById(2L).orElseThrow(),
                                userRepository.findById(2L).orElseThrow(),
                                null
                        ),
                        new Comment(
                                "Tôi đồng ý với bạn.",
                                new Date(),
                                new Date(),
                                null,
                                postRepository.findById(3L).orElseThrow(),
                                userRepository.findById(3L).orElseThrow(),
                                null
                        ),
                        new Comment(
                                "Rất hữu ích!",
                                new Date(),
                                new Date(),
                                null,
                                postRepository.findById(4L).orElseThrow(),
                                userRepository.findById(4L).orElseThrow(),
                                null
                        ),
                        new Comment(
                                "Tuyệt vời quá!",
                                new Date(java.sql.Timestamp.valueOf("2022-01-15 09:15:00").getTime()),
                                new Date(java.sql.Timestamp.valueOf("2023-07-15 09:15:00").getTime()),
                                null,
                                postRepository.findById(5L).orElseThrow(),
                                userRepository.findById(5L).orElseThrow(),
                                null
                        )
                );

                topLevelComments.forEach(commentRepository::save);

                // Tạo các bình luận con
                List<Comment> childComments = List.of(
                        new Comment(
                                "Tôi đồng ý với bạn!",
                                new Date(),
                                new Date(),
                                null,
                                postRepository.findById(1L).orElseThrow(),
                                userRepository.findById(6L).orElseThrow(),
                                topLevelComments.get(0) // Bình luận con của "Bài viết rất hay!"
                        ),
                        new Comment(
                                "Hoàn toàn chính xác!",
                                new Date(),
                                new Date(),
                                null,
                                postRepository.findById(1L).orElseThrow(),
                                userRepository.findById(7L).orElseThrow(),
                                topLevelComments.get(0)
                        ),
                        new Comment(
                                "Cảm ơn bạn, rất hữu ích.",
                                new Date(),
                                new Date(),
                                null,
                                postRepository.findById(2L).orElseThrow(),
                                userRepository.findById(8L).orElseThrow(),
                                topLevelComments.get(1)
                        ),
                        new Comment(
                                "Tôi cũng nghĩ như vậy.",
                                new Date(),
                                new Date(),
                                null,
                                postRepository.findById(3L).orElseThrow(),
                                userRepository.findById(9L).orElseThrow(),
                                topLevelComments.get(2)
                        ),
                        new Comment(
                                "Quá đúng!",
                                new Date(),
                                new Date(),
                                null,
                                postRepository.findById(4L).orElseThrow(),
                                userRepository.findById(10L).orElseThrow(),
                                topLevelComments.get(3)
                        )
                );

                childComments.forEach(commentRepository::save);

                log.info("Comments and replies have been initialized.");
            }


            // Tạo Reaction
            if (reactionRepository.count() == 0) {
                reactionRepository.saveAll(List.of(
                        new Reaction("Thích"),
                        new Reaction("Yêu thích"),
                        new Reaction("Haha"),
                        new Reaction("Giận dữ"),
                        new Reaction("Buồn"),
                        new Reaction("Ngạc nhiên"),
                        new Reaction("Không thích")
                ));
            }
            var reactions = reactionRepository.findAll();

            // Tạo PostLike
            if (likeRepository.count() == 0) {
                List<Like> likes = List.of(
                        new Like(
                                new LikeKey(1L, 1L),
                                userRepository.findById(1L).orElseThrow(),
                                postRepository.findById(1L).orElseThrow(),
                                reactionRepository.findById(1L).orElseThrow(),
                                java.sql.Timestamp.valueOf("2024-09-29 23:34:00")
                        ),
                        new Like(
                                new LikeKey(2L, 2L),
                                userRepository.findById(2L).orElseThrow(),
                                postRepository.findById(2L).orElseThrow(),
                                reactionRepository.findById(1L).orElseThrow(),
                                java.sql.Timestamp.valueOf("2023-06-26 21:09:00")
                        ),
                        new Like(
                                new LikeKey(3L, 3L),
                                userRepository.findById(3L).orElseThrow(),
                                postRepository.findById(3L).orElseThrow(),
                                reactionRepository.findById(2L).orElseThrow(),
                                java.sql.Timestamp.valueOf("2023-05-22 20:23:00")
                        ),
                        new Like(
                                new LikeKey(4L, 4L),
                                userRepository.findById(4L).orElseThrow(),
                                postRepository.findById(4L).orElseThrow(),
                                reactionRepository.findById(2L).orElseThrow(),
                                java.sql.Timestamp.valueOf("2024-08-26 11:17:00")
                        ),
                        new Like(
                                new LikeKey(5L, 5L),
                                userRepository.findById(5L).orElseThrow(),
                                postRepository.findById(5L).orElseThrow(),
                                reactionRepository.findById(1L).orElseThrow(),
                                java.sql.Timestamp.valueOf("2023-07-27 09:17:00")
                        ),
                        new Like(
                                new LikeKey(6L, 6L),
                                userRepository.findById(6L).orElseThrow(),
                                postRepository.findById(6L).orElseThrow(),
                                reactionRepository.findById(3L).orElseThrow(),
                                java.sql.Timestamp.valueOf("2024-06-28 18:08:00")
                        ),
                        new Like(
                                new LikeKey(7L, 7L),
                                userRepository.findById(7L).orElseThrow(),
                                postRepository.findById(7L).orElseThrow(),
                                reactionRepository.findById(2L).orElseThrow(),
                                java.sql.Timestamp.valueOf("2024-05-18 12:10:00")
                        ),
                        new Like(
                                new LikeKey(8L, 8L),
                                userRepository.findById(8L).orElseThrow(),
                                postRepository.findById(8L).orElseThrow(),
                                reactionRepository.findById(1L).orElseThrow(),
                                java.sql.Timestamp.valueOf("2024-04-30 07:10:00")
                        ),
                        new Like(
                                new LikeKey(9L, 9L),
                                userRepository.findById(9L).orElseThrow(),
                                postRepository.findById(9L).orElseThrow(),
                                reactionRepository.findById(3L).orElseThrow(),
                                java.sql.Timestamp.valueOf("2024-02-22 12:40:00")
                        ),
                        new Like(
                                new LikeKey(10L, 10L),
                                userRepository.findById(10L).orElseThrow(),
                                postRepository.findById(10L).orElseThrow(),
                                reactionRepository.findById(1L).orElseThrow(),
                                java.sql.Timestamp.valueOf("2023-10-22 16:03:00")
                        )

                );

                likes.forEach(likeRepository::save);
                log.info("Post likes have been initialized.");
            }


            //Tạo room
            if (roomRepository.count() == 0) {
                roomRepository.saveAll(List.of(
                        new Room("Phòng họp nhóm lập trình", java.sql.Timestamp.valueOf("2024-09-22 12:00:00"), null),
                        new Room("Phòng thảo luận thiết kế", java.sql.Timestamp.valueOf("2024-10-02 17:00:00"), null),
                        new Room("Phòng giải trí", java.sql.Timestamp.valueOf("2023-11-05 14:30:00"), null),
                        new Room("Phòng hội thảo bảo mật", java.sql.Timestamp.valueOf("2024-01-15 19:15:10"), null),
                        new Room("Phòng dự án ABC", java.sql.Timestamp.valueOf("2023-09-15 20:15:00"), null),
                        new Room("Phòng tư vấn pháp lý", java.sql.Timestamp.valueOf("2024-03-25 09:15:00"), null),
                        new Room("Phòng phát triển sản phẩm", java.sql.Timestamp.valueOf("2022-07-15 09:15:00"), null),
                        new Room("Phòng tổ chức sự kiện", java.sql.Timestamp.valueOf("2024-01-15 20:18:00"), null),
                        new Room("Phòng học trực tuyến", java.sql.Timestamp.valueOf("2021-01-15 09:46:00"), null),
                        new Room("Phòng thư giãn", java.sql.Timestamp.valueOf("2024-12-05 11:14:00"), null),
                        new Room("Phòng hỗ trợ khách hàng", java.sql.Timestamp.valueOf("2024-01-23 02:21:00"), null),
                        new Room("Phòng sáng tạo ý tưởng", java.sql.Timestamp.valueOf("2022-11-13 19:45:10"), null),
                        new Room("Phòng chia sẻ kiến thức", java.sql.Timestamp.valueOf("2023-09-12 23:15:00"), null),
                        new Room("Phòng dự án XYZ", java.sql.Timestamp.valueOf("2024-01-15 09:15:00"), null),
                        new Room("Phòng lập kế hoạch", java.sql.Timestamp.valueOf("2024-03-17 06:55:00"), null),
                        new Room("Phòng học lập trình nâng cao", java.sql.Timestamp.valueOf("2022-02-26 19:13:00"), null),
                        new Room("Phòng phân tích dữ liệu", java.sql.Timestamp.valueOf("2023-11-15 09:15:00"), java.sql.Timestamp.valueOf("2024-01-15 09:15:00")),
                        new Room("Phòng đào tạo nội bộ", java.sql.Timestamp.valueOf("2023-12-15 05:55:00"), java.sql.Timestamp.valueOf("2024-02-15 09:15:00")),
                        new Room("Phòng chăm sóc sức khỏe", java.sql.Timestamp.valueOf("2023-01-15 09:15:00"), null),
                        new Room("Phòng nghiên cứu khoa học", java.sql.Timestamp.valueOf("2023-01-15 09:15:00"), java.sql.Timestamp.valueOf("2024-01-15 09:15:00"))
                ));
                log.info("Rooms have been initialized.");
            }


            var rooms = roomRepository.findAll();

            //Tạo message
            if (messageRepository.count() == 0) {
                List<Message> messages = List.of(
                        new Message(
                                roomRepository.findById(1L).orElseThrow(),
                                userRepository.findById(1L).orElseThrow(),
                                "Xin chào các bạn!",
                                java.sql.Timestamp.valueOf("2024-09-14 04:00:00"),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(1L).orElseThrow(),
                                userRepository.findById(2L).orElseThrow(),
                                "Chào bạn!",
                                java.sql.Timestamp.valueOf("2024-09-14 04:10:00"),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(2L).orElseThrow(),
                                userRepository.findById(3L).orElseThrow(),
                                "Mọi người đã chuẩn bị xong chưa?",
                                java.sql.Timestamp.valueOf("2024-01-14 02:07:00"),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(2L).orElseThrow(),
                                userRepository.findById(4L).orElseThrow(),
                                "Tôi đã xong rồi!",
                                java.sql.Timestamp.valueOf("2024-01-14 04:00:00"),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(3L).orElseThrow(),
                                userRepository.findById(5L).orElseThrow(),
                                "Có ai muốn chơi game không?",
                                java.sql.Timestamp.valueOf("2024-04-04 04:00:00"),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(3L).orElseThrow(),
                                userRepository.findById(6L).orElseThrow(),
                                "Chắc chắn rồi!",
                                java.sql.Timestamp.valueOf("2024-04-14 14:28:00"),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(4L).orElseThrow(),
                                userRepository.findById(7L).orElseThrow(),
                                "Chúng ta sẽ thảo luận về vấn đề gì?",
                                java.sql.Timestamp.valueOf("2022-09-19 08:16:34"),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(4L).orElseThrow(),
                                userRepository.findById(8L).orElseThrow(),
                                "Vấn đề bảo mật hệ thống.",
                                java.sql.Timestamp.valueOf("2022-09-20 04:00:00"),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(5L).orElseThrow(),
                                userRepository.findById(9L).orElseThrow(),
                                "Tôi đã gửi tài liệu cho mọi người.",
                                new Date(),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(5L).orElseThrow(),
                                userRepository.findById(10L).orElseThrow(),
                                "Cảm ơn bạn!",
                                new Date(),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(6L).orElseThrow(),
                                userRepository.findById(11L).orElseThrow(),
                                "Mọi thứ đã chuẩn bị xong.",
                                new Date(),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(6L).orElseThrow(),
                                userRepository.findById(12L).orElseThrow(),
                                "Tốt lắm.",
                                new Date(),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(7L).orElseThrow(),
                                userRepository.findById(13L).orElseThrow(),
                                "Dự án đã tiến triển như thế nào?",
                                new Date(),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(7L).orElseThrow(),
                                userRepository.findById(14L).orElseThrow(),
                                "Đang đi đúng kế hoạch.",
                                new Date(),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(8L).orElseThrow(),
                                userRepository.findById(15L).orElseThrow(),
                                "Khi nào sự kiện sẽ bắt đầu?",
                                new Date(),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(8L).orElseThrow(),
                                userRepository.findById(16L).orElseThrow(),
                                "Bắt đầu lúc 10h sáng mai.",
                                new Date(),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(9L).orElseThrow(),
                                userRepository.findById(17L).orElseThrow(),
                                "Tôi sẽ tham gia buổi học trực tuyến.",
                                new Date(),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(9L).orElseThrow(),
                                userRepository.findById(18L).orElseThrow(),
                                "Tôi cũng vậy.",
                                new Date(),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(10L).orElseThrow(), // Room ID = 10
                                userRepository.findById(19L).orElseThrow(), // User ID = 19
                                "Tôi thích không gian thư giãn ở đây.",
                                new Date(),
                                "Đã gửi"
                        ),
                        new Message(
                                roomRepository.findById(10L).orElseThrow(), // Room ID = 10
                                userRepository.findById(20L).orElseThrow(), // User ID = 20
                                "Cảm ơn bạn!",
                                new Date(),
                                "Đã gửi"
                        )
                );

                messages.forEach(messageRepository::save);
                log.info("Messages have been initialized.");
            }


            // Tạo RelationshipType
            if (relationshipTypeRepository.count() == 0) {
                relationshipTypeRepository.saveAll(List.of(
                        new RelationshipType("Bạn bè"),
                        new RelationshipType("Yêu cầu"),
                        new RelationshipType("Đang chờ xác nhận")
                ));
            }

            var relationshipTypes = relationshipTypeRepository.findAll();

            // Tạo Relationship
            if (relationshipRepository.count() == 0) {
                List<Relationship> relationships = List.of(
                        new Relationship(
                                userRepository.findById(1L).orElseThrow(),
                                userRepository.findById(2L).orElseThrow(),
                                relationshipTypeRepository.findById(1L).orElseThrow(),
                                java.sql.Timestamp.valueOf("2022-09-19 08:16:34")
                        ),
                        new Relationship(
                                userRepository.findById(2L).orElseThrow(),
                                userRepository.findById(1L).orElseThrow(),
                                relationshipTypeRepository.findById(2L).orElseThrow(),
                                new Date() // CURRENT_TIMESTAMP
                        ),
                        new Relationship(
                                userRepository.findById(3L).orElseThrow(),
                                userRepository.findById(4L).orElseThrow(),
                                relationshipTypeRepository.findById(1L).orElseThrow(),
                                java.sql.Timestamp.valueOf("2023-01-15 12:00:00")
                        ),
                        new Relationship(
                                userRepository.findById(4L).orElseThrow(),
                                userRepository.findById(3L).orElseThrow(),
                                relationshipTypeRepository.findById(3L).orElseThrow(),
                                java.sql.Timestamp.valueOf("2023-05-22 15:30:00")
                        ),
                        new Relationship(
                                userRepository.findById(5L).orElseThrow(), // User Owner ID = 5
                                userRepository.findById(6L).orElseThrow(), // User Referenced ID = 6
                                relationshipTypeRepository.findById(2L).orElseThrow(), // Relationship Type ID = 2
                                java.sql.Timestamp.valueOf("2023-12-10 08:15:00") // Updated At
                        ),
                        new Relationship(
                                userRepository.findById(7L).orElseThrow(),
                                userRepository.findById(8L).orElseThrow(),
                                relationshipTypeRepository.findById(1L).orElseThrow(),
                                java.sql.Timestamp.valueOf("2024-03-01 10:45:00")
                        ),
                        new Relationship(
                                userRepository.findById(8L).orElseThrow(),
                                userRepository.findById(7L).orElseThrow(),
                                relationshipTypeRepository.findById(3L).orElseThrow(),
                                new Date() // CURRENT_TIMESTAMP
                        ),
                        new Relationship(
                                userRepository.findById(9L).orElseThrow(),
                                userRepository.findById(10L).orElseThrow(),
                                relationshipTypeRepository.findById(2L).orElseThrow(),
                                java.sql.Timestamp.valueOf("2024-05-20 14:00:00")
                        )
                );

                relationships.forEach(relationshipRepository::save);
                log.info("Relationships have been initialized.");
            }

            // Tạo UserRoom
            if (userRoomRepository.count() == 0) {
                userRoomRepository.saveAll(List.of(
                        new UserRoom(new UserRoomKey(1L, 1L), new User(1L), new Room(1L)),
                        new UserRoom(new UserRoomKey(2L, 1L), new User(2L), new Room(1L)),
                        new UserRoom(new UserRoomKey(3L, 2L), new User(3L), new Room(2L)),
                        new UserRoom(new UserRoomKey(4L, 2L), new User(4L), new Room(2L)),
                        new UserRoom(new UserRoomKey(5L, 3L), new User(5L), new Room(3L)),
                        new UserRoom(new UserRoomKey(6L, 3L), new User(6L), new Room(3L)),
                        new UserRoom(new UserRoomKey(7L, 4L), new User(7L), new Room(4L)),
                        new UserRoom(new UserRoomKey(8L, 4L), new User(8L), new Room(4L)),
                        new UserRoom(new UserRoomKey(9L, 5L), new User(9L), new Room(5L)),
                        new UserRoom(new UserRoomKey(10L, 5L), new User(10L), new Room(5L)),
                        new UserRoom(new UserRoomKey(11L, 6L), new User(11L), new Room(6L)),
                        new UserRoom(new UserRoomKey(12L, 6L), new User(12L), new Room(6L)),
                        new UserRoom(new UserRoomKey(13L, 7L), new User(13L), new Room(7L)),
                        new UserRoom(new UserRoomKey(14L, 7L), new User(14L), new Room(7L)),
                        new UserRoom(new UserRoomKey(15L, 8L), new User(15L), new Room(8L)),
                        new UserRoom(new UserRoomKey(16L, 8L), new User(16L), new Room(8L)),
                        new UserRoom(new UserRoomKey(17L, 9L), new User(17L), new Room(9L)),
                        new UserRoom(new UserRoomKey(18L, 9L), new User(18L), new Room(9L)),
                        new UserRoom(new UserRoomKey(19L, 10L), new User(19L), new Room(10L)),
                        new UserRoom(new UserRoomKey(20L, 10L), new User(20L), new Room(10L))
                ));
            }

        };
    }
}
