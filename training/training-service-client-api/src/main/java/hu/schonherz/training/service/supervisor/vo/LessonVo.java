package hu.schonherz.training.service.supervisor.vo;

import java.io.Serializable;

public class LessonVo implements Serializable {

	private Long id;
	private Long userGroupId;
	private String lessonName;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5305570925310516393L;

	public LessonVo() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Long userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

}
