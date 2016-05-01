package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import hu.schonherz.training.service.admin.EventService;
import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.EventVo;
import hu.schonherz.training.service.admin.vo.UserVo;

@ManagedBean(name = "writeFeedbackBean")
@ViewScoped
public class WriteFeedbackBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2972060697014376693L;
	

	@EJB
	EventService eventService;

	@EJB
	UserService userService;

	private List<EventVo> eventsToShow = new ArrayList<>();

	private List<UserVo> usersToShow = new ArrayList<>();

	private UserVo loggedInUser = new UserVo();
	
	private EventVo selectedEvent = new EventVo();
	
	private String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
	

	@PostConstruct
	public void init() {
		
		try {
			usersToShow.clear();
			loggedInUser = userService.findUserByName(username);
			eventsToShow = eventService.findEventsByUserOrderedByDate(loggedInUser.getId());
			for (EventVo event : eventsToShow) {
				List<UserVo> usersToInspect = new ArrayList<>();
				usersToInspect.addAll(event.getUsers());
				for (UserVo user : usersToInspect) {
					boolean isOk = true;
					if (user.getUserName().contentEquals(username)) {
						isOk = false;
					} else {
						for (UserVo userToShow : usersToShow) {
							if (user.getId() == userToShow.getId()) {
								isOk = false;
							}
						}
					}
					if (isOk) {
						usersToShow.add(user);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void eventChanged(ValueChangeEvent e) {
		usersToShow.clear();
		String idString = e.getNewValue().toString();
		Long id = Long.parseLong(idString);
		selectedEvent = eventService.findEventById(id);
		usersToShow.addAll(selectedEvent.getUsers());
	}

	/**
	 * 
	 */
	public WriteFeedbackBean() {

	}

	/**
	 * @return the eventsToShow
	 */
	public List<EventVo> getEventsToShow() {
		return eventsToShow;
	}

	/**
	 * @param eventsToShow
	 *            the eventsToShow to set
	 */
	public void setEventsToShow(List<EventVo> eventsToShow) {
		this.eventsToShow = eventsToShow;
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
	 * @return the usersToShow
	 */
	public List<UserVo> getUsersToShow() {
		return usersToShow;
	}

	/**
	 * @param usersToShow
	 *            the usersToShow to set
	 */
	public void setUsersToShow(List<UserVo> usersToShow) {
		this.usersToShow = usersToShow;
	}

	/**
	 * @return the selectedEvent
	 */
	public EventVo getSelectedEvent() {
		return selectedEvent;
	}

	/**
	 * @param selectedEvent the selectedEvent to set
	 */
	public void setSelectedEvent(EventVo selectedEvent) {
		this.selectedEvent = selectedEvent;
	}
}
