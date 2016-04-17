package hu.schonherz.training.exam.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import hu.schonherz.training.entity.BaseEntity;

/**
 * The database entity of a Question
 */
@Entity
@Table(name = "question")
public class Question extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exam_id")
	private Exam exam;

	@Column(name = "text")
	private String text;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id")
	private QuestionType questionType;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
	private List<Option> optionList;

	public Question() {
		super();
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public List<Option> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}

}