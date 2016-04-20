package hu.schonherz.training.service.exam.vo;

import java.util.List;

public class QuestionTypeVo extends BaseIdentityVo {
	private static final long serialVersionUID = 9122075670750880034L;

	private List<QuestionVo> questionList;
	private String name;

	public QuestionTypeVo() {
		super();
	}

	public List<QuestionVo> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<QuestionVo> questionList) {
		this.questionList = questionList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
