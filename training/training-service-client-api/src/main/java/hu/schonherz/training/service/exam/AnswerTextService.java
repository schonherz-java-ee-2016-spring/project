package hu.schonherz.training.service.exam;

import hu.schonherz.training.service.exam.vo.AnswerTextVo;

public interface AnswerTextService extends BaseService<AnswerTextVo> {

	public void save(AnswerTextVo vo) throws Exception;

	public AnswerTextVo getByAnswerId(Long id) throws Exception;
	
	
}
