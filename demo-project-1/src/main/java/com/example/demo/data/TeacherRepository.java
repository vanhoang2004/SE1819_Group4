package com.example.demo.data;

import com.example.demo.entity.Class;
import com.example.demo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query(value = "select t.userId, u.username, u.password, u.useremail, t.subjectid\n" +
            " from teachers t\n" +
            "join users u on u.UserID = t.UserID\n" +
            "where u.username  =:username", nativeQuery = true)
    Teacher findByUserid(String username);

    @Query(value = "select t.subjectid\n" +
            "from teachers t\n" +
            "join users u on t.userid = u.userid\n" +
            "where u.username =:username", nativeQuery = true)
    int findSubjectidByUserName(String username);
}
