package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Chapter;

public interface IChapter {
	List<Chapter> findChapterBySubjectId(int id);
}
