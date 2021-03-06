package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hu.schonherz.training.service.admin.EventService;
import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.EventVo;

@ManagedBean(name = "timelineBean")
@ViewScoped
public class TimelineBean implements Serializable {

	private static final long serialVersionUID = 203865612177768480L;

	@EJB
	private EventService eventService;
	@EJB
	private UserService userService;

	List<EventVo> events;

	private long nextId;

	@PostConstruct
	public void init() {
		try {
			Long id = userService.findUserByName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser())
					.getId();
			events = eventService.findEventsByUserOrderedByDate(id);
			Date date = new GregorianCalendar().getTime();

			try {
				nextId = events.stream().filter(e -> {
					return e.getDate().after(date);
				}).findFirst().get().getId();
			} catch (NoSuchElementException e) {
				nextId = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			events = new ArrayList<>();
		}
	}

	public List<EventVo> getEvents() {
		return events;
	}

	public void setEvents(List<EventVo> events) {
		this.events = events;
	}

	public long getNextId() {
		return nextId;
	}

	public void setNextId(long nextId) {
		this.nextId = nextId;
	}

}
