package hu.schonherz.training.service.exam.vo;

public class OptionVo extends BaseIdentityVo {

	private static final long serialVersionUID = 1L;
	private Boolean correct;
	private String text;
	private String note;

	public OptionVo() {
		super();
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
