package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hu.schonherz.training.service.supervisor.HomeworkService;
import hu.schonherz.training.service.supervisor.vo.HomeworkVo;

@ManagedBean(name = "calendarBean")
@ViewScoped
public class CalendarBean implements Serializable {

	private static final long serialVersionUID = 203865612177768480L;

	@EJB
	private HomeworkService homeworkService;

	private List<HomeworkVo> homeworks;

	@PostConstruct
	public void init() {
		try {
			homeworks = homeworkService.getAllHomeworks();
			// homeworks = new ArrayList<>();
			// HomeworkVo homework = new HomeworkVo();
			// homework.setDeadline(new GregorianCalendar().getTime());
			// homework.setId((long) 0);
			// homework.setDescription("asdf asd");
			// homework.setTitle("yolo");
			//
			// homeworks.add(homework);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<HomeworkVo> getHomeworks() {
		return homeworks;
	}

	public void setHomeworks(List<HomeworkVo> homeworks) {
		this.homeworks = homeworks;
	}

}
