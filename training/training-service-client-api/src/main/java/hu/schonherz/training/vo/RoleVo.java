package hu.schonherz.training.vo;

import java.io.Serializable;
import java.sql.Date;

public class RoleVo implements Serializable {

	private static final long serialVersionUID = -8403753397412273249L;

	private Long id;
	private String name;
	private Date recDate;
	private Date modDate;
	private String recUser;
	private String modUser;

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
		return "RoleVO [id=" + id + ", Name=" + name + "]";
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
