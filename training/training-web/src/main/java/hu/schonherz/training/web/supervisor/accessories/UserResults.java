package hu.schonherz.training.web.supervisor.accessories;

import java.io.Serializable;
import java.util.List;

import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.vo.ExamResultVo;
import hu.schonherz.training.service.supervisor.vo.HomeworkResultVo;

public class UserResults implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserVo user;

	private List<HomeworkResultVo> homeworkResults;

	private List<ExamResultVo> examResults;

	public UserResults() {
		// TODO Auto-generated constructor stub
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public List<HomeworkResultVo> getHomeworkResults() {
		return homeworkResults;
	}

	public void setHomeworkResults(List<HomeworkResultVo> homeworkResults) {
		this.homeworkResults = homeworkResults;
	}

	public List<ExamResultVo> getExamResults() {
		return examResults;
	}

	public void setExamResults(List<ExamResultVo> examResults) {
		this.examResults = examResults;
	}

}
