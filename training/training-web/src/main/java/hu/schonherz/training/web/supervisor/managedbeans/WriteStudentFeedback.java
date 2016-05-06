package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hu.schonherz.training.service.admin.EventService;
import hu.schonherz.training.service.admin.RoleGroupService;
import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.EventVo;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.FeedbackService;
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

	// variables for list events related to the logged in student
	private UserVo loggedInUser = new UserVo();
	private String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
	private List<EventVo> eventsToInspect = new ArrayList<>();
	private Set<UserVo> users = new HashSet<>();
	private List<EventList> events = new ArrayList<>();

	@PostConstruct
	public void init() {
		try {
			loggedInUser = userService.findUserByName(username);
			eventsToInspect = eventService.findEventsByUserOrderedByDate(loggedInUser.getId());
			for (EventVo event : eventsToInspect) {
				events.add(new EventList(event.getId(), event.getName(), event.getType(), event.getDate()));
				users.addAll(event.getUsers());
			}
			users.remove(loggedInUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
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


}
