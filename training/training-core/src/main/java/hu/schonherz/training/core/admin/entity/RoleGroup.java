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
@Table(name = "RoleGroup")
public class RoleGroup extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "role_to_roleGroup", joinColumns = @JoinColumn(name = "rolegroup_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	
}
