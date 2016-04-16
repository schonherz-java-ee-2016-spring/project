package hu.schonherz.training.exam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import hu.schonherz.training.entity.BaseEntity;

/**
 * The database entity of an Answer note
 * 
 * These notes are written by the Instructor to comment your {@link Answer}
 */
@Entity
@Table(name = "answer_note")
public class AnswerNote extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * The {@link Answer} which has the note attached to it
	 * 
	 * Represented as {@code answer_id} in the database
	 */
	@OneToOne
	@PrimaryKeyJoinColumn
	private Answer answer;

	/**
	 * The note that contains the message
	 */
	@Column(nullable = true)
	// @Type(type = "org.hibernate.type.StringClobType")
	// @Lob
	private String note;

	public AnswerNote() {
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
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
}
