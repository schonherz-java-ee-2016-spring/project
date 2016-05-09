package hu.schonherz.training.service.supervisor;

import java.util.List;

import hu.schonherz.training.service.admin.vo.EventVo;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.vo.ExamResultVo;

public interface ExamResultService {

	public List<ExamResultVo> getAll() throws Exception;

	public List<ExamResultVo> getExamResultByUser(UserVo userVo) throws Exception;

	public List<ExamResultVo> getExamResultByExam(EventVo examVo) throws Exception;
}
