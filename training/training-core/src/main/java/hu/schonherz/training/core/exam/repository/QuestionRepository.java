package hu.schonherz.training.core.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.exam.entity.Question;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
