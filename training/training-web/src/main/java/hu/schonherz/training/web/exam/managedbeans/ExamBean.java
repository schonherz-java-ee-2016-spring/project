package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
	private String modifiedTitle;
	private List<ExamVo> examList;

	@PostConstruct
	public void init() {
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
		RequestContext.getCurrentInstance().update("renameForm");
	}

	public void renameExamTitle() throws Exception {
		FacesContext currentInstance = FacesContext.getCurrentInstance();

		Long selectedExamId = Long.parseLong(examIdAsString);
		ExamVo examVo = examService.getById(selectedExamId);
		examVo.setTitle(modifiedTitle);

		try {
			examService.updateTitle(examVo);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Exam renamed");
			currentInstance.addMessage(null, facesMessage);
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Couldn't rename exam");
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
		}
		updateExamList();
		updateViewContent();
	}

	public void registerNewExam() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();

		ExamVo exam = new ExamVo();
		exam.setTitle(newExamTitle);

		try {
			getExamService().add(exam);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "Exam created");
			currentInstance.addMessage(null, facesMessage);
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Couldn't create exam");
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

	public String getModifiedTitle() {
		return modifiedTitle;
	}

	public void setModifiedTitle(String modifiedTitle) {
		this.modifiedTitle = modifiedTitle;
	}

	public List<ExamVo> getExamList() {
		return examList;
	}

	public void setExamList(List<ExamVo> examList) {
		this.examList = examList;
	}

}
