package hu.schonherz.training.service.exam;

import hu.schonherz.training.service.exam.vo.QuestionVo;

public interface QuestionService extends BaseService<QuestionVo>{
	
	public void add(QuestionVo vo, Long...id) throws Exception;
	
	public void remove(Long questionId) throws Exception;
	
	public void updateTitle(QuestionVo questioVo) throws Exception;
}
