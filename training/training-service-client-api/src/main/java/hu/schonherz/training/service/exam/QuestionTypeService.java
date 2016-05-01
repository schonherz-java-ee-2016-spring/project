package hu.schonherz.training.service.exam;

import hu.schonherz.training.service.exam.vo.QuestionTypeVo;

public interface QuestionTypeService extends BaseService<QuestionTypeVo> {
	
	public void add(QuestionTypeVo vo) throws Exception;

	public QuestionTypeVo getByName(String questionTypeName) throws Exception;
}
