package hu.schonherz.training.exam.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import hu.schonherz.training.entity.BaseEntity;

/**
 * The database entity of an Exam
 */
@Entity
@Table(name = "exam")
public class Exam extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * The title of the Exam
	 * 
	 * Every Title is unique Titles cannot be empty
	 */
	@Column(unique = true, nullable = false)
	private String title;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "exam")
	private List<Question> questionList;

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public Exam() {
		super();
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


}
