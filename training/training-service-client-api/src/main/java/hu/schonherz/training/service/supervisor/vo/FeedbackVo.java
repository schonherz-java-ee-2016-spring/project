package hu.schonherz.training.service.supervisor.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import hu.schonherz.training.service.admin.vo.EventVo;
import hu.schonherz.training.service.admin.vo.UserVo;

public class FeedbackVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserVo sender;
	private Collection<UserVo> rated;
	private String feedbackMessage;
	private Date recDate;
	private boolean isPublic;
	private EventVo event;
	
	
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
	 * @return the event
	 */
	public EventVo getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(EventVo event) {
		this.event = event;
	}

	/**
	 * @return the rated
	 */
	public Collection<UserVo> getRated() {
		return rated;
	}

	/**
	 * @param rated the rated to set
	 */
	public void setRated(Collection<UserVo> rated) {
		this.rated = rated;
	}

}
