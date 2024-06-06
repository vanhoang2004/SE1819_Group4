package com.example.demo.data;

import com.example.demo.entity.Question;
import com.example.demo.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    //find list of question by teacherpractice id
    @Query(value = "SELECT q.* FROM questions q " +
            "JOIN teacherpracticedetails tpd ON q.questionid = tpd.questionid " +
            "JOIN teacherpractice tp ON tp.teacherpracticeid = tpd.teacherpracticeid WHERE tp.teacherpracticeid = :teacherpracticeid", nativeQuery = true)
    List<Question> findQuestionByTeacherPracticeid(Integer teacherpracticeid);

    //find question by questionid
    @Query(value = "select*from questions\n" +
            "where questionid =:questionid", nativeQuery = true)
    Question findQuestionByQuestionId(Integer questionid);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO TeacherPracticeDetails (TeacherPracticeID, QuestionID)\n" +
            "VALUES\n" +
            "(:teacherpracticeid, (SELECT MAX(QuestionID) FROM Questions))", nativeQuery = true)
    void insertQuestionByQuestionId(Integer teacherpracticeid);
}
