package hu.schonherz.training.core.supervisor.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.supervisor.entity.Homework;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface HomeworkRepository extends JpaRepository<Homework, Long> {
	public Homework findHomeworkByTitle(String title);
	public List<Homework> findHomeworksByDeadlineBetween(Date from, Date to);
}
