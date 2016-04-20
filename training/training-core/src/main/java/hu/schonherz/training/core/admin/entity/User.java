package hu.schonherz.training.core.admin.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "User")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 50, unique = true)
	private String userName;

	@Column(nullable = false, length = 100)
	private String fullName;

	@Column(nullable = false, length = 100, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isActive;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rolegroup_to_user", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "rolegroup_id", referencedColumnName = "id"))
	private Collection<RoleGroup> roleGroups;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "group_to_user", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"))
	private Collection<UserGroup> groups;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<UserGroup> getGroups() {
		return groups;
	}

	public void setGroups(Collection<UserGroup> groups) {
		this.groups = groups;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Collection<RoleGroup> getRoleGroups() {
		return roleGroups;
	}

	public void setRoleGroups(Collection<RoleGroup> roleGroups) {
		this.roleGroups = roleGroups;
	}
}
