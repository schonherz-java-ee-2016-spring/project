package hu.schonherz.training.exam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Answer answer;

	@Column(nullable = true)
	private String note;

	public AnswerNote() {
		super();
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
