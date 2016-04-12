package hu.schonherz.training.exam.vo;

import java.io.Serializable;

public class QuestionVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String text;
	private QuestionTypeVo questionType;

	public QuestionVo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
