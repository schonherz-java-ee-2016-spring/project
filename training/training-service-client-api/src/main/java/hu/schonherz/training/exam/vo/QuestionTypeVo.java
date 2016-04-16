package hu.schonherz.training.exam.vo;

import java.io.Serializable;
import java.util.List;

public class QuestionTypeVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private List<QuestionVo> questionList;
	private String name;

	public QuestionTypeVo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<QuestionVo> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<QuestionVo> questionList) {
		this.questionList = questionList;
	}

}
