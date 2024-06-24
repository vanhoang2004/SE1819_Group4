
DROP SCHEMA IF EXISTS `demo1`;
CREATE SCHEMA `demo1`;
USE `demo1`;

-- Creating the 'Users' table
CREATE TABLE IF NOT EXISTS Users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    Fullname VARCHAR(100) NOT NULL,
    Useremail VARCHAR(100) NOT NULL UNIQUE
);

-- Creating the 'Subjects' table
CREATE TABLE IF NOT EXISTS Subjects (
    SubjectID INT AUTO_INCREMENT PRIMARY KEY,
    Subjectname VARCHAR(100) NOT NULL
);

-- Creating the 'Classes' table
CREATE TABLE IF NOT EXISTS Classes (
    Classcode INT AUTO_INCREMENT PRIMARY KEY,
    Classname VARCHAR(100) NOT NULL
);

-- Creating the 'Levels' table
CREATE TABLE Levels (
    LevelID INT NOT NULL AUTO_INCREMENT,
    LevelName VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY (LevelID)
);

-- Creating the 'MockTests' table
CREATE TABLE MockTests (
    MockTestID INT NOT NULL AUTO_INCREMENT,
    MockTestTitle VARCHAR(45) DEFAULT NULL,
    SubjectID INT not NULL,
    Start datetime default null,
    End datetime default null,
    PRIMARY KEY (MockTestID),
    FOREIGN KEY (SubjectID) REFERENCES Subjects(SubjectID)
);

-- Creating the 'Chapters' table
CREATE TABLE Chapters (
    ChapterID INT NOT NULL AUTO_INCREMENT,
    ChapterName TEXT DEFAULT NULL,
    SubjectID INT DEFAULT NULL,
    PRIMARY KEY (ChapterID),
    FOREIGN KEY (SubjectID) REFERENCES Subjects(SubjectID)
);

-- Creating the 'Questions' table
CREATE TABLE Questions (
    QuestionID INT NOT NULL AUTO_INCREMENT,
    QuestionTitle TEXT DEFAULT NULL,
    Image VARCHAR(200) DEFAULT NULL,
    Option1 VARCHAR(200) DEFAULT NULL,
    Option2 VARCHAR(200) DEFAULT NULL,
    Option3 VARCHAR(200) DEFAULT NULL,
    Option4 VARCHAR(200) DEFAULT NULL,
    Answer VARCHAR(200) DEFAULT NULL,
    SubjectID INT DEFAULT NULL,
    ChapterID INT DEFAULT NULL,
    LevelID INT DEFAULT NULL,
    status INT DEFAULT NULL,
    PRIMARY KEY (QuestionID),
    FOREIGN KEY (SubjectID) REFERENCES Subjects(SubjectID),
    FOREIGN KEY (ChapterID) REFERENCES Chapters(ChapterID),
    FOREIGN KEY (LevelID) REFERENCES Levels(LevelID)
);

-- Creating the 'MockTestDetails' table
CREATE TABLE MockTestDetails (
    MockTestID INT DEFAULT NULL,
    QuestionID INT DEFAULT NULL,
    FOREIGN KEY (MockTestID) REFERENCES MockTests(MockTestID),
    FOREIGN KEY (QuestionID) REFERENCES Questions(QuestionID)
);

-- Creating the 'StudentTakenQuestions' table
CREATE TABLE StudentTakenQuestions (
    StudentQuestionID INT NOT NULL AUTO_INCREMENT,
    Choice VARCHAR(200) DEFAULT NULL,
    Result VARCHAR(200) DEFAULT NULL,
    QuestionID INT DEFAULT NULL,
    PRIMARY KEY (StudentQuestionID),
    FOREIGN KEY (QuestionID) REFERENCES Questions(QuestionID)
);
-- Creating the 'Students' table
CREATE TABLE IF NOT EXISTS Students (
    UserID int NOT NULL,
    Classcode int NOT NULL,
    PRIMARY KEY (UserID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (Classcode) REFERENCES Classes(Classcode)
);

-- Creating the 'TakenMockTest' table
CREATE TABLE TakenMockTest (
    MockTestID int DEFAULT NULL,
    UserID int DEFAULT NULL,
    StudentQuestionID INT DEFAULT NULL,
    Score FLOAT DEFAULT NULL,
    FOREIGN KEY (MockTestID) REFERENCES MockTests(MockTestID),
    FOREIGN KEY (UserID) REFERENCES Students(UserID),
    FOREIGN KEY (StudentQuestionID) REFERENCES StudentTakenQuestions(StudentQuestionID)
);

-- Creating the 'StudentPracticeQuestions' table
CREATE TABLE StudentPracticeQuestions (
    StudentPracticeID INT NOT NULL AUTO_INCREMENT,
    Choice VARCHAR(200) DEFAULT NULL,
    Result VARCHAR(200) DEFAULT NULL,
    QuestionID INT DEFAULT NULL,
    PRIMARY KEY (StudentPracticeID),
    FOREIGN KEY (QuestionID) REFERENCES Questions(QuestionID)
);

-- Creating the 'StudentTakenPractices' table
CREATE TABLE StudentTakenPractices (
    UserID int NOT NULL,
    StudentPracticeID INT DEFAULT NULL,
    Score FLOAT DEFAULT NULL,
    primary key (UserID),
    FOREIGN KEY (UserID) REFERENCES Students(UserID),
    FOREIGN KEY (StudentPracticeID) REFERENCES StudentPracticeQuestions(StudentPracticeID)
);



-- Creating the 'Teachers' table
CREATE TABLE IF NOT EXISTS Teachers (
    UserID int NOT NULL,
    SubjectID INT,
    PRIMARY KEY (UserID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (SubjectID) REFERENCES Subjects(SubjectID)
);

-- Creating the 'Managers' table
CREATE TABLE IF NOT EXISTS Managers (
    UserID int NOT NULL,
    SubjectID INT,
    PRIMARY KEY (UserID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (SubjectID) REFERENCES Subjects(SubjectID)
);

-- Creating the 'Admins' table
CREATE TABLE IF NOT EXISTS Admins (
    UserID int NOT NULL,
    Title VARCHAR(50) ,
    PRIMARY KEY (UserID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- Creating the 'TeacherClass' table
CREATE TABLE IF NOT EXISTS TeacherClass (
    ClassCode int NOT NULL,
    UserID int NOT NULL,
    PRIMARY KEY (Classcode, UserID),
    FOREIGN KEY (Classcode) REFERENCES Classes(Classcode),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- Creating the 'StudentSubject' table
CREATE TABLE IF NOT EXISTS StudentSubject (
    UserID int NOT NULL,
    SubjectID INT,
    PRIMARY KEY (UserID, SubjectID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (SubjectID) REFERENCES Subjects(SubjectID)
);

-- Creating the 'ClassSubject' table
CREATE TABLE IF NOT EXISTS ClassSubject (
    Classcode int NOT NULL,
    SubjectID INT,
    PRIMARY KEY (Classcode, SubjectID),
    FOREIGN KEY (Classcode) REFERENCES Classes(Classcode),
    FOREIGN KEY (SubjectID) REFERENCES Subjects(SubjectID)
);

-- Creating the 'TeacherMaterials' table
CREATE TABLE IF NOT EXISTS TeacherMaterials (
    TeacherMaterialID INT AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(255) NOT NULL,
    Content TEXT NOT NULL,
    ClassCode int NOT NULL,
    SubjectID INT,
    FOREIGN KEY (ClassCode) REFERENCES Classes(ClassCode),
    FOREIGN KEY (SubjectID) REFERENCES Subjects(SubjectID)
);

-- 'TeacherPractice' tableclasses
CREATE TABLE IF NOT EXISTS TeacherPractice (
    TeacherPracticeID INT AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(255) NOT NULL,
    ClassCode INT NOT NULL,
    SubjectID INT,
    Deadline DATE NOT NULL,
    PublishDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ClassCode) REFERENCES Classes(Classcode),
    FOREIGN KEY (SubjectID) REFERENCES Subjects(SubjectID)
);


-- Creating the 'Materials' table
CREATE TABLE IF NOT EXISTS Materials (
    MaterialID INT AUTO_INCREMENT PRIMARY KEY,
    SubjectID INT,
    ChapterID INT,
    Title VARCHAR(255) NOT NULL,
    Content TEXT NOT NULL,
    FOREIGN KEY (SubjectID) REFERENCES Subjects(SubjectID),
    FOREIGN KEY (ChapterID) REFERENCES Chapters(ChapterID)
); 

-- 'TeacherPracticeDetails' table
CREATE TABLE IF NOT EXISTS TeacherPracticeDetails (
    TeacherPracticeID INT DEFAULT NULL,
    QuestionID INT DEFAULT NULL,
    FOREIGN KEY (TeacherPracticeID) REFERENCES TeacherPractice(TeacherPracticeID),
    FOREIGN KEY (QuestionID) REFERENCES Questions(QuestionID)
);

ALTER TABLE Users ADD COLUMN Role ENUM('ROLE_STUDENT', 'ROLE_TEACHER', 'ROLE_MANAGER', 'ROLE_ADMIN') NOT NULL;
ALTER TABLE users ADD COLUMN Enabled BOOLEAN DEFAULT TRUE;

-- Creating the 'Notifications' table
CREATE TABLE IF NOT EXISTS Notifications (
    NotificationID INT AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(255) NOT NULL,
    Message TEXT NOT NULL,
    Type ENUM('general', 'class') NOT NULL,
    ClassCode INT NULL,
    FOREIGN KEY (ClassCode) REFERENCES Classes(ClassCode),
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
--     `Read` BIT NOT NULL DEFAULT 0
);

DELIMITER //

CREATE TRIGGER after_insert_mocktests
AFTER INSERT ON MockTests
FOR EACH ROW
BEGIN
    DECLARE notification_message VARCHAR(255);
    DECLARE end_datetime DATETIME;
    DECLARE notification_time DATETIME;

    -- Create first notification message
    SET notification_message = CONCAT('A new mock test has been added: "', NEW.MockTestTitle, '".');
    INSERT INTO Notifications (title, message, type) VALUES ('New Mock Test', notification_message, 'general');
    
    -- Create second notification message
    SET end_datetime = NEW.End;
    SET notification_time = DATE_SUB(end_datetime, INTERVAL 1 DAY);
    SET notification_message = CONCAT('This mock test, "', NEW.MockTestTitle, '" expires in 1 day. If you have taken the test, please ignore this message.');
    INSERT INTO Notifications (title, message, type, createdat) VALUES ('Mock Test Expiry Reminder', notification_message, 'general', notification_time);
    
    -- Create third notification message
    SET notification_message = CONCAT('This mock test, "', NEW.MockTestTitle,'" has expired.');
    INSERT INTO Notifications (title, message, type, createdat) VALUES ('Mock Test Expired', notification_message, 'general', end_datetime);
    
    -- Schedule the second notification for 1 day before the end time
END//

DELIMITER ;

-- Creating the 'NotificationClasses' table 
-- CREATE TABLE IF NOT EXISTS NotificationClasses (
--     NotificationID INT,
--     ClassCode INT,
--     FOREIGN KEY (NotificationID) REFERENCES Notifications(NotificationID),
--     FOREIGN KEY (ClassCode) REFERENCES Classes(ClassCode),
--     PRIMARY KEY (NotificationID, ClassCode)
-- );

DELIMITER //

CREATE TRIGGER after_insert_teachermaterials
AFTER INSERT ON TeacherMaterials
FOR EACH ROW
BEGIN
    DECLARE notification_message VARCHAR(255);
    DECLARE class_code INT;

    -- Create notification message
    SET notification_message = CONCAT('A new material has been added to your class: "', NEW.Title, '".');
    SET class_code = NEW.ClassCode;
    
    -- Insert into Notifications table
    INSERT INTO Notifications (title, message, type, classcode) VALUES ('New Material', notification_message, 'class', class_code);

    -- Retrieve the notification_id of the inserted notification
--     SET @notification_id = LAST_INSERT_ID();

    -- Insert into NotificationClasses table
--     INSERT INTO NotificationClasses (NotificationID, ClassCode) VALUES (@notification_id, class_code);
END//

DELIMITER ;