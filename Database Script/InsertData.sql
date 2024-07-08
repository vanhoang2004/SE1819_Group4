-- users table
INSERT INTO Users (Username, Password, Fullname, Useremail) VALUES
    	('trang', '{noop}123', 'Vũ Hương Trang', 'huongtrangvu9@gmail.com'),
	('van', '{noop}123', 'Nguyễn Thị Hoàng Vân', 'vannthhe180236@gmail.com'),
	('nqa', '{noop}123', 'Nguyễn Quang Anh', 'anhnqhe181459@fpt.edu.vn'),
	('phuong', '{noop}123', 'Phạm Mai Phương', 'phuongpmhs180289@fpt.edu.vn'),
    	('user1', '{noop}password1', 'John Doe', 'john.doe1@example.com'),
	('user2', '{noop}password2', 'Jane Smith', 'jane.smith2@example.com'),
	('user3', '{noop}password3', 'Alice Johnson', 'alice.johnson3@example.com'),
	('user4', '{noop}password4', 'Bob Brown', 'bob.brown4@example.com'),
	('user5', '{noop}password5', 'Charlie Davis', 'charlie.davis5@example.com'),
	('user6', '{noop}password6', 'Diana Evans', 'diana.evans6@example.com'),
	('user7', '{noop}password7', 'Ethan Foster', 'ethan.foster7@example.com'),
	('user8', '{noop}password8', 'Fiona Green', 'fiona.green8@example.com'),
	('user9', '{noop}password9', 'George Harris', 'george.harris9@example.com'),
	('user10', '{noop}password10', 'Hannah Clark', 'hannah.clark10@example.com'),
	('user11', '{noop}password11', 'Ian Lewis', 'ian.lewis11@example.com'),
	('user12', '{noop}password12', 'Jessica King', 'jessica.king12@example.com'),
	('user13', '{noop}password13', 'Kevin Scott', 'kevin.scott13@example.com'),
	('user14', '{noop}password14', 'Laura Walker', 'laura.walker14@example.com'),
	('user15', '{noop}password15', 'Michael Young', 'michael.young15@example.com'),
	('user16', '{noop}password16', 'Nina Wright', 'nina.wright16@example.com'),
	('user17', '{noop}password17', 'Oliver Hill', 'oliver.hill17@example.com'),
	('user18', '{noop}password18', 'Paula Baker', 'paula.baker18@example.com'),
	('user19', '{noop}password19', 'Quincy White', 'quincy.white19@example.com'),
	('user20', '{noop}password20', 'Rachel Martin', 'rachel.martin20@example.com'),
	('user21', '{noop}password21', 'Samuel Thompson', 'samuel.thompson21@example.com'),
	('user22', '{noop}password22', 'Tina Robinson', 'tina.robinson22@example.com'),
	('user23', '{noop}password23', 'Ursula Clark', 'ursula.clark23@example.com'),
	('user24', '{noop}password24', 'Victor Lewis', 'victor.lewis24@example.com'),
	('user25', '{noop}password25', 'Wendy Lee', 'wendy.lee25@example.com'),
	('user26', '{noop}password26', 'Xander Hall', 'xander.hall26@example.com');


-- 'Subjects' table
INSERT INTO Subjects (Subjectname) VALUES 
('Toán'), 
('Ngữ Văn'), 
('Tiếng Anh'), 
('Địa Lý'), 
('Lịch Sử'), 
('GDCD'), 
('Hóa Học'), 
('Vật Lý'), 
('Sinh Học');

-- ‘Classes’ table
INSERT INTO Classes (Classname) VALUES
    ('12A1'), ('12A3');
    
-- 'Levels' table
INSERT INTO Levels (LevelName) VALUES 
('Nhận biết'), 
('Thông hiểu'), 
('Vận dụng');

-- MockTests
INSERT INTO MockTests (MockTestTitle, SubjectID, Start, End) VALUES
('Thi thử giữa kì', 1, '2024-07-01 09:00:00', '2024-07-08 09:00:00'),
('Thi thử cuối kì', 1, '2024-08-01 09:00:00', '2024-08-08 09:00:00'),
('Thi thử giữa kì', 2, '2024-07-01 09:00:00', '2024-07-08 09:00:00'),
('Thi thử cuối kì', 2, '2024-08-01 09:00:00', '2024-08-08 09:00:00'),
('Thi thử giữa kì', 4, '2024-07-01 09:00:00', '2024-07-08 09:00:00');

-- ‘Chapters’ table
-- Subject 'Toán'
INSERT INTO Chapters (ChapterName, SubjectID) VALUES 
('Sự đồng biến, nghịch biến của hàm số', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Toán')),
('Cực trị của hàm số', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Toán')),
('Giá trị lớn nhất và giá trị nhỏ nhất của hàm số', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Toán')),
('Đường tiệm cận', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Toán')),
('Khảo sát sự biến thiên và vẽ đồ thị của hàm số', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Toán'));

-- Subject 'Tiếng Anh'
INSERT INTO Chapters (ChapterName, SubjectID) VALUES 
('Life stories', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Tiếng Anh')),
('Urbanisation', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Tiếng Anh')),
('The green movement', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Tiếng Anh')),
('The mass media', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Tiếng Anh')),
('Cultural identity', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Tiếng Anh'));

-- Subject 'Ngữ Văn'
INSERT INTO Chapters (ChapterName, SubjectID) VALUES 
('Tuyên Ngôn Độc Lập (Hồ Chí Minh)', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn')),
('Tây tiến (Quang Dũng)', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn')),
('Việt Bắc (Tố Hữu)', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn')),
('Đất nước (Nguyễn Khoa Điềm)', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn')),
('Sóng (Xuân Quỳnh)', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn'));

-- Subject 'Địa Lý'
INSERT INTO Chapters (ChapterName, SubjectID) VALUES 
('Vị trí địa lí, phạm vi lãnh thổ', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý')),
('Lịch sử hình thành và phát triển lãnh thổ', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý')),
('Đất nước nhiều đồi núi', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý')),
('Thiên nhiên chịu ảnh hưởng sâu sắc của biển', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý')),
('Thiên nhiên nhiệt đới ẩm gió mùa', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'));

-- Subject 'Lịch Sử'
INSERT INTO Chapters (ChapterName, SubjectID) VALUES 
('Sự hình thành trật tự thế giới mới sau chiến tranh thế giới thứ hai (1945 – 1949)', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch Sử')),
('Liên Xô và các nước Đông u (1945 - 2000). Liên Bang Nga (1991 - 2000)', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch Sử')),
('Các nước Á, Phi và Mĩ Latinh (1945 - 2000)', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch Sử')),
('Phong trào dân tộc dân chủ ở Việt Nam từ năm 1919 đến năm 1925', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch Sử')),
('Phong trào giải phóng dân tộc và tổng khởi nghĩa tháng Tám (1939-1945). Nước Việt Nam Dân chủ Cộng hòa ra đời', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch Sử'));

-- Subject 'GDCD'
INSERT INTO Chapters (ChapterName, SubjectID) VALUES 
('Pháp luật và đời sống', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'GDCD')),
('Thực hiện pháp luật', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'GDCD')),
('Công dân bình đẳng trước pháp luật', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'GDCD')),
('Quyền bình đẳng của công dân trong một số lĩnh vực đời sống', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'GDCD')),
('Quyền bình đẳng giữa các dân tộc, tôn giáo', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'GDCD'));

-- Subject 'Vật Lý'
INSERT INTO Chapters (ChapterName, SubjectID) VALUES 
('Dao động điều hòa', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Vật Lý')),
('Con lắc lò xo', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Vật Lý')),
('Con lắc đơn', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Vật Lý')),
('Dao động tắt dần. Dao động cưỡng bức', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Vật Lý')),
('Tổng hợp hai dao động điều hòa cùng phương, cùng tần số', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Vật Lý'));

-- Subject 'Hóa Học'
INSERT INTO Chapters (ChapterName, SubjectID) VALUES 
('Este', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Hóa Học')),
('Lipit', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Hóa Học')),
('Khái niệm về xà phòng và chất giặt rửa tổng hợp', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Hóa Học')),
('Luyện tập: Este và chất béo', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Hóa Học')),
('Glucozơ', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Hóa Học'));

-- Subject 'Sinh Học'
INSERT INTO Chapters (ChapterName, SubjectID) VALUES 
('Gen, mã di truyền và quá trình nhân đôi ADN', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh Học')),
('Phiên mã và dịch mã', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh Học')),
('Điều hòa hoạt động gen', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh Học')),
('Đột biến gen', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh Học')),
('Nhiễm sắc thể và đột biến cấu trúc nhiễm sắc thể', (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh Học'));

-- Questions
-- Ngu van de 1
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Việc sử dụng cặp đại từ xưng hô mình – ta có tác dụng?',
    '',
    'Gợi nghĩa tình thân thiết, gắn bó',
    'Là cách gọi quen thuộc trong ca dao dân ca',
    'Cả hai đáp án trên đều đúng',
    'Cả hai đáp án trên đều sai',
    'Cả hai đáp án trên đều đúng',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn'),
    13,
    1,
    1
);
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Biện pháp nghệ thuật được sử dụng ở bốn câu thơ đầu bài thơ Việt Bắc là:',
    '',
    'Nhân hóa',
    'Hoán dụ',
    'Ẩn dụ',
    'Câu hỏi tu từ, điệp từ',
    'Câu hỏi tu từ, điệp từ',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn'),
    13,
    1,
    1
);

INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Hành động “cầm tay” trong câu thơ “Cầm tay nhau biết nói gì hôm nay” thể hiện:',
    '',
    'Sự luyến tiếc giữa chàng trai miền xuôi và cô gái Việt Bắc khi chia tay nhau',
    'Thể hiện tình đồng chí keo sơn, gắn bó, sự sẻ chia, cùng nhau vượt qua khó khăn trong bom đạn',
    'Sự luyến tiếc và nghĩa tình keo sơn gắn bó giữa cách mạng và Việt Bắc, gợi nhớ những cuộc chia tay trong văn học trung đại',
    'Tất cả các đáp án trên',
    'Sự luyến tiếc và nghĩa tình keo sơn gắn bó giữa cách mạng và Việt Bắc, gợi nhớ những cuộc chia tay trong văn học trung đại',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn'),
    13,
    2,
    1
);

INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Câu thơ nào sau đây diễn tả cảm giác trống vắng, gợi nhớ quá khứ sâu nặng. Tác giả mượn cái thừa để nói cái thiếu?',
    '',
    '“– Mình đi, có nhớ những ngày / Mây nguồn suối lũ, những mây cùng mù"',
    '“Mình về, có nhớ chiến khu / Miếng cơm chấm muối, mối thù nặng vai?”',
    '“Mình về, rừng núi nhớ ai / Trám bùi để rụng, măng mai để già”',
    '“Mình đi, có nhớ những nhà / Hắt hiu lau xám, đậm đà lòng son”',
    '“Mình về, rừng núi nhớ ai / Trám bùi để rụng, măng mai để già”',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn'),
    13,
    2,
    1
);

INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Câu thơ nào dưới đây sử dụng biện pháp so sánh?',
    '',
    '“– Ta với mình, mình với ta / Lòng ta sau trước mặn mà đinh ninh"',
    '“Mình đi mình lại nhớ mình / Nguồn bao nhiêu nước, nghĩa tình bấy nhiêu”',
    '“Nhớ từng bản khói cùng sương / Sớm khuya bếp lửa người thương đi về”',
    '“Nhớ từng rừng nứa bờ tre / Ngòi Thia, sông Đáy, suối Lê vơi đầy”',
    '“Mình đi mình lại nhớ mình / Nguồn bao nhiêu nước, nghĩa tình bấy nhiêu”',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn'),
    13,
    3,
    1
);

-- Ngu Van de 2
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Nội dung lời tuyên bố của Hồ Chí Minh khi kết thúc bản Tuyên ngôn Độc lập là:',
    '',
    'Kêu gọi toàn thể quốc dân đồng bào đứng lên đấu tranh đấu tranh với thực dân Pháp để giành quyền làm chủ.',
    'Tuyên bố thoát li hẳn quan hệ thực dân với Pháp, xóa bỏ hết những hiệp ước mà Pháp đã kí về nước Việt Nam, xóa bỏ tất cả mọi đặc quyền của Pháp trên đất nước Việt Nam.',
    'Khẳng định quyền hưởng tự do và độc lập của Việt Nam, đồng thời khẳng định quyết tâm bảo vệ đến cùng nền độc lập.',
    'Khẳng định nhân dân Việt Nam nói riêng và nhân dân ba nước Đông Dương có quyền được hưởng quyền độc lập tự do.',
    'Khẳng định quyền hưởng tự do và độc lập của Việt Nam, đồng thời khẳng định quyết tâm bảo vệ đến cùng nền độc lập.',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn'),
    11,
    1,
    1
);

INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Phương án nào không nêu đúng giá trị lịch sử to lớn của bản Tuyên ngôn Độc lập của Hồ Chí Minh?',
    '',
    'Tuyên ngôn Độc lập là lời tuyên bố xóa bỏ ách đô hộ của thực dân Pháp đối với dân tộc ta suốt hơn 80 năm, xóa bỏ chế độ chế độ phong kiến đã tồn tại hàng nghìn năm trên đất nước ta.',
    'Tuyên ngôn Độc lập thể hiện một cách sâu sắc và hùng hồn tinh thần yêu nước, yêu chuộng độc lập tự do và lí tưởng đấu tranh giải phóng dân tộc của tác giả cũng như của toàn thể dân tộc ta.',
    'Tuyên ngôn Độc lập đã khẳng định nền độc lập tự chủ của dân tộc ta, mở ra một kỉ nguyên độc lập, tự chủ, tiến lên Chủ nghĩa xã hội trên đất nước ta.',
    'Tuyên ngôn Độc lập tuyên bố sự ra đời của nước Việt Nam mới, thoát khỏi thân phận thuộc địa để hòa nhập vào cộng đồng nhân loại với tư cách là một nước độc lập, tự do và dân chủ.',
    'Tuyên ngôn Độc lập thể hiện một cách sâu sắc và hùng hồn tinh thần yêu nước, yêu chuộng độc lập tự do và lí tưởng đấu tranh giải phóng dân tộc của tác giả cũng như của toàn thể dân tộc ta.',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn'),
    11,
    1,
    1
);

INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    '"Tuyên ngôn Độc lập” của Hồ Chí Minh được viết theo thể loại nào sau đây?',
    '',
    'Văn nhật dụng.',
    'Văn chính luận.',
    'Kí',
    'Truyện.',
    'Văn chính luận.',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn'),
    11,
    2,
    1
);

INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Đọc đoạn văn sau và điền từ thích hợp vào dấu [...]: "Thế mà hơn 80 năm nay, bọn thực dân Pháp lợi dụng lá cờ tự do, bình đẳng, bác ái, đến cướp đất nước ta, áp bức đồng bào ta. Hành động của chúng trái hẳn với [...]" (trích Tuyên ngôn Độc lập, Hồ Chí Minh)',
    '',
    '"nhân đạo và chính nghĩa".',
    '"dân chủ và tiến bộ xã hội".',
    '“luật pháp và công lí”',
    '"lẽ phải và công lí".',
    '"nhân đạo và chính nghĩa".',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn'),
    11,
    2,
    1
);
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Chủ tịch Hồ Chí Minh ra đi tìm đường cứu nước năm nào?',
    '',
    '1930',
    '1923',
    '1911',
    '1912',
    '1911',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn'),
    11,
    3,
    1
);
-- dia ly de 1
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Nước ta nằm ở vị trí:',
    '',
    'rìa phía Đông của bán đảo Đông Dương',
    'rìa phía Tây của bán đảo Đông Dương.',
    'trung tâm châu Á',
    'phía đông Đông Nam Á',
    'rìa phía Đông của bán đảo Đông Dương',
    (SELECT SubjectID FROM Subjects WHERE SubjectName = 'Địa Lý'),
    16,
    1,
    1
);

INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Nằm ở rìa phía Đông của bán đảo Đông Dương là nước:',
    '',
    'Lào',
    'Campuchia',
    'Việt Nam',
    'Mi-an-ma',
    'Việt Nam',
    (SELECT SubjectID FROM Subjects WHERE SubjectName = 'Địa Lý'),
    16,
    1,
    1
);

INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Điểm cực Bắc của nước ta là xã Lũng Cú thuộc tỉnh:',
    '',
    'Cao Bằng',
    'Hà Giang',
    'Yên Bái',
    'Lạng Sơn',
    'Hà Giang',
    (SELECT SubjectID FROM Subjects WHERE SubjectName = 'Địa Lý'),
    16,
    2,
    1
);

INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Vị trí địa lí của nước ta là:',
    '',
    'nằm ở phía Đông bán đảo Đông Dương, gần trung tâm khu vực Đông Nam Á',
    'nằm ở phía Tây bán đảo Đông Dương, gần trung tâm khu vực Đông Nam Á',
    'nằm ở phía Đông bán đảo Đông Dương, gần trung tâm khu vực châu Á',
    'nằm ở phía Tây bán đảo Đông Dương, gần trung tâm khu vực châu Á',
    'nằm ở phía Đông bán đảo Đông Dương, gần trung tâm khu vực Đông Nam Á',
    (SELECT SubjectID FROM Subjects WHERE SubjectName = 'Địa Lý'),
    16,
    2,
    1
);

INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Điểm cực Đông của nước ta là xã Vạn Thạnh thuộc tỉnh:',
    '',
    'Ninh Thuận',
    'Khánh Hòa',
    'Đà Nẵng',
    'Phú Yên',
    'Khánh Hòa',
    (SELECT SubjectID FROM Subjects WHERE SubjectName = 'Địa Lý'),
    16,
    3,
    1
);

-- Toan de 1
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Tính giá trị biểu thức',
    'https://www.vietjack.com/bai-tap-trac-nghiem-giai-tich-12/images/bai-tap-trac-nghiem-luy-thua-phan-1.PNG',
    'A',
    'B',
    'C',
    'D',
    'D',
    (SELECT SubjectID FROM Subjects WHERE SubjectName = 'Toán'),
    1,
    1,
    1
);

INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Nếu x > y > 0 thì',
    'https://www.vietjack.com/bai-tap-trac-nghiem-giai-tich-12/images/bai-tap-trac-nghiem-luy-thua-phan-1-6.PNG',
    'A',
    'B',
    'C',
    'D',
    'D',
    (SELECT SubjectID FROM Subjects WHERE SubjectName = 'Toán'),
    1,
    1,
    1
);

INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Tính giá trị của biểu thức',
    'https://www.vietjack.com/bai-tap-trac-nghiem-giai-tich-12/images/bai-tap-trac-nghiem-luy-thua-phan-2-1.PNG',
    'A',
    'B',
    'C',
    'D',
    'D',
    (SELECT SubjectID FROM Subjects WHERE SubjectName = 'Toán'),
    1,
    2,
    1
);

INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Giá trị của biểu thức nào sau đây bằng 0,0000000375?',
    'https://www.vietjack.com/bai-tap-trac-nghiem-giai-tich-12/images/bai-tap-trac-nghiem-luy-thua-phan-2-2.PNG',
    'A',
    'B',
    'C',
    'D',
    'C',
    (SELECT SubjectID FROM Subjects WHERE SubjectName = 'Toán'),
    1,
    2,
    1
);

INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Rút gọn biểu thức',
    'https://www.vietjack.com/bai-tap-trac-nghiem-giai-tich-12/images/bai-tap-trac-nghiem-luy-thua-phan-2-3.PNG',
    'A',
    'B',
    'C',
    'D',
    'D',
    (SELECT SubjectID FROM Subjects WHERE SubjectName = 'Toán'),
    1,
    3,
    1
);
-- toan de 2
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Cho hàm số y = f(x) có đồ thị như hình vẽ. Điểm cực đại của đồ thị hàm số là',
    'https://www.vietjack.com/bai-tap-trac-nghiem-giai-tich-12/images/bai-tap-trac-nghiem-cuc-tri-cua-ham-so-phan-1.PNG',
    'M(0; 2)',
    'N(-2; -14)',
    'P(2; -14)',
    'N(-2; -14) và P(2; -14)',
    'M(0; 2)',
    (SELECT SubjectID FROM Subjects WHERE SubjectName = 'Toán'),
    3,
    1,
    1
);

INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Cho hàm số y = f(x) xác định, liên tục trên R và có bảng biến thiên. Mệnh đề nào sau đây là đúng?',
    'https://www.vietjack.com/bai-tap-trac-nghiem-giai-tich-12/images/bai-tap-trac-nghiem-cuc-tri-cua-ham-so-phan-1-1.PNG',
    'Hàm số có đúng hai cực trị',
    'Hàm số có điểm cực tiểu là -2',
    'Hàm số có giá trị cực đại bằng 0.',
    'Hàm số đạt cực đại tại x = 0 đạt cực tiểu tại x = -1 và x = 1',
    'Hàm số đạt cực đại tại x = 0 đạt cực tiểu tại x = -1 và x = 1',
    (SELECT SubjectID FROM Subjects WHERE SubjectName = 'Toán'),
    3,
    1,
    1
);

INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Giá trị lớn nhất của hàm số đạt được khi x nhận giá trị bằng:',
    'https://www.vietjack.com/bai-tap-trac-nghiem-giai-tich-12/images/bai-tap-trac-nghiem-gia-tri-lon-nhat-va-nho-nhat-cua-ham-so-phan-1-1.PNG',
    '1',
    '5',
    '0',
    'Không có đáp án',
    'Không có đáp án',
    (SELECT SubjectID FROM Subjects WHERE SubjectName = 'Toán'),
    3,
    2,
    1
);

INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'GTLN của hàm số trên khoảng (0; 4) đạt được',
    'https://www.vietjack.com/bai-tap-trac-nghiem-giai-tich-12/images/bai-tap-trac-nghiem-gia-tri-lon-nhat-va-nho-nhat-cua-ham-so-phan-2-1.PNG',
    'x = 1',
    'x = -1',
    'x = √2',
    'Không tồn tại',
    'x = 1',
    (SELECT SubjectID FROM Subjects WHERE SubjectName = 'Toán'),
    3,
    2,
    1
);

INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Một hành lang giữa hai tòa tháp có hình dạng một hình lăng trụ đứng. Hai mặt bên ABB’A’ và ACC’A’ là hai tấm kính hình chữ nhật dài 20m, rộng 5m. Với độ dài xấp xỉ nào của BC thì thể tích hành lang này lớn nhất',
    'https://www.vietjack.com/bai-tap-trac-nghiem-giai-tich-12/images/bai-tap-trac-nghiem-gia-tri-lon-nhat-va-nho-nhat-cua-ham-so-phan-2-7.PNG',
    '6m',
    '7m',
    '8m',
    '9m',
    '7m',
    (SELECT SubjectID FROM Subjects WHERE SubjectName = 'Toán'),
    3,
    3,
    1
);

-- Su
-- chapter 21
-- Câu 1
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Hội nghị nào dưới đây đã đánh dấu sự hình thành trật tự thế giới mới sau Chiến tranh thế giới thứ hai?',
    '',
    'Hội nghị Ianta.',
    'Hội nghị Potsdam.',
    'Hội nghị Tehran.',
    'Hội nghị San Francisco.',
    'Hội nghị Ianta.',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    2,
    0
);

-- Câu 2
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Kế hoạch Marshall của Mỹ nhằm mục đích gì sau Chiến tranh thế giới thứ hai?',
    '',
    'Tái thiết các nước châu Âu bị tàn phá bởi chiến tranh.',
    'Ngăn chặn sự lan rộng của chủ nghĩa cộng sản.',
    'Thiết lập ảnh hưởng kinh tế của Mỹ ở châu Âu.',
    'Tất cả các mục đích trên.',
    'Tất cả các mục đích trên.',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    2,
    0
);

-- Câu 3
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Hiệp ước Bắc Đại Tây Dương (NATO) được thành lập vào năm nào?',
    '',
    '1947',
    '1948',
    '1949',
    '1950',
    '1949',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    2,
    0
);

-- Câu 4
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Sự kiện nào đánh dấu sự khởi đầu của Chiến tranh Lạnh?',
    '',
    'Thành lập Liên Hợp Quốc.',
    'Thành lập NATO.',
    'Thông điệp Truman.',
    'Kế hoạch Marshall.',
    'Thông điệp Truman.',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    2,
    0
);

-- Câu 5
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Hội nghị Ianta đã đưa ra những quyết định gì quan trọng?',
    '',
    'Phân chia phạm vi ảnh hưởng ở châu Âu và châu Á.',
    'Thành lập Liên Hợp Quốc.',
    'Thỏa thuận về việc chiếm đóng nước Đức sau chiến tranh.',
    'Tất cả các quyết định trên.',
    'Tất cả các quyết định trên.',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    2,
    0
);

-- Câu 6
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Tại sao Kế hoạch Marshall được coi là một công cụ trong Chiến tranh Lạnh?',
    '',
    'Nó giúp các nước Tây Âu phục hồi kinh tế.',
    'Nó củng cố ảnh hưởng của Mỹ ở Tây Âu.',
    'Nó ngăn chặn sự lan rộng của chủ nghĩa cộng sản.',
    'Cả ba lý do trên.',
    'Cả ba lý do trên.',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    2,
    0
);

-- Câu 7
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Hiến chương Liên Hợp Quốc được thông qua tại hội nghị nào?',
    '',
    'Hội nghị Ianta.',
    'Hội nghị San Francisco.',
    'Hội nghị Potsdam.',
    'Hội nghị Tehran.',
    'Hội nghị San Francisco.',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    2,
    0
);

-- Câu 8
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Tổ chức Liên Hợp Quốc ra đời nhằm mục đích gì?',
    '',
    'Duy trì hòa bình và an ninh quốc tế.',
    'Phát triển quan hệ hữu nghị giữa các quốc gia.',
    'Thúc đẩy hợp tác quốc tế về kinh tế, văn hóa, xã hội.',
    'Tất cả các mục đích trên.',
    'Tất cả các mục đích trên.',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    2,
    0
);

-- Câu 9
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Học thuyết Truman được Mỹ đưa ra nhằm mục đích gì?',
    '',
    'Ngăn chặn sự lan rộng của chủ nghĩa cộng sản.',
    'Hỗ trợ tài chính cho các nước Tây Âu.',
    'Tăng cường sức mạnh quân sự của Mỹ.',
    'Phát triển kinh tế ở các nước đồng minh.',
    'Ngăn chặn sự lan rộng của chủ nghĩa cộng sản.',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    2,
    0
);

-- Câu 10
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Sự kiện nào dưới đây không liên quan đến quá trình hình thành trật tự thế giới mới sau Chiến tranh thế giới thứ hai?',
    '',
    'Thành lập Liên Hợp Quốc.',
    'Thông điệp Truman.',
    'Kế hoạch Marshall.',
    'Hội nghị Bandung.',
    'Hội nghị Bandung.',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    2,
    0
);
-- Câu 1
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Hội nghị Ianta diễn ra vào thời gian nào?',
    '',
    'Tháng 2 năm 1945',
    'Tháng 3 năm 1945',
    'Tháng 4 năm 1945',
    'Tháng 5 năm 1945',
    'Tháng 2 năm 1945',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    1,
    0
);

-- Câu 2
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Tổ chức Liên Hợp Quốc chính thức ra đời vào ngày nào?',
    '',
    '24 tháng 10 năm 1945',
    '25 tháng 10 năm 1945',
    '26 tháng 10 năm 1945',
    '27 tháng 10 năm 1945',
    '24 tháng 10 năm 1945',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    1,
    0
);

-- Câu 3
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Kế hoạch Marshall được Mỹ công bố vào năm nào?',
    '',
    '1947',
    '1948',
    '1949',
    '1950',
    '1947',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    1,
    0
);

-- Câu 4
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Mục tiêu chính của Kế hoạch Marshall là gì?',
    '',
    'Tái thiết kinh tế châu Âu sau chiến tranh',
    'Ngăn chặn sự lan rộng của chủ nghĩa cộng sản',
    'Tăng cường sức mạnh quân sự của Mỹ',
    'Phát triển khoa học công nghệ',
    'Tái thiết kinh tế châu Âu sau chiến tranh',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    1,
    0
);

-- Câu 5
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Hiến chương Liên Hợp Quốc được thông qua tại hội nghị nào?',
    '',
    'Hội nghị San Francisco',
    'Hội nghị Ianta',
    'Hội nghị Potsdam',
    'Hội nghị Tehran',
    'Hội nghị San Francisco',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    1,
    0
);

-- Câu 6
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Mục đích của việc thành lập Liên Hợp Quốc là gì?',
    '',
    'Duy trì hòa bình và an ninh quốc tế',
    'Phát triển quan hệ hữu nghị giữa các quốc gia',
    'Thúc đẩy hợp tác quốc tế về kinh tế, văn hóa, xã hội',
    'Tất cả các mục đích trên',
    'Tất cả các mục đích trên',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    1,
    0
);

-- Câu 7
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'NATO là viết tắt của tổ chức nào?',
    '',
    'Hiệp ước Bắc Đại Tây Dương',
    'Tổ chức Hiệp ước Đông Nam Á',
    'Liên minh quân sự châu Âu',
    'Hiệp ước Trung Đông',
    'Hiệp ước Bắc Đại Tây Dương',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    1,
    0
);

-- Câu 8
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Sự kiện nào đánh dấu sự khởi đầu của Chiến tranh Lạnh?',
    '',
    'Thông điệp Truman',
    'Kế hoạch Marshall',
    'Thành lập NATO',
    'Thành lập Liên Hợp Quốc',
    'Thông điệp Truman',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    1,
    0
);

-- Câu 9
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Hội nghị Ianta quyết định điều gì?',
    '',
    'Phân chia phạm vi ảnh hưởng ở châu Âu và châu Á',
    'Thành lập Liên Hợp Quốc',
    'Thỏa thuận về việc chiếm đóng nước Đức',
    'Tất cả các quyết định trên',
    'Tất cả các quyết định trên',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    1,
    0
);

-- Câu 10
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Hiến chương Liên Hợp Quốc được thông qua tại hội nghị nào?',
    '',
    'Hội nghị San Francisco',
    'Hội nghị Ianta',
    'Hội nghị Potsdam',
    'Hội nghị Tehran',
    'Hội nghị San Francisco',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    1,
    0
);

-- Câu 1
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Hiệp định Potsdam được ký kết nhằm mục đích gì?',
    '',
    'Phân chia nước Đức thành các khu vực chiếm đóng',
    'Thành lập Liên Hợp Quốc',
    'Quyết định các vấn đề sau chiến tranh ở châu Âu',
    'Thành lập NATO',
    'Phân chia nước Đức thành các khu vực chiếm đóng',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    3,
    0
);

-- Câu 2
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Mục tiêu chính của Kế hoạch Marshall là gì?',
    '',
    'Ngăn chặn sự lan rộng của chủ nghĩa cộng sản tại châu Âu',
    'Xây dựng lại kinh tế châu Âu',
    'Hỗ trợ các nước châu Âu sau chiến tranh',
    'Tất cả các mục tiêu trên',
    'Tất cả các mục tiêu trên',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    3,
    0
);

-- Câu 3
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Hội nghị nào đánh dấu sự khởi đầu của trật tự thế giới mới sau chiến tranh thế giới thứ hai?',
    '',
    'Hội nghị Ianta',
    'Hội nghị Potsdam',
    'Hội nghị San Francisco',
    'Hội nghị Tehran',
    'Hội nghị Ianta',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    3,
    0
);

-- Câu 4
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Mục tiêu của Liên Xô khi tham gia Hội nghị Ianta là gì?',
    '',
    'Đảm bảo an ninh cho biên giới phía Tây của Liên Xô',
    'Giúp đỡ các nước Đông Âu thiết lập chính phủ thân Liên Xô',
    'Chia sẻ quyền lực với Mỹ và Anh ở châu Âu',
    'Tất cả các mục tiêu trên',
    'Tất cả các mục tiêu trên',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    3,
    0
);

-- Câu 5
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Tại sao việc thành lập Liên Hợp Quốc được coi là một bước ngoặt lịch sử?',
    '',
    'Đánh dấu sự hợp tác quốc tế rộng lớn',
    'Tạo ra một tổ chức quốc tế duy trì hòa bình',
    'Thúc đẩy hợp tác kinh tế, xã hội toàn cầu',
    'Tất cả các lý do trên',
    'Tất cả các lý do trên',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    3,
    0
);

-- Câu 6
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Điểm chung giữa Kế hoạch Marshall và Kế hoạch Truman là gì?',
    '',
    'Ngăn chặn sự lan rộng của chủ nghĩa cộng sản',
    'Tái thiết kinh tế châu Âu',
    'Hỗ trợ tài chính cho các nước Tây Âu',
    'Thúc đẩy hòa bình và an ninh quốc tế',
    'Ngăn chặn sự lan rộng của chủ nghĩa cộng sản',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    3,
    0
);

-- Câu 7
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Sự ra đời của NATO phản ánh điều gì về tình hình thế giới sau chiến tranh?',
    '',
    'Sự gia tăng căng thẳng giữa các cường quốc',
    'Xu hướng hợp tác quân sự giữa các quốc gia',
    'Mối đe dọa từ chủ nghĩa cộng sản',
    'Tất cả các ý trên',
    'Tất cả các ý trên',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    3,
    0
);

-- Câu 8
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Vì sao Liên Xô không tham gia vào Kế hoạch Marshall?',
    '',
    'Không đồng ý với điều kiện của Mỹ',
    'Lo ngại sự ảnh hưởng của Mỹ ở châu Âu',
    'Muốn tự lực tái thiết kinh tế',
    'Tất cả các lý do trên',
    'Tất cả các lý do trên',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    3,
    0
);

-- Câu 9
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Tổ chức Liên Hợp Quốc có bao nhiêu thành viên sáng lập ban đầu?',
    '',
    '50',
    '51',
    '52',
    '53',
    '51',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    3,
    0
);

-- Câu 10
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Nội dung nào dưới đây phản ánh đúng nhất về trật tự thế giới mới sau chiến tranh thế giới thứ hai?',
    '',
    'Sự phân chia ảnh hưởng giữa các cường quốc',
    'Sự hợp tác giữa các quốc gia trong Liên Hợp Quốc',
    'Sự gia tăng căng thẳng giữa các khối quân sự',
    'Tất cả các nội dung trên',
    'Tất cả các nội dung trên',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    21,
    3,
    0
);
-- Chapter 22
-- Câu 1
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Hiệp ước Warszawa được thành lập vào năm nào?',
    '',
    '1954',
    '1955',
    '1956',
    '1957',
    '1955',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    22,
    3,
    0
);

-- Câu 2
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Nguyên nhân chính dẫn đến sự sụp đổ của Liên Xô vào năm 1991 là gì?',
    '',
    'Kinh tế suy thoái nghiêm trọng',
    'Chính trị mất ổn định và khủng hoảng',
    'Phong trào đòi độc lập tại các nước cộng hòa',
    'Tất cả các nguyên nhân trên',
    'Tất cả các nguyên nhân trên',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    22,
    3,
    0
);

-- Câu 3
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Sự kiện nào đánh dấu sự tan rã của khối Warszawa?',
    '',
    'Cách mạng Nhung ở Tiệp Khắc (1989)',
    'Sự sụp đổ của Bức tường Berlin (1989)',
    'Hiệp định giải thể khối Warszawa (1991)',
    'Sự tan rã của Liên Xô (1991)',
    'Hiệp định giải thể khối Warszawa (1991)',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    22,
    3,
    0
);

-- Câu 4
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Tại sao chính sách Perestroika và Glasnost của Gorbachev không thành công?',
    '',
    'Không đạt được sự đồng thuận từ các nhà lãnh đạo',
    'Khủng hoảng kinh tế và xã hội trầm trọng',
    'Sự phản đối từ các nước cộng hòa thuộc Liên Xô',
    'Tất cả các lý do trên',
    'Tất cả các lý do trên',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    22,
    3,
    0
);

-- Câu 5
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Sau khi Liên Xô tan rã, nước nào trở thành quốc gia kế thừa hợp pháp của Liên Xô tại Liên Hợp Quốc?',
    '',
    'Ukraine',
    'Kazakhstan',
    'Liên bang Nga',
    'Belarus',
    'Liên bang Nga',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    22,
    3,
    0
);

-- Câu 6
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Đặc điểm nổi bật của nền kinh tế Liên bang Nga trong giai đoạn 1991-2000 là gì?',
    '',
    'Phát triển mạnh mẽ và ổn định',
    'Khủng hoảng và suy thoái nghiêm trọng',
    'Phụ thuộc vào đầu tư nước ngoài',
    'Tăng trưởng nhanh chóng và bền vững',
    'Khủng hoảng và suy thoái nghiêm trọng',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    22,
    3,
    0
);

-- Câu 7
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Nguyên nhân chủ yếu khiến các nước Đông Âu chuyển đổi từ chế độ xã hội chủ nghĩa sang chế độ tư bản chủ nghĩa là gì?',
    '',
    'Áp lực từ Liên Xô',
    'Áp lực từ phương Tây',
    'Sự thất bại của mô hình kinh tế xã hội chủ nghĩa',
    'Sự can thiệp quân sự từ NATO',
    'Sự thất bại của mô hình kinh tế xã hội chủ nghĩa',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    22,
    3,
    0
);

-- Câu 8
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Sự kiện nào đánh dấu sự kết thúc của Chiến tranh Lạnh?',
    '',
    'Hiệp định Paris về Việt Nam (1973)',
    'Hiệp định Helsinki (1975)',
    'Cuộc gặp thượng đỉnh Malta (1989)',
    'Sự tan rã của Liên Xô (1991)',
    'Cuộc gặp thượng đỉnh Malta (1989)',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    22,
    3,
    0
);

-- Câu 9
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Chính sách đối ngoại của Liên bang Nga trong thập kỷ 1990 tập trung vào điều gì?',
    '',
    'Mở rộng ảnh hưởng tại Đông Âu',
    'Tăng cường hợp tác với phương Tây',
    'Giảm sự phụ thuộc vào các nước phương Tây',
    'Khôi phục quyền lực của Liên Xô cũ',
    'Tăng cường hợp tác với phương Tây',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    22,
    3,
    0
);

-- Câu 10
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Sự kiện nào dưới đây là một phần trong chính sách Cải tổ của Gorbachev?',
    '',
    'Bỏ cấm vận chống Cuba',
    'Rút quân khỏi Afghanistan',
    'Thiết lập quan hệ ngoại giao với Việt Nam',
    'Phát động Chiến tranh Lạnh',
    'Rút quân khỏi Afghanistan',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    22,
    3,
    0
);
-- chapter 23
-- Câu 1
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Phong trào giải phóng dân tộc ở châu Phi diễn ra mạnh mẽ nhất vào thời gian nào?',
    '',
    'Thập niên 1940',
    'Thập niên 1950',
    'Thập niên 1960',
    'Thập niên 1970',
    'Thập niên 1960',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    23,
    1,
    0
);

-- Câu 2
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Đất nước nào ở Đông Nam Á giành được độc lập đầu tiên sau Chiến tranh thế giới thứ hai?',
    '',
    'Việt Nam',
    'Indonesia',
    'Philippines',
    'Malaysia',
    'Indonesia',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    23,
    1,
    0
);

-- Câu 3
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Sự kiện nào đánh dấu sự chấm dứt của chế độ Apartheid ở Nam Phi?',
    '',
    'Cuộc bầu cử dân chủ đầu tiên (1994)',
    'Sự thành lập của Đại hội Dân tộc Phi (ANC)',
    'Nelson Mandela được thả tự do',
    'Tất cả các sự kiện trên',
    'Cuộc bầu cử dân chủ đầu tiên (1994)',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    23,
    1,
    0
);

-- Câu 4
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Phong trào không liên kết được thành lập tại hội nghị nào?',
    '',
    'Hội nghị Bandung',
    'Hội nghị Genève',
    'Hội nghị San Francisco',
    'Hội nghị Yalta',
    'Hội nghị Bandung',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    23,
    1,
    0
);

-- Câu 5
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Ai là lãnh tụ của cuộc cách mạng Cuba năm 1959?',
    '',
    'Che Guevara',
    'Fidel Castro',
    'Raul Castro',
    'Camilo Cienfuegos',
    'Fidel Castro',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    23,
    1,
    0
);

-- Câu 6
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Đất nước nào ở châu Phi được biết đến với tên gọi "Quê hương của nền văn minh nhân loại"?',
    '',
    'Ai Cập',
    'Ethiopia',
    'Nam Phi',
    'Ghana',
    'Ai Cập',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    23,
    1,
    0
);

-- Câu 7
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Nhà lãnh đạo nổi tiếng nào của Ấn Độ đã phát động phong trào bất bạo động chống thực dân Anh?',
    '',
    'Mahatma Gandhi',
    'Jawaharlal Nehru',
    'Subhas Chandra Bose',
    'Rajendra Prasad',
    'Mahatma Gandhi',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    23,
    1,
    0
);

-- Câu 8
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Quốc gia nào ở Mỹ Latinh đã trở thành nước xã hội chủ nghĩa đầu tiên và duy nhất vào nửa sau thế kỷ XX?',
    '',
    'Mexico',
    'Cuba',
    'Brazil',
    'Argentina',
    'Cuba',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    23,
    1,
    0
);

-- Câu 9
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Chính sách "Đổi mới" ở Việt Nam được khởi xướng vào năm nào?',
    '',
    '1985',
    '1986',
    '1987',
    '1988',
    '1986',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    23,
    1,
    0
);

-- Câu 10
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Sự kiện nào đánh dấu sự thống nhất của đất nước Việt Nam?',
    '',
    'Chiến dịch Hồ Chí Minh (1975)',
    'Hiệp định Paris (1973)',
    'Phong trào "Đổi mới" (1986)',
    'Tuyên ngôn Độc lập (1945)',
    'Chiến dịch Hồ Chí Minh (1975)',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    23,
    1,
    0
);
-- chapter 24
-- Câu 1
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Sự kiện nào đánh dấu sự ra đời của Đảng Cộng sản Việt Nam?',
    '',
    'Cuộc họp lần đầu tiên tại Kỳ trân nhà Thanh',
    'Cuộc họp tại Bạch Đằng',
    'Cuộc họp tại Hà Nội',
    'Cuộc họp tại Tân Trào',
    'Cuộc họp lần đầu tiên tại Kỳ trân nhà Thanh',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    24,
    1,
    0
);

-- Câu 2
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Ai là người sáng lập ra Đảng Cộng sản Việt Nam?',
    '',
    'Nguyễn Ái Quốc',
    'Hồ Chí Minh',
    'Phan Bội Châu',
    'Võ Nguyên Giáp',
    'Nguyễn Ái Quốc',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    24,
    1,
    0
);

-- Câu 3
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Sự kiện nào được coi là lễ khai sinh của phong trào dân tộc đấu tranh chống phong kiến, thực dân tại Việt Nam?',
    '',
    'Biên Bản cuộc họp Ban Dân chủ',
    'Cuộc họp Hội nghị Bác Ái',
    'Thành lập Hội Việt Nam Cách mạng Thanh niên',
    'Xuất bản tờ báo Tiền phong',
    'Thành lập Hội Việt Nam Cách mạng Thanh niên',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    24,
    1,
    0
);

-- Câu 4
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Ai là người sáng lập ra Việt Nam Quốc dân đảng?',
    '',
    'Nguyễn Ái Quốc',
    'Hồ Chí Minh',
    'Phan Bội Châu',
    'Võ Nguyên Giáp',
    'Phan Bội Châu',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    24,
    1,
    0
);

-- Câu 5
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Cuộc họp lịch sử nào được xem là lễ khai sinh của phong trào dân chủ mới ở Việt Nam?',
    '',
    'Hội nghị Phan Chu Trinh',
    'Hội nghị Nghệ An',
    'Hội nghị Nấm Lá',
    'Hội nghị Bạch Đằng',
    'Hội nghị Phan Chu Trinh',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    24,
    1,
    0
);

-- Câu 6
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Cuộc họp nào được coi là lễ khai sinh của phong trào dân chủ Việt Nam vào năm 1925?',
    '',
    'Hội nghị Nghệ An',
    'Hội nghị Bạch Đằng',
    'Hội nghị Nấm Lá',
    'Hội nghị Phan Chu Trinh',
    'Hội nghị Nghệ An',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    24,
    1,
    0
);

-- Câu 7
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Cuộc họp nào được coi là lễ khai sinh của phong trào dân chủ mới ở Việt Nam vào năm 1919?',
    '',
    'Hội nghị Tháng Tám',
    'Hội nghị Phan Chu Trinh',
    'Hội nghị Tháng Mười',
    'Hội nghị Bạch Đằng',
    'Hội nghị Tháng Tám',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    24,
    1,
    0
);

-- chapter 25
-- Câu 1
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Ai là người đọc tuyên ngôn độc lập tại lễ kỷ niệm tự do tại quảng trường Ba Đình ngày 2 tháng 9 năm 1945?',
    '',
    'Hồ Chí Minh',
    'Nguyễn Ái Quốc',
    'Võ Nguyên Giáp',
    'Phan Bội Châu',
    'Hồ Chí Minh',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    25,
    2,
    0
);

-- Câu 2
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Thành lập nước Việt Nam Dân chủ Cộng hòa vào ngày nào?',
    '',
    '19/08/1945',
    '28/08/1945',
    '02/09/1945',
    '30/08/1945',
    '02/09/1945',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    25,
    2,
    0
);

-- Câu 3
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Tuyên ngôn nào được đọc tại quảng trường Ba Đình ngày 2 tháng 9 năm 1945?',
    '',
    'Tuyên ngôn độc lập',
    'Tuyên ngôn thống nhất dân tộc',
    'Tuyên ngôn chiến thắng',
    'Tuyên ngôn cách mạng',
    'Tuyên ngôn độc lập',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    25,
    2,
    0
);

-- Câu 4
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Vị tướng nào là người lãnh đạo quân đội Việt Nam Dân chủ Cộng hòa trong thời kỳ kháng chiến chống Pháp và Mỹ?',
    '',
    'Võ Nguyên Giáp',
    'Nguyễn Bình',
    'Hồ Chí Minh',
    'Trần Hưng Đạo',
    'Võ Nguyên Giáp',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    25,
    2,
    0
);

-- Câu 5
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Thành lập nước Việt Nam Dân chủ Cộng hòa có ý nghĩa như thế nào đối với dân tộc Việt Nam?',
    '',
    'Là sự thể hiện quyền tự chủ, độc lập của dân tộc',
    'Là sự lãnh đạo của cách mạng tháng Tám',
    'Là sự cải cách chính trị của nước ta',
    'Là sự cách mạng của nước ta',
    'Là sự thể hiện quyền tự chủ, độc lập của dân tộc',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    25,
    2,
    0
);

-- Câu 6
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Nước Việt Nam Dân chủ Cộng hòa ra đời vào thời gian nào?',
    '',
    'Sau chiến tranh thế giới thứ hai',
    'Sau chiến tranh thế giới thứ nhất',
    'Trong thời gian chiến tranh thế giới thứ hai',
    'Trước chiến tranh thế giới thứ hai',
    'Sau chiến tranh thế giới thứ hai',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    25,
    2,
    0
);

-- Câu 7
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Thành lập nước Việt Nam Dân chủ Cộng hòa là sự kiện nào đánh dấu bước ngoặt trong lịch sử dân tộc Việt Nam?',
    '',
    'Cách mạng tháng Tám năm 1945',
    'Chiến thắng tại Điện Biên Phủ',
    'Cách mạng tháng Hai năm 1930',
    'Cách mạng tháng Tám năm 1945',
    'Cách mạng tháng Tám năm 1945',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    25,
    2,
    0
);
-- Câu 8 
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Ai là người đọc tuyên bố độc lập tại quảng trường Ba Đình?',
    '',
    'Hồ Chí Minh',
    'Nguyễn Ái Quốc',
    'Phan Bội Châu',
    'Võ Nguyên Giáp',
    'Hồ Chí Minh',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    25,
    2,
    0
);

-- Câu 9
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Quốc gia nào đã tuyên bố ra đời sau cuộc kháng chiến chống Pháp và Nhật?',
    '',
    'Việt Nam Dân chủ Cộng hòa',
    'Việt Nam Cộng hòa',
    'Việt Nam Dân chủ Xã hội chủ nghĩa',
    'Việt Nam Xã hội chủ nghĩa',
    'Việt Nam Dân chủ Cộng hòa',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    25,
    2,
    0
);

-- Câu 10
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Thành lập nước Việt Nam Dân chủ Cộng hòa được diễn ra vào thời gian nào?',
    '',
    'Sau chiến tranh thế giới thứ hai',
    'Trong thời kỳ chiến tranh thế giới thứ nhất',
    'Sau chiến tranh thế giới thứ nhất',
    'Trước chiến tranh thế giới thứ hai',
    'Sau chiến tranh thế giới thứ hai',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),
    25,
    2,
    0
);

-- Dia ly
-- chapter 16
-- Câu 1
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Kinh tuyến nào chia cắt Trái Đất thành hai nửa, phân tách giữa miền Đông và miền Tây?',
    '',
    'Kinh tuyến 0 độ (kích địa)',
    'Kinh tuyến 180 độ',
    'Kinh tuyến 90 độ Đông',
    'Kinh tuyến 90 độ Tây',
    'Kinh tuyến 180 độ',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),
    16,
    1,
    0
);

-- Câu 2
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Đường xích đạo chia cắt Trái Đất thành những phần nào?',
    '',
    'Bắc cực và Nam cực',
    'Miền Bắc và Miền Nam',
    'Miền Bắc, miền Trung và miền Nam',
    'Miền Bắc, miền Đông và miền Tây',
    'Miền Bắc và Miền Nam',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),
    16,
    1,
    0
);

-- Câu 3
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Châu lục nào là phần lớn nhất của Trái Đất theo diện tích?',
    '',
    'Châu Á',
    'Châu Phi',
    'Châu Âu',
    'Châu Mỹ',
    'Châu Á',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),
    16,
    1,
    0
);

-- Câu 4
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Châu lục nào là phần lớn nhất của Trái Đất theo dân số?',
    '',
    'Châu Á',
    'Châu Phi',
    'Châu Âu',
    'Châu Mỹ',
    'Châu Á',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),
    16,
    1,
    0
);

-- Câu 5
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Biển nào là biển lớn nhất trên Trái Đất?',
    '',
    'Đại Tây Dương',
    'Thái Bình Dương',
    'Ấn Độ Dương',
    'Bắc Băng Dương',
    'Thái Bình Dương',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),
    16,
    1,
    0
);

-- Câu 6
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Châu lục nào nằm trên cả hai bán cầu?',
    '',
    'Châu Á',
    'Châu Phi',
    'Châu Âu',
    'Châu Mỹ',
    'Châu Á',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),
    16,
    1,
    0
);

-- Câu 7
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Vùng đất nào được mệnh danh là “điểm giao thoa” của các lục địa?',
    '',
    'Bắc Cực',
    'Nam Cực',
    'Đại Tây Dương',
    'Thái Bình Dương',
    'Bắc Cực',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),
    16,
    1,
    0
);

-- Câu 8
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Biển nào nằm ở khu vực lớn nhất trên cả ba bán cầu?',
    '',
    'Thái Bình Dương',
    'Ấn Độ Dương',
    'Đại Tây Dương',
    'Bắc Băng Dương',
    'Thái Bình Dương',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),
    16,
    1,
    0
);

-- Câu 9
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Châu lục nào nằm hoàn toàn ở bán cầu Bắc?',
    '',
    'Châu Âu',
    'Châu Á',
    'Châu Phi',
    'Châu Mỹ',
    'Châu Âu',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),
    16,
    1,
    0
);
-- Câu 1
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Đường xích đạo chia cắt Trái Đất thành hai nửa, phân tách giữa miền Đông và miền Tây là:',
    'Kinh tuyến 0 độ (kích địa)',
    'Kinh tuyến 180 độ',
    'Kinh tuyến 90 độ Đông',
    'Kinh tuyến 90 độ Tây',
    'Kinh tuyến 180 độ',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),
    16,
    2,
    0
);

-- Câu 2
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Điểm giao thoa của các lục địa thường được xem như là:',
    'Bắc Cực',
    'Nam Cực',
    'Đại Tây Dương',
    'Thái Bình Dương',
    'Bắc Cực',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),
    16,
    2,
    0
);

-- Câu 3
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Biển nào là biển lớn nhất trên Trái Đất?',
    'Đại Tây Dương',
    'Thái Bình Dương',
    'Ấn Độ Dương',
    'Bắc Băng Dương',
    'Thái Bình Dương',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),
    16,
    2,
    0
);

-- Câu 4
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Vị trí địa lí nào được coi là "điểm giao thoa" của các lục địa?',
    'Bắc Cực',
    'Nam Cực',
    'Đại Tây Dương',
    'Thái Bình Dương',
    'Bắc Cực',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),
    16,
    2,
    0
);

-- Câu 5
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Đường xích đạo chia cắt Trái Đất thành những phần nào?',
    'Bắc cực và Nam cực',
    'Miền Bắc và Miền Nam',
    'Miền Bắc, miền Trung và miền Nam',
    'Miền Bắc, miền Đông và miền Tây',
    'Bắc cực và Nam cực',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),
    16,
    2,
    0
);

-- Câu 6
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Châu lục nào nằm trên cả hai bán cầu?',
    'Châu Á',
    'Châu Phi',
    'Châu Âu',
    'Châu Mỹ',
    'Châu Á',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),
    16,
    2,
    0
);

-- Câu 7
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Biển nào nằm ở khu vực lớn nhất trên cả ba bán cầu?',
    'Thái Bình Dương',
    'Ấn Độ Dương',
    'Đại Tây Dương',
    'Bắc Băng Dương',
    'Thái Bình Dương',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),
    16,
    2,
    0
);

-- Câu 8
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Châu lục nào nằm hoàn toàn ở bán cầu Bắc?',
    'Châu Âu',
    'Châu Á',
    'Châu Phi',
    'Châu Mỹ',
    'Châu Âu',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),
    16,
    2,
    0
);

-- Câu 9
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Biển nào là biển giữa lục địa?',
    'Biển Địa Trung Hải',
    'Biển Caribe',
    'Biển Hồ',
    'Biển Baltic',
    'Biển Địa Trung Hải',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),
    16,
    2,
    0
);

-- sinh
-- chapter 41
-- Câu 1
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Cấu trúc nào tạo nên một gen?',
    'Axit nucleic',
    'Protein',
    'Carbohydrate',
    'Chất béo',
    'Axit nucleic',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    1,
    0
);

-- Câu 2
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Gen làm gì trong tế bào?',
    'Tạo ra axit nucleic',
    'Tạo ra protein',
    'Tạo ra carbohydrate',
    'Tạo ra chất béo',
    'Tạo ra protein',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    1,
    0
);

-- Câu 3
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Đâu không phải là một phần của một gen?',
    'Promotor',
    'Introns',
    'Exons',
    'mRNA',
    'mRNA',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    1,
    0
);

-- Câu 4
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Quá trình nào tạo ra protein từ mã gen?',
    'Transcription',
    'Translation',
    'Replication',
    'Mutation',
    'Translation',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    1,
    0
);

-- Câu 5
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Trong quá trình nhân đôi ADN, nucleotide mới được thêm vào chuỗi ADN ở đâu?',
    'Phía 5',
    'Phía 3',
    'Giữa chuỗi',
    'Không nơi nào',
    'Phía 3',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    1,
    0
);

-- Câu 6
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'ADN được nhân đôi trong quá trình nào?',
    'Transcription',
    'Translation',
    'Replication',
    'Mutation',
    'Replication',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    1,
    0
);

-- Câu 7
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Trong chuỗi ADN, nucleotide được ghép nối với nhau qua liên kết nào?',
    'Liên kết axit peptid',
    'Liên kết hydro',
    'Liên kết hydrogen',
    'Liên kết phosphodiester',
    'Liên kết phosphodiester',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    1,
    0
);

-- Câu 8
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Tập hợp của các gen trên một nhiễm sắc thể được gọi là gì?',
    'ADN',
    'Genotype',
    'Genome',
    'mRNA',
    'Genome',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    1,
    0
);

-- Câu 9
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Tác nhân nào sau đây không gây ra đột biến gen?',
    'Tia X',
    'Tia gama',
    'Tia cực tím',
    'Tia hồng ngoại',
    'Tia hồng ngoại',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    1,
    0
);

-- Câu 10
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Quá trình nào giúp tạo ra mRNA từ một gen?',
    'Transcription',
    'Translation',
    'Replication',
    'Mutation',
    'Transcription',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    1,
    0
);

-- Câu 1
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Ngoài DNA, loại axit nucleic nào cũng đóng vai trò quan trọng trong quá trình mã hóa gen?',
    'RNA',
    'mRNA',
    'rRNA',
    'tRNA',
    'RNA',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    2,
    0
);

-- Câu 2
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Quá trình di truyền thông qua gen được gọi là gì?',
    'Replication',
    'Mutation',
    'Heredity',
    'Transcription',
    'Heredity',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    2,
    0
);

-- Câu 3
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Quá trình nào dịch mã gen thành protein?',
    'Transcription',
    'Translation',
    'Replication',
    'Mutation',
    'Translation',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    2,
    0
);

-- Câu 4
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Cấu trúc nào của gen quyết định đặc tính của một sinh vật?',
    'Introns',
    'Exons',
    'Promotor',
    'Allele',
    'Exons',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    2,
    0
);

-- Câu 5
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Quá trình nào tạo ra một bản sao chính xác của một chuỗi DNA?',
    'Transcription',
    'Translation',
    'Replication',
    'Mutation',
    'Replication',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    2,
    0
);

-- Câu 6
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Enzyme nào đảm nhận vai trò chính trong quá trình nhân đôi ADN?',
    'DNA polymerase',
    'RNA polymerase',
    'Helicase',
    'Ligase',
    'DNA polymerase',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    2,
    0
);

-- Câu 7
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Quá trình nào tạo ra một mRNA từ một gen?',
    'Transcription',
    'Translation',
    'Replication',
    'Mutation',
    'Transcription',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    2,
    0
);

-- Câu 8
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Trong quá trình nhân đôi ADN, chuỗi gốc và chuỗi mới được tổ chức như thế nào?',
    'Song song',
    'Cặp đối xứng',
    'Chồng lên nhau',
    'Nhiều chiều',
    'Cặp đối xứng',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    2,
    0
);

-- Câu 9
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Trong quá trình di truyền, điều gì xác định các đặc điểm di truyền của một cá thể?',
    'ADN',
    'mRNA',
    'Protein',
    'Carbohydrate',
    'ADN',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    2,
    0
);

-- Câu 10
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Genotype của một cá thể quyết định bởi những yếu tố nào?',
    'Môi trường và di truyền',
    'Chỉ di truyền',
    'Chỉ môi trường',
    'Không phụ thuộc vào yếu tố nào',
    'Chỉ di truyền',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    2,
    0
);

-- Câu 1
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Điều gì xác định sự khác biệt giữa các cá thể của cùng một loài?',
    'Môi trường',
    'Genotype',
    'Phản ứng hóa học',
    'Đặc điểm vật lý',
    'Genotype',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    3,
    0
);

-- Câu 2
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Sự khác biệt giữa các gen có thể dẫn đến sự đa dạng nào?',
    'Phenotypic',
    'Genotypic',
    'Biochemical',
    'Structural',
    'Phenotypic',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    3,
    0
);

-- Câu 3
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Genotype của một cá thể do những yếu tố nào xác định?',
    'Môi trường và di truyền',
    'Chỉ di truyền',
    'Chỉ môi trường',
    'Không phụ thuộc vào yếu tố nào',
    'Chỉ di truyền',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    3,
    0
);

-- Câu 4
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Cấu trúc nào của gen quyết định đặc tính của một sinh vật?',
    'Introns',
    'Exons',
    'Promotor',
    'Allele',
    'Exons',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    3,
    0
);

-- Câu 5
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Điều gì xác định hình thức bên ngoài của một cá thể?',
    'Genotype',
    'Phenotype',
    'Chromosome',
    'Mutation',
    'Phenotype',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    3,
    0
);

-- Câu 6
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Quá trình di truyền thông qua gen được gọi là gì?',
    'Replication',
    'Mutation',
    'Heredity',
    'Transcription',
    'Heredity',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    3,
    0
);

-- Câu 7
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Quá trình nào tạo ra một mRNA từ một gen?',
    'Transcription',
    'Translation',
    'Replication',
    'Mutation',
    'Transcription',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    3,
    0
);

-- Câu 8
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Trong quá trình nhân đôi ADN, enzyme nào đảm nhận vai trò chính?',
    'DNA polymerase',
    'RNA polymerase',
    'Helicase',
    'Ligase',
    'DNA polymerase',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    3,
    0
);

-- Câu 9
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Quá trình nào dịch mã gen thành protein?',
    'Transcription',
    'Translation',
    'Replication',
    'Mutation',
    'Translation',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    3,
    0
);

-- Câu 10
INSERT INTO Questions (
    QuestionTitle, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Trong quá trình di truyền, điều gì xác định các đặc điểm di truyền của một cá thể?',
    'ADN',
    'mRNA',
    'Protein',
    'Carbohydrate',
    'ADN',
    (SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh học'),
    41,
    3,
    0
);




-- MockTestDetails
INSERT INTO MockTestDetails (MockTestID, QuestionID)
VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(2, 6),
(2, 7),
(2, 8),
(2, 9),
(2, 10),
(3, 11),
(3, 12),
(3, 13),
(3, 14),
(3, 15),
(4, 16),
(4, 17),
(4, 18),
(4, 19),
(4, 20),
(5, 21),
(5, 22),
(5, 23),
(5, 24),
(5, 25);

-- ‘Teachers’ table
INSERT INTO Teachers VALUES
  	(1, 1),
	(3, 2),
	(4, 3),
	(5, 4),
	(6, 5),
	(7, 6),
	(8, 7),
	(9, 8),
	(10, 9);

-- ‘Managers’ table
INSERT INTO Managers VALUES
(11, 1),
	(12, 2),
	(13, 3),
	(14, 4),
  	(15, 5),
	(16, 6),
	(17, 7),
	(18, 8),
	(19, 9);
    
-- ‘Admins’ table
INSERT INTO Admins VALUES
    (2, 'Title');
    
-- ‘TeacherClass’ table
INSERT INTO TeacherClass VALUES
   	(1, 1),
	(1, 3),
	(1, 4),
	(1, 5),
	(1, 6),
	(2, 7),
	(2, 8),
	(2, 9),
	(2, 10),
	(1, 7),
	(2, 3);
    
-- 'Students' table
INSERT INTO Students VALUES
    (20, 1),
    (21, 1),
    (22, 1),
    (23, 1),
    (24, 1),
	(25, 2),
    (26, 2),
    (27, 2),
    (28, 2),
    (29, 2),
    (30, 2);
    
-- StudentSubject table
INSERT INTO StudentSubject (UserID, SubjectID)
SELECT s.UserID, sub.SubjectID
FROM Students s
CROSS JOIN Subjects sub;

-- ClassSubject
INSERT INTO ClassSubject (Classcode, SubjectID) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9),
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9);

-- TeacherMaterials Table
INSERT INTO TeacherMaterials (Title, Content, ClassCode, SubjectID) VALUES
('Đáp án đề 1 Lớp 12A1','https://vietjack.com/de-kiem-tra-lop-12/',(SELECT Classcode FROM classes WHERE Classname = '12A1'),(SELECT SubjectID FROM Subjects WHERE Subjectname = 'Toán')),
('Đáp án đề 1 Lớp 12A3','https://vietjack.com/de-kiem-tra-lop-12/',(SELECT Classcode FROM classes WHERE Classname = '12A3'),(SELECT SubjectID FROM Subjects WHERE Subjectname = 'Toán')),
('Đáp án đề 2 Lớp 12A1','https://loigiaihay.com/de-kiem-tra-15-phut-de-so-1-chuong-1-giai-tich-12-c47a44772.html',(SELECT Classcode FROM classes WHERE Classname = '12A1'),(SELECT SubjectID FROM Subjects WHERE Subjectname = 'Toán')),
('Đáp án đề 2 Lớp 12A3','https://loigiaihay.com/de-kiem-tra-15-phut-de-so-1-chuong-1-giai-tich-12-c47a44772.html',(SELECT Classcode FROM classes WHERE Classname = '12A3'),(SELECT SubjectID FROM Subjects WHERE Subjectname = 'Toán'));


-- 'Materials' table
 INSERT INTO Materials (SubjectID, ChapterID, Title, Content) VALUES
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Toán'),1,'Tổng hợp lý thuyết chương 1','https://butbi.hocmai.vn/su-dong-bien-nghich-bien-cua-ham-so.html'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Toán'),2,'Tổng hợp lý thuyết cực trị của hàm số ','https://vietjack.com/toan-lop-12/ly-thuyet-cuc-tri-ham-so.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Toán'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Giá trị lớn nhất và giá trị nhỏ nhất của hàm số'),'Tổng hợp lý thuyết Giá trị lớn nhất và giá trị nhỏ nhất của hàm số ','https://vietjack.com/toan-lop-12/ly-thuyet-gia-tri-lon-nhat-va-gia-tri-nho-nhat-cua-ham-so.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Toán'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Đường tiệm cận'),'Tổng hợp lý thuyết Đường tiệm cận','https://vietjack.com/toan-lop-12/ly-thuyet-duong-tiem-can.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Toán'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Khảo sát sự biến thiên và vẽ đồ thị của hàm số'),'Tổng hợp lý thuyết Khảo sát sự biến thiên và vẽ đồ thị của hàm số','https://vietjack.com/toan-lop-12/ly-thuyet-khao-sat-su-bien-thien-va-ve-do-thi-ham-so.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Tiếng Anh'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Life stories'),'Từ vựng unit Life stories','https://vietjack.com/tieng-anh-12-ilearn/tu-vung-unit-1-lop-12.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Tiếng Anh'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Urbanisation'),'Từ vựng unit Urbanisation','https://zim.vn/tu-vung-tieng-anh-12-unit-2-urbanisation'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Tiếng Anh'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'The green movement'),'Từ vựng unit The green movement','https://zim.vn/tu-vung-tieng-anh-12-unit-3-the-green-movement'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Tiếng Anh'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'The mass media'),'Từ vựng unit The mass media','https://zim.vn/tu-vung-tieng-anh-12-unit-4-the-mass-media'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Tiếng Anh'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Cultural identity'),'Từ vựng unit Cultural identity ','https://zim.vn/tu-vung-tieng-anh-12-unit-5-cultural-identity'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Tuyên Ngôn Độc Lập (Hồ Chí Minh)'),'Tóm tắt văn bản Tuyên Ngôn Độc Lập (Hồ Chí Minh) ','https://loigiaihay.com/tuyen-ngon-doc-lap-ho-chi-minh-c360a51362.html'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Tây tiến (Quang Dũng)'),'Tóm tắt văn bản Tây tiến (Quang Dũng) ','https://loigiaihay.com/tay-tien-quang-dung-c360a51377.html'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Việt Bắc (Tố Hữu)'),'Tóm tắt văn bản Việt Bắc (Tố Hữu) ','https://loigiaihay.com/viet-bac-to-huu-c360a51380.html'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Đất nước (Nguyễn Khoa Điềm)'),'Tóm tắt văn bản Đất nước (Nguyễn Khoa Điềm) ','https://loigiaihay.com/dat-nuoc-nguyen-khoa-diem-c360a51383.html'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Ngữ Văn'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Sóng (Xuân Quỳnh)'),'Tóm tắt văn bản Sóng (Xuân Quỳnh) ','https://loigiaihay.com/song-xuan-quynh-c360a51399.html'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Sự hình thành trật tự thế giới mới sau chiến tranh thế giới thứ hai (1945 – 1949)'),'Lý thuyết bài Sự hình thành trật tự thế giới mới sau chiến tranh thế giới thứ hai (1945 – 1949','https://vietjack.com/giai-bai-tap-lich-su-12/ly-thuyet-su-hinh-thanh-trat-tu-the-gioi-moi-sau-chien-tranh-the-gioi-thu-hai.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Liên Xô và các nước Đông u (1945 - 2000). Liên Bang Nga (1991 - 2000)'),'Lý thuyết bài Liên Xô và các nước Đông  u (1945 - 2000). Liên Bang Nga (1991 - 2000)','https://vietjack.com/giai-bai-tap-lich-su-12/ly-thuyet-su-hinh-thanh-trat-tu-the-gioi-moi-sau-chien-tranh-the-gioi-thu-hai.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Các nước Á, Phi và Mĩ Latinh (1945 - 2000)'),'Lý thuyết bài Các nước Á, Phi và Mĩ Latinh (1945 - 2000)','https://vietjack.com/giai-bai-tap-lich-su-12/ly-thuyet-cac-nuoc-chau-phi-va-mi-latinh.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Phong trào giải phóng dân tộc và tổng khởi nghĩa tháng Tám (1939-1945). Nước Việt Nam Dân chủ Cộng hòa ra đời'),'Lý thuyết bài Phong trào giải phóng dân tộc và tổng khởi nghĩa tháng Tám (1939-1945). Nước Việt Nam Dân chủ Cộng hòa ra đời','https://vietjack.com/giai-bai-tap-lich-su-12/ly-thuyet-phong-trao-giai-phong-dan-toc-va-tong-khoi-nghia-thang-tam-1939-1945.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Lịch sử'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Phong trào dân tộc dân chủ ở Việt Nam từ năm 1919 đến năm 1925'),'Lý thuyết bài Phong trào dân tộc dân chủ ở Việt Nam từ năm 1919 đến năm 1925','https://vietjack.com/giai-bai-tap-lich-su-12/ly-thuyet-phong-trao-dan-toc-dan-chu-o-viet-nam-tu-nam-1919-den-nam-1925.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'GDCD'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Pháp luật và đời sống'),'Lý thuyết bài Pháp luật và đời sống','https://vietjack.com/giai-bai-tap-giao-duc-cong-dan-12/ly-thuyet-trac-nghiem-bai-1-phap-luat-va-doi-song.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'GDCD'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Thực hiện pháp luật'),'Lý thuyết bài Thực hiện pháp luật','https://vietjack.com/giai-bai-tap-giao-duc-cong-dan-12/ly-thuyet-trac-nghiem-bai-2-thuc-hien-phap-luat.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'GDCD'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Quyền bình đẳng của công dân trong một số lĩnh vực đời sống'),'Lý thuyết bài Quyền bình đẳng của công dân trong một số lĩnh vực đời sống','https://vietjack.com/giai-bai-tap-giao-duc-cong-dan-12/ly-thuyet-trac-nghiem-bai-4-quyen-binh-dang-cua-cong-dan-trong-mot-so-linh-vuc-doi-song.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'GDCD'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Quyền bình đẳng giữa các dân tộc, tôn giáo'),'Lý thuyết bài Quyền bình đẳng giữa các dân tộc, tôn giáo','https://vietjack.com/giai-bai-tap-giao-duc-cong-dan-12/ly-thuyet-trac-nghiem-bai-5-quyen-binh-dang-giua-cac-dan-toc-ton-giao.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh Học'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Đột biến gen'),'Lý thuyết bài Đột biến gen','https://vietjack.com/giai-bai-tap-sinh-hoc-12/ly-thuyet-dot-bien-gen.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh Học'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Gen, mã di truyền và quá trình nhân đôi ADN'),'Lý thuyết bài Gen, mã di truyền và quá trình nhân đôi ADN','https://vietjack.com/giai-bai-tap-sinh-hoc-12/ly-thuyet-gen-ma-di-truyen-va-qua-trinh-nhan-doi-adn.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh Học'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Phiên mã và dịch mã'),'Lý thuyết bài Phiên mã và dịch mã','https://vietjack.com/giai-bai-tap-sinh-hoc-12/ly-thuyet-phien-ma-va-dich-ma.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh Học'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Điều hòa hoạt động gen'),'Lý thuyết bài Điều hòa hoạt động gen','https://vietjack.com/giai-bai-tap-sinh-hoc-12/ly-thuyet-dieu-hoa-hoat-dong-gen.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Sinh Học'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Nhiễm sắc thể và đột biến cấu trúc nhiễm sắc thể'),'Lý thuyết bài Nhiễm sắc thể và đột biến cấu trúc nhiễm sắc thể','https://vietjack.com/giai-bai-tap-sinh-hoc-12/ly-thuyet-nhiem-sac-the-va-dot-bien-cau-truc-nhiem-sac-the.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Hóa Học'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Este'),'Lý thuyết bài Este','https://haylamdo.com/hoa-hoc-lop-12/tong-hop-ly-thuyet-chuong-este-lipit.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Hóa Học'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Lipit'),'Lý thuyết bài Lipit','https://lop12.com/bai-hoc-hoa-12/hoc-hoa-hoc-12-bai-2-lipit.html'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Hóa Học'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Khái niệm về xà phòng và chất giặt rửa tổng hợp'),'Lý thuyết bài Khái niệm về xà phòng và chất giặt rửa tổng hợp','https://vndoc.com/hoa-12-bai-3-khai-niem-ve-xa-phong-va-chat-giat-rua-tong-hop-241276'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Hóa Học'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Luyện tập: Este và chất béo'),'Luyện tập: Este và chất béo','https://lop12.com/bai-hoc-hoa-12/hoc-hoa-hoc-12-bai-4-luyen-tap-este-va-chat-beo.html'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Hóa Học'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Glucozơ'),'Lý thuyết bài Glucozơ','https://loigiaihay.com/ly-thuyet-glucozo-c55a8059.html'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Vật Lý'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Dao động điều hòa'),'Lý thuyết bài Dao động điều hòa','https://vietjack.com/vat-ly-lop-12/ly-thuyet-dao-dong-dieu-hoa.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Vật Lý'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Con lắc lò xo'),'Lý thuyết bài Con lắc lò xo','https://vietjack.com/vat-ly-lop-12/ly-thuyet-con-lac-lo-xo.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Vật Lý'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Con lắc đơn'),'Lý thuyết bài Con lắc đơn','https://vietjack.com/vat-ly-lop-12/ly-thuyet-con-lac-don.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Vật Lý'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Dao động tắt dần. Dao động cưỡng bức'),'Lý thuyết bài Dao động tắt dần. Dao động cưỡng bức','https://vietjack.com/vat-ly-lop-12/ly-thuyet-dao-dong-tat-dan-dao-dong-cuong-buc.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Vật Lý'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Tổng hợp hai dao động điều hòa cùng phương, cùng tần số'),'Lý thuyết bài Tổng hợp hai dao động điều hòa cùng phương, cùng tần số','https://loigiaihay.com/ly-thuyet-tong-hop-hai-dao-dong-dieu-hoa-cung-phuong-cung-tan-so-phuong-phap-gian-do-fre-nen-c63a6332.html'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Vị trí địa lí, phạm vi lãnh thổ'),'Lý thuyết bài Vị trí địa lí, phạm vi lãnh thổ','https://vietjack.com/giai-bai-tap-dia-li-12/ly-thuyet-vi-tri-dia-li-pham-vi-lanh-tho.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Thiên nhiên chịu ảnh hưởng sâu sắc của biển'),'Lý thuyết bài Thiên nhiên chịu ảnh hưởng sâu sắc của biển','https://vietjack.com/giai-bai-tap-dia-li-12/ly-thuyet-thien-nhien-chiu-anh-huong-sau-sac-cua-bien.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Thiên nhiên nhiệt đới ẩm gió mùa'),'Lý thuyết bài Thiên nhiên nhiệt đới ẩm gió mùa','https://vietjack.com/giai-bai-tap-dia-li-12/ly-thuyet-thien-nhien-nhiet-doi-am-gio-mua.jsp'),
((SELECT SubjectID FROM Subjects WHERE Subjectname = 'Địa Lý'),(SELECT ChapterID FROM chapters WHERE ChapterName= 'Lịch sử hình thành và phát triển lãnh thổ'),'Lịch sử hình thành và phát triển lãnh thổ','https://vietjack.me/ly-thuyet-lich-su-hinh-thanh-va-phat-trien-lanh-tho-tiep-theo-dia-li-l-22300.html');

-- TeacherPractice 1 cho mon Hoa lop 10A1
INSERT INTO TeacherPractice (TeacherPracticeID, Title, ClassCode, SubjectID, Deadline)
VALUES (1, 'Hoàn thành bài này trước thời hạn không là ăn 0 nhé', 1, 7, '2024-08-15');

-- Question for teacherpractice 1
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Este có mùi dứa là',
    '',
    'isoamyl axetat.',
    'etyl butirat.',
    'etyl axetat.',
    'geranyl acetate.',
    'etyl butirat',
    (SELECT SubjectID FROM Subjects WHERE SubjectName = 'Hóa Học'),
    1,
    1,
    1
);
INSERT INTO Questions (
    QuestionTitle, Image, Option1, Option2, Option3, Option4, Answer, SubjectID, ChapterID, LevelID, status
) VALUES (
    'Cho 13,6 gam phenyl axetat tác dụng với 250 ml dung dịch NaOH 1M, cô cạn dung dịch sau phản ứng được m gam chất rắn. Giá trị của m là',
    '',
    '19,8.',
    '21,8.',
    '14,2',
    '11,6.',
    '21,8.',
    (SELECT SubjectID FROM Subjects WHERE SubjectName = 'Hóa Học'),
    1,
    1,
    1
);

-- TeacherPracticeDetails for TeacherPracticeID = 1

INSERT INTO TeacherPracticeDetails (TeacherPracticeID, QuestionID) VALUES
(1, 26),
(1, 27);

-- Cập nhật vai trò của sinh viên
UPDATE Users
SET Role = 'ROLE_STUDENT'
WHERE UserID IN (SELECT UserID FROM Students);

-- Cập nhật vai trò của giáo viên
UPDATE Users
SET Role = 'ROLE_TEACHER'
WHERE UserID IN (SELECT UserID FROM Teachers);

-- Cập nhật vai trò của quản lý
UPDATE Users
SET Role = 'ROLE_MANAGER'
WHERE UserID IN (SELECT UserID FROM Managers);

-- Cập nhật vai trò của quản trị viên
UPDATE Users
SET Role = 'ROLE_ADMIN'
WHERE UserID IN (SELECT UserID FROM Admins);





