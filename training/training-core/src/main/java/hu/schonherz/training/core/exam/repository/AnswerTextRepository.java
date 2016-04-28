package hu.schonherz.training.core.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.exam.entity.AnswerText;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface AnswerTextRepository extends JpaRepository<AnswerText, Long> {
	public AnswerText findTextByAnswerId(@Param("id") Long id) throws Exception;
}
