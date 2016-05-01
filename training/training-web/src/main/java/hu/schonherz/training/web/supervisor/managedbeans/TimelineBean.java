package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hu.schonherz.training.service.admin.EventService;
import hu.schonherz.training.service.admin.vo.EventVo;

@ManagedBean(name = "timelineBean")
@ViewScoped
public class TimelineBean implements Serializable {

	private static final long serialVersionUID = 203865612177768480L;

	@EJB
	private EventService eventService;

	List<EventVo> events;

	private long nextId;
	

	@PostConstruct
	public void init() {
		try {
			events = eventService.findAllEvent();
			events = events.stream().sorted((e1, e2) -> { return e1.getDate().compareTo(e2.getDate());}).collect(Collectors.toList());
			Date date = new GregorianCalendar().getTime();
			nextId = events.stream()
					.filter(e -> { return e.getDate().after(date); })
					.min((e1, e2) -> {return e1.getDate().compareTo(e2.getDate());})
					.get().getId();
		} catch (Exception e) {
			e.printStackTrace();
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
