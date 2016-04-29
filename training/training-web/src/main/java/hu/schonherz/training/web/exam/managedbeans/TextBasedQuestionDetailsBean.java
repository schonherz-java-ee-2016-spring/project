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

	private String questionText;

	private String questionNoteText;

	public String getQuestionIdAsString() {
		return questionIdAsString;
	}

	public void setQuestionIdAsString(String questionIdAsString) {
		this.questionIdAsString = questionIdAsString;
	}

	public String getQuestionText() {
		QuestionVo questionVo;
		try {
			questionVo = getQuestionService().getById(Long.parseLong(questionIdAsString));
			questionText = questionVo.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionText;
	}

	public void setQuestionText(String questionTitleInputText) {
		this.questionText = questionTitleInputText;
		QuestionVo questionVo;
		try {
			questionVo = getQuestionService().getById(Long.parseLong(questionIdAsString));

			if (questionTitleInputText.length() < 1)
				questionVo.setText("You can't leave the question's text unfilled");
			else
				questionVo.setText(questionTitleInputText);
			getQuestionService().updateText(questionVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getQuestionNoteText() {
		QuestionVo questionVo;
		try {
			questionVo = getQuestionService().getById(Long.parseLong(questionIdAsString));
			questionNoteText = questionVo.getNote();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionNoteText;
	}

	public void setQuestionNoteText(String questionNoteText) {
		this.questionNoteText = questionNoteText;
		QuestionVo questionVo;
		try {
			questionVo = getQuestionService().getById(Long.parseLong(questionIdAsString));
			if (questionNoteText.length() < 1)
				questionVo.setNote("You can't leave the question's note unfilled");
			else
				questionVo.setNote(questionNoteText);
			getQuestionService().updateNote(questionVo);
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
