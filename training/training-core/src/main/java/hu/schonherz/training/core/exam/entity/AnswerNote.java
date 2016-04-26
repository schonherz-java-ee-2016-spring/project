package hu.schonherz.training.core.exam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import hu.schonherz.training.core.admin.entity.BaseEntity;

/**
 * The database entity of an Answer note
 * 
 * These notes are written by the Instructor to comment your {@link Answer}
 */
@Entity
@Table(name = "answer_note")
@NamedQueries(value = {
		@NamedQuery(name = "findNoteByAnswerId", query = "SELECT a FROM AnswerNote a WHERE a.answer.id = :id") })
public class AnswerNote extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "answer_id")
	private Answer answer;

	@Column(nullable = true)
	private String note;

	public AnswerNote() {
		super();
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
