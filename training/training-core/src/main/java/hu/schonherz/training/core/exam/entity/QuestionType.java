package hu.schonherz.training.core.exam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import hu.schonherz.training.core.admin.entity.BaseEntity;

/**
 * The database entity of a Question type
 */
@Entity
@Table(name = "question_type")
public class QuestionType extends BaseEntity {
	private static final long serialVersionUID = 1L;


	@Column(nullable = false, unique = true)
	private String name;

	public QuestionType() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
