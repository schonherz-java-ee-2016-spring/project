package hu.schonherz.training.service.admin.vo;

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
	private Date recDate;
	private Date modDate;
	private String recUser;
	private String modUser;
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
	@Override
	public String toString() {
		return name;
	}
	public Collection<RoleVo> getRoles() {
		return roles;
	}
	public void setRoles(Collection<RoleVo> roles) {
		this.roles = roles;
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
	

	
}
