package hu.schonherz.training.core.supervisor.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import hu.schonherz.training.core.admin.entity.BaseEntity;
import hu.schonherz.training.core.admin.entity.User;

@Entity
@Table(name = "interview")
public class Interview extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8891120133955636474L;

	@Column(name = "company", nullable = false, length = 100)
	private String company;

	@Column(name = "interview_date", nullable = false)
	private Date interviewDate;

	@Column(name = "description", nullable = false)
	@Lob
	private String description;

	@OneToOne
	@JoinColumn(name = "interviewer_id", referencedColumnName = "id", nullable = false)
	private User interviewer;

	@OneToOne
	@JoinColumn(name = "interviewed_id", referencedColumnName = "id", nullable = false)
	private User interviewed;

	/**
	 * 
	 */
	public Interview() {
		super();
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the interviewDate
	 */
	public Date getInterviewDate() {
		return interviewDate;
	}

	/**
	 * @param interviewDate
	 *            the interviewDate to set
	 */
	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the interviewer
	 */
	public User getInterviewer() {
		return interviewer;
	}

	/**
	 * @param interviewer the interviewer to set
	 */
	public void setInterviewer(User interviewer) {
		this.interviewer = interviewer;
	}

	/**
	 * @return the interviewed
	 */
	public User getInterviewed() {
		return interviewed;
	}

	/**
	 * @param interviewed the interviewed to set
	 */
	public void setInterviewed(User interviewed) {
		this.interviewed = interviewed;
	}

}
