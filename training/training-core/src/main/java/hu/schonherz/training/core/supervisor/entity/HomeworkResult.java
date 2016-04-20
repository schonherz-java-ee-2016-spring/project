package hu.schonherz.training.core.supervisor.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import hu.schonherz.training.core.admin.entity.BaseEntity;
import hu.schonherz.training.core.admin.entity.User;

/**
 * HomeworkResult entity for managing homework results.
 * 
 * @author Mark Bohan
 *
 */
@Entity
@Table(name = "homework_result")
public class HomeworkResult extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 6158032614960856230L;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "homework_id", referencedColumnName = "id")
	private Homework homework;

	private Integer score;

	@Lob
	private String comment;

	public HomeworkResult() {
		super();
	}

	/**
	 * Returns the user.
	 * 
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 * 
	 * @param user
	 *            the user
	 */

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Returns the homework
	 * 
	 * @return the homework
	 */
	public Homework getHomework() {
		return homework;
	}

	/**
	 * Sets the homework.
	 * 
	 * @param homework
	 *            the homework.
	 */
	public void setHomework(Homework homework) {
		this.homework = homework;
	}

	/**
	 * Returns the score.
	 * 
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 * 
	 * @param score
	 *            the score
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * Returns the comment.
	 * 
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comment
	 * 
	 * @param comment
	 *            the comment.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

}
