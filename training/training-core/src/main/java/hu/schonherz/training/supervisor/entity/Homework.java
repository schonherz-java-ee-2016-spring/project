package hu.schonherz.training.supervisor.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import hu.schonherz.training.entity.BaseEntity;
import hu.schonherz.training.entity.UserGroup;

/**
 * Homework entity for managing homeworks
 * 
 * @author Mark Bohan
 *
 */
@Entity
@Table(name = "homework")
public class Homework extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 6158032614960856230L;

	@Column(unique = true, nullable = false)
	private String title;

	@Column(name = "deadline_date", nullable = false)
	private Date deadline;

	private Integer maximumScore;

	private String description;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "user_group_id", referencedColumnName = "id")
	private UserGroup userGroup;

	public Homework() {
		super();
	}

	/**
	 * Returns the title of the homework.
	 * 
	 * @return the title of the homework
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of homework.
	 * 
	 * @param title
	 *            the title of homework
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns the date of deadline.
	 * 
	 * @return the date of deadline
	 */
	public Date getDeadline() {
		return deadline;
	}

	/**
	 * Sets the deadline of homework.
	 * 
	 * @param deadline
	 *            the deadline of homework
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	/**
	 * Returns the maximum score of homework.
	 * 
	 * @return the maximum score of homework
	 */
	public Integer getMaximumScore() {
		return maximumScore;
	}

	/**
	 * 
	 * Sets the maximum score of homework.
	 * 
	 * @param maximumScore
	 *            the maximum score of homework
	 */
	public void setMaximumScore(Integer maximumScore) {
		this.maximumScore = maximumScore;
	}

	/**
	 * Returns the description of this homework.
	 * 
	 * @return the description of this homework
	 */

	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of homework.
	 * 
	 * @param description
	 *            the description of homework.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the user group which has the homework.
	 * 
	 * @return the user group which has the homework
	 */

	public UserGroup getUserGroup() {
		return userGroup;
	}

	/**
	 * Sets the user group which has the homework.
	 * 
	 * @param userGroup
	 *            the user group which has the homework
	 */
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
}
