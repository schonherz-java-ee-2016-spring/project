package hu.schonherz.training.exam.vo;

import java.io.Serializable;
import java.util.List;

public class ExamVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	private List<QuestionVo> questionList;

	public ExamVo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<QuestionVo> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<QuestionVo> questionList) {
		this.questionList = questionList;
	}

}
