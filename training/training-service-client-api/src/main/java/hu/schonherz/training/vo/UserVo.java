package hu.schonherz.training.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class UserVo implements Serializable {

	private static final long serialVersionUID = 5932000328505763772L;

	private Long id;
	private String userName;
	private String fullName;
	private String email;
	private Date registrationDate;
	private String password;
	private boolean isActive;
	private Collection<RoleGroupVo> roleGroups;
	private Collection<UserGroupVo> groups;

	public UserVo() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Collection<RoleGroupVo> getRoleGroups() {
		return roleGroups;
	}

	public void setRoleGroups(Collection<RoleGroupVo> roleGroups) {
		this.roleGroups = roleGroups;
	}

	public Collection<UserGroupVo> getGroups() {
		return groups;
	}

	public void setGroups(Collection<UserGroupVo> groups) {
		this.groups = groups;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", userName=" + userName + ", fullName=" + fullName + ", email=" + email
				+ ", registrationDate=" + registrationDate + ", password=" + password + ", isActive=" + isActive
				+ ", roleGroups=" + roleGroups + ", groups=" + groups + "]";
	}

}
