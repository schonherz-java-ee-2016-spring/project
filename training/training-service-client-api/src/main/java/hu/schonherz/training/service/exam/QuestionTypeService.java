package hu.schonherz.training.service.exam;

import hu.schonherz.training.service.exam.vo.QuestionTypeVo;

public interface QuestionTypeService extends BaseService<QuestionTypeVo> {
	
	public void save(QuestionTypeVo vo) throws Exception;
}
