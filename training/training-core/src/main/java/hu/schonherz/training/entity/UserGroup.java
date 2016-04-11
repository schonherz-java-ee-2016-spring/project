package hu.schonherz.training.entity;

import java.util.Collection;
import java.util.Date;

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

	private String groupName;
	
	private Date creationDate;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rolegroup_to_usergroup", joinColumns = @JoinColumn(name = "usergroup_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "rolegroup_id", referencedColumnName = "id"))
	private Collection<RoleGroup> roleGroups;
	
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
	
	public Collection<RoleGroup> getRoleGroups() {
		return roleGroups;
	}

	public void setRoles(Collection<RoleGroup> roleGroups) {
		this.roleGroups = roleGroups;
	}

	
}
