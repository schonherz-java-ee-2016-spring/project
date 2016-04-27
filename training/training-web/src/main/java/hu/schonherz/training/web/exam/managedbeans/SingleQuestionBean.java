package hu.schonherz.training.web.exam.managedbeans;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import hu.schonherz.training.service.exam.vo.OptionVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@ManagedBean(name = "singleQuestionBean")
@SessionScoped
public class SingleQuestionBean extends SelectorQuestionBean {
	private static final long serialVersionUID = 1L;
	private OptionVo correctOption;

	@PostConstruct
	public void initBean() {
		optionList = new ArrayList<OptionVo>();
	}

	@Override
	protected void updatePageContent() {
		optionList.clear();
		questionText = "";
		questionNoteText = "";
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
		question = new QuestionVo();
		question.setOptions(optionList);
		question.setQuestionType(questionTypeService.getById(1L));
		question.setNote(questionNoteText);
		question.setText(questionText);
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
		setUpQuestion();
		questionService.save(question, examId);
	}

	@Override
	public void tryToSaveQuestion() throws Exception {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		if (getCorrectOption() == null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "One answer!!!!");
			currentInstance.addMessage(null, facesMessage);
		} else {
			getCorrectOption().setCorrect(true);
			try {
				saveQuestion();
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "");
				currentInstance.addMessage(null, facesMessage);
				updatePageContent();

			} catch (Exception e) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "");
				currentInstance.addMessage(null, facesMessage);
				e.printStackTrace();
			}
		}
	}

	public OptionVo getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(OptionVo correctOption) {
		this.correctOption = correctOption;
	}

	public String getQuestionNoteText() {
		return questionNoteText;
	}

	public void setQuestionNoteText(String questionNoteText) {
		this.questionNoteText = questionNoteText;
	}

	
}
