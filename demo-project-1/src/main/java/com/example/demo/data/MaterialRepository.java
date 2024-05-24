package com.example.demo.data;

import com.example.demo.entity.Materials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Materials, Integer> {

}
