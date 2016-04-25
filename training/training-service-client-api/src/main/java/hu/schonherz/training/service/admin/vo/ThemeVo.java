package hu.schonherz.training.service.admin.vo;

import java.io.Serializable;

public class ThemeVo implements Serializable {

	private static final long serialVersionUID = -8205543465661230351L;

	private String name;
	private String type;
	private String themeCode;
	private String description;
	private int hours;
	
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
	public int getHours() {
		return hours;
	}

	/**
	 * @param hours
	 *            the hours to set
	 */
	public void setHours(int hours) {
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
}
