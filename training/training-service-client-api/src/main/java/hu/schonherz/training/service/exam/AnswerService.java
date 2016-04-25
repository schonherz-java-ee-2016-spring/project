package hu.schonherz.training.service.exam;

import java.util.List;

import hu.schonherz.training.service.exam.vo.AnswerVo;

public interface AnswerService extends BaseService<AnswerVo> {

	public void save(AnswerVo vo) throws Exception;

	public List<AnswerVo> getAllByUserId(Long id) throws Exception;
}
