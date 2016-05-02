package hu.schonherz.training.core.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.exam.entity.Answer;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface AnswerRepository extends JpaRepository<Answer, Long> {
	public List<Answer> findAnswersByUserId(@Param("id") Long id) throws Exception;
	
	@Modifying
	@Query(value = "UPDATE answer SET good = ?1 WHERE id = ?2", nativeQuery = true)
	public Integer modifyGoodById(boolean good, Long id) throws Exception;
}
