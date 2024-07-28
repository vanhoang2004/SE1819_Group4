package com.example.demo.data;

import com.example.demo.entity.PriorityWeight;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PriorityWeightRepository extends JpaRepository<PriorityWeight, Integer>{

	@Modifying
	@Query(value = "UPDATE PriorityWeight SET weight = 0 WHERE userid = :userid AND questionid IN :questionids", nativeQuery = true)
	void updatePriorityWeight(@Param("userid") int userid, @Param("questionids") List<Integer> questionids);
	@Modifying
	@Transactional
	@Query(value = "INSERT IGNORE INTO PriorityWeight (userid, questionid) VALUES (:userid, :i);", nativeQuery = true)
	void insertPriority(int userid, int i);

	@Query(value ="SELECT DISTINCT pw.userid FROM PriorityWeight pw", nativeQuery = true)
	List<Integer> findDistinctUserIds();

	// Đếm số câu hỏi của user theo levelId, chapterId, subjectId
	@Query(value ="SELECT q.levelid, q.chapterid, q.subjectid, COUNT(pw.questionid) " +
			"FROM PriorityWeight pw " +
			"JOIN Question q ON pw.questionid = q.QuestionID " +
			"WHERE pw.userid = :userid " +
			"GROUP BY q.levelid, q.chapterid, q.subjectid", nativeQuery = true)
	List<Object[]> countQuestionsForUser(@Param("userid") int userid);

	// Xóa tất cả các bản ghi của user theo userId
	@Transactional
	@Query(value = "DELETE FROM PriorityWeight WHERE userid = :userId", nativeQuery = true)
	void deleteByUserId(@Param("userId") int userId);

}
