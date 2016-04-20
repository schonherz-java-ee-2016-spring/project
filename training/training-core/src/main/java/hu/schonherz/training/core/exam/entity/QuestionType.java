package hu.schonherz.training.core.exam.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import hu.schonherz.training.core.admin.entity.BaseEntity;

/**
 * The database entity of a Question type
 */
@Entity
@Table(name = "question_type")
public class QuestionType extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "questionType")
	private List<Question> questionList;

	@Column(nullable = false, unique = true)
	private String name;

	public QuestionType() {
		super();
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
