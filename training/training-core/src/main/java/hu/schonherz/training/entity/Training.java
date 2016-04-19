package hu.schonherz.training.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "Training")
public class Training extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private Date beginning;
	private Date end;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBeginning() {
		return beginning;
	}
	public void setBeginning(Date beginning) {
		this.beginning = beginning;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "Training [name=" + name + ", beginning=" + beginning + ", end=" + end + "]";
	}
}
