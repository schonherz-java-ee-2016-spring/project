package hu.schonherz.training.core.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.exam.entity.Exam;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface ExamRepository extends JpaRepository<Exam, Long> {
	
	public Exam findByTitleIgnoreCase(String title) throws Exception;
	
	@Modifying
	@Query(value = "UPDATE exam SET title = ?1 WHERE id = ?2", nativeQuery = true)
	public Integer updateExamTitleById(String title, Long id) throws Exception;

}
