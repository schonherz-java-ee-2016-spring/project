package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hu.schonherz.training.service.admin.vo.EventVo;
import hu.schonherz.training.service.admin.vo.UserVo;

@ManagedBean(name="writeFeedbackBean")
@ViewScoped
public class WriteFeedbackBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2972060697014376693L;
	
	@ManagedProperty("#{eventsBean.allEvent}")
	List<EventVo> events;
	
	
	List<EventVo> eventsToShow = new ArrayList<>();
	
	@ManagedProperty("#{usersBean.users}")
	List<UserVo> users;
	
	UserVo loggedInUser = new UserVo();
	
	@PostConstruct
	public void init() {
		String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		for (UserVo user : users) {
			if (user.getUserName().equals(username)) {
				loggedInUser = user;
			}
		}
		for (EventVo event : events) {
			Collection<UserVo> usrVo = event.getUsers();
			for (UserVo uVo : usrVo) {
				if (uVo.equals(loggedInUser)) {
					eventsToShow.add(event);
				}
			}
		}
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
	 * @param eventsToShow the eventsToShow to set
	 */
	public void setEventsToShow(List<EventVo> eventsToShow) {
		this.eventsToShow = eventsToShow;
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
}
