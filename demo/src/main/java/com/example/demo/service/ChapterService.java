package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Chapter;
import com.example.demo.repository.ChapterRepository;
@Service
public class ChapterService implements IChapter{
	private final ChapterRepository chapterRepository;
	
	@Autowired
	public ChapterService(ChapterRepository chapterRepository) {
		this.chapterRepository = chapterRepository;
	}

	@Override
	public List<Chapter> findChapterBySubjectId(int id) {
		return chapterRepository.findChapterBySubjectId(id);
	}

}
