package hu.schonherz.training.core.supervisor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.admin.entity.Event;
import hu.schonherz.training.core.admin.entity.User;
import hu.schonherz.training.core.supervisor.entity.HomeworkResult;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface HomeworkResultRepository extends JpaRepository<HomeworkResult, Long> {
	public List<HomeworkResult> findHomeworkResultsByUser(User user);
	public List<HomeworkResult> findHomeworkResultsByHomework(Event homework);
}
