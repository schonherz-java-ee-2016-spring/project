package hu.schonherz.training.core.supervisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.supervisor.entity.Interview;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface InterviewRepository extends JpaRepository<Interview, Long> {

}