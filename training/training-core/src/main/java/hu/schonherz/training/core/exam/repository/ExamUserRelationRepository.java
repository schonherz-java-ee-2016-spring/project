package hu.schonherz.training.core.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.exam.relationtable.ExamUserRelation;


@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface ExamUserRelationRepository extends JpaRepository<ExamUserRelation, Long> {

	public List<ExamUserRelation> findAllByExamId(@Param("id") Long id) throws Exception;
	
	public List<ExamUserRelation> findAllByUserId(@Param("id") Long id) throws Exception;
	
	public void deleteAllByExamId(@Param("id") Long id) throws Exception;
	
	public void deleteAllByUserId(@Param("id") Long id) throws Exception;
}
