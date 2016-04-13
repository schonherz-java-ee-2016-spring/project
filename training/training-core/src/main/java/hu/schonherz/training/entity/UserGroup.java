package hu.schonherz.training.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "UserGroup")
public class UserGroup extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String groupName;
	
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate = new Date();
	
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
		return creationDate;
	}

	public void setCreaationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Collection<RoleGroup> getRoleGroups() {
		return roleGroups;
	}

	public void setRoleGroups(Collection<RoleGroup> roleGroups) {
		this.roleGroups = roleGroups;
	}
}
