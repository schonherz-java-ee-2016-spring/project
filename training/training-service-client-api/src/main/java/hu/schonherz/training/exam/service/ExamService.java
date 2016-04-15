package hu.schonherz.training.exam.service;

import java.util.List;

import hu.schonherz.training.exam.vo.ExamVo;

public interface ExamService {

	public List<ExamVo> getExamList() throws Exception;
	
	public List<ExamVo> getExamListSortedById() throws Exception;
	
	public ExamVo getExamById(Long id) throws Exception;
	
	public void createExam(ExamVo examVo) throws Exception;
	
	public void modifyExamTitle(ExamVo examVo) throws Exception;

}
