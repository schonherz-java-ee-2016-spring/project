package hu.schonherz.training.service.exam;

import java.util.List;

import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.vo.ExamUserRelationVo;
import hu.schonherz.training.service.exam.vo.ExamVo;

public interface ExamUserRelationService {

	public List<ExamVo> getAllExamByUserId(Long examId) throws Exception;

	public List<UserVo> getAllUserByExamId(Long userId) throws Exception;
	
	public ExamUserRelationVo getByExamIdAndUserId(Long examId, Long userId) throws Exception;

	public void add(ExamUserRelationVo examUserRelationVo) throws Exception;

	public void removeAllByUserId(Long userId) throws Exception;

	public void removeAllByExamId(Long examId) throws Exception;
}
