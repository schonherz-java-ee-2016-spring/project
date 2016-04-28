package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
	
	private void mock() {
		events = new ArrayList<EventVo>();

		Calendar calendar;


		// homework
		EventVo event1 = new EventVo();
		event1.setId((long) 0);
		event1.setType("Homework");
		event1.setPlace("home");
		event1.setDescription("Lorem ipsum dolor sit amet. ");
		event1.setName("Homework test 1");
		calendar = new GregorianCalendar();
		event1.setDate(calendar.getTime());
		events.add(event1);

		EventVo event2 = new EventVo();
		event2.setId((long) 1);
		event2.setType("Homework");
		event2.setPlace("home");
		event2.setDescription("Lorem ipsum dolor sit amet. ");
		event2.setName("Homework test 2");
		calendar.add(Calendar.MONTH, 1);
		event2.setDate(calendar.getTime());
		events.add(event2);

		EventVo event3 = new EventVo();
		event3.setId((long) 2);
		event3.setType("Homework");
		event3.setPlace("home");
		event3.setDescription("Lorem ipsum dolor sit amet. ");
		event3.setName("Homework test 3");
		calendar.add(Calendar.MONTH, -3);
		event3.setDate(calendar.getTime());
		events.add(event3);

		// test
		EventVo event4 = new EventVo();
		event4.setId((long) 3);
		event4.setType("Test");
		event4.setPlace("office");
		event4.setDescription("Lorem ipsum dolor sit amet. ");
		event4.setName("Test test 1");
		calendar = new GregorianCalendar();
		calendar.add(Calendar.YEAR, 1);
		event4.setDate(calendar.getTime());
		events.add(event4);

		EventVo event5 = new EventVo();
		event5.setId((long) 4);
		event5.setType("Test");
		event5.setPlace("office");
		event5.setDescription("Lorem ipsum dolor sit amet. ");
		event5.setName("Test test 2");
		calendar.add(Calendar.MONTH, 2);
		event5.setDate(calendar.getTime());
		events.add(event5);

		EventVo event6 = new EventVo();
		event6.setId((long) 5);
		event6.setType("Test");
		event6.setPlace("office");
		event6.setDescription("Lorem ipsum dolor sit amet. ");
		event6.setName("Test test 3");
		calendar.add(Calendar.MONTH, -3);
		event6.setDate(calendar.getTime());
		events.add(event6);

		// interview
		EventVo event7 = new EventVo();
		event7.setId((long) 6);
		event7.setType("Interview");
		event7.setPlace("company");
		event7.setDescription("Lorem ipsum dolor sit amet. ");
		event7.setName("Interview test 1");
		calendar = new GregorianCalendar();
		calendar.add(Calendar.MONTH, 1);
		event7.setDate(calendar.getTime());
		events.add(event7);

		EventVo event8 = new EventVo();
		event8.setId((long) 7);
		event8.setType("Interview");
		event8.setPlace("company");
		event8.setDescription("Lorem ipsum dolor sit amet. ");
		event8.setName("Interview test 2");
		calendar.add(Calendar.MONTH, -1);
		event8.setDate(calendar.getTime());
		events.add(event8);

		EventVo event9 = new EventVo();
		event9.setId((long) 8);
		event9.setType("Interview");
		event9.setPlace("company");
		event9.setDescription("Lorem ipsum dolor sit amet. ");
		event9.setName("Interview test 3");
		calendar.add(Calendar.MONTH, 5);
		event9.setDate(calendar.getTime());
		events.add(event9);
		
		events = events.stream().sorted((e1, e2) -> { return e1.getDate().compareTo(e2.getDate());}).collect(Collectors.toList());

	}

	@PostConstruct
	public void init() {
		try {
			// events = eventService.findAllEvent();
			mock();
			
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