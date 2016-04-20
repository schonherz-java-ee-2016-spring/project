package hu.schonherz.training.core.admin.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "UserGroup")
public class UserGroup extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, unique = true)
	private String groupName;
	
	@Column(nullable = false)
	private String description;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "roleGroup_to_userGroup", joinColumns = @JoinColumn(name = "userGroup_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roleGroup_id", referencedColumnName = "id"))
	private Collection<RoleGroup> roleGroups;
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Collection<RoleGroup> getRoleGroups() {
		return roleGroups;
	}

	public void setRoleGroups(Collection<RoleGroup> roleGroups) {
		this.roleGroups = roleGroups;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
