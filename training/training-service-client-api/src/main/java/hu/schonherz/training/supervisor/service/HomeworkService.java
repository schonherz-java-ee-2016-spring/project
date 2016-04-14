package hu.schonherz.training.supervisor.service;

import java.util.Date;
import java.util.List;

import hu.schonherz.training.supervisor.vo.HomeworkVo;

public interface HomeworkService {
	public HomeworkVo createHomework(HomeworkVo homeworkVo);

	public void deleteHomework(Long id);

	public HomeworkVo getHomeworkById(Long id);

	public HomeworkVo getHomeworkByTitle(String title);

	public List<HomeworkVo> getAllHomeworks();

	public List<HomeworkVo> getHomeworksBetween(Date from, Date to);
}
