package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.InterviewService;
import hu.schonherz.training.service.supervisor.vo.InterviewVo;

@ManagedBean(name = "createInterviewBean")
@ViewScoped
public class CreateInterviewBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5240872371946634042L;

	@EJB
	private InterviewService interviewService;

	private String company;
	private Date interviewDate;
	private UserVo interviewed;
	private UserVo interviewer;

	public void addInterview() {
		InterviewVo interviewerTmp = null;
		InterviewVo interviewedTmp = null;
		InterviewVo interviewVo = new InterviewVo();
		try {
			interviewerTmp = interviewService.getAllByInterviewerAndDate(interviewer.getId(), interviewDate);
			interviewedTmp = interviewService.getAllByInterviewedAndDate(interviewed.getId(), interviewDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		FacesContext currentInstance = FacesContext.getCurrentInstance();

		if (interviewDate == null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Date of interview must filled!");
			currentInstance.addMessage(null, facesMessage);
			return;
		}

		interviewVo.setInterviewDate(interviewDate);

		if (interviewerTmp != null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Interviewer already has an interview that time!");
			currentInstance.addMessage(null, facesMessage);
			return;
		}

		interviewVo.setInterviewer(interviewer);

		if (interviewedTmp != null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Student already has an interview that time!");
			currentInstance.addMessage(null, facesMessage);
			return;
		}

		interviewVo.setInterviewed(interviewed);

		interviewVo.setCompany(company);

		try {
			interviewService.addInterview(interviewVo);
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"En error occured adding the interview!");
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
		}

		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes!", "Interview created!");
		currentInstance.addMessage(null, facesMessage);

	}

	/**
	 * @return the interviewService
	 */
	public InterviewService getInterviewService() {
		return interviewService;
	}

	/**
	 * @param interviewService the interviewService to set
	 */
	public void setInterviewService(InterviewService interviewService) {
		this.interviewService = interviewService;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the interviewDate
	 */
	public Date getInterviewDate() {
		return interviewDate;
	}

	/**
	 * @param interviewDate the interviewDate to set
	 */
	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	/**
	 * @return the interviewed
	 */
	public UserVo getInterviewed() {
		return interviewed;
	}

	/**
	 * @param interviewed the interviewed to set
	 */
	public void setInterviewed(UserVo interviewed) {
		this.interviewed = interviewed;
	}

	/**
	 * @return the interviewer
	 */
	public UserVo getInterviewer() {
		return interviewer;
	}

	/**
	 * @param interviewer the interviewer to set
	 */
	public void setInterviewer(UserVo interviewer) {
		this.interviewer = interviewer;
	}
	
}
