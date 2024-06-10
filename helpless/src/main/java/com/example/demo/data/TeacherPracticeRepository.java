package com.example.demo.data;

import com.example.demo.entity.Mocktest;
import com.example.demo.entity.TeacherMaterials;
import com.example.demo.entity.TeacherPractice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherPracticeRepository extends JpaRepository<TeacherPractice, Integer> {
    @Query(value = "select * from teacherpractice \n" +
            "where classcode =:classcode and subjectid =:subjectid", nativeQuery = true)
    List<TeacherPractice> findTeacherPracticeByClassAndSubject(Integer classcode, Integer subjectid);

    @Query(value = "select * from \n" +
            "teacherpractice\n" +
            "where teacherpracticeid =:teacherpracticeid", nativeQuery = true)
    TeacherPractice findTeacherPracticeById(Integer teacherpracticeid);

    @Query(value =
            "select*from teacherpractice\n" +
                    "where subjectid =:subjectid and classcode =:classcode and title like %:keyword%", nativeQuery = true)
    List<TeacherPractice> searchTeacherPractice(Integer subjectid, Integer classcode, @Param("keyword") String keyword);

}
