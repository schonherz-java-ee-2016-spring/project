package hu.schonherz.training.exam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	 * Every Title is unique
	 * Titles cannot be empty
	 */
	@Column(unique = true, nullable = false)
	private String title;

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
