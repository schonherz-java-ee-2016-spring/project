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
	public List<Answer> findAllByUserId(@Param("id") Long id) throws Exception;
	
	@Modifying
	@Query(value = "UPDATE answer SET good = ?2 WHERE id = ?1", nativeQuery = true)
	public Integer updateGoodById(Long id, boolean good) throws Exception;
}
