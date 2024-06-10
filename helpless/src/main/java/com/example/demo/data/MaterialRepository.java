package com.example.demo.data;

import com.example.demo.entity.Material;
import com.example.demo.entity.Mocktest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
    @Query(value = "select * from materials\n" +
            "            where subjectid = :subjectid", nativeQuery = true)
    List<Material> findMaterialBySubjectid(int subjectid);

    @Query(value = "select*from materials m\n" +
            "where m.subjectid =:subjectid and m.title like %:keyword%", nativeQuery = true)
    List<Material> searchMaterial(Integer subjectid, @Param("keyword") String keyword);
}