package hu.schonherz.training.exam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import hu.schonherz.training.entity.BaseEntity;
import hu.schonherz.training.entity.User;

/**
 * The database entity of an Answer
 * 
 * This entity works like a relational table to connect a {@link User} with an
 * {@link Option} he chose.
 */
@Entity
@Table(name = "answer")
public class Answer extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@OneToOne(mappedBy = "answer")
	private AnswerNote answerNote;
	
	@OneToOne(mappedBy = "answer")
	private AnswerText answerText;
	
	/**
	 * The {@link User} who submitted the Answer
	 * 
	 * Represented as {@code user_id} in the database
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	/**
	 * The {@link Option} that was submitted
	 * 
	 * Represented as {@code option_id} in the database
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id")
	private Option option;

	/**
	 * The result of the Answer
	 * 
	 * It is usually the same as the option's {@code correct} field, but if the
	 * Question's type is text-based, the Answer is not an Option that can be
	 * evaluated immediately, it must be approved by an Instructor
	 */
	@Column(name = "good", nullable = true)
	private Boolean good;

	public Answer() {
		super();
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the option
	 */
	public Option getOption() {
		return option;
	}

	/**
	 * @param option
	 *            the option to set
	 */
	public void setOption(Option option) {
		this.option = option;
	}

	/**
	 * @return the good
	 */
	public Boolean isGood() {
		return good;
	}

	/**
	 * @param good
	 *            the good to set
	 */
	public void setGood(Boolean good) {
		this.good = good;
	}

	public AnswerNote getAnswerNote() {
		return answerNote;
	}

	public void setAnswerNote(AnswerNote answerNote) {
		this.answerNote = answerNote;
	}

	public AnswerText getAnswerText() {
		return answerText;
	}

	public void setAnswerText(AnswerText answerText) {
		this.answerText = answerText;
	}

}
