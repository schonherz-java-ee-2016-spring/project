package hu.schonherz.training.web.exam.managedbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.Part;

import org.primefaces.context.RequestContext;

import hu.schonherz.training.service.exam.vo.OptionVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@ManagedBean(name = "multipleQuestionDetailsBean")
@SessionScoped
public class MultipleQuestionDetailsBean extends SelectorQuestionBean {
	private static final long serialVersionUID = 1L;

	private String questionIdAsString;
	private List<OptionVo> correctOptions;
	private Boolean initLoading = true;
	private String editOptionText;
	private String oldOptionText;

	private String usableImageLink;
	private Part image;

	@ManagedProperty("#{out}")
	private ResourceBundle bundle;

	public void saveImage() {
		try (InputStream input = getImage().getInputStream()) {
			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
			String folder = ec.getRealPath("/") + "/questionimages/" + questionIdAsString + "/";
			String filename = getImage().getSubmittedFileName();
			if (!Files.exists(Paths.get(folder))) {
				Files.createDirectories(Paths.get(folder));
			}
			Files.copy(input, new File(folder, filename).toPath(), StandardCopyOption.REPLACE_EXISTING);

			setUsableImageLink("Kép linkje: " + ec.getRequestScheme() + "://" + ec.getRequestServerName() + ":"
					+ ec.getRequestServerPort() + "/training-web/questionimages/" + questionIdAsString + "/" + filename);
			RequestContext.getCurrentInstance().update("usableImageLinkForm");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
			if (correctOptions.contains(o)) {
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
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("optionexists"),
					"");
			currentInstance.addMessage(null, facesMessage);
		} else {
			optionList.add(option);
			clearOptionText();
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("succes"), "");
			currentInstance.addMessage(null, facesMessage);
		}
		RequestContext.getCurrentInstance().update("optionTableForm");

	}

	private void clearOptionText() {
		optionText = "";
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
		questionService.add(question, examId);
	}

	@Override
	public void tryToSaveQuestion() throws Exception {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		if (correctOptions == null || optionList.isEmpty()) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					bundle.getString("qneedsatleastoneoption"), "");
			currentInstance.addMessage(null, facesMessage);
		} else {
			try {
				saveQuestion();
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("succes"),
						"");
				currentInstance.addMessage(null, facesMessage);
				updatePageContent();
				FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
						FacesContext.getCurrentInstance(), null, "examQuestions.xhtml?faces-redirect=true");

			} catch (Exception e) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
						"");
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
	public void setQuestionNoteText(String questionNoteText) {
		Long questionId = Long.parseLong(questionIdAsString);
		try {
			QuestionVo questionVo = questionService.getById(questionId);
			if (questionNoteText.length() < 1)
				questionVo.setNote("You can't leave the note unfilled");
			else
				questionVo.setNote(questionNoteText);
			questionService.modifyNote(questionVo);
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
			questionService.modifyText(questionVo);
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

	public void updateOptionList() {
		Long id = Long.parseLong(questionIdAsString);
		try {
			optionList = questionService.getById(id).getOptions();
			correctOptions = optionList.stream().filter(o -> o.getCorrect()).collect(Collectors.toList());
			initLoading = false;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setUpEditOption(ActionEvent event) {
		oldOptionText = event.getComponent().getAttributes().get("optionName").toString();
		editOptionText = oldOptionText;
	}

	public void editOption() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		try {
			if (optionList.stream().filter(o -> o.getText().equalsIgnoreCase(editOptionText)).count() > 0) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						bundle.getString("optionexists"), "");
				currentInstance.addMessage(null, facesMessage);

			} else {

				option = new OptionVo();
				option.setText(editOptionText);

				for (int i = 0; i < optionList.size(); i++) {
					if (optionList.get(i).getText().equalsIgnoreCase(oldOptionText)) {
						optionList.remove(i);
						optionList.add(i, option);
					}

				}
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("succes"),
						"");
				currentInstance.addMessage(null, facesMessage);
				RequestContext.getCurrentInstance().update("editDialogForm");
				RequestContext.getCurrentInstance().update("optionTableForm");
			}
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"), "");
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
		}

	}

	public String getQuestionIdAsString() {
		return questionIdAsString;
	}

	public void setQuestionIdAsString(String questionIdAsString) {
		this.questionIdAsString = questionIdAsString;
	}

	public List<OptionVo> getCorrectOptions() {
		return correctOptions;
	}

	public void setCorrectOptions(List<OptionVo> correctOptions) {
		this.correctOptions = correctOptions;
	}

	public Boolean getInitLoading() {
		return initLoading;
	}

	public void setInitLoading(Boolean initLoading) {
		this.initLoading = initLoading;
	}

	public String getEditOptionText() {
		return editOptionText;
	}

	public void setEditOptionText(String editOptionText) {
		this.editOptionText = editOptionText;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public String getUsableImageLink() {
		return usableImageLink;
	}

	public void setUsableImageLink(String usableImageLink) {
		this.usableImageLink = usableImageLink;
	}

	public Part getImage() {
		return image;
	}

	public void setImage(Part image) {
		this.image = image;
	}

}
