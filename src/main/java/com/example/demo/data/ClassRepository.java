package com.example.demo.data;

import com.example.demo.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class,Integer> {
}
