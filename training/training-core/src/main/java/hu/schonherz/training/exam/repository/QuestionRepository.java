package hu.schonherz.training.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import hu.schonherz.training.exam.entity.Question;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
