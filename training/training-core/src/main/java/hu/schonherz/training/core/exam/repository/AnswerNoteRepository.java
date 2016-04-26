package hu.schonherz.training.core.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.exam.entity.AnswerNote;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface AnswerNoteRepository extends JpaRepository<AnswerNote, Long> {
	public AnswerNote findNoteByAnswerId(@Param("id") Long id) throws Exception;
}
