package hu.schonherz.training.service.supervisor.vo;

import java.io.Serializable;
import java.util.Date;

import hu.schonherz.training.service.admin.vo.UserGroupVo;

/**
 * Value object represents homework.
 * @author Mark Bohan
 *
 */
public class HomeworkVo implements Serializable {

	private static final long serialVersionUID = -7274189534206810891L;
	
	private Long id;
	private Date recDate;
	private Date modDate;
	private String recUser;
	private String modUser;
	private String title;
	private Date deadline;
	private Integer maximumScore;
	private String description;
	private UserGroupVo usergroup;
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
	public Date getRecDate() {
		return recDate;
	}
	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}
	public Date getModDate() {
		return modDate;
	}
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	public String getRecUser() {
		return recUser;
	}
	public void setRecUser(String recUser) {
		this.recUser = recUser;
	}
	public String getModUser() {
		return modUser;
	}
	public void setModUser(String modUser) {
		this.modUser = modUser;
	}
	public UserGroupVo getUsergroup() {
		return usergroup;
	}
	public void setUsergroup(UserGroupVo usergroup) {
		this.usergroup = usergroup;
	}

}
