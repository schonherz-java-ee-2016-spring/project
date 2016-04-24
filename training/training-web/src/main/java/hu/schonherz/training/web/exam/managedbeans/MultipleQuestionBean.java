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
	private String questionIdAsString;
	private String questionTextInput = "Question text";


	private String optionTextInput;
	private List<OptionVo> options;
	private List<OptionVo> correctOptions;

	@PostConstruct
	private void initBean() {
		setOptions(new ArrayList<OptionVo>());
	}

	private void updateView() {
		RequestContext.getCurrentInstance().update("optionTableForm");
		RequestContext.getCurrentInstance().update("questionTitleForm");
	}

	private void clearView() {
		options.clear();
		updateView();
	}

	public void saveNewQuestion() throws Exception {
		QuestionVo question = new QuestionVo();
		setUpQuestionVo(question);
		if (options.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "You must have at least one option."));
		} else {
			getCorrectOptions().forEach(o -> o.setCorrect(true));
			try {
				Long examId = Long.parseLong(examIdAsString);
				question.setOptions(options);
				questionService.add(question, examId);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Question saved."));
				clearView();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Something went wrong."));
				e.printStackTrace();
			}
		}
	}

	public void addNewOption() {
		OptionVo optionVo = new OptionVo();
		setUpOptionVo(optionVo);
		if (options.stream().map(o -> o.getOptionText()).filter(o -> o.equalsIgnoreCase(optionVo.getOptionText()))
				.count() > 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Option already exists."));
		} else {
			options.add(optionVo);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Option added."));
		}
		updateView();
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

	private void setUpQuestionVo(QuestionVo questionVo) throws NumberFormatException, Exception {
		questionVo.setOptions(options);
		questionVo.setQuestionType(questionTypeService.getById(2L));
		questionVo.setText(questionTextInput);
	}

	private void setUpOptionVo(OptionVo optionVo) {
		optionVo.setOptionText(optionTextInput);
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

	public String getQuestionTextInput() {
		QuestionVo questionVo;
		try {
			questionVo = questionService.getById(Long.parseLong(questionIdAsString));
			questionTextInput = questionVo.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionTextInput;
	}

	public void setQuestionTextInput(String questionTextInput) {
		this.questionTextInput = questionTextInput;
		QuestionVo questionVo;
		try {
			questionVo = questionService.getById(Long.parseLong(questionIdAsString));
			questionVo.setText(questionTextInput);
			questionService.updateTitle(questionVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<OptionVo> getOptions() {
		return options;
	}

	public void setOptions(List<OptionVo> options) {
		this.options = options;
	}

	public String getOptionTextInput() {
		return optionTextInput;
	}

	public void setOptionTextInput(String optionTextInput) {
		this.optionTextInput = optionTextInput;
	}

	public String getQuestionIdAsString() {
		return questionIdAsString;
	}

	public void setQuestionIdAsString(String questionIdAsString) {
		this.questionIdAsString = questionIdAsString;
	}
}
