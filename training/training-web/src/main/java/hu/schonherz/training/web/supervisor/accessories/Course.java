package hu.schonherz.training.web.supervisor.accessories;

import java.io.Serializable;
import java.util.List;

import hu.schonherz.training.service.admin.vo.UserGroupVo;
import hu.schonherz.training.service.supervisor.vo.LessonsVo;

public class Course implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserGroupVo userGroup;

	private List<UserResults> userResults;

	private List<LessonsVo> lessons;

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

	public List<LessonsVo> getLessons() {
		return lessons;
	}

	public void setLessons(List<LessonsVo> lessons) {
		this.lessons = lessons;
	}

}
