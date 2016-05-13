package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import hu.schonherz.training.service.admin.RoleGroupService;
import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.EventVo;
import hu.schonherz.training.service.admin.vo.RoleGroupVo;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.FeedbackService;
import hu.schonherz.training.service.supervisor.vo.FeedbackVo;
import hu.schonherz.training.web.admin.managedbeans.MailSenderBean;
import hu.schonherz.training.web.supervisor.accessories.EventList;

@ManagedBean(name = "writeStudentFeedback")
@ViewScoped
public class WriteStudentFeedback implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 903264090835225598L;

	@EJB
	EventService eventService;

	@EJB
	UserService userService;

	@EJB
	FeedbackService feedbackService;

	@EJB
	RoleGroupService roleGroupService;
	
	@ManagedProperty(value = "#{mailSenderBean}")
	private MailSenderBean mailSenderBean;
	@Resource(mappedName = "java:jboss/mail/Default")
	private Session mailSessionSeznam;

	// variables for list events related to the logged in student
	private UserVo loggedInUser = new UserVo();
	private String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
	private List<EventVo> eventsToInspect = new ArrayList<>();
	private Set<UserVo> users = new HashSet<>();
	private List<UserVo> usersToShow = new ArrayList<>();
	private List<EventList> events = new ArrayList<>();
	
	// variables for send new feedback
	private String eventId;
	private String instructorUsername;
	private String isPublic;
	private String feedbackMessage;

	@PostConstruct
	public void init() {
		try {
			loggedInUser = userService.findUserByName(username);
			eventsToInspect = eventService.findEventsByUserOrderedByDate(loggedInUser.getId());
			for (EventVo event : eventsToInspect) {
				events.add(new EventList(event.getId(), event.getName(), event.getType(), event.getDate()));
				users.addAll(event.getUsers());
			}
			for (UserVo user : users) {
				for (RoleGroupVo roleGroupVo : user.getRoleGroups()) {
					if (roleGroupVo.getName().contentEquals("Instructor Role Group")) {
						usersToShow.add(user);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendEventFeedback() {
		
		FacesContext currentInstance = FacesContext.getCurrentInstance();
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
		List<UserVo> usrs = new ArrayList<>();
		for (UserVo user : feedback.getEvent().getUsers()) {
			if (user.getUserName().contentEquals(username) == false) {
				usrs.add(user);
			}
		}
		feedback.setRated(usrs);
		feedback.setSender(loggedInUser);
		feedback.setRecDate(new Date());
		try {
			feedbackService.giveFeedback(feedback);
			for (UserVo user : feedback.getRated()) {
				String mailMessage = "<h3 style='font-style: normal;'><span style='font-weight: bold;'>Kedves "
						+ user.getFullName()
						+ "!</span></h3><div style='font-style: normal; font-weight: normal;'><br></div><div><span style='font-style: normal; font-weight: bold;'>Új visszajelzést kaptál</span><span style='font-weight: normal;'> a következő felhasználótól: <span style='font-style: italic; text-decoration: underline;'>"
						+ loggedInUser.getFullName()
						+ "</span>.</span></div><div style='font-weight: normal;'>A visszajelzés üzenetét bejelentkezés után a <span style='font-style: italic;'>Visszajelzések</span> menüpont alatt olvashatod.</div><div style='font-style: normal; font-weight: normal;'><br></div><div style='font-style: normal; font-weight: normal;'>Üdvözlettel,</div><div style='font-style: normal; font-weight: normal;'>Schönherz Training Application</div>";
				mailSenderBean.sendMail(mailSessionSeznam, "noreply@javatraining.hu", user.getEmail(), "Új visszajelzés",
						mailMessage);
			}
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
	
	public void sendInstructorFeedback() {
		
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		FeedbackVo feedback = new FeedbackVo();
		List<UserVo> instructor = new ArrayList<>();
		try {
			instructor.add(userService.findUserByName(instructorUsername));
			feedback.setRated(instructor);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
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
		feedback.setSender(loggedInUser);
		feedback.setRecDate(new Date());
		try {
			feedbackService.giveFeedback(feedback);
			String mailMessage = "<h3 style='font-style: normal;'><span style='font-weight: bold;'>Kedves "
					+ userService.findUserByName(instructorUsername).getFullName()
					+ "!</span></h3><div style='font-style: normal; font-weight: normal;'><br></div><div><span style='font-style: normal; font-weight: bold;'>Új visszajelzést kaptál</span><span style='font-weight: normal;'> a következő felhasználótól: <span style='font-style: italic; text-decoration: underline;'>"
					+ loggedInUser.getFullName()
					+ "</span>.</span></div><div style='font-weight: normal;'>A visszajelzés üzenetét bejelentkezés után a <span style='font-style: italic;'>Visszajelzések</span> menüpont alatt olvashatod.</div><div style='font-style: normal; font-weight: normal;'><br></div><div style='font-style: normal; font-weight: normal;'>Üdvözlettel,</div><div style='font-style: normal; font-weight: normal;'>Schönherz Training Application</div>";
			mailSenderBean.sendMail(mailSessionSeznam, "noreply@javatraining.hu",
					userService.findUserByName(instructorUsername).getEmail(), "Új visszajelzés", mailMessage);
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
	 * @return the eventsToInspect
	 */
	public List<EventVo> getEventsToInspect() {
		return eventsToInspect;
	}

	/**
	 * @param eventsToInspect the eventsToInspect to set
	 */
	public void setEventsToInspect(List<EventVo> eventsToInspect) {
		this.eventsToInspect = eventsToInspect;
	}

	/**
	 * @return the users
	 */
	public Set<UserVo> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<UserVo> users) {
		this.users = users;
	}

	/**
	 * @return the events
	 */
	public List<EventList> getEvents() {
		return events;
	}

	/**
	 * @param events the events to set
	 */
	public void setEvents(List<EventList> events) {
		this.events = events;
	}

	/**
	 * @return the eventId
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the isPublic
	 */
	public String getIsPublic() {
		return isPublic;
	}

	/**
	 * @param isPublic the isPublic to set
	 */
	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * @return the feedbackMessage
	 */
	public String getFeedbackMessage() {
		return feedbackMessage;
	}

	/**
	 * @param feedbackMessage the feedbackMessage to set
	 */
	public void setFeedbackMessage(String feedbackMessage) {
		this.feedbackMessage = feedbackMessage;
	}

	/**
	 * @return the instructorUsername
	 */
	public String getInstructorUsername() {
		return instructorUsername;
	}

	/**
	 * @param instructorUsername the instructorUsername to set
	 */
	public void setInstructorUsername(String instructorUsername) {
		this.instructorUsername = instructorUsername;
	}

	/**
	 * @return the usersToShow
	 */
	public List<UserVo> getUsersToShow() {
		return usersToShow;
	}

	/**
	 * @param usersToShow the usersToShow to set
	 */
	public void setUsersToShow(List<UserVo> usersToShow) {
		this.usersToShow = usersToShow;
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
