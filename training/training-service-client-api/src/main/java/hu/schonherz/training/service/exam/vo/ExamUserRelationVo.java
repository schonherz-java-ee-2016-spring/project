package hu.schonherz.training.service.exam.vo;

import hu.schonherz.training.service.admin.vo.UserVo;

public class ExamUserRelationVo extends BaseIdentityVo {
	private static final long serialVersionUID = 1L;

	private ExamVo exam;
	private UserVo user;

	public ExamUserRelationVo() {
		super();
	}

	public ExamVo getExam() {
		return exam;
	}

	public void setExam(ExamVo exam) {
		this.exam = exam;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

}
