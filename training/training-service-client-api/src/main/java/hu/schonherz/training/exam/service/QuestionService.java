package hu.schonherz.training.exam.service;

import hu.schonherz.training.exam.vo.QuestionVo;

public interface QuestionService {
 
	public void createQuestion(QuestionVo questionVo) throws Exception;

	public void removeQuestion(QuestionVo questionVo) throws Exception;
	
}
