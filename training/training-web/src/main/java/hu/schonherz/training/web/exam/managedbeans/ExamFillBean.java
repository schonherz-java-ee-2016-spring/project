package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;
import java.util.List;

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

//godbean inc
@ManagedBean(name = "examFillBean")
@SessionScoped
public class ExamFillBean implements Serializable {

	private static final long serialVersionUID = 1L;
  
	public ExamFillBean() {
		counter = 0;
		textbasedOptionAnswer = "ur answer goes here";
	}
	
	@EJB
	private ExamService examService;
	@EJB
	private QuestionService questionService;
	@EJB
	private QuestionTypeService questionTypeService;
	@EJB
	private OptionService optionService;

	private List<QuestionVo> questionList;
	private String examIdAsString;
	private String firstQuestionIdAsString;
	private String questionIdAsString;
	private int counter;
	private String textbasedOptionAnswer;

	private List<OptionVo> optionList;
	private List<OptionVo> selectedOptionList;
	public void toTheNextQuestion() {
		// Activeindex ++ This way next tab inc
		setCounter(getCounter() + 1);
		
		// Set the question to get the correct options for it
		setQuestionIdAsString(String.valueOf(getQuestionList().get(getCounter()).getId()));

		// Answer persist will be here too
		saveAnswer();
	}

	public void saveAnswer() {
		
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		try {
			// idejön a save
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "FATALEERROR CONTACT IVÁNYIGÁBOR!", "Couldn't presist your answer");
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
		}

	}

	public void syso() { // Always use debugger option instead of syso!!
		System.out.println(" firstQuestionIdAsString: " + firstQuestionIdAsString);
		System.out.println("Questionlist elsokerdes: " + getQuestionList().get(0).getText());
		System.out.println("Qtype: " + getQuestionList().get(0).getQuestionType().getId());
		System.out.println("Examid: " + examIdAsString);
		System.out.println("optionList.get(0).getOptionText() :" + getOptionList());
		System.out.println("optionList.size() :" + getOptionList().size());
		System.out.println("Counter: " + getCounter());
	}

	public List<QuestionVo> getQuestionList() {
		try {
			Long examId = Long.parseLong(examIdAsString);
			questionList = questionService.getAllById(examId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return questionList;
	}

	public void setQuestionList(List<QuestionVo> questionList) {
		this.questionList = questionList;
	}

	public String getExamIdAsString() {
		return examIdAsString;
	}

	public void setExamIdAsString(String examIdAsString) {
		this.examIdAsString = examIdAsString;
	}

	public String getFirstQuestionIdAsString() {
		return firstQuestionIdAsString;
	}

	public void setFirstQuestionIdAsString(String firstQuestionIdAsString) {
		this.firstQuestionIdAsString = firstQuestionIdAsString;
	}

	public ExamService getExamService() {
		return examService;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	public QuestionTypeService getQuestionTypeService() {
		return questionTypeService;
	}

	public void setQuestionTypeService(QuestionTypeService questionTypeService) {
		this.questionTypeService = questionTypeService;
	}

	public OptionService getOptionService() {
		return optionService;
	}

	public void setOptionService(OptionService optionService) {
		this.optionService = optionService;
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public List<OptionVo> getOptionList() {
		Long id = Long.parseLong(questionIdAsString);
		try {
			optionList = questionService.getById(id).getOptions();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return optionList;
	}

	public String getQuestionIdAsString() {
		return questionIdAsString;
	}

	public void setQuestionIdAsString(String questionIdAsString) {
		this.questionIdAsString = questionIdAsString;
	}

	public void setOptionList(List<OptionVo> optionList) {
		this.optionList = optionList;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void resetCounter() { // for testing purpose only
		setCounter(0);
	}

	public String getTextbasedOptionAnswer() {
		return textbasedOptionAnswer;
	}

	public void setTextbasedOptionAnswer(String textbasedOptionAnswer) {
		this.textbasedOptionAnswer = textbasedOptionAnswer;
	}

	public List<OptionVo> getSelectedOptionList() {
		return selectedOptionList;
	}

	public void setSelectedOptionList(List<OptionVo> selectedOptionList) {
		this.selectedOptionList = selectedOptionList;
	}
	

}
