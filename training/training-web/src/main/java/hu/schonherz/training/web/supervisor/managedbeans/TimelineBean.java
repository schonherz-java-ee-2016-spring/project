package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.List;

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
	
	@PostConstruct
	public void init() {
		try {
			events = eventService.findAllEvent();
			
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

}
