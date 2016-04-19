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
import hu.schonherz.training.supervisor.service.InterviewService;
import hu.schonherz.training.supervisor.vo.FeedbackVo;

@ManagedBean(name = "feedbacksBean")
@ViewScoped
public class FeedbacksBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6239504593798844987L;

	@EJB
	private FeedbackService feedbackServie;
	
	@EJB
	private InterviewService interviewService;
	
	@ManagedProperty(value = "#{interviewsBean}")
	InterviewsBean interviewsBean;
	
	private List<FeedbackVo> feedbacks;
	
	@PostConstruct
	public void init() {
		try {
			feedbacks = feedbackServie.getAll();
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
	 * @param feedbacks the feedbacks to set
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
	 * @param interviewsBean the interviewsBean to set
	 */
	public void setInterviewsBean(InterviewsBean interviewsBean) {
		this.interviewsBean = interviewsBean;
	}

	
}
