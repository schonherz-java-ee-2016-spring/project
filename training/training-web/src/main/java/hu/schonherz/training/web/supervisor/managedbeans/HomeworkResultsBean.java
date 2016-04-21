package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hu.schonherz.training.service.supervisor.HomeworkResultService;
import hu.schonherz.training.service.supervisor.vo.HomeworkResultVo;
import hu.schonherz.training.service.supervisor.vo.HomeworkVo;
import hu.schonherz.training.service.admin.vo.UserVo;

@ManagedBean(name = "homeworksBean")
@ViewScoped
public class HomeworkResultsBean implements Serializable {
	private static final long serialVersionUID = -2619626826122817902L;
	
	@EJB
	private HomeworkResultService homeworkResultService;
	
	private List<HomeworkResultVo> homeworkResults;	
	
	private HomeworkResultVo selected;
	
	@PostConstruct
	public void init() {
		homeworkResults = homeworkResultService.getAllHomeworkResults();
		if(homeworkResults == null)
			homeworkResults = new ArrayList<>();
		selected = new HomeworkResultVo();
	}

	public HomeworkResultVo create(HomeworkResultVo homeworkResultVo) {
		return homeworkResultService.createHomeworkResult(homeworkResultVo);
	}

	public HomeworkResultVo create() {
		return homeworkResultService.createHomeworkResult(selected);
	}
	public HomeworkResultVo getById(Long id) {
		return homeworkResultService.getHomeworkResultById(id);
	}

	public List<HomeworkResultVo> getHomeworkResultsByUser(UserVo userVo) {
		return homeworkResultService.getHomeworkResultsByUser(userVo);
	}

	public List<HomeworkResultVo> getHomeworkResultsByHomework(HomeworkVo homeworkVo) {
		return homeworkResultService.getHomeworkResultsByHomework(homeworkVo);
	}

	public List<HomeworkResultVo> getAllHomeworkResults() {
		return homeworkResultService.getAllHomeworkResults();
	}

	public List<HomeworkResultVo> getHomeworkResultsBetween(Date from, Date to) {
		return homeworkResultService.getHomeworkResultsBetween(from, to);
	}

	public List<HomeworkResultVo> getHomeworkResultsByUsers(List<UserVo> userVos) {
		return homeworkResultService.getHomeworkResultsByUsers(userVos);
	}

	public Long getId() {
		return selected.getId();
	}

	public void setId(Long id) {
		selected.setId(id);
	}

	public UserVo getUser() {
		return selected.getUser();
	}

	public void setUser(UserVo user) {
		selected.setUser(user);
	}

	public HomeworkVo getHomework() {
		return selected.getHomework();
	}

	public void setHomework(HomeworkVo homework) {
		selected.setHomework(homework);
	}

	public Integer getScore() {
		return selected.getScore();
	}

	public void setScore(Integer score) {
		selected.setScore(score);
	}

	public String getComment() {
		return selected.getComment();
	}

	public void setComment(String comment) {
		selected.setComment(comment);
	}

	public HomeworkResultVo getSelected() {
		return selected;
	}

	public void setSelected(HomeworkResultVo selected) {
		this.selected = selected;
	}
	
	
	

}
