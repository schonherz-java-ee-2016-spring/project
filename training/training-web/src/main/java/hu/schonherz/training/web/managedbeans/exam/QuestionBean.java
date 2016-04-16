package hu.schonherz.training.web.managedbeans.exam;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import hu.schonherz.training.exam.service.ExamService;
import hu.schonherz.training.exam.service.QuestionService;
import hu.schonherz.training.exam.vo.QuestionVo;

@ManagedBean(name = "questionBean")
@SessionScoped
public class QuestionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private QuestionService questionService;
	@EJB
	private ExamService examService;

	private String newQuestionExamID;
	private String newQuestionText;
	private String examIdAsString;

	public String goToCreateQuestionPage() {
		return "createQuestion";
	}

	public List<QuestionVo> getQuestionList() {
		Long examId = Long.parseLong(examIdAsString);
		List<QuestionVo> questionVoList = null;
		try {
			questionVoList = examService.getExamById(examId).getQuestionList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return questionVoList;
	}

	public void registerNewQuestion() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();

		if (newQuestionText.isEmpty()) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Text field is empty!");
			currentInstance.addMessage(null, facesMessage);
			return;
		}

		QuestionVo questionVo = new QuestionVo();
		questionVo.setText(newQuestionText);

		// +Exam

		// +Questiontype

		try {
			getQuestionService().createQuestion(questionVo);

			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!",
					"\"" + newQuestionText + "\" exam created!");
			currentInstance.addMessage(null, facesMessage);
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Couldn't create exam: \"" + newQuestionText + "\"");
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().update("questionTable");
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public String getNewQuestionText() {
		return newQuestionText;
	}

	public void setNewQuestionText(String newQuestionText) {
		this.newQuestionText = newQuestionText;
	}

	public String getExamIdAsString() {
		return examIdAsString;
	}

	public void setExamIdAsString(String examIdAsString) {
		this.examIdAsString = examIdAsString;
	}

	public String getNewQuestionExamID() {
		return newQuestionExamID;
	}

	public void setNewQuestionExamID(String newQuestionExamID) {
		this.newQuestionExamID = newQuestionExamID;
	}

}
