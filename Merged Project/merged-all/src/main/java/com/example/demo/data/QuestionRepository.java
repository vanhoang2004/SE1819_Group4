package com.example.demo.data;

import com.example.demo.entity.Question;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Query(value = "SELECT q.QuestionID, q.QuestionTitle, q.Image,q.Option1,q.Option2,q.Option3,q.Option4,q.Answer,q.SubjectID,q.ChapterID,q.LevelID,q.status FROM questions q "+
            "INNER JOIN managers mgr ON q.SubjectID = mgr.SubjectID "+
            "Where mgr.UserID = :userID",nativeQuery = true)
    List<Question> findQuestionBySubjectID (int userID);

    @Query(value = "SELECT q.QuestionID, q.QuestionTitle, q.Image,q.Option1,q.Option2,q.Option3,q.Option4,q.Answer,q.SubjectID,q.ChapterID,q.LevelID,q.status FROM questions q "+
            "INNER JOIN mocktestdetails m ON m.QuestionID= q.QuestionID Where m.MockTestID= :mocktestid ",nativeQuery = true)
    List<Question> mockTestDetails (int mocktestid);

    @Query(value="SELECT * FROM questions WHERE QuestionID= :id",nativeQuery = true)
    Question findQuestionById(int id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO MockTestDetails (MockTestID, QuestionID)\n" +
            "VALUES\n" +
            "(:mocktestid, :questionid)", nativeQuery = true)
    void insertQuestionByQuestionId(Integer mocktestid, Integer questionid);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO TeacherPracticeDetails (teacherpracticeid, questionid) VALUES (:teacherpracticeid, :questionid)", nativeQuery = true)
    void insertQuestionPracticeByQuestionId(@Param("teacherpracticeid") Integer teacherpracticeid, @Param("questionid") Integer questionid);


    // search and filter
    @Query(value = "SELECT q.QuestionID, q.QuestionTitle, q.Image,q.Option1,q.Option2,q.Option3,q.Option4,q.Answer,q.SubjectID,q.ChapterID,q.LevelID,q.status FROM questions q "+
            "INNER JOIN managers mgr ON q.SubjectID = mgr.SubjectID "+
            "Where mgr.UserID = :userID and (q.QuestionTitle like %:keyword% or q.ChapterID = :chapterID )",nativeQuery = true)
    List<Question> searchQuestion (int userID,String keyword,Integer chapterID);

    @Query(value = "SELECT q.QuestionID, q.QuestionTitle, q.Image,q.Option1,q.Option2,q.Option3,q.Option4,q.Answer,q.SubjectID,q.ChapterID,q.LevelID,q.status FROM questions q "+
            "INNER JOIN managers mgr ON q.SubjectID = mgr.SubjectID "+
            "Where mgr.UserID = :userID and q.ChapterID = :chapterID",nativeQuery = true)
    List<Question> FilterQuestion (int userID,Integer chapterID);

    @Query(value = "SELECT q.QuestionID, q.QuestionTitle, q.Image,q.Option1,q.Option2,q.Option3,q.Option4,q.Answer,q.SubjectID,q.ChapterID,q.LevelID,q.status FROM questions q "+
            "INNER JOIN managers mgr ON q.SubjectID = mgr.SubjectID "+
            "Where mgr.UserID = :userID and q.status  = :status",nativeQuery = true)
    List<Question> questionByStatus (int userID,Integer status);
//
    @Query(value = "SELECT q.QuestionID, q.QuestionTitle, q.Image, q.Option1, q.Option2, q.Option3, q.Option4, q.Answer, q.SubjectID, q.ChapterID, q.LevelID, q.status\r\n"
            + "FROM teacherpractice AS t\r\n"
            + "JOIN teacherpracticedetails AS td ON t.TeacherPracticeID = td.TeacherPracticeID\r\n"
            + "JOIN questions AS q ON td.QuestionID = q.QuestionID\r\n"
            + "WHERE t.TeacherPracticeID = :id", nativeQuery = true)
    List<Question> getQuestionByTeacherPractice(int id);

    @Query(value = "SELECT * FROM Questions WHERE SubjectID = :subjectid AND ChapterID= :chapterid AND LevelID= :levelid", nativeQuery = true)
    List<Question> findBySubjectIdAndChapterIdWithLevel(int subjectid, int chapterid, int levelid, Pageable pageable);

    @Query(value = "SELECT \r\n"
            + "q.QuestionID , \r\n"
            + "q.QuestionTitle , \r\n"
            + "q.Image , \r\n"
            + "q.Option1, \r\n"
            + "q.Option2 , \r\n"
            + "q.Option3, \r\n"
            + "q.Option4, \r\n"
            + "q.Answer, \r\n"
            + "q.SubjectID, \r\n"
            + "q.ChapterID, \r\n"
            + "q.LevelID, \r\n"
            + "q.status \r\n"
            + "FROM \r\n"
            + "    demo1.questions q \r\n"
            + "JOIN \r\n"
            + "    demo1.mocktestdetails md \r\n"
            + "ON \r\n"
            + "q.QuestionID = md.QuestionID \r\n"
            + "JOIN \r\n"
            + "    demo1.mocktests m \r\n"
            + "ON \r\n"
            + "    m.MockTestID = md.MockTestID \r\n"
            + "WHERE \r\n"
            + "    m.MockTestID =:mocktestid", nativeQuery = true)
    List<Question> getQuestionByMocktest(int mocktestid);

    // list question in queue of approval by subject
    @Query(value = "select * from questions\n" +
            "where subjectid =:subjectid and status = 2 and username =:username", nativeQuery = true)
    List<Question> findQuestionBySubjectId(Integer subjectid, String username);


    //filter by chapterid
    @Query(value = "select * from questions q\n" +
            "where subjectid =:subjectid and chapterid =:chapterid and status = 2", nativeQuery = true)
    List<Question> findQuestionByChapterId(Integer subjectid, Integer chapterid);

    //search by name
    @Query(value = "select * from questions q\n" +
            "where subjectid =:subjectid and questiontitle like %:keyword% and status = 2", nativeQuery = true)
    List<Question> searchQuestionBytitle(Integer subjectid, @Param("keyword") String keyword);

    //find list of question by teacherpractice id
    @Query(value = "SELECT q.* FROM questions q " +
            "JOIN teacherpracticedetails tpd ON q.questionid = tpd.questionid " +
            "JOIN teacherpractice tp ON tp.teacherpracticeid = tpd.teacherpracticeid WHERE tp.teacherpracticeid = :teacherpracticeid", nativeQuery = true)
    List<Question> findQuestionByTeacherPracticeid(Integer teacherpracticeid);

    // delete by id
    @Modifying
    @Transactional
    @Query(value = "delete from teacherpracticedetails where questionid =:questionid", nativeQuery = true)
    void DeleteQuestionByQuestionId(Integer questionid);
}
