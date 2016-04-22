package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import hu.schonherz.training.service.exam.ExamService;
import hu.schonherz.training.service.exam.OptionService;
import hu.schonherz.training.service.exam.QuestionService;
import hu.schonherz.training.service.exam.QuestionTypeService;
import hu.schonherz.training.service.exam.vo.OptionVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@ManagedBean(name = "textBasedQuestionBean")
@SessionScoped
public class TextBasedQuestionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String newQuestionText;
	private String examIdAsString;

	@EJB
	private QuestionService questionService;

	@EJB
	private OptionService optionService;

	@EJB
	private ExamService examService;

	@EJB
	private QuestionTypeService questionTypeService;

	@PostConstruct
	public void init() {
		newQuestionText = "";
	}

	public void createTextBasedQuestion() throws Exception {
		FacesContext currentInstance = FacesContext.getCurrentInstance();

		QuestionVo newQuestion = new QuestionVo();
		Long examId = Long.parseLong(examIdAsString);
		newQuestion.setText(newQuestionText);
		newQuestion.setQuestionType(questionTypeService.getById(3L));
		newQuestion.setOptions(Arrays.asList(new OptionVo()));

		try {
			questionService.add(newQuestion, examId);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "");
			currentInstance.addMessage(null, facesMessage);
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "");
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
		}

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

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public OptionService getOptionService() {
		return optionService;
	}

	public void setOptionService(OptionService optionService) {
		this.optionService = optionService;
	}

	public ExamService getExamService() {
		return examService;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

}
