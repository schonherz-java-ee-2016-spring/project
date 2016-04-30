package hu.schonherz.training.service.exam.vo;

import hu.schonherz.training.service.admin.vo.UserVo;

public class AnswerVo extends BaseIdentityVo {
	private static final long serialVersionUID = 1L;
	private UserVo user;
	private OptionVo option;
	private Boolean good;

	public AnswerVo() {
		super();
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public OptionVo getOption() {
		return option;
	}

	public void setOption(OptionVo option) {
		this.option = option;
	}

	public Boolean getGood() {
		return good;
	}

	public void setGood(Boolean good) {
		this.good = good;
	}


}
