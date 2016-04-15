package hu.schonherz.training.exam.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import hu.schonherz.training.entity.BaseEntity;

/**
 * The database entity of an Answer text
 * 
 * These entities represent a text-based answer to {@link Question}s that expect
 * text-based answers.
 */
@Entity
@Table(name = "answer_text")
public class AnswerText extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * The {@link Answer} which has the text attached to it
	 * 
	 * Represented as {@code answer_id} in the database
	 */
	@OneToOne
	@JoinColumn(name = "answer_id", referencedColumnName = "id")
	private Answer answer;

	/**
	 * The submitted text answer
	 */
	// @Type(type = "org.hibernate.type.StringClobType")
	@Lob
	private String text;

	public AnswerText() {
		super();
	}

	/**
	 * @return the answer
	 */
	public Answer getAnswer() {
		return answer;
	}

	/**
	 * @param answer
	 *            the answer to set
	 */
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
}
