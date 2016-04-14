package hu.schonherz.training.supervisor.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import hu.schonherz.training.entity.BaseEntity;

@Entity
@Table(name = "homework")
public class Homework extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 6158032614960856230L;

	@Column(unique = true, nullable = false)
	private String title;

	@Column(name = "deadline_date", nullable = false)
	private Date deadline;

	private Integer maximumScore;

	@Lob
	private String description;

	public Homework() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Integer getMaximumScore() {
		return maximumScore;
	}

	public void setMaximumScore(Integer maximumScore) {
		this.maximumScore = maximumScore;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
