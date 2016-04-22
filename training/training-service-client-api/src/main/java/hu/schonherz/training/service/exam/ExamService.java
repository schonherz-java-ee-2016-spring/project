package hu.schonherz.training.service.exam;

import java.util.List;

import hu.schonherz.training.service.exam.vo.ExamVo;

public interface ExamService extends BaseService<ExamVo> {

	public List<ExamVo> getAllSortedById() throws Exception;

	public void updateTitle(ExamVo examVo) throws Exception;

}
