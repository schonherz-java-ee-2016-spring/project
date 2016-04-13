package hu.schonherz.training.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class UserGroupVo implements Serializable {

	private static final long serialVersionUID = -8403753397412273249L;

	private Long id;
	private String groupName;
	private Date creationDate;
	private List<RoleGroupVo> roleGroups;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return groupName;
	}

	public void setName(String GroupName) {
		groupName = GroupName;
	}

	@Override
	public String toString() {
		return "RoleVO [id=" + id + ", Name=" + groupName + "]";
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<RoleGroupVo> getRoleGroups() {
		return roleGroups;
	}

	public void setRoleGroups(List<RoleGroupVo> roleGroups) {
		this.roleGroups = roleGroups;
	}

}
