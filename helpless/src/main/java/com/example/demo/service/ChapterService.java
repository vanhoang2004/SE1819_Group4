package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Chapter;
import com.example.demo.data.ChapterRepository;
@Service
public class ChapterService {
	private final ChapterRepository chapterRepository;
	
	@Autowired
	public ChapterService(ChapterRepository chapterRepository) {
		this.chapterRepository = chapterRepository;
	}

	public List<Chapter> findChapterBySubjectId(int id) {
		return chapterRepository.findChapterBySubjectId(id);
	}
	
	public Optional<Chapter> getChapterByChapterId(int id) {
		return chapterRepository.getChapterByChapterId(id);
	}

}
