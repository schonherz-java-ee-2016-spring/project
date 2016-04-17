package hu.schonherz.training.supervisor.web.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hu.schonherz.training.supervisor.service.InterviewService;
import hu.schonherz.training.supervisor.vo.InterviewVo;

@ManagedBean(name = "interviewsBean")
@ViewScoped
public class InterviewsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3607430344352669043L;

	@EJB
	private InterviewService interviewService;

	private String company;
	private Date interviewDate;
	private Long interviewedId;
	private Long interviewerId;

	private List<InterviewVo> interviews;

	@PostConstruct
	public void init() {
		try {
			interviews = interviewService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<InterviewVo> getAllInterviews() {
		List<InterviewVo> interviewVos = null;
		try {
			if (interviewService.getAll() == null) {
				interviewVos = new ArrayList<>();
			} else {
				interviewVos = interviewService.getAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return interviewVos;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
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
	 * @param interviewDate
	 *            the interviewDate to set
	 */
	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	/**
	 * @return the interviewedId
	 */
	public Long getInterviewedId() {
		return interviewedId;
	}

	/**
	 * @param interviewedId
	 *            the interviewedId to set
	 */
	public void setInterviewedId(Long interviewedId) {
		this.interviewedId = interviewedId;
	}

	/**
	 * @return the interviewerId
	 */
	public Long getInterviewerId() {
		return interviewerId;
	}

	/**
	 * @param interviewerId
	 *            the interviewerId to set
	 */
	public void setInterviewerId(Long interviewerId) {
		this.interviewerId = interviewerId;
	}

	/**
	 * @return the interviews
	 */
	public List<InterviewVo> getInterviews() {
		return interviews;
	}

	/**
	 * @param interviews
	 *            the interviews to set
	 */
	public void setInterviews(List<InterviewVo> interviews) {
		this.interviews = interviews;
	}

}
