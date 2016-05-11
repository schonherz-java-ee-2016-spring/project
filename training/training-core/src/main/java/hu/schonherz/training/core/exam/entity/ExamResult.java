package hu.schonherz.training.core.exam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import hu.schonherz.training.core.admin.entity.BaseEntity;
import hu.schonherz.training.core.admin.entity.User;

/**
 * The database entity of an ExamResult
 */
@Entity
@Table(name = "exam_result")
public class ExamResult extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "exam_id", referencedColumnName = "id")
	private Exam exam;

	@Column(name = "points", nullable = false)
	private Integer points;

	public ExamResult() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

}
