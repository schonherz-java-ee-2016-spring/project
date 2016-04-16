package hu.schonherz.training.exam.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	/**
	 * The {@link Exam} of the Question
	 * 
	 * Represented as {@code type_id} in the database
	 */
	@ManyToOne
	@JoinColumn(name = "exam_id")
	private Exam exam;
	
	// Rich text pls
	/**
	 * The text (or picture) of the Question
	 */
	@Column(name = "text")
	private String text;

	/**
	 * The {@link QuestionType} of the Question
	 * 
	 * Represented as {@code type_id} in the database
	 */
	@ManyToOne
	@JoinColumn(name = "type_id")
	private QuestionType questionType;

	@OneToMany(mappedBy = "question")
	private List<Option> optionList;

	public Question() {
		super();
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

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public List<Option> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}

}