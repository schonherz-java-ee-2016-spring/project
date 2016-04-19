package hu.schonherz.training.service.exam.vo;

public class OptionVo extends BaseIdentityVo {

	private static final long serialVersionUID = 1L;
	private QuestionVo question;
	private Boolean correct;
	private String optionText;

	public OptionVo() {
		super();
	}

	public QuestionVo getQuestion() {
		return question;
	}

	public void setQuestion(QuestionVo question) {
		this.question = question;
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	public String getOptionText() {
		return optionText;
	}

	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

}
