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

	private List<ObsEventList> displayList = new ArrayList<>();
	private UserVo loggedInUser = new UserVo();
	private String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

	private List<EventVo> events = new ArrayList<>();
	private List<UserVo> users = new ArrayList<>();
	boolean isOk;

	@PostConstruct
	public void init() {
		Long eventId;
		String eventName;
		String studentName = "";
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
					}
				}
				eventId = event.getId();
				eventName = event.getName();
				if (!studentName.isEmpty()) {
					displayList.add(new ObsEventList(eventId, eventName, studentName));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
}
