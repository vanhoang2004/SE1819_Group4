package com.example.demo.data;

import com.example.demo.entity.Mocktest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MockTestRepository extends JpaRepository<Mocktest,Integer> {
}
