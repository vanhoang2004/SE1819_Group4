package com.example.demo.data;

import com.example.demo.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager,Integer> {
}
