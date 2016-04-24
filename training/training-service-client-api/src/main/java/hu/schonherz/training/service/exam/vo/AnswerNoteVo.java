package hu.schonherz.training.service.exam.vo;

public class AnswerNoteVo extends BaseIdentityVo {
	private static final long serialVersionUID = 1L;
	private AnswerVo answer;
	private String note;

	public AnswerNoteVo() {
		super();
	}

	public AnswerVo getAnswer() {
		return answer;
	}

	public void setAnswer(AnswerVo answer) {
		this.answer = answer;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
