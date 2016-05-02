package hu.schonherz.training.web.supervisor.accessories;

import java.io.Serializable;

public class EventList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6840608404724687788L;

	private Long eventId;
	private String eventName;
	private String ratedName;
	private String ratedUsername;
	/**
	 * 
	 */
	public EventList(Long id, String eName, String sName, String sUsername) {
		eventId = id;
		eventName = eName;
		ratedName = sName;
		ratedUsername = sUsername;
	}
	
	
	/**
	 * @param ratedName
	 * @param ratedUsername
	 */
	public EventList(String ratedName, String ratedUsername) {
		super();
		this.ratedName = ratedName;
		this.ratedUsername = ratedUsername;
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
	 * @return the ratedName
	 */
	public String getRatedName() {
		return ratedName;
	}
	/**
	 * @param ratedName the ratedName to set
	 */
	public void setRatedName(String ratedName) {
		this.ratedName = ratedName;
	}
	/**
	 * @return the ratedUsername
	 */
	public String getRatedUsername() {
		return ratedUsername;
	}
	/**
	 * @param ratedUsername the ratedUsername to set
	 */
	public void setRatedUsername(String ratedUsername) {
		this.ratedUsername = ratedUsername;
	}
	
	
}
