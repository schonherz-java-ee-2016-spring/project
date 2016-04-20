package hu.schonherz.training.supervisor.vo;

import java.io.Serializable;

import hu.schonherz.training.exam.vo.ExamVo;
import hu.schonherz.training.vo.UserVo;

public class ExamResultVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -752392944337556510L;
	private Long id;
	private UserVo user;
	private ExamVo exam;
	private Integer score;
	private Integer maxScore;

	public ExamResultVo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}

}
