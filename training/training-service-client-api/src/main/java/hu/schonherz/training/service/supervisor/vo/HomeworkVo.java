package hu.schonherz.training.service.supervisor.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Value object represents homework.
 * @author Mark Bohan
 *
 */
public class HomeworkVo implements Serializable {

	private static final long serialVersionUID = -7274189534206810891L;
	
	private Long id;
	private String title;
	private Date deadline;
	private Integer maximumScore;
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "HomeworkVo [id=" + id + ", title=" + title + ", deadline=" + deadline + ", maximumScore=" + maximumScore
				+ ", description=" + description + "]";
	}

}
