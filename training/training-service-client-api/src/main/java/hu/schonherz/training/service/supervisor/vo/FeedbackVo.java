package hu.schonherz.training.service.supervisor.vo;

import java.io.Serializable;
import java.util.Date;

import hu.schonherz.training.service.admin.vo.UserVo;

public class FeedbackVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserVo sender;
	private UserVo rated;
	private String feedbackMessage;
	private Date recDate;
	private boolean isPublic;
	private String eventType;
	
	
	/**
	 * 
	 */
	public FeedbackVo() {
		
	}

	/**
	 * @return the sender
	 */
	public UserVo getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
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
	 * @param rated the rated to set
	 */
	public void setRated(UserVo rated) {
		this.rated = rated;
	}

	/**
	 * @return the feedbackMessage
	 */
	public String getFeedbackMessage() {
		return feedbackMessage;
	}

	/**
	 * @param feedbackMessage the feedbackMessage to set
	 */
	public void setFeedbackMessage(String feedbackMessage) {
		this.feedbackMessage = feedbackMessage;
	}

	/**
	 * @return the isPublic
	 */
	public boolean isPublic() {
		return isPublic;
	}

	/**
	 * @param isPublic the isPublic to set
	 */
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	/**
	 * @return the recDate
	 */
	public Date getRecDate() {
		return recDate;
	}

	/**
	 * @param recDate the recDate to set
	 */
	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}



	public String toString() {
		return "Feedback [sender=" + sender + ", rated=" + rated + ", feedbackMessage="
				+ feedbackMessage + ", isPublic=" + isPublic + ", eventType=" + eventType + "].";
	}

}
