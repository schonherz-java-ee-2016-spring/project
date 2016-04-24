package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import hu.schonherz.training.service.exam.ExamService;
import hu.schonherz.training.service.exam.OptionService;
import hu.schonherz.training.service.exam.QuestionService;
import hu.schonherz.training.service.exam.QuestionTypeService;
import hu.schonherz.training.service.exam.vo.OptionVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@ManagedBean(name = "multipleQuestionDetailsBean")
@SessionScoped
public class MultipleQuestionDetailsBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String examIdAsString;
	private String questionIdAsString;
	private Boolean initLoading = true;

	private List<OptionVo> optionList;
	private List<OptionVo> correctOptions;
	private String newOptionText;
	
	private String questionTitleInputText;

	@EJB
	private ExamService examService;
	@EJB
	private QuestionService questionService;
	@EJB
	private QuestionTypeService questionTypeService;
	@EJB
	private OptionService optionService;

	public void saveNewQuestion() throws Exception {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		if (correctOptions == null || optionList.isEmpty()) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Min one option");
			currentInstance.addMessage(null, facesMessage);
		} else {
			try {
				save();
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "");
				currentInstance.addMessage(null, facesMessage);
				updatePageContent();
				FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
						.handleNavigation(FacesContext.getCurrentInstance(), null, "examQuestions.xhtml");

			} catch (Exception e) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "");
				currentInstance.addMessage(null, facesMessage);
				e.printStackTrace();
			}

		}
	}
	
	public void save() throws Exception {
		Long examId = Long.parseLong(examIdAsString);
		Long questionId = Long.parseLong(questionIdAsString);

		QuestionVo newQuestion = questionService.getById(questionId);
		setUpQuestionVo(newQuestion);

		questionService.remove(questionId);
		questionService.add(newQuestion, examId);
	}
	
	public void removeOption(ActionEvent event) {
		String optionName = event.getComponent().getAttributes().get("optionName").toString();
		try {
			optionList = optionList.stream().filter(o -> !o.getText().equalsIgnoreCase(optionName))
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updatePageContent() {
		RequestContext.getCurrentInstance().update("optionTableForm");
		RequestContext.getCurrentInstance().update("questionTitleForm");
	}

	public void addNewOption() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		OptionVo optionVo = new OptionVo();
		setUpOptionVo(optionVo);
		if (optionList.stream().filter(o -> o.getText().equalsIgnoreCase(optionVo.getText())).count() > 0) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Option is exists");
			currentInstance.addMessage(null, facesMessage);
		} else {
			optionList.add(optionVo);
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

	public String getNewOptionText() {
		return newOptionText;
	}

	public void setNewOptionText(String newOptionText) {
		this.newOptionText = newOptionText;
	}

	private void setUpOptionVo(OptionVo optionVo) {
		optionVo.setText(newOptionText);
	}

	private void setUpQuestionVo(QuestionVo newQuestion) {
		optionList.stream().forEach(o -> {
			if (correctOptions.contains(o)) {
				o.setCorrect(true);
			} else {
				o.setCorrect(false);
			}
			o.setId(null);
		});

		newQuestion.setId(null);
		newQuestion.setOptions(optionList);
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

	public String getQuestionIdAsString() {
		return questionIdAsString;
	}

	public void setQuestionIdAsString(String questionIdAsString) {
		this.questionIdAsString = questionIdAsString;
	}

	public List<OptionVo> getOptionList() {
		if (initLoading == true) {
			Long id = Long.parseLong(questionIdAsString);
			try {
				optionList = questionService.getById(id).getOptions();
				correctOptions = optionList.stream().filter(o -> o.getCorrect()).collect(Collectors.toList());
				initLoading = false;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return optionList;
	}

	public void setOptionList(List<OptionVo> optionList) {
		this.optionList = optionList;
	}

	public Boolean getInitLoading() {
		return initLoading;
	}

	public void setInitLoading(Boolean initLoading) {
		this.initLoading = initLoading;
	}

	public List<OptionVo> getCorrectOptions() {
		return correctOptions;
	}

	public void setCorrectOptions(List<OptionVo> correctOptions) {
		this.correctOptions = correctOptions;
	}

	public String getQuestionTitleInputText() {
		QuestionVo questionVo;
		try {
			questionVo = questionService.getById(Long.parseLong(questionIdAsString));
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
			questionVo = questionService.getById(Long.parseLong(questionIdAsString));
			questionVo.setText(questionTitleInputText);
			questionService.updateTitle(questionVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
