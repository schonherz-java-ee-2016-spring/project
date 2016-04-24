package hu.schonherz.training.service.exam;

import java.util.List;

import hu.schonherz.training.service.exam.vo.ExamVo;

public interface ExamService extends BaseService<ExamVo> {
	
	public void add(ExamVo vo, Long...id) throws Exception;

	public List<ExamVo> getAllSortedById() throws Exception;
	
	public void remove(Long examId) throws Exception;

	public void updateTitle(ExamVo vo) throws Exception;
	
	

}
