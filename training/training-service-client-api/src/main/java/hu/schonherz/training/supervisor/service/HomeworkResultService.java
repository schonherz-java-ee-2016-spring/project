package hu.schonherz.training.supervisor.service;

import java.util.Date;
import java.util.List;

import hu.schonherz.training.supervisor.vo.HomeworkResultVo;
import hu.schonherz.training.supervisor.vo.HomeworkVo;
import hu.schonherz.training.vo.UserVo;

public interface HomeworkResultService {
	public HomeworkResultVo createHomeworkResult(HomeworkResultVo homeworkResultVo);

	public HomeworkResultVo getHomeworkResultById(Long id);

	public List<HomeworkResultVo> getHomeworkResultsByUser(UserVo userVo);

	public List<HomeworkResultVo> getHomeworkResultsByHomework(HomeworkVo homeworkVo);

	public List<HomeworkResultVo> getAllHomeworkResults();

	public List<HomeworkResultVo> getHomeworkResultsBetween(Date from, Date to);

	public List<HomeworkResultVo> getHomeworkResultsByUsers(List<UserVo> userVos);

}
