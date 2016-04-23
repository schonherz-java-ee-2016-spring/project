package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import hu.schonherz.training.service.supervisor.InterviewService;
import hu.schonherz.training.service.supervisor.vo.InterviewVo;

@ManagedBean(name = "interviewsBean")
@SessionScoped
public class InterviewsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3607430344352669043L;

	@EJB
	private InterviewService interviewService;

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

	/**
	 * @return the interviewService
	 */
	public InterviewService getInterviewService() {
		return interviewService;
	}

	/**
	 * @param interviewService
	 *            the interviewService to set
	 */
	public void setInterviewService(InterviewService interviewService) {
		this.interviewService = interviewService;
	}

}
