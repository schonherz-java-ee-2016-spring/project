package hu.schonherz.training.core.exam.relationtable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import hu.schonherz.training.core.admin.entity.BaseEntity;
import hu.schonherz.training.core.admin.entity.User;
import hu.schonherz.training.core.exam.entity.Exam;

/**
 * The exam-user relation table
 */

@Entity
@Table(name = "exam_user_relation")
@NamedQueries(value = {
		@NamedQuery(name = "findAllByExamId", query = "SELECT eur FROM ExamUserRelation eur WHERE eur.exam.id = :id"),
		@NamedQuery(name = "findAllByUserId", query = "SELECT eur FROM ExamUserRelation eur WHERE eur.user.id = :id"),
		@NamedQuery(name = "deleteAllByExamId", query = "DELETE FROM ExamUserRelation eur WHERE eur.exam.id = :id"),
		@NamedQuery(name = "deleteAllByUserId", query = "DELETE FROM ExamUserRelation eur WHERE eur.user.id = :id") })
public class ExamUserRelation extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "exam_id")
	private Exam exam;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private User user;

	public ExamUserRelation() {
		super();
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
