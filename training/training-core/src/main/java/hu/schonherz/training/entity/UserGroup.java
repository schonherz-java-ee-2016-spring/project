package hu.schonherz.training.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "UserGroup")
public class UserGroup extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	
}
