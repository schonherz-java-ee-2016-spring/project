package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hu.schonherz.training.service.admin.EventService;
import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.EventVo;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.FeedbackService;
import hu.schonherz.training.service.supervisor.vo.FeedbackVo;
import hu.schonherz.training.web.supervisor.accessories.ObsEventList;

@ManagedBean(name = "writeObsFeedback")
@ViewScoped
public class WriteObsFeedback implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3407529009405012528L;

	@EJB
	EventService eventService;

	@EJB
	UserService userService;

	@EJB
	FeedbackService feedbackService;

	private List<ObsEventList> displayList = new ArrayList<>();
	private UserVo loggedInUser = new UserVo();
	private String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
	private List<EventVo> events = new ArrayList<>();
	private List<UserVo> users = new ArrayList<>();
	boolean isOk;

	private String eventAndRatedSelector;
	private String[] split;
	private String eventId;
	private String ratedUsername;
	private String feedbackMessage;
	private String isPublic;

	@PostConstruct
	public void init() {
		Long eventId;
		String eventName;
		String studentName = "";
		String studentUsername = "";
		try {
			loggedInUser = userService.findUserByName(username);
			events = eventService.findEventsByUserOrderedByDate(loggedInUser.getId());
			for (EventVo event : events) {
				users.addAll(event.getUsers());
				for (UserVo user : users) {

					isOk = true;

					if (user.getUserName().contentEquals(username)) {
						isOk = false;
					}

					if (isOk) {
						studentName = user.getFullName();
						studentUsername = user.getUserName();
					}
				}
				eventId = event.getId();
				eventName = event.getName();
				if (!studentName.isEmpty()) {
					displayList.add(new ObsEventList(eventId, eventName, studentName, studentUsername));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendFeedback() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		split = eventAndRatedSelector.split(";");
		eventId = split[0];
		ratedUsername = split[1];
		FeedbackVo feedback = new FeedbackVo();
		feedback.setEvent(eventService.findEventById(Long.parseLong(eventId)));
		feedback.setFeedbackMessage(feedbackMessage);
		if (feedbackMessage == null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Feedback message is missing");
			currentInstance.addMessage(null, facesMessage);
			return;
		}
		if (isPublic.contentEquals(isPublic)) {
			feedback.setPublic(true);
		} else {
			feedback.setPublic(false);
		}
		try {
			feedback.setRated(userService.findUserByName(ratedUsername));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		feedback.setSender(loggedInUser);
		feedback.setRecDate(new Date());

		try {
			feedbackService.giveFeedback(feedback);
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Feedback could not be sent");
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
		}

		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes!",
				"Feedback successfully sent");
		currentInstance.addMessage("growl", facesMessage);
	}

	/**
	 * 
	 */
	public WriteObsFeedback() {

	}

	/**
	 * @return the displayList
	 */
	public List<ObsEventList> getDisplayList() {
		return displayList;
	}

	/**
	 * @param displayList
	 *            the displayList to set
	 */
	public void setDisplayList(List<ObsEventList> displayList) {
		this.displayList = displayList;
	}

	/**
	 * @return the loggedInUser
	 */
	public UserVo getLoggedInUser() {
		return loggedInUser;
	}

	/**
	 * @param loggedInUser
	 *            the loggedInUser to set
	 */
	public void setLoggedInUser(UserVo loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	/**
	 * @return the display
	 */

	/**
	 * @return the events
	 */
	public List<EventVo> getEvents() {
		return events;
	}

	/**
	 * @param events
	 *            the events to set
	 */
	public void setEvents(List<EventVo> events) {
		this.events = events;
	}

	/**
	 * @return the users
	 */
	public List<UserVo> getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(List<UserVo> users) {
		this.users = users;
	}

	/**
	 * @return the isOk
	 */
	public boolean isOk() {
		return isOk;
	}

	/**
	 * @param isOk
	 *            the isOk to set
	 */
	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	/**
	 * @return the eventId
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * @return the ratedUsername
	 */
	public String getRatedUsername() {
		return ratedUsername;
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
	public String getIsPublic() {
		return isPublic;
	}

	/**
	 * @param isPublic
	 *            the isPublic to set
	 */
	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * @return the eventAndRatedSelector
	 */
	public String getEventAndRatedSelector() {
		return eventAndRatedSelector;
	}

	/**
	 * @param eventAndRatedSelector
	 *            the eventAndRatedSelector to set
	 */
	public void setEventAndRatedSelector(String eventAndRatedSelector) {
		this.eventAndRatedSelector = eventAndRatedSelector;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the split
	 */
	public String[] getSplit() {
		return split;
	}

	/**
	 * @param split the split to set
	 */
	public void setSplit(String[] split) {
		this.split = split;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * @param ratedUsername the ratedUsername to set
	 */
	public void setRatedUsername(String ratedUsername) {
		this.ratedUsername = ratedUsername;
	}
}
