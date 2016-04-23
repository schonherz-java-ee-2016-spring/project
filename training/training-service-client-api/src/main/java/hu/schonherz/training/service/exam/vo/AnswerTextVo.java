package hu.schonherz.training.service.exam.vo;

public class AnswerTextVo extends BaseIdentityVo {
	private static final long serialVersionUID = -6407762391623541803L;

	private AnswerVo answer;
	private String text;

	public AnswerTextVo() {
		super();
	}

	public AnswerVo getAnswer() {
		return answer;
	}

	public void setAnswer(AnswerVo answer) {
		this.answer = answer;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
