package hu.schonherz.training.supervisor.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import hu.schonherz.training.entity.BaseEntity;
import hu.schonherz.training.entity.User;

@Entity
@Table(name = "interview")
public class Interview extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8891120133955636474L;

	@Column(nullable = false, length = 100)
	private String company;

	@Column(nullable = false)
	private Date interviewDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "interviewer_to_interview", joinColumns = @JoinColumn(name = "interview_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "interviewer_id", referencedColumnName = "id"))
	private User interviewer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "interviewed_to_interview", joinColumns = @JoinColumn(name = "interview_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "interviewed_id", referencedColumnName = "id"))
	private User interviewed;

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
	 * @return the interviewer
	 */
	public User getInterviewer() {
		return interviewer;
	}

	/**
	 * @param interviewer
	 *            the interviewer to set
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
	 * @param interviewed
	 *            the interviewed to set
	 */
	public void setInterviewed(User interviewed) {
		this.interviewed = interviewed;
	}

}
