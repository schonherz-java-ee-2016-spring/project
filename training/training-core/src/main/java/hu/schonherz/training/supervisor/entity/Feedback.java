package hu.schonherz.training.supervisor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import hu.schonherz.training.entity.BaseEntity;
import hu.schonherz.training.entity.User;

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

	@ManyToOne
	@JoinTable(name = "rated_to_feedback", joinColumns = @JoinColumn(name = "feedback_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "rated_id", referencedColumnName = "id"))
	private User rated;

	@OneToOne
	@JoinTable(name = "interview_to_feedback", joinColumns = @JoinColumn(name = "feedback_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "interview_id", referencedColumnName = "id"))
	private Interview interviewId;

	@Column(name = "score", nullable = false, length = 1)
	private Integer score;

	@Column(name = "detailed_feedback", nullable = false, columnDefinition = "text")
	private String detailedFeedback;

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

}
