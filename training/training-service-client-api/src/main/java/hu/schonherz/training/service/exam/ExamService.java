package hu.schonherz.training.service.exam;

import java.util.List;

import hu.schonherz.training.service.exam.vo.ExamVo;

public interface ExamService extends BaseService<ExamVo> {

	public List<ExamVo> findAllSortedById() throws Exception;

	public void modifyTitle(ExamVo examVo) throws Exception;

}
