package hu.schonherz.training.exam.vo;

import hu.schonherz.training.vo.UserVo;

public class AnswerVo extends BaseIdentityVo {
	private static final long serialVersionUID = 844532347627686325L;

	private UserVo user;
	private OptionVo option;
	private Boolean good;
	private AnswerNoteVo answerNote;
	private AnswerTextVo answerText;

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

	public Boolean isGood() {
		return good;
	}

	public void setGood(Boolean good) {
		this.good = good;
	}

	public AnswerNoteVo getAnswerNote() {
		return answerNote;
	}

	public void setAnswerNote(AnswerNoteVo answerNote) {
		this.answerNote = answerNote;
	}

	public AnswerTextVo getAnswerText() {
		return answerText;
	}

	public void setAnswerText(AnswerTextVo answerText) {
		this.answerText = answerText;
	}

}
