package hu.schonherz.training.service.exam;

import java.util.List;

import hu.schonherz.training.service.exam.vo.QuestionVo;

public interface QuestionService extends BaseService<QuestionVo> {
	
	public void add(QuestionVo vo, Long examId) throws Exception;
	
	public void modifyNote(QuestionVo vo) throws Exception;
	
	public void modifyText(QuestionVo vo) throws Exception;
	
	public List<QuestionVo> getAllByExamId(Long examId) throws Exception; 
}
