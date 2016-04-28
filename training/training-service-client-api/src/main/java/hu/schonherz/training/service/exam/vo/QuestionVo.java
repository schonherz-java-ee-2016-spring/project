package hu.schonherz.training.service.exam.vo;

import java.util.List;

public class QuestionVo extends BaseIdentityVo {
	private static final long serialVersionUID = 1L;

	private String text;
	private String note;
	private QuestionTypeVo questionType;
	private List<OptionVo> options;

	public QuestionVo() {
		super();
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

	public QuestionTypeVo getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionTypeVo questionType) {
		this.questionType = questionType;
	}

	public List<OptionVo> getOptions() {
		return options;
	}

	public void setOptions(List<OptionVo> options) {
		this.options = options;
	}

}
