package hu.schonherz.training.web.managedbeans.exam;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolationException;

import hu.schonherz.training.exam.service.ExamService;
import hu.schonherz.training.exam.vo.ExamVo;

@ManagedBean(name = "examAddBean")
@SessionScoped
public class ExamAddBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ExamService examService;

	private String examTitle;

	public void registerExam() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();

		if (examTitle.isEmpty()) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Name field is empty!");
			currentInstance.addMessage(null, facesMessage);
			return;
		}

		ExamVo exam = new ExamVo();
		exam.setTitle(examTitle);

		try {
			getExamService().createExam(exam);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", "\"" + examTitle + "\" exam created!");
			currentInstance.addMessage(null, facesMessage);
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Couldn't create exam: \"" + examTitle + "\"");
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
		}
	}

	public ExamService getExamService() {
		return examService;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	public String getExamTitle() {
		return examTitle;
	}

	public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
	}
}
