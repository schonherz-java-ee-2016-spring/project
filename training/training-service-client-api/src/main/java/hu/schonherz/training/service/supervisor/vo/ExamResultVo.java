package hu.schonherz.training.service.supervisor.vo;

import java.io.Serializable;

import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.vo.ExamVo;

public class ExamResultVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -752392944337556510L;
	private UserVo user;
	private ExamVo exam;
	private Integer score;

	public ExamResultVo() {
		super();
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public ExamVo getExam() {
		return exam;
	}

	public void setExam(ExamVo exam) {
		this.exam = exam;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
