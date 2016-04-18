package hu.schonherz.training.web.managedbeans.exam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import hu.schonherz.training.exam.service.ExamService;
import hu.schonherz.training.exam.service.OptionService;
import hu.schonherz.training.exam.service.QuestionService;
import hu.schonherz.training.exam.service.QuestionTypeService;
import hu.schonherz.training.exam.vo.OptionVo;
import hu.schonherz.training.exam.vo.QuestionVo;

@ManagedBean(name = "singleQuestionBean")
@SessionScoped
public class SingleQuestionBean implements Serializable {
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
	private String newOptionText;
	private boolean newOptionCorrect;

	@PostConstruct
	private void initBean() {
		newOptions = new ArrayList<OptionVo>();
	}

	public void handleKeyEvent() {
	}

	public void saveNewQuestion() throws Exception {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		newQuestion = new QuestionVo();
		setUpQuestionVo(newQuestion);

		try {
			boolean isOnlyOneRightOption = newOptions.stream().filter(o -> o.getCorrect()).collect(Collectors.toList())
					.size() == 1;
			if (!isOnlyOneRightOption) {
				throw new IllegalArgumentException();
			}

			questionService.create(newQuestion);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!",
					"\"" + newQuestionText + "\" question created!");
			currentInstance.addMessage(null, facesMessage);
		} catch (IllegalArgumentException ex) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Couldn't create question: \"" + newQuestionText + "\"" + "There must be only one right answer!");
			currentInstance.addMessage(null, facesMessage);
			ex.printStackTrace();
		} catch (Exception ex) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Couldn't create question: \"" + newQuestionText + "\"");
			currentInstance.addMessage(null, facesMessage);
			ex.printStackTrace();
		}
		cleanPropertites();

	}

	public void addNewOption() {
		OptionVo optionVo = new OptionVo();
		setUpOptionVo(optionVo);
		newOptions.add(optionVo);
		RequestContext.getCurrentInstance().update("optionTable");
	}

	private void cleanPropertites() {
		newQuestion = null;
		newOptions.clear();
		newQuestionText = "";
		newOptionText = "";
		newOptionCorrect = false;
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

	public boolean isNewOptionCorrect() {
		return newOptionCorrect;
	}

	public void setNewOptionCorrect(boolean newOptionCorrect) {
		this.newOptionCorrect = newOptionCorrect;
	}

	private void setUpQuestionVo(QuestionVo questionVo) throws NumberFormatException, Exception {
		questionVo.setExam(examService.findById(Long.parseLong(examIdAsString)));
		questionVo.setOptionList(newOptions);
		questionVo.setQuestionType(questionTypeService.findById(2L));
		questionVo.setText(newQuestionText);
	}

	private void setUpOptionVo(OptionVo optionVo) {
		optionVo.setQuestion(newQuestion);
		optionVo.setOptionText(newOptionText);
		optionVo.setCorrect(newOptionCorrect);
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
}
