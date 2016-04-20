package hu.schonherz.training.core.admin.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "Theme")
public class Theme extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private String name;
	private String type;
	//Ez "köti" majd össze a fő témát és az altémákat a hierarchia miatt.
	private String themeCode;
	@Lob
	private String description;
	private int hours;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	@Override
	public String toString() {
		return "Theme [name=" + name + ", type=" + type + ", description=" + description + ", hours=" + hours + "]";
	}
	public String getThemeCode() {
		return themeCode;
	}
	public void setThemeCode(String themeCode) {
		this.themeCode = themeCode;
	}
}