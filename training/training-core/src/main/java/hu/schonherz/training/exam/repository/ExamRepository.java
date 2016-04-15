package hu.schonherz.training.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.exam.entity.Exam;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface ExamRepository extends JpaRepository<Exam, Long> {
	
	public List<Exam> findAllByOrderByIdAsc() throws Exception;
	
	@Modifying
	@Query(value = "UPDATE exam SET title = ?1 WHERE id = ?2", nativeQuery = true)
	public Integer modifyExamTitleById(String title, Long id) throws Exception;

}
