package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

import hu.schonherz.training.service.exam.ExamService;
import hu.schonherz.training.service.exam.OptionService;
import hu.schonherz.training.service.exam.QuestionService;
import hu.schonherz.training.service.exam.QuestionTypeService;
import hu.schonherz.training.service.exam.vo.OptionVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

public abstract class SelectorQuestionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	protected String examIdAsString;
	protected String questionText;
	protected String optionText;
	protected QuestionVo question;
	protected OptionVo option;
	protected List<OptionVo> optionList;

	@EJB
	protected ExamService examService;
	@EJB
	protected QuestionService questionService;
	@EJB
	protected QuestionTypeService questionTypeService;
	@EJB
	protected OptionService optionService;

	protected abstract void updatePageContent();

	protected abstract void setUpOption();

	protected abstract void setUpQuestion() throws Exception;

	public abstract void addOption();

	public abstract void removeOption(ActionEvent event);

	public abstract void saveQuestion() throws Exception;

	public abstract void tryToSaveQuestion() throws Exception;

	public String getExamIdAsString() {
		return examIdAsString;
	}

	public void setExamIdAsString(String examIdAsString) {
		this.examIdAsString = examIdAsString;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getOptionText() {
		return optionText;
	}

	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

	public QuestionVo getQuestion() {
		return question;
	}

	public void setQuestion(QuestionVo question) {
		this.question = question;
	}

	public OptionVo getOption() {
		return option;
	}

	public void setOption(OptionVo option) {
		this.option = option;
	}

	public List<OptionVo> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<OptionVo> optionList) {
		this.optionList = optionList;
	}

	public ExamService getExamService() {
		return examService;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
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
}
