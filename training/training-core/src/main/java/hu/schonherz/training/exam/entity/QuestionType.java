package hu.schonherz.training.exam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import hu.schonherz.training.entity.BaseEntity;

/**
 * The database entity of a Question type
 */
@Entity
@Table(name = "question_type")
public class QuestionType extends BaseEntity {
	private static final long serialVersionUID = 1L;

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

}
