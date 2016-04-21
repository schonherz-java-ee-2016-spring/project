package hu.schonherz.training.supervisor.web.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import hu.schonherz.training.supervisor.service.HomeworkService;
import hu.schonherz.training.supervisor.vo.HomeworkVo;

@ManagedBean(name = "homeworksBean")
@SessionScoped
public class HomeworksBean implements Serializable {
	private static final long serialVersionUID = -4351328894295907018L;

	@EJB
	private HomeworkService homeworkService;


	private List<HomeworkVo> homeworks;
	
	private HomeworkVo selected;
	
	


	@PostConstruct
	public void init() {
		homeworks = homeworkService.getAllHomeworks();
		if(homeworks == null)
			homeworks = new ArrayList<>();
		selected = new HomeworkVo();
	}
	
	


	public List<HomeworkVo> getHomeworks() {
		return homeworks;
	}



	public void setHomeworks(List<HomeworkVo> homeworks) {
		this.homeworks = homeworks;
	}

	public void delete(){
		homeworkService.deleteHomework(selected.getId());
	}
	public void delete(Long id) {
		homeworkService.deleteHomework(id);
	}

	public List<HomeworkVo> getAllHomeworks() {
		return homeworkService.getAllHomeworks();
	}

	public List<HomeworkVo> getHomeworksBetween(Date from, Date to) {
		return homeworkService.getHomeworksBetween(from, to);
	}



	public Long getId() {
		return selected.getId();
	}



	public void setId(Long id) {
		selected.setId(id);
	}



	public String getTitle() {
		return selected.getTitle();
	}



	public void setTitle(String title) {
		selected.setTitle(title);
	}



	public Date getDeadline() {
		return selected.getDeadline();
	}



	public void setDeadline(Date deadline) {
		selected.setDeadline(deadline);
	}



	public Integer getMaximumScore() {
		return selected.getMaximumScore();
	}



	public void setMaximumScore(Integer maximumScore) {
		selected.setMaximumScore(maximumScore);
	}



	public String getDescription() {
		return selected.getDescription();
	}



	public void setDescription(String description) {
		selected.setDescription(description);
	}



	public HomeworkVo getSelected() {
		return selected;
	}



	public void setSelected(HomeworkVo selected) {
		this.selected = selected;
	}


}
