package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import hu.schonherz.training.service.exam.ExamService;
import hu.schonherz.training.service.exam.vo.ExamVo;

@ManagedBean(name = "examBean")
@ViewScoped
public class ExamBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ExamService examService;

	private String newExamTitle;
	private String examIdAsString;
	private List<ExamVo> examList;
	private String booleanChangeExamIdAsString;

	@ManagedProperty("#{out}")
	private ResourceBundle bundle;

	@PostConstruct
	public void init() {
		updateExamList();
	}

	public void removeExam(ActionEvent event) {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		String examIdAsString = event.getComponent().getAttributes().get("examIdAsString").toString();
		Long examId = Long.parseLong(examIdAsString);
		try {
			examService.removeById(examId);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("succes"),
					bundle.getString("examdeleted"));
			currentInstance.addMessage(null, facesMessage);
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
					bundle.getString("erralreadyfilledout"));
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
		}
		updateExamList();
	}

	public void updateExamList() {
		try {
			examList = getExamService().getAllSortedById();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateViewContent() {
		RequestContext.getCurrentInstance().update("examTable");
	}

	public void registerNewExam() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		ExamVo exam = new ExamVo();
		exam.setStatus(false);
		exam.setTitle(newExamTitle);

		try {
			getExamService().add(exam);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("succes"),
					bundle.getString("examcreated"));
			currentInstance.addMessage(null, facesMessage);
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
					bundle.getString("errcouldntcreateexam"));
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
		}
		updateExamList();
		updateViewContent();
	}

	public void setStatusToTrue(ActionEvent event) {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		String booleanChangeExamIdAsString = event.getComponent().getAttributes().get("booleanChangeExamIdAsString")
				.toString();
		Long examid = Long.parseLong(booleanChangeExamIdAsString);
		try {
			examService.modifyStatusToTrue(examid);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("succes"),
					bundle.getString("examstarted"));
			currentInstance.addMessage(null, facesMessage);

		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
					bundle.getString("errcouldntstartexam"));
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
		}
		updateExamList();
		updateViewContent();
	}

	public void setStatusToFalse(ActionEvent event) {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		String booleanChangeExamIdAsString = event.getComponent().getAttributes().get("booleanChangeExamIdAsString")
				.toString();
		Long examid = Long.parseLong(booleanChangeExamIdAsString);
		try {
			examService.modifyStatusToFalse(examid);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("succes"),
					bundle.getString("examstopped"));
			currentInstance.addMessage(null, facesMessage);

		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
					bundle.getString("errcouldntstopexam"));
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
		}
		updateExamList();
		updateViewContent();
	}

	public ExamService getExamService() {
		return examService;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	public String getNewExamTitle() {
		return newExamTitle;
	}

	public void setNewExamTitle(String newExamTitle) {
		this.newExamTitle = newExamTitle;
	}

	public String getExamIdAsString() {
		return examIdAsString;
	}

	public void setExamIdAsString(String examIdAsString) {
		this.examIdAsString = examIdAsString;
	}

	public List<ExamVo> getExamList() {
		return examList;
	}

	public void setExamList(List<ExamVo> examList) {
		this.examList = examList;
	}

	public String getBooleanChangeExamIdAsString() {
		return booleanChangeExamIdAsString;
	}

	public void setBooleanChangeExamIdAsString(String booleanChangeExamIdAsString) {
		this.booleanChangeExamIdAsString = booleanChangeExamIdAsString;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

}
