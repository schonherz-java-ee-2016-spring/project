package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import hu.schonherz.training.service.exam.QuestionService;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@ManagedBean(name = "textbasedQuestionDetailsBean")
@SessionScoped
public class TextBasedQuestionDetailsBean implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@ManagedProperty("#{out}")
	private ResourceBundle bundle;
	
	@EJB
	private QuestionService questionService;

	private String questionIdAsString;

	private String questionText;

	private String questionNoteText;

	public void saveQuestion() {
		setQuestionText(questionText);
	}

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
				questionVo.setText(bundle.getString("setqtext"));
			else
				questionVo.setText(questionTitleInputText);
			getQuestionService().modifyText(questionVo);
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
					FacesContext.getCurrentInstance(), null, "examQuestions.xhtml?faces-redirect=true");
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
				questionVo.setNote(bundle.getString("setqnote"));
			else
				questionVo.setNote(questionNoteText);
			getQuestionService().modifyNote(questionVo);
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

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}
	
	
}
