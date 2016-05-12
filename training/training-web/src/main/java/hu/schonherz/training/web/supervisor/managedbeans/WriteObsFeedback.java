package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Session;

import hu.schonherz.training.service.admin.EventService;
import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.EventVo;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.FeedbackService;
import hu.schonherz.training.service.supervisor.vo.FeedbackVo;
import hu.schonherz.training.web.admin.managedbeans.MailSenderBean;
import hu.schonherz.training.web.supervisor.accessories.EventList;

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
	
	@ManagedProperty(value = "#{mailSenderBean}")
	private MailSenderBean mailSenderBean;
	@Resource(mappedName = "java:jboss/mail/Default")
	private Session mailSessionSeznam;

//	variables for list events related to the logged in observer
	private List<EventList> displayList = new ArrayList<>();
	private UserVo loggedInUser = new UserVo();
	private String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
	private List<EventVo> events = new ArrayList<>();
	private List<UserVo> users = new ArrayList<>();
	

//	variables for save new feedback
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
		Date eventDate;
		boolean isOk;
		try {
//			get the logged in user
			loggedInUser = userService.findUserByName(username);
//			get the events related to the logged in user
			events = eventService.findEventsByUserOrderedByDate(loggedInUser.getId());
			for (EventVo event : events) {
//				get users related to the events
				users.addAll(event.getUsers());
				for (UserVo user : users) {

					isOk = true;
//					do not include the logged in user (no one can write feedback about him-/herself)
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
				eventDate = event.getDate();
//				store the event and student together for every events
				if (!studentName.isEmpty()) {
					displayList.add(new EventList(eventId, eventName, eventDate, studentName, studentUsername));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendFeedback() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
//		eventId and rated student's name passed in one String, need to split it into two
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
		if (isPublic.equalsIgnoreCase("true")) {
			feedback.setPublic(true);
		} else {
			feedback.setPublic(false);
		}
		List<UserVo> rateds = new ArrayList<>();
		try {
			rateds.add(userService.findUserByName(ratedUsername));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		feedback.setRated(rateds);
		feedback.setSender(loggedInUser);
		feedback.setRecDate(new Date());

		try {
			feedbackService.giveFeedback(feedback);
			String mailMessage = "<h3 style='font-style: normal;'><span style='font-weight: bold;'>Kedves "
					+ userService.findUserByName(ratedUsername).getFullName()
					+ "!</span></h3><div style='font-style: normal; font-weight: normal;'><br></div><div><span style='font-style: normal; font-weight: bold;'>Új visszajelzést kaptál</span><span style='font-weight: normal;'> a következő felhasználótól: <span style='font-style: italic; text-decoration: underline;'>"
					+ loggedInUser.getFullName()
					+ "</span>.</span></div><div style='font-weight: normal;'>A visszajelzés üzenetét bejelentkezés után a <span style='font-style: italic;'>Visszajelzések</span> menüpont alatt olvashatod.</div><div style='font-style: normal; font-weight: normal;'><br></div><div style='font-style: normal; font-weight: normal;'>Üdvözlettel,</div><div style='font-style: normal; font-weight: normal;'>Schönherz Training Application</div>";
			mailSenderBean.sendMail(mailSessionSeznam, "SCHTraining",
					userService.findUserByName(ratedUsername).getEmail(), "Új visszajelzés", mailMessage);
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
	public List<EventList> getDisplayList() {
		return displayList;
	}

	/**
	 * @param displayList
	 *            the displayList to set
	 */
	public void setDisplayList(List<EventList> displayList) {
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

	/**
	 * @return the mailSenderBean
	 */
	public MailSenderBean getMailSenderBean() {
		return mailSenderBean;
	}

	/**
	 * @param mailSenderBean the mailSenderBean to set
	 */
	public void setMailSenderBean(MailSenderBean mailSenderBean) {
		this.mailSenderBean = mailSenderBean;
	}
}
