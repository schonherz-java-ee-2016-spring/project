package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hu.schonherz.training.service.admin.EventService;
import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.EventVo;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.FeedbackService;

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

//	variables for list events related to the logged in student
	private UserVo loggedInUser = new UserVo();
	private String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
	private List<EventVo> events = new ArrayList<>();
	private List<UserVo> users = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		boolean isOk;
		try {
			loggedInUser = userService.findUserByName(username);
			events = eventService.findEventsByUserOrderedByDate(loggedInUser.getId());
			List<UserVo> usersToInspect = new ArrayList<>();
			for (EventVo event : events) {
				usersToInspect.addAll(event.getUsers());
				for (UserVo user : usersToInspect) {
					isOk = true;
					for (UserVo usr : users) {
						if (usr.getId() == user.getId()) {
							isOk = false;
						}
					}
					if (isOk) {
						users.add(user);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public WriteStudentFeedback() {
		
	}

	/**
	 * @return the loggedInUser
	 */
	public UserVo getLoggedInUser() {
		return loggedInUser;
	}

	/**
	 * @param loggedInUser the loggedInUser to set
	 */
	public void setLoggedInUser(UserVo loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	/**
	 * @return the events
	 */
	public List<EventVo> getEvents() {
		return events;
	}

	/**
	 * @param events the events to set
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
	 * @param users the users to set
	 */
	public void setUsers(List<UserVo> users) {
		this.users = users;
	}

	
}
