package hu.schonherz.training.service.exam;

import java.util.List;

import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.vo.ExamUserRelationVo;
import hu.schonherz.training.service.exam.vo.ExamVo;

public interface ExamUserRelationService {

	public List<ExamVo> getAllExamByUserId(Long id) throws Exception;

	public List<UserVo> getAllUserByExamId(Long id) throws Exception;

	public void add(ExamUserRelationVo vo) throws Exception;

	public void removeAllByUserId(Long userId) throws Exception;

	public void removeAllByExamId(Long examId) throws Exception;
}
