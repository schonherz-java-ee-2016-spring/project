package hu.schonherz.training.core.supervisor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.admin.entity.User;
import hu.schonherz.training.core.exam.entity.Exam;
import hu.schonherz.training.core.exam.entity.ExamResult;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {

	public List<ExamResult> findExamResultsByUser(User user);

	public List<ExamResult> findExamResultsByExam(Exam exam);
	
	public ExamResult findByExamIdAndUserId(Long examId, Long userId) throws Exception;
}
