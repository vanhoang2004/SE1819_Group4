package com.example.demo.data;

import com.example.demo.entity.PriorityWeight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PriorityWeightRepository extends JpaRepository<PriorityWeight, Integer>{
	
	@Modifying
	@Query(value = "UPDATE PriorityWeight SET weight = 0 WHERE userid = :userid AND questionid IN :questionids", nativeQuery = true)
	void updatePriorityWeight(@Param("userid") int userid, @Param("questionids") List<Integer> questionids);

}
