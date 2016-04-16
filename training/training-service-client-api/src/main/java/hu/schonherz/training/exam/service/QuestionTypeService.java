package hu.schonherz.training.exam.service;

import java.util.List;

import hu.schonherz.training.exam.vo.QuestionTypeVo;

public interface QuestionTypeService {
	
	public List<QuestionTypeVo> getQuestionTypeList() throws Exception;

	public void createQuestionType(QuestionTypeVo questionTypeVo) throws Exception;
	
	public QuestionTypeVo getQuestionTypeById(Long questionTypeId) throws Exception;

}
