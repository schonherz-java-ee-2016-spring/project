package hu.schonherz.training.web.supervisor.accessories;

import java.io.Serializable;

public class ObsEventList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6840608404724687788L;

	private Long eventId;
	private String eventName;
	private String studentName;
	/**
	 * 
	 */
	public ObsEventList(Long id, String eName, String sName) {
		eventId = id;
		eventName = eName;
		studentName = sName;
	}
	/**
	 * @return the eventId
	 */
	public Long getEventId() {
		return eventId;
	}
	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}
	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	
}
