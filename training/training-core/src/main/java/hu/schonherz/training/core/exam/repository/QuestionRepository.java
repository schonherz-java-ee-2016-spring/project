package hu.schonherz.training.core.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.exam.entity.Question;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	@Modifying
	@Query(value = "UPDATE question SET text = ?1 WHERE id = ?2", nativeQuery = true)
	public Integer modifyQuestionTitleById(String text, Long id) throws Exception;
	
	@Modifying
	@Query(value = "UPDATE question SET note = ?1 WHERE id = ?2", nativeQuery = true)
	public Integer modifyQuestionNoteById(String text, Long id) throws Exception;
}
