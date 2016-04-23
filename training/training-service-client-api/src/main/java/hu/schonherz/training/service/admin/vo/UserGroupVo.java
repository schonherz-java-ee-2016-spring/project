package hu.schonherz.training.service.admin.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class UserGroupVo implements Serializable {

	private static final long serialVersionUID = -8403753397412273249L;

	private Long id;
	private String groupName;
	private String description;
	private Date recDate;
	private Date modDate;
	private String recUser;
	private String modUser;
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
		return groupName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<RoleGroupVo> getRoleGroups() {
		return roleGroups;
	}

	public void setRoleGroups(List<RoleGroupVo> roleGroups) {
		this.roleGroups = roleGroups;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
