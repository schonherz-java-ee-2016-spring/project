package hu.schonherz.training.service.admin.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


public class EventVo implements Serializable{

	private static final long serialVersionUID = -5526760535114235035L;
	
	private Long id;
	private String name;
	private String type;
	private String description;
	private String place;
	private Date date;
	private Date recDate;
	private Date modDate;
	private String recUser;
	private String modUser;
	private Collection<UserGroupVo> groups;
	private Collection<UserVo> users;
	
	public EventVo() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Date getTime() {
		return date;
	}
	public void setTime(Date time) {
		this.date = time;
	}
	@Override
	public String toString() {
		return "Event [name=" + name + ", type=" + type + ", description=" + description + ", place=" + place
				+ ", time=" + date + "]";
	}
	public Collection<UserGroupVo> getGroups() {
		return groups;
	}
	public void setGroups(Collection<UserGroupVo> groups) {
		this.groups = groups;
	}
	public Collection<UserVo> getUsers() {
		return users;
	}
	public void setUsers(Collection<UserVo> users) {
		this.users = users;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
