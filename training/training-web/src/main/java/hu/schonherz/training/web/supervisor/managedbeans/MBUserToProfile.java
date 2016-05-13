package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hu.schonherz.training.service.admin.ThemeService;
import hu.schonherz.training.service.admin.TrainingService;
import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.ThemeVo;
import hu.schonherz.training.service.admin.vo.TrainingVo;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.ExamResultService;
import hu.schonherz.training.service.supervisor.HomeworkResultService;
import hu.schonherz.training.service.supervisor.UserDetailsService;
import hu.schonherz.training.service.supervisor.vo.ExamResultVo;
import hu.schonherz.training.service.supervisor.vo.HomeworkResultVo;
import hu.schonherz.training.service.supervisor.vo.UserDetailsVo;
import hu.schonherz.training.web.supervisor.accessories.UserResults;

@ManagedBean(name = "profileBean")
@ViewScoped
public class MBUserToProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserDetailsVo userDetailsVo;

	private UserResults userResults;

	private UserVo user;
	private Integer examSum;
	private Integer homeworkSum;

	private List<ThemeVo> themes;

	@EJB
	private UserService userService;

	@EJB
	private UserDetailsService userDetailsService;

	@EJB
	private ExamResultService examResultService;

	@EJB
	private HomeworkResultService homeworkResultService;

	@EJB
	private ThemeService themeService;

	@EJB
	private TrainingService trainingService;

	@PostConstruct
	public void init() {
		examSum = new Integer(0);
		homeworkSum = new Integer(0);
		userResults = new UserResults();
		userDetailsVo = new UserDetailsVo();
		String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		try {
			user = userService.findUserByName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<TrainingVo> trainingVos = trainingService.getAllTrainings();
		themes = themeService.findAllTheme();
		try {
			if (userDetailsService.getUserDetailsByUser(user) == null) {
				userDetailsVo.setUser(user);
				try {
					userDetailsService.add(userDetailsVo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				userDetailsVo = userDetailsService.getUserDetailsByUser(user);
			}
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		// try {
		// userDetailsVo = userDetailsService.getUserDetailsByUser(user);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		userResults.setUser(user);
		List<ExamResultVo> examResults = new ArrayList<>();
		try {
			examResults = examResultService.getExamResultByUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<HomeworkResultVo> homeworkResults = homeworkResultService.getHomeworkResultsByUser(user);
		userResults.setExamResults(examResults);
		userResults.setHomeworkResults(homeworkResults);
		for (ExamResultVo examResultVo : examResults) {
			examSum += examResultVo.getPoints();
		}
		for (HomeworkResultVo homeworkResultVo : homeworkResults) {
			homeworkSum += homeworkResultVo.getScore();
		}
	}

	public void changeData() {
		userService.updateUser(user);
		try {
			userDetailsService.modifyUserDetails(user.getId(), userDetailsVo.getPhoneNumber(),
					userDetailsVo.getAddress(), userDetailsVo.getPlaceOfBirth(), new Date(),
					userDetailsVo.getNationality());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserDetailsVo getUserDetailsVo() {
		return userDetailsVo;
	}

	public void setUserDetailsVo(UserDetailsVo userDetailsVo) {
		this.userDetailsVo = userDetailsVo;
	}

	public UserResults getUserResults() {
		return userResults;
	}

	public void setUserResults(UserResults userResults) {
		this.userResults = userResults;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public List<ThemeVo> getThemes() {
		return themes;
	}

	public void setThemes(List<ThemeVo> themes) {
		this.themes = themes;
	}

	public Integer getExamSum() {
		return examSum;
	}

	public void setExamSum(Integer examSum) {
		this.examSum = examSum;
	}

	public Integer getHomeworkSum() {
		return homeworkSum;
	}

	public void setHomeworkSum(Integer homeworkSum) {
		this.homeworkSum = homeworkSum;
	}

}
