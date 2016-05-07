package hu.schonherz.training.core.admin.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "Training")
public class Training extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	private Date beginning;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "theme_to_training", joinColumns = @JoinColumn(name = "training_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "theme_id", referencedColumnName = "id"))
	private Collection<Theme> themes;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_to_training", joinColumns = @JoinColumn(name = "training_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	private Collection<User> users;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "groups_to_training", joinColumns = @JoinColumn(name = "training_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
	private Collection<UserGroup> userGroups;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBeginning() {
		return beginning;
	}
	public void setBeginning(Date beginning) {
		this.beginning = beginning;
	}

	@Override
	public String toString() {
		return "Training [name=" + name + ", beginning=" + beginning + ", end=" + endDate + "]";
	}

	public Collection<Theme> getThemes() {
		return themes;
	}

	public void setThemes(Collection<Theme> themes) {
		this.themes = themes;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public Collection<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Collection<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}
}
