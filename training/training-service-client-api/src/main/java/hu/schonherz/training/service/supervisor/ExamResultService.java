package hu.schonherz.training.service.supervisor;

import java.util.List;

import hu.schonherz.training.service.admin.vo.EventVo;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.vo.ExamResultVo;

public interface ExamResultService {
	
	public void add(ExamResultVo examResultVo) throws Exception;

	public List<ExamResultVo> getAll() throws Exception;

	public List<ExamResultVo> getExamResultByUser(UserVo userVo) throws Exception;


	public List<ExamResultVo> getAll() throws Exception;

	public List<ExamResultVo> getExamResultByUser(UserVo userVo) throws Exception;

	public List<ExamResultVo> getExamResultByExam(EventVo examVo) throws Exception;
	
	public ExamResultVo getByExamIdAndUserId(Long examId, Long userId) throws Exception;
	
	public void modifyScore(Long examId, Long userId, Integer score) throws Exception;
}
