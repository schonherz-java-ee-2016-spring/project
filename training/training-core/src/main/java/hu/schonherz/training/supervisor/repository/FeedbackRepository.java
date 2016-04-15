package hu.schonherz.training.supervisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.supervisor.entity.Feedback;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}