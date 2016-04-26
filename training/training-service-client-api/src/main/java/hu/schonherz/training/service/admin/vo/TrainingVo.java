package hu.schonherz.training.service.admin.vo;

import java.io.Serializable;
import java.util.Date;

public class TrainingVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Date beginning;
	private Date endDate;
	private String description;
	private Date recDate;
	private Date modDate;
	private String recUser;
	private String modUser;
	
	// private List<ThemeVo> themes;
	
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

	//
	// public Collection<Theme> getThemes() {
	// return themes;
	// }
	//
	// public void setThemes(Collection<Theme> themes) {
	// this.themes = themes;
	// }

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
