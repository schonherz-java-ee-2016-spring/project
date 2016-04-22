package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hu.schonherz.training.service.supervisor.FeedbackService;
import hu.schonherz.training.service.supervisor.vo.FeedbackVo;
import hu.schonherz.training.service.supervisor.vo.InterviewVo;

@ManagedBean(name = "feedbacksBean")
@ViewScoped
public class FeedbacksBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6239504593798844987L;

	@EJB
	FeedbackService feedbackService;
	
	List<FeedbackVo> feedbacks;
	
//	@PostConstruct
//	public void init() {
//		try {
//			feedbacks = feedbackService.getAll();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	@PostConstruct
	public void init() {
		
	}
	
	public FeedbacksBean() {
		// TODO Auto-generated constructor stub
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
}
