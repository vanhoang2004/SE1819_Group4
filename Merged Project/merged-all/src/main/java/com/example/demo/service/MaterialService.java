package com.example.demo.service;

import com.example.demo.data.MaterialRepository;
import com.example.demo.entity.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
