package hu.schonherz.training.exam.vo;

import java.io.Serializable;

public class OptionVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private QuestionVo question;
	private Boolean correct;
	private String optionText;

	public OptionVo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
