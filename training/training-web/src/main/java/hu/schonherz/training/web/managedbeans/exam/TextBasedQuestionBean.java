package hu.schonherz.training.web.managedbeans.exam;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import hu.schonherz.training.exam.service.ExamService;
import hu.schonherz.training.exam.service.OptionService;
import hu.schonherz.training.exam.service.QuestionService;
import hu.schonherz.training.exam.service.QuestionTypeService;
import hu.schonherz.training.exam.vo.OptionVo;
import hu.schonherz.training.exam.vo.QuestionVo;

@ManagedBean(name = "textBasedQuestionBean")
@SessionScoped
public class TextBasedQuestionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String newQuestionText = "";
	private String examIdAsString;

	@PostConstruct
	public void init() {
		newQuestionText = "";
	}

	@EJB
	private QuestionService questionService;

	@EJB
	private OptionService optionService;

	@EJB
	private ExamService examService;

	@EJB
	private QuestionTypeService questionTypeService;

	public void createTextBasedQuestion() throws Exception {
		FacesContext currentInstance = FacesContext.getCurrentInstance();

		QuestionVo newQuestion = new QuestionVo();

		Long examId = Long.parseLong(examIdAsString);
		newQuestion.setExam(examService.findById(examId));
		newQuestion.setText(newQuestionText);

		newQuestion.setQuestionType(questionTypeService.findById(3L));

		OptionVo newOption = new OptionVo();
		newOption.setQuestion(newQuestion);

		try {
			getOptionService().create(newOption);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!",
					"\"" + newQuestionText + "\" Text based question created!");
			currentInstance.addMessage(null, facesMessage);
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Couldn't create textbased question: \"" + newQuestionText + "\"");
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
