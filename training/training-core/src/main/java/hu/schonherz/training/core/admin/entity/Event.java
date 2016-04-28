package hu.schonherz.training.core.admin.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Event")
public class Event extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String type;
	private String description;
	private String place;
	private Date date;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "group_to_event", joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
	private Collection<UserGroup> groups;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_to_event", joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
	private Collection<User> users;
	
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Collection<UserGroup> getGroups() {
		return groups;
	}
	public void setGroups(Collection<UserGroup> groups) {
		this.groups = groups;
	}
	public Collection<User> getUsers() {
		return users;
	}
	public void setUsers(Collection<User> users) {
		this.users = users;
	}
}
