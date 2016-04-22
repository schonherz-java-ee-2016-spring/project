package hu.schonherz.training.service.exam.vo;

public class OptionVo extends BaseIdentityVo {

	private static final long serialVersionUID = 1L;
	private Boolean correct;
	private String optionText;

	public OptionVo() {
		super();
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
