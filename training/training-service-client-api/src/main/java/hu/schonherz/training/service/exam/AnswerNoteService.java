package hu.schonherz.training.service.exam;

import hu.schonherz.training.service.exam.vo.AnswerNoteVo;

public interface AnswerNoteService extends BaseService<AnswerNoteVo> {

	public void save(AnswerNoteVo vo) throws Exception;

	public AnswerNoteVo getByAnswerId(Long id) throws Exception;
}
