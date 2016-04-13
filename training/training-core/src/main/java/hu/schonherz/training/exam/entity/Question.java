package hu.schonherz.training.exam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import hu.schonherz.training.entity.BaseEntity;

@Entity
@Table(name = "question")
public class Question extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "exam_id", referencedColumnName = "id")
	private Exam exam;

	@ManyToOne
	@JoinColumn(name = "type_id", referencedColumnName = "id")
	private QuestionType questionType;

	// Rich text pls
	@Column(name = "text")
	private String text;

	public Question() {
		super();
	}

	/**
	 * @return the exam
	 */
	public Exam getExam() {
		return exam;
	}

	/**
	 * @param exam
	 *            the exam to set
	 */
	public void setExam(Exam exam) {
		this.exam = exam;
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