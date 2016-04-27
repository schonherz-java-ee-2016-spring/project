package hu.schonherz.training.core.supervisor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import hu.schonherz.training.core.admin.entity.BaseEntity;
import hu.schonherz.training.core.admin.entity.User;

@Entity
@Table(name = "feedback")
public class Feedback extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6712969283784007008L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "sender_to_feedback", joinColumns = @JoinColumn(name = "feedback_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "sender_id", referencedColumnName = "id"))
	private User sender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "rated_to_feedback", joinColumns = @JoinColumn(name = "feedback_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "rated_id", referencedColumnName = "id"))
	private User rated;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinTable(name = "event_to_feedback", joinColumns = @JoinColumn(name = "feedback_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"))
//	private Event event;

	@Column(name = "event_type", nullable = false)
	private String eventType;
	
	@Column(name = "feedback_message", nullable = false, columnDefinition = "text")
	private String feedbackMessage;

	@Column(name = "is_public", nullable = false)
	private boolean isPublic;

	/**
	 * @return the sender
	 */
	public User getSender() {
		return sender;
	}

	/**
	 * @param sender
	 *            the sender to set
	 */
	public void setSender(User sender) {
		this.sender = sender;
	}

	/**
	 * @return the rated
	 */
	public User getRated() {
		return rated;
	}

	/**
	 * @param rated
	 *            the rated to set
	 */
	public void setRated(User rated) {
		this.rated = rated;
	}

	/**
	 * @return the event
	 */
//	public Event getEvent() {
//		return event;
//	}
//
//	/**
//	 * @param event
//	 *            the event to set
//	 */
//	public void setEvent(Event event) {
//		this.event = event;
//	}

	/**
	 * @return the feedbackMessage
	 */
	public String getFeedbackMessage() {
		return feedbackMessage;
	}

	/**
	 * @param feedbackMessage
	 *            the feedbackMessage to set
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
	 * @param isPublic
	 *            the isPublic to set
	 */
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
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

	@Override
	public String toString() {
		return "Feedback [sender=" + sender + ", rated=" + rated + ", feedbackMessage="
				+ feedbackMessage + ", isPublic=" + isPublic + ", eventType=" + eventType + "].";
	}
}
