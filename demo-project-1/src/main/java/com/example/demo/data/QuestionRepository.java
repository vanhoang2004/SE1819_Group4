package com.example.demo.data;

import com.example.demo.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
Question findQuestionBYID(int id);
}
