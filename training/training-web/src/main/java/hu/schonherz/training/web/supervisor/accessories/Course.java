package hu.schonherz.training.web.supervisor.accessories;

import java.io.Serializable;
import java.util.List;

import hu.schonherz.training.service.admin.vo.ThemeVo;
import hu.schonherz.training.service.admin.vo.UserGroupVo;

public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserGroupVo userGroup;

	private List<UserResults> userResults;

	private List<ThemeVo> themes;

	public Course() {
		super();
	}

	public UserGroupVo getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroupVo userGroup) {
		this.userGroup = userGroup;
	}

	public List<UserResults> getUserResults() {
		return userResults;
	}

	public void setUserResults(List<UserResults> userResults) {
		this.userResults = userResults;
	}

	public List<ThemeVo> getThemes() {
		return themes;
	}

	public void setThemes(List<ThemeVo> themes) {
		this.themes = themes;
	}

}
