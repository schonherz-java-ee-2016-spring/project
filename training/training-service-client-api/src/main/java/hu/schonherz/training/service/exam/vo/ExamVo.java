package hu.schonherz.training.service.exam.vo;

import java.util.List;

public class ExamVo extends BaseIdentityVo {
	private static final long serialVersionUID = -3311497706032167761L;

	private String title;
	private List<QuestionVo> questions;

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

}
