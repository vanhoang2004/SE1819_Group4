package com.example.demo.service;

import com.example.demo.data.ChapterRepository;
import com.example.demo.entity.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
	
	public Chapter getChapterByChapterId(int id) {
		return chapterRepository.getChapterByChapterId(id);
	}

}
