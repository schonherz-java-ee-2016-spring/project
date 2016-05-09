package hu.schonherz.training.service.supervisor.vo;

import java.io.Serializable;

import hu.schonherz.training.service.admin.vo.EventVo;
import hu.schonherz.training.service.admin.vo.UserVo;

public class ExamResultVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -752392944337556510L;
	private UserVo user;
	private EventVo exam;
	private Integer points;

	public ExamResultVo() {
		super();
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public EventVo getExam() {
		return exam;
	}

	public void setExam(EventVo exam) {
		this.exam = exam;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

}
