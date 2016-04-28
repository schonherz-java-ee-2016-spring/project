package hu.schonherz.training.service.exam.vo;

import java.io.Serializable;

import hu.schonherz.training.service.admin.vo.UserVo;

public class ExamUserRelationVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private ExamVo examVo;
	private UserVo userVo;

	public ExamUserRelationVo() {
		super();
	}

	/**
	 * @return the examVo
	 */
	public ExamVo getExamVo() {
		return examVo;
	}

	/**
	 * @param examVo
	 *            the examVo to set
	 */
	public void setExamVo(ExamVo examVo) {
		this.examVo = examVo;
	}

	/**
	 * @return the userVo
	 */
	public UserVo getUserVo() {
		return userVo;
	}

	/**
	 * @param userVo
	 *            the userVo to set
	 */
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

}
