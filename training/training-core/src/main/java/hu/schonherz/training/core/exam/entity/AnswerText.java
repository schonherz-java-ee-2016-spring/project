package hu.schonherz.training.core.exam.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
public class AnswerText extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Answer answer;

	@Lob
	private String text;

	public AnswerText() {
		super();
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
