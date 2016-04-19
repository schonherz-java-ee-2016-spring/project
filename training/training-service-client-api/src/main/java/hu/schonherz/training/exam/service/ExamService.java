package hu.schonherz.training.exam.service;

import java.util.List;

import hu.schonherz.training.exam.vo.ExamVo;

public interface ExamService extends BaseService<ExamVo> {

	public List<ExamVo> findAllSortedById() throws Exception;

	public void modifyTitle(ExamVo examVo) throws Exception;

}
