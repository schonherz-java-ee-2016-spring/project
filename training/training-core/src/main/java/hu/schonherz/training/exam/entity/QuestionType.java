package hu.schonherz.training.exam.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import hu.schonherz.training.entity.BaseEntity;

/**
 * The database entity of a Question type
 */
@Entity
@Table(name = "question_type")
public class QuestionType extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "questionType")
	private List<Question> questionList;

	/**
	 * The name of the Question type
	 * 
	 * Every Name is unique
	 * Names cannot be empty
	 */
	@Column(nullable = false, unique = true)
	private String name;

	public QuestionType() {
		super();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

}
