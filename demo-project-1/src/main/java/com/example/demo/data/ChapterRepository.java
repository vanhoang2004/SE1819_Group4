package com.example.demo.data;

import com.example.demo.entity.Chapters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapters, Integer> {
}
