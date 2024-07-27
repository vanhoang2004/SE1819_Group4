package com.example.demo.data;

import com.example.demo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    @Query("SELECT t FROM Teacher t WHERE t.user.userId = :userId")
    Teacher findTeacherById(@Param("userId") int id);

    @Query("SELECT t FROM Teacher t WHERE t.user.username LIKE :search% OR t.user.fullname LIKE :search% OR t.user.useremail LIKE :search%")
    List<Teacher> search(@Param("search") String search);

    @Query("SELECT t FROM Teacher t JOIN t.subject s WHERE s.subjectId = :filter")
    List<Teacher> filterBySubject(@Param("filter") int filter);

    @Query(value = "select t.userId, u.username, u.password, u.useremail, t.subjectid\n" +
            " from teachers t\n" +
            "join users u on u.UserID = t.UserID\n" +
            "where u.username  =:username", nativeQuery = true)
    Teacher findByUsername(String username);

    @Query(value = "select t.subjectid\n" +
            "from teachers t\n" +
            "join users u on t.userid = u.userid\n" +
            "where u.username =:username", nativeQuery = true)
    int findSubjectIdByUsername(String username);

    @Query(value="select UserID from teacherclass where classcode = :classcode", nativeQuery = true)
    List<Integer> getTeacherInClass(int classcode);
}
