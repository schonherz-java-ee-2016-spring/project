package hu.schonherz.training.core.exam.relationtable;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import hu.schonherz.training.core.admin.entity.User;
import hu.schonherz.training.core.exam.entity.Exam;

/**
 * The exam-user relation table
 */

@Entity
@Table(name = "exam_user_relation")
@NamedQueries(value = {
		@NamedQuery(name = "findAllUserByExamId", query = "SELECT eur.exam FROM ExamUserRelation eur WHERE eur.exam.id = :id"),
		@NamedQuery(name = "findAllExamByUserId", query = "SELECT eur.user FROM ExamUserRelation eur WHERE eur.user.id = :id") })
public class ExamUserRelation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "exam_id")
	private Exam exam;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	public ExamUserRelation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
