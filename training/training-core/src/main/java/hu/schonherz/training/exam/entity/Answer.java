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

	@OneToOne(fetch = FetchType.LAZY ,mappedBy = "answer")
	private AnswerNote answerNote;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "answer")
	private AnswerText answerText;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id")
	private Option option;

	@Column(name = "good", nullable = true)
	private Boolean good;

	public Answer() {
		super();
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public Boolean getGood() {
		return good;
	}

	public void setGood(Boolean good) {
		this.good = good;
	}

}
