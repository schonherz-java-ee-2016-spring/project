package hu.schonherz.training.service.exam;

import java.util.List;

import hu.schonherz.training.service.exam.vo.ExamVo;

public interface ExamService extends BaseService<ExamVo> {

	public void save(ExamVo vo) throws Exception;

	public void updateTitle(ExamVo vo) throws Exception;
	
	public ExamVo getByTitle(String title) throws Exception;

	public List<ExamVo> getAllSortedById() throws Exception;
}
