package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import hu.schonherz.training.service.exam.ExamService;
import hu.schonherz.training.service.exam.OptionService;
import hu.schonherz.training.service.exam.QuestionService;
import hu.schonherz.training.service.exam.QuestionTypeService;
import hu.schonherz.training.service.exam.vo.OptionVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@ManagedBean(name = "multipleQuestionBean")
@SessionScoped
public class MultipleQuestionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ExamService examService;
	@EJB
	private QuestionService questionService;
	@EJB
	private QuestionTypeService questionTypeService;
	@EJB
	private OptionService optionService;

	private String examIdAsString;

	private QuestionVo newQuestion;
	private String newQuestionText;

	private List<OptionVo> newOptions;
	private List<OptionVo> correctOptions;
	private String newOptionText;

	@PostConstruct
	private void initBean() {
		newOptions = new ArrayList<OptionVo>();
	}

	public void saveNewQuestion() throws Exception {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		newQuestion = new QuestionVo();
		setUpQuestionVo(newQuestion);
		if (newOptions.isEmpty()) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "At least option");
			currentInstance.addMessage(null, facesMessage);
		} else {
			getCorrectOptions().forEach(o -> o.setCorrect(true));
			try {
				Long examId = Long.parseLong(examIdAsString);
				newQuestion.setOptions(newOptions);
				questionService.add(newQuestion, examId);
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!","");
				currentInstance.addMessage(null, facesMessage);
				updateView();
			} catch (Exception e) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!","");
				currentInstance.addMessage(null, facesMessage);
				e.printStackTrace();
			}
		}
	}
	
	public void updateView() {
		newOptions.clear();
		newQuestionText = "";
		RequestContext.getCurrentInstance().update("optionTableForm");
		RequestContext.getCurrentInstance().update("questionTitleForm");
	}

	public void addNewOption() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		OptionVo optionVo = new OptionVo();
		setUpOptionVo(optionVo);
		if (newOptions.stream().map(o -> o.getOptionText()).filter(o -> o.equalsIgnoreCase(optionVo.getOptionText()))
				.count() > 0) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Option is exist");
			currentInstance.addMessage(null, facesMessage);
		} else {
			newOptions.add(optionVo);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "");
			currentInstance.addMessage(null, facesMessage);
		}
		
		RequestContext.getCurrentInstance().update("optionTableForm");
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

	public String getExamIdAsString() {
		return examIdAsString;
	}

	public void setExamIdAsString(String examIdAsString) {
		this.examIdAsString = examIdAsString;
	}

	public QuestionVo getNewQuestion() {
		return newQuestion;
	}

	public void setNewQuestion(QuestionVo newQuestion) {
		this.newQuestion = newQuestion;
	}

	public String getNewQuestionText() {
		return newQuestionText;
	}

	public void setNewQuestionText(String newQuestionText) {
		this.newQuestionText = newQuestionText;
	}

	public List<OptionVo> getNewOptions() {
		return newOptions;
	}

	public void setNewOptions(List<OptionVo> newOptions) {
		this.newOptions = newOptions;
	}

	public String getNewOptionText() {
		return newOptionText;
	}

	public void setNewOptionText(String newOptionText) {
		this.newOptionText = newOptionText;
	}

	private void setUpQuestionVo(QuestionVo questionVo) throws NumberFormatException, Exception {
		questionVo.setOptions(newOptions);
		questionVo.setQuestionType(questionTypeService.getById(2L));
		questionVo.setText(newQuestionText);
	}

	private void setUpOptionVo(OptionVo optionVo) {
		optionVo.setOptionText(newOptionText);
	}

	public QuestionTypeService getQuestionTypeService() {
		return questionTypeService;
	}

	public void setQuestionTypeService(QuestionTypeService questionTypeService) {
		this.questionTypeService = questionTypeService;
	}

	public ExamService getExamService() {
		return examService;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	public List<OptionVo> getCorrectOptions() {
		return correctOptions;
	}

	public void setCorrectOptions(List<OptionVo> correctOptions) {
		this.correctOptions = correctOptions;
	}
}
