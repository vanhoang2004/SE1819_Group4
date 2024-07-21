package com.example.demo.service;

import com.example.demo.data.MockTestRepository;
import com.example.demo.data.StudentRepository;
import com.example.demo.data.TakenMockTestRepository;
import com.example.demo.entity.MockTest;
import com.example.demo.entity.Student;
import com.example.demo.entity.TakenMockTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TakenMockTestService {

	public final TakenMockTestRepository takenMockTestRepository;
	public final StudentRepository studentRepository;
	public final MockTestRepository mockTestRepository;
	@Autowired
	public TakenMockTestService(TakenMockTestRepository takenMockTestRepository, StudentRepository studentRepository,
								MockTestRepository mockTestRepository) {

		this.takenMockTestRepository = takenMockTestRepository;
		this.studentRepository = studentRepository;
		this.mockTestRepository = mockTestRepository;
	}

	public void saveTakenMockTest(TakenMockTest takenMockTest) {
		takenMockTestRepository.save(takenMockTest);
	}

	public TakenMockTest getTakenMockTest(String username, int mocktestid) {
		Student student = studentRepository.getStudentByUsername(username);
		MockTest mt = mockTestRepository.getMockTestById(mocktestid);
		TakenMockTest takenMockTest = takenMockTestRepository.findByMockTestAndStudent(mocktestid, student.getUserId());
		return takenMockTest;
	}


	public void saveMockTestStartime(String username, int mocktestid, LocalDateTime starttime) {
		Student student = studentRepository.getStudentByUsername(username);
		MockTest mt = mockTestRepository.getMockTestById(mocktestid);
		TakenMockTest takenMockTest = takenMockTestRepository.findByMockTestAndStudent(mocktestid, student.getUserId());
		if(takenMockTest == null) {
			takenMockTest = new TakenMockTest();
			takenMockTest.setMockTest(mt);
			takenMockTest.setStudent(student);
		}
		takenMockTest.setStarttime(starttime);
		takenMockTestRepository.save(takenMockTest);
	}

	public void saveMockTestEnd(String username, int mocktestid,float score, LocalDateTime endtime) {
		Student student = studentRepository.getStudentByUsername(username);
		MockTest mt = mockTestRepository.getMockTestById(mocktestid);
		TakenMockTest takenMockTest = takenMockTestRepository.findByMockTestAndStudent(mocktestid, student.getUserId());
		if(takenMockTest != null) {
			takenMockTest.setEndtime(endtime);
			takenMockTest.setScore(score);
			takenMockTestRepository.save(takenMockTest);
		}
		else {
			throw new RuntimeException("No record found for the given mock test and student");
		}
	}
	public List<TakenMockTest> getTakenMockTestByMockTestAndClass(int classcode, int mocktestid) {
		return takenMockTestRepository.getTakenMockTestByMockTestAndClass(classcode,mocktestid);
	}
	public List<TakenMockTest> getTakenMockTestByMockTest(int mocktestid) {
		return takenMockTestRepository.getTakenMockTestByMockTest(mocktestid);
	}


}
