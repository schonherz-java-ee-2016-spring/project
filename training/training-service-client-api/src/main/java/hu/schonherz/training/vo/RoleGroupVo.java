package hu.schonherz.training.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class RoleGroupVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Date createDate;
	private Date editDate;
	private Collection<RoleVo> roles;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	
	@Override
	public String toString() {
		return "RoleGroupVo [id=" + id + ", name=" + name + ", createDate=" + createDate + ", editDate=" + editDate
				+ "]";
	}
	public Collection<RoleVo> getRoles() {
		return roles;
	}
	public void setRoles(Collection<RoleVo> roles) {
		this.roles = roles;
	}
	

	
}
