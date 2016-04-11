package hu.schonherz.training.vo;

import java.io.Serializable;

public class UserGroupVo implements Serializable {

	private static final long serialVersionUID = -8403753397412273249L;

	private Long id;
	private String groupName;

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
		return "RoleVO [id=" + id + ", Name=" + groupName + "]";
	}

}
