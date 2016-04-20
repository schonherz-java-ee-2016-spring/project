package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import hu.schonherz.training.service.supervisor.FeedbackService;
import hu.schonherz.training.service.supervisor.vo.FeedbackVo;

@ManagedBean(name = "feedbacksBean")
@SessionScoped
public class FeedbacksBeans implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4486666013742222540L;

	@EJB
	private FeedbackService feedbackService;

	private String detailedFeedback;
	private Date feedbackDate;
	private boolean isPublic;
	private Integer score;
	private Long interviewId;
	private Long ratedId;
	private Long senderId;

	private List<FeedbackVo> feedbacks;

	public void init() {
		try {
			feedbacks = feedbackService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<FeedbackVo> getAllFeedbacks() {
		List<FeedbackVo> feedbackVos = null;
		try {
			if (feedbackService.getAll() == null) {
				feedbackVos = new ArrayList<>();
			} else {
				feedbackVos = feedbackService.getAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return feedbackVos;
	}

	/**
	 * @return the detailedFeedback
	 */
	public String getDetailedFeedback() {
		return detailedFeedback;
	}

	/**
	 * @param detailedFeedback
	 *            the detailedFeedback to set
	 */
	public void setDetailedFeedback(String detailedFeedback) {
		this.detailedFeedback = detailedFeedback;
	}

	/**
	 * @return the feedbackDate
	 */
	public Date getFeedbackDate() {
		return feedbackDate;
	}

	/**
	 * @param feedbackDate
	 *            the feedbackDate to set
	 */
	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	/**
	 * @return the isPublic
	 */
	public boolean isPublic() {
		return isPublic;
	}

	/**
	 * @param isPublic
	 *            the isPublic to set
	 */
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * @return the interviewId
	 */
	public Long getInterviewId() {
		return interviewId;
	}

	/**
	 * @param interviewId
	 *            the interviewId to set
	 */
	public void setInterviewId(Long interviewId) {
		this.interviewId = interviewId;
	}

	/**
	 * @return the ratedId
	 */
	public Long getRatedId() {
		return ratedId;
	}

	/**
	 * @param ratedId
	 *            the ratedId to set
	 */
	public void setRatedId(Long ratedId) {
		this.ratedId = ratedId;
	}

	/**
	 * @return the senderId
	 */
	public Long getSenderId() {
		return senderId;
	}

	/**
	 * @param senderId
	 *            the senderId to set
	 */
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
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

}
