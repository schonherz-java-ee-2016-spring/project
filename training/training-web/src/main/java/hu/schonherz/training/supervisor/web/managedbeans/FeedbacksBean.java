package hu.schonherz.training.supervisor.web.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import hu.schonherz.training.supervisor.service.FeedbackService;
import hu.schonherz.training.supervisor.vo.FeedbackVo;
import hu.schonherz.training.supervisor.vo.InterviewVo;

@ManagedBean(name = "feedbacksBean")
@ViewScoped
public class FeedbacksBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6239504593798844987L;

	@EJB
	private FeedbackService feedbackServie;

	@ManagedProperty(value = "#{interviewsBean}")
	InterviewsBean interviewsBean;
	
	private List<InterviewVo> interviews;

	private String company;
	
	private List<FeedbackVo> feedbacks;

	@PostConstruct
	public void init() {
		try {
			feedbacks = feedbackServie.getAll();
			for (FeedbackVo feedback : feedbacks) {
				for (InterviewVo interview : interviews) {
					if (feedback.getInterview().getId() == interview.getId()) {
						setCompany(interview.getCompany());
					}
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<FeedbackVo> getAllFeedbacks() {
		List<FeedbackVo> vos = null;
		try {
			if (feedbackServie.getAll() == null) {
				vos = new ArrayList<>();
			} else {
				vos = feedbackServie.getAll();
				for (FeedbackVo vo : vos) {
					for (InterviewVo interview : interviews) {
						if (vo.getInterview().getId() == interview.getId()) {
							setCompany(interview.getCompany());
						}
					}		
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vos;
	}

	/**
	 * @return the feedbacks
	 */
	public List<FeedbackVo> getFeedbacks() {
		return feedbacks;
	}

	/**
	 * @param feedbacks
	 *            the feedbacks to set
	 */
	public void setFeedbacks(List<FeedbackVo> feedbacks) {
		this.feedbacks = feedbacks;
	}

	/**
	 * @return the interviewsBean
	 */
	public InterviewsBean getInterviewsBean() {
		return interviewsBean;
	}

	/**
	 * @param interviewsBean
	 *            the interviewsBean to set
	 */
	public void setInterviewsBean(InterviewsBean interviewsBean) {
		this.interviewsBean = interviewsBean;
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

}
