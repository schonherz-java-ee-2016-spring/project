package hu.schonherz.training.supervisor.vo;

import java.io.Serializable;
import java.util.Date;

public class InterviewVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2516723235373929807L;

	private Long id;
	private String company;
	private Date interviewDate;
	private String description;

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

	@Override
	public String toString() {
		return "InterviewVO [id = " + id + " company = " + company + " interviewDate = " + interviewDate
				+ " description = " + description + "]";
	}
}
