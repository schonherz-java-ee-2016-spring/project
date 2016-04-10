package hu.schonherz.training.vo;

import java.io.Serializable;

public class RoleVo implements Serializable {

	private static final long serialVersionUID = -8403753397412273249L;

	private Long id;
	private String Name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public String toString() {
		return "RoleVO [id=" + id + ", Name=" + Name + "]";
	}

}
