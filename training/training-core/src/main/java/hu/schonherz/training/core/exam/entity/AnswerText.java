package hu.schonherz.training.core.exam.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import hu.schonherz.training.core.admin.entity.BaseEntity;

/**
 * The database entity of an Answer text
 * 
 * These entities represent a text-based answer to {@link Question}s that expect
 * text-based answers.
 */
@Entity
@Table(name = "answer_text")
@NamedQueries(value = {
		@NamedQuery(name = "findTextByAnswerId", query = "SELECT a FROM AnswerText a WHERE a.answer.id = :id") })
public class AnswerText extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	
	@OneToOne
	@JoinColumn(name = "answer_id")
	private Answer answer;

	private String text;

	public AnswerText() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
