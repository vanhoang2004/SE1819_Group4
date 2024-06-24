package com.example.demo.data;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "select s.classcode, u.userid,  u.username, u.password, u.fullname, u.useremail\n" +
            "            from students s\n" +
            "            join users u on s.userid = u.userid\n" +
            "            where s.classcode =:classcode", nativeQuery = true)
    List<Student> findStudentByClasscode(int classcode);

    @Query(value =
            "select s.classcode, u.userid,  u.username, u.password,u.fullname, u.useremail\n" +
                    "from students s\n" +
                    "join users u on u.userid = s.userid\n" +
                    "where s.classcode =:classcode and u.username like %:keyword%", nativeQuery = true)
    List<Student> searchStudentByUsername(Integer classcode, @Param("keyword") String keyword);

//    @Query(value =
//            "select *\n" +
//                    "from mocktests m\n" +
//                    "where m.subjectid =:subjectid and m.mocktesttitle like %:keyword%", nativeQuery = true)
//    List<Mocktest> searchMocktest(Integer subjectid, @Param("keyword") String keyword);

    @Query(value="SELECT s.* FROM Students s JOIN Users u ON u.UserID = s.UserID WHERE u.Username = :username", nativeQuery = true)
    Student getStudentByUsername(@Param("username") String username);
}