package hu.schonherz.training.service.exam;

import java.util.List;

import hu.schonherz.training.service.exam.vo.QuestionVo;

public interface QuestionService extends BaseService<QuestionVo> {
	
	public void save(QuestionVo vo, Long examId) throws Exception;
	
	public void updateNote(QuestionVo vo) throws Exception ;
	
	public void updateText(QuestionVo vo) throws Exception;
	
	public List<QuestionVo> getAllById(Long examId) throws Exception; 
}
