package hu.schonherz.training.service.exam.vo.eval;

public class EvalAnswer {
	private String userName;
	private String answerText;
	private Boolean good;

	public EvalAnswer() {
		super();
	}

	public EvalAnswer(String userName, String answerText, Boolean good) {
		super();
		this.userName = userName;
		this.answerText = answerText;
		this.good = good;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public Boolean getGood() {
		return good;
	}

	public void setGood(Boolean good) {
		this.good = good;
	}
}
