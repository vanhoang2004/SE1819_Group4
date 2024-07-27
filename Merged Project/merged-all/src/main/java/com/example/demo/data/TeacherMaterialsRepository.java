package com.example.demo.data;

import com.example.demo.entity.TeacherMaterials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherMaterialsRepository extends JpaRepository<TeacherMaterials, Integer> {
    @Query(value = "select * from teachermaterials " +
            "where classcode =:classcode and subjectid=:subjectid", nativeQuery = true)
    List<TeacherMaterials> findTeacherMaterialsByClassAndSubject(Integer classcode, Integer subjectid);

    @Query(value = "select * from \n" +
            "teachermaterials\n" +
            "where teachermaterialid= :teachermaterialid", nativeQuery = true)
    TeacherMaterials findTeacherMaterialsById(Integer teachermaterialid);


    @Query(value =
            "select*from teachermaterials\n" +
                    "where subjectid =:subjectid and classcode =:classcode and title like %:keyword%", nativeQuery = true)
    List<TeacherMaterials> searchTeacherMaterial(Integer subjectid, Integer classcode, @Param("keyword") String keyword);

    @Query(value="SELECT * FROM TeacherMaterials WHERE ClassCode = :classcode AND SubjectID =:subjectid", nativeQuery = true)
    List<TeacherMaterials> getAllTeachermaterialByClassAndSubject(@Param("classcode") int classcode,@Param("subjectid") int subjectid);
}

