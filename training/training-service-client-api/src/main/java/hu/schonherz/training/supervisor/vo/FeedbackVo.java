package hu.schonherz.training.supervisor.vo;

import java.io.Serializable;
import java.util.Date;

import hu.schonherz.training.vo.UserVo;

public class FeedbackVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private UserVo sender;
	private UserVo rated;
	private InterviewVo interview;
	private Integer score;
	private String detailedFeedback;
	private boolean isPublic;
	private Date feedbackDate;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the sender
	 */
	public UserVo getSender() {
		return sender;
	}

	/**
	 * @param sender
	 *            the sender to set
	 */
	public void setSender(UserVo sender) {
		this.sender = sender;
	}

	/**
	 * @return the rated
	 */
	public UserVo getRated() {
		return rated;
	}

	/**
	 * @param rated
	 *            the rated to set
	 */
	public void setRated(UserVo rated) {
		this.rated = rated;
	}

	/**
	 * @return the interview
	 */
	public InterviewVo getInterview() {
		return interview;
	}

	/**
	 * @param interview
	 *            the interview to set
	 */
	public void setInterview(InterviewVo interview) {
		this.interview = interview;
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

	@Override
	public String toString() {
		return "FeedbacVO [id = " + id + " sender = " + sender.getId() + " rated = " + rated.getId() + " interview = "
				+ interview.getId() + " score = " + score + " detailedFeedback = " + detailedFeedback + " isPublic = "
				+ isPublic + " feedbackDate = " + feedbackDate + "]";

	}

}
