package hu.schonherz.training.core.exam.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import hu.schonherz.training.core.admin.entity.BaseEntity;

/**
 * The database entity of a Question
 */
@Entity
@Table(name = "question")
public class Question extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "text")
	private String text;

	@Column(name = "note", nullable = true)
	private String note;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id")
	private QuestionType questionType;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "question_id")
	@OrderBy("id asc")
	private Collection<Option> options;

	public Question() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public Collection<Option> getOptions() {
		return options;
	}

	public void setOptions(Collection<Option> options) {
		this.options = options;
	}

}