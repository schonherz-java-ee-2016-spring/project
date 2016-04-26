package hu.schonherz.training.service.admin.vo;

import java.io.Serializable;
import java.sql.Date;

public class ThemeVo implements Serializable {

	private static final long serialVersionUID = -8205543465661230351L;

	private String name;
	private String type;
	private String themeCode;
	private Date recDate;
	private Date modDate;
	private String recUser;
	private String modUser;
	private String description;
	private Integer hours;
	
	public ThemeVo(){
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the themeCode
	 */
	public String getThemeCode() {
		return themeCode;
	}

	/**
	 * @param themeCode
	 *            the themeCode to set
	 */
	public void setThemeCode(String themeCode) {
		this.themeCode = themeCode;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the hours
	 */
	public Integer getHours() {
		return hours;
	}

	/**
	 * @param hours
	 *            the hours to set
	 */
	public void setHours(Integer hours) {
		this.hours = hours;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ThemeVo [name=" + name + ", type=" + type + ", themeCode=" + themeCode + ", description=" + description
				+ ", hours=" + hours + "]";
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
