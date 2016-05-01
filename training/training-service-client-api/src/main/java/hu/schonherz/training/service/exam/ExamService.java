package hu.schonherz.training.service.exam;

import java.util.List;

import hu.schonherz.training.service.exam.vo.ExamVo;

public interface ExamService extends BaseService<ExamVo> {

	public void add(ExamVo vo) throws Exception;

	public void modifyTitle(ExamVo vo) throws Exception;
	
	public ExamVo getByTitle(String title) throws Exception;

	public List<ExamVo> getAllSortedById() throws Exception;
}
