package hu.schonherz.training.service.exam.vo;

import java.util.List;

public class ExamVo extends BaseIdentityVo {

	private static final long serialVersionUID = 1L;
	private String title;
	private List<QuestionVo> questions;
	private Boolean status;

	public ExamVo() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<QuestionVo> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionVo> questions) {
		this.questions = questions;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	
}
