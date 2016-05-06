package hu.schonherz.training.core.supervisor.entity;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import hu.schonherz.training.core.admin.entity.BaseEntity;
import hu.schonherz.training.core.admin.entity.Event;
import hu.schonherz.training.core.admin.entity.User;

@Entity
@Table(name = "feedback")
public class Feedback extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6712969283784007008L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "sender_to_feedback", joinColumns = @JoinColumn(name = "feedback_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "sender_id", referencedColumnName = "id"))
	private User sender;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rated_to_feedback", joinColumns = @JoinColumn(name = "feedback_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "rated_id", referencedColumnName = "id"))
	private Collection<User> rated;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "event_to_feedback", joinColumns = @JoinColumn(name = "feedback_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"))
	private Event event;
	
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
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

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
	 * @return the rated
	 */
	public Collection<User> getRated() {
		return rated;
	}

	/**
	 * @param rated the rated to set
	 */
	public void setRated(Collection<User> rated) {
		this.rated = rated;
	}

	public static Comparator<Feedback> FeedbackDateComparator = new Comparator<Feedback>() {

		@Override
		public int compare(Feedback o1, Feedback o2) {
			Date feedbackDate1 = o1.getRecDate();
			Date feedbackDate2 = o2.getRecDate();
			return feedbackDate2.compareTo(feedbackDate1);
		}
		
	};
}
