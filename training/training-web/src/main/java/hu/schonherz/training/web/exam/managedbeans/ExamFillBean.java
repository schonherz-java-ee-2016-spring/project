package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.AnswerService;
import hu.schonherz.training.service.exam.AnswerTextService;
import hu.schonherz.training.service.exam.ExamService;
import hu.schonherz.training.service.exam.OptionService;
import hu.schonherz.training.service.exam.QuestionService;
import hu.schonherz.training.service.exam.QuestionTypeService;
import hu.schonherz.training.service.exam.vo.AnswerTextVo;
import hu.schonherz.training.service.exam.vo.AnswerVo;
import hu.schonherz.training.service.exam.vo.OptionVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

//godbean already
@ManagedBean(name = "examFillBean")
@SessionScoped
public class ExamFillBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{out}")
	private ResourceBundle bundle;
	
	@EJB
	private ExamService examService;
	@EJB
	private QuestionService questionService;
	@EJB
	private QuestionTypeService questionTypeService;
	@EJB
	private OptionService optionService;
	@EJB
	private AnswerService answerService;
	@EJB
	private UserService userService;
	@EJB
	private AnswerTextService answerTextService;

	private List<QuestionVo> questionList;
	private String examIdAsString;
	private String questionIdAsString;
	private int counter;
	private String textbasedOptionAnswer;

	private List<QuestionVo> localQuestionList;

	private List<OptionVo> optionList;
	private List<OptionVo> selectedOptionList;
	private OptionVo selectedOption;

	public void toTheNextQuestionMulti() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();

		if ((questionList.size() - 1) > getCounter()) {
			setCounter(getCounter() + 1);
			setQuestionIdAsString(String.valueOf(getLocalQuestionList().get(getCounter()).getId()));
			toTheNextQuestionMultiSave();
		} else {
			toTheNextQuestionMultiSave();
			finishedExam(currentInstance);
		}
	}

	public void toTheNextQuestionSingle() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();

		if ((questionList.size() - 1) > getCounter()) {
			setCounter(getCounter() + 1);
			setQuestionIdAsString(String.valueOf(getLocalQuestionList().get(getCounter()).getId()));
			toTheNextQuestionSingleSave(currentInstance);
		} else {
			toTheNextQuestionSingleSave(currentInstance);
			finishedExam(currentInstance);
		}
	}

	public void toTheNextQuestionText() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();

		if ((questionList.size() - 1) > getCounter()) {
			setCounter(getCounter() + 1);
			setQuestionIdAsString(String.valueOf(getLocalQuestionList().get(getCounter()).getId()));
			toTheNextQuestionTextSave();
		} else {
			toTheNextQuestionTextSave();
			finishedExam(currentInstance);
		}
		textbasedOptionAnswer="";
	}

	private void toTheNextQuestionTextSave() {

		AnswerVo answerVo = new AnswerVo();
		answerVo.setOption(optionList.get(0));
		AnswerTextVo answerTextVo = new AnswerTextVo();

		answerTextVo.setText(textbasedOptionAnswer);
		UserVo userVo;
		try {
			userVo = userService.findUserByName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
			answerVo.setUser(userVo);
			answerTextVo.setAnswer(answerVo);
			answerTextService.add(answerTextVo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void toTheNextQuestionMultiSave() {

		for (int i = 0; i < selectedOptionList.size(); i++) {

			AnswerVo answerVo = new AnswerVo();
			answerVo.setGood(selectedOptionList.get(i).getCorrect());
			answerVo.setOption(optionList.get(i));
			UserVo userVo;
			try {
				userVo = userService
						.findUserByName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
				answerVo.setUser(userVo);
				answerService.add(answerVo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void toTheNextQuestionSingleSave(FacesContext currentInstance) {
		if (selectedOption == null) {
			System.out.println("something went wrong");
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"), bundle.getString("markcorrectoption"));
			currentInstance.addMessage(null, facesMessage);
			counter--;
			setQuestionIdAsString(String.valueOf(getQuestionList().get(getCounter()).getId()));
		} else {

			AnswerVo answerVo = new AnswerVo();
			answerVo.setGood(selectedOption.getCorrect());
			answerVo.setOption(selectedOption);
			UserVo userVo;
			try {
				userVo = userService
						.findUserByName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
				answerVo.setUser(userVo);
				answerService.add(answerVo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void finishedExam(FacesContext currentInstance) {
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
		.handleNavigation(FacesContext.getCurrentInstance(), null, "examChoose.xhtml?faces-redirect=true");
	}

	public List<QuestionVo> getQuestionList() {
		try {
			Long examId = Long.parseLong(examIdAsString);
			questionList = questionService.getAllByExamId(examId);
			localQuestionList = questionList;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return questionList;
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

	public void setQuestionList(List<QuestionVo> questionList) {
		this.questionList = questionList;
	}

	public String getExamIdAsString() {
		return examIdAsString;
	}

	public void setExamIdAsString(String examIdAsString) {
		this.examIdAsString = examIdAsString;
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

	public AnswerService getAnswerService() {
		return answerService;
	}

	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}

	public OptionVo getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(OptionVo selectedOption) {
		this.selectedOption = selectedOption;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<QuestionVo> getLocalQuestionList() {
		return localQuestionList;
	}

	public void setLocalQuestionList(List<QuestionVo> localQuestionList) {
		this.localQuestionList = localQuestionList;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public AnswerTextService getAnswerTextService() {
		return answerTextService;
	}

	public void setAnswerTextService(AnswerTextService answerTextService) {
		this.answerTextService = answerTextService;
	}
	
	

}
