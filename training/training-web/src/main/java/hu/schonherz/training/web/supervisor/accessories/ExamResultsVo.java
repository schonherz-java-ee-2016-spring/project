package hu.schonherz.training.web.supervisor.accessories;

import java.io.Serializable;

import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.vo.ExamVo;

public class ExamResultsVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserVo user;
	private Integer score;
	private ExamVo exam;

	public ExamResultsVo() {
		// TODO Auto-generated constructor stub
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public ExamVo getExam() {
		return exam;
	}

	public void setExam(ExamVo exam) {
		this.exam = exam;
	}

}
