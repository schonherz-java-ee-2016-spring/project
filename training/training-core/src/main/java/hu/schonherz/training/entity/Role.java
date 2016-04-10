package hu.schonherz.training.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
public class Role extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
