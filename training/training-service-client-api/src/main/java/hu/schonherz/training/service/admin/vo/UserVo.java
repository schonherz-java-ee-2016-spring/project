package hu.schonherz.training.service.admin.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class UserVo implements Serializable {

	private static final long serialVersionUID = 5932000328505763772L;

	private Long id;
	private String userName;
	private String fullName;
	private String email;
	private Date recDate;
	private Date modDate;
	private String recUser;
	private String modUser;
	private String password;
	private String hashCode;
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
				+ ", registrationDate=" + recDate + ", password=" + password + ", isActive=" + isActive
				+ ", roleGroups=" + roleGroups + ", groups=" + groups + "]";
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

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

}
