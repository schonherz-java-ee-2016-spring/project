package hu.schonherz.training.core.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import hu.schonherz.training.core.exam.relationtable.ExamUserRelation;

public interface ExamUserRelationRepository extends JpaRepository<ExamUserRelation, Long> {

	public List<ExamUserRelation> findAllByExamId(@Param("id") Long id) throws Exception;
	
	public List<ExamUserRelation> findAllByUserId(@Param("id") Long id) throws Exception;

}
