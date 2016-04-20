package hu.schonherz.training.service.exam.vo;

import java.util.List;

public class QuestionVo extends BaseIdentityVo {
	private static final long serialVersionUID = -3071254276606991707L;

	private ExamVo exam;
	private String text;
	private QuestionTypeVo questionType;
	private List<OptionVo> optionList;

	public QuestionVo() {
		super();
	}

	public ExamVo getExam() {
		return exam;
	}

	public void setExam(ExamVo exam) {
		this.exam = exam;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public QuestionTypeVo getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionTypeVo questionType) {
		this.questionType = questionType;
	}

	public List<OptionVo> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<OptionVo> optionList) {
		this.optionList = optionList;
	}

}
