package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import hu.schonherz.training.service.exam.QuestionService;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@ManagedBean(name = "textbasedQuestionDetailsBean")
@SessionScoped
public class TextBasedQuestionDetailsBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private QuestionService questionService; 

	private String questionIdAsString;

	private String questionTitleInputText;

	public String getQuestionIdAsString() {
		return questionIdAsString;
	}

	public void setQuestionIdAsString(String questionIdAsString) {
		this.questionIdAsString = questionIdAsString;
	}

	public String getQuestionTitleInputText() {
		QuestionVo questionVo;
		try {
			questionVo = getQuestionService().getById(Long.parseLong(questionIdAsString));
			questionTitleInputText = questionVo.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionTitleInputText;
	}

	public void setQuestionTitleInputText(String questionTitleInputText) {
		this.questionTitleInputText = questionTitleInputText;
		QuestionVo questionVo;
		try {
			questionVo = getQuestionService().getById(Long.parseLong(questionIdAsString));
			questionVo.setText(questionTitleInputText);
			getQuestionService().updateText(questionVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
}
