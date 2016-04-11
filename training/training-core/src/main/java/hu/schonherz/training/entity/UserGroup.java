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
	
	private Date creaationDate;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "roleGroup_to_userGroup", joinColumns = @JoinColumn(name = "userGroup_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roleGroup_id", referencedColumnName = "id"))
	private Collection<RoleGroup> roleGroups;
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Date getCreaationDate() {
		return creaationDate;
	}

	public void setCreaationDate(Date creaationDate) {
		this.creaationDate = creaationDate;
	}

	public Collection<RoleGroup> getRoleGroups() {
		return roleGroups;
	}

	public void setRoleGroups(Collection<RoleGroup> roleGroups) {
		this.roleGroups = roleGroups;
	}

	
}
