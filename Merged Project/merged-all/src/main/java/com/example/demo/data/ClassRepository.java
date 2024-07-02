package com.example.demo.data;

import com.example.demo.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class,Integer> {
    @Query("SELECT c FROM Class c WHERE c.classCode = :classCode")
    Class findClassById(@Param("classCode") int code);

    @Query("SELECT c FROM Class c WHERE c.className like %:search%")
    List<Class> search(@Param("search") String search);

    @Query(value = "select c.classcode, c.classname\n" +
            "from users u\n" +
            "join teacherclass tc on tc.userid = u.userid\n" +
            "join classes c on c.classcode = tc.classcode\n" +
            "where u.username =:username", nativeQuery = true)
    List<Class> findClassByUserId(String username);

    @Query(value = "select c.classcode, c.classname\n" +
            "from classes c\n" +
            "join teacherclass tc on tc.classcode = c.classcode\n" +
            "where tc.userid =:userid and c.classname like %:keyword%", nativeQuery = true)
    List<Class> searchClass(Integer userid, @Param("keyword") String keyword);

}
