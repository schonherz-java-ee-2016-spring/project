package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;
import java.util.List;

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

@ManagedBean(name = "singleQuestionDetailsBean")
@SessionScoped
public class SingleQuestionDetailsBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String examIdAsString;
	private String questionIdAsString;
	private Boolean initLoading = true;

	private List<OptionVo> optionList;
	private OptionVo correctOption;
	private String newOptionText;

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
		if (correctOption == null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "One answer!!!!");
			currentInstance.addMessage(null, facesMessage);
		} else {
			try {
				Long examId = Long.parseLong(examIdAsString);
				Long questionId = Long.parseLong(questionIdAsString);

				QuestionVo newQuestion = questionService.getById(questionId);
				setUpQuestionVo(newQuestion);
				questionService.remove(questionId);

				questionService.add(newQuestion, examId);
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

	public void updatePageContent() {
		RequestContext.getCurrentInstance().update("optionTableForm");
		RequestContext.getCurrentInstance().update("questionTitleForm");
	}

	public void addNewOption() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		OptionVo optionVo = new OptionVo();
		setUpOptionVo(optionVo);
		if (optionList.stream().filter(o -> o.getOptionText().equalsIgnoreCase(optionVo.getOptionText())).count() > 0) {
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
		optionVo.setOptionText(newOptionText);
	}

	private void setUpQuestionVo(QuestionVo newQuestion) {
		optionList.stream().forEach(o -> {
			if (o.getOptionText().equalsIgnoreCase(correctOption.getOptionText())) {
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

	public OptionVo getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(OptionVo correctOption) {
		this.correctOption = correctOption;
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
				correctOption = optionList.stream().filter(o -> o.getCorrect()).findFirst().get();
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
}
