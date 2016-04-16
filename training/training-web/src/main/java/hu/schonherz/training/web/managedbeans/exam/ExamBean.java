package hu.schonherz.training.web.managedbeans.exam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import hu.schonherz.training.exam.service.ExamService;
import hu.schonherz.training.exam.vo.ExamVo;

@ManagedBean(name = "examBean")
@SessionScoped
public class ExamBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ExamService examService;

	private String newExamTitle;
	private String examIdAsString;
	private String modifiedTitle;
	
	public String goToExamDetailsPage() {
		return "examDetails";
	}

	public void renameTitle() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		Long selectedExamId = Long.parseLong(examIdAsString);

		try {
			ExamVo examVo = examService.getExamById(selectedExamId);
			examVo.setTitle(modifiedTitle);
			examService.modifyExamTitle(examVo);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!",
					"\"" + modifiedTitle + "\" exam renamed!");
			currentInstance.addMessage(null, facesMessage);
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Couldn't rename exam: \"" + modifiedTitle + "\"");
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
		}

		RequestContext.getCurrentInstance().update("examTable");
		RequestContext.getCurrentInstance().update("renameForm");
	}

	public void registerNewExam() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();

		if (newExamTitle.isEmpty()) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Name field is empty!");
			currentInstance.addMessage(null, facesMessage);
			return;
		}

		ExamVo exam = new ExamVo();
		exam.setTitle(newExamTitle);

		try {
			getExamService().createExam(exam);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!",
					"\"" + newExamTitle + "\" exam created!");
			currentInstance.addMessage(null, facesMessage);
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Couldn't create exam: \"" + newExamTitle + "\"");
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().update("examTable");
		RequestContext.getCurrentInstance().update("renameForm");
	}

	public List<ExamVo> getExamList() {
		List<ExamVo> result = new ArrayList<ExamVo>();
		try {
			result = getExamService().getExamListSortedById();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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

}
