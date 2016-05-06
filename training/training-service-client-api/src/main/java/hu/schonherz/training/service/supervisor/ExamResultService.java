package hu.schonherz.training.service.supervisor;

import java.util.List;

import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.vo.ExamVo;
import hu.schonherz.training.service.supervisor.vo.ExamResultVo;

public interface ExamResultService {
	
	public void add(ExamResultVo examResultVo) throws Exception;

	public List<ExamResultVo> getAll() throws Exception;

	public List<ExamResultVo> getExamResultByUser(UserVo userVo) throws Exception;

	public List<ExamResultVo> getExamResultByExam(ExamVo examVo) throws Exception;
	
	public ExamResultVo getByExamIdAndUserId(Long examId, Long userId) throws Exception;
}
