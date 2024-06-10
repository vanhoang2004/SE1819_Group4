package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.TeacherMaterial;

public interface TeacherMaterialRepository extends JpaRepository<TeacherMaterial, Integer> {
	@Query(value="SELECT * FROM TeacherMaterials WHERE ClassCode = :classcode AND SubjectID =:subjectid", nativeQuery = true)
	List<TeacherMaterial> getAllTeachermaterialByClassAndSubject(@Param("classcode") int classcode,@Param("subjectid") int subjectid);

}
