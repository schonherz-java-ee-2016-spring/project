package hu.schonherz.training.core.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import hu.schonherz.training.core.admin.entity.User;
import hu.schonherz.training.core.exam.entity.Exam;
import hu.schonherz.training.core.exam.relationtable.ExamUserRelation;

public interface ExamUserRelationRepository extends JpaRepository<ExamUserRelation, Long> {

	public List<User> findAllUserByExamId(@Param("id") Long id) throws Exception;
	
	public List<Exam> findAllExamByUserId(@Param("id") Long id) throws Exception;

}
