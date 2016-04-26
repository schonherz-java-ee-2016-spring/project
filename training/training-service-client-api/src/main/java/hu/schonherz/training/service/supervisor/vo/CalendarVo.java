package hu.schonherz.training.service.supervisor.vo;

import java.io.Serializable;
import java.util.Date;

public class CalendarVo implements Serializable{
	private static final long serialVersionUID = -4008618666948421357L;
	
	private String title;
	private String description;
	private Date date;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
