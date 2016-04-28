package hu.schonherz.training.web.exam.managedbeans;

import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import hu.schonherz.training.service.exam.vo.OptionVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@ManagedBean(name = "singleQuestionDetailsBean")
@SessionScoped
public class SingleQuestionDetailsBean extends SelectorQuestionBean {
	private static final long serialVersionUID = 1L;

	private String questionIdAsString;
	private OptionVo correctOption;
	private Boolean initLoading = true;

	@Override
	protected void updatePageContent() {
		optionList.clear();
		questionText = "";
		RequestContext.getCurrentInstance().update("optionTableForm");
		RequestContext.getCurrentInstance().update("questionTitleForm");
		RequestContext.getCurrentInstance().update("questionNoteForm");
	}

	@Override
	protected void setUpOption() {
		option = new OptionVo();
		option.setText(optionText);
	}

	@Override
	protected void setUpQuestion() throws Exception {
		question.setId(null);
		optionList.stream().forEach(o -> {
			if (o.getText().equalsIgnoreCase(correctOption.getText())) {
				o.setCorrect(true);
			} else {
				o.setCorrect(false);
			}
			o.setId(null);
		});
		question.setOptions(optionList);
	}

	@Override
	public void addOption() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		setUpOption();
		if (optionList.stream().filter(o -> o.getText().equalsIgnoreCase(option.getText())).count() > 0) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Option is exists");
			currentInstance.addMessage(null, facesMessage);
		} else {
			optionList.add(option);
			optionText = "";
			RequestContext.getCurrentInstance().update("optionPanelGrid");
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "");
			currentInstance.addMessage(null, facesMessage);
		}
		RequestContext.getCurrentInstance().update("optionTableForm");
	}

	@Override
	public void removeOption(ActionEvent event) {
		String optionName = event.getComponent().getAttributes().get("optionName").toString();
		optionList = optionList.stream().filter(o -> !o.getText().equalsIgnoreCase(optionName))
				.collect(Collectors.toList());
	}

	@Override
	public void saveQuestion() throws Exception {
		Long examId = Long.parseLong(examIdAsString);
		Long questionId = Long.parseLong(questionIdAsString);

		question = questionService.getById(questionId);
		setUpQuestion();

		questionService.removeById(questionId);
		questionService.save(question, examId);
	}

	@Override
	public void tryToSaveQuestion() throws Exception {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		if (correctOption == null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Mark the correct option");
			currentInstance.addMessage(null, facesMessage);
		} else {
			try {
				saveQuestion();
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

	@Override
	public List<OptionVo> getOptionList() {
		if (initLoading == true) {
			updateOptionList();
		}
		return optionList;
	}

	@Override
	public String getQuestionText() {
		updateQuestionText();
		return questionText;
	}

	@Override
	public void setQuestionText(String questionText) {
		updateSetQuestionText(questionText);
		this.questionText = questionText;
	}

	public void updateSetQuestionText(String questionText) {
		Long questionId = Long.parseLong(questionIdAsString);
		try {
			QuestionVo questionVo = questionService.getById(questionId);
			if (questionText.length() < 1)
				questionVo.setText("You can't leave the question's text unfilled");
			else
				questionVo.setText(questionText);
			questionService.updateText(questionVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateQuestionText() {
		Long questionId = Long.parseLong(questionIdAsString);
		try {
			QuestionVo question = questionService.getById(questionId);
			questionText = question.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setQuestionNoteText(String questionNoteText) {
		Long questionId = Long.parseLong(questionIdAsString);
		try {
			QuestionVo questionVo = questionService.getById(questionId);

			if (questionNoteText.length() < 1)
				questionVo.setNote("You can't leave the question's note unfilled");
			else
				questionVo.setNote(questionNoteText);

			questionService.updateNote(questionVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getQuestionNoteText() {
		Long questionId = Long.parseLong(questionIdAsString);
		try {
			QuestionVo question = questionService.getById(questionId);
			questionText = question.getNote();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionText;

	}

	public void updateOptionList() {
		Long id = Long.parseLong(questionIdAsString);
		try {
			optionList = questionService.getById(id).getOptions();
			correctOption = optionList.stream().filter(o -> o.getCorrect()).findFirst().orElse(null);
			initLoading = false;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String getQuestionIdAsString() {
		return questionIdAsString;
	}

	public void setQuestionIdAsString(String questionIdAsString) {
		this.questionIdAsString = questionIdAsString;
	}

	public OptionVo getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(OptionVo correctOption) {
		this.correctOption = correctOption;
	}

	public Boolean getInitLoading() {
		return initLoading;
	}

	public void setInitLoading(Boolean initLoading) {
		this.initLoading = initLoading;
	}

}
