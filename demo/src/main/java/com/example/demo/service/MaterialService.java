package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Material;
import com.example.demo.repository.MaterialRepository;
@Service
public class MaterialService {
	private final MaterialRepository materialRepository;
	
	@Autowired
	public MaterialService(MaterialRepository materialRepository) {
		
		this.materialRepository = materialRepository;
	}


	public List<Material> getAllMaterialBySubjectAndChapter(int subjectID, int chapterID) {
		return materialRepository.getAllMaterialBySubjectAndChapter(subjectID, chapterID);
	}
}
