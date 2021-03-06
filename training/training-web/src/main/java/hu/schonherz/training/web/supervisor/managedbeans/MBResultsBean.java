package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hu.schonherz.training.service.admin.ThemeService;
import hu.schonherz.training.service.admin.TrainingService;
import hu.schonherz.training.service.admin.UserGroupService;
import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.TrainingVo;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.ExamResultService;
import hu.schonherz.training.service.supervisor.HomeworkResultService;
import hu.schonherz.training.service.supervisor.vo.ExamResultVo;
import hu.schonherz.training.service.supervisor.vo.HomeworkResultVo;
import hu.schonherz.training.web.supervisor.accessories.Course;
import hu.schonherz.training.web.supervisor.accessories.UserResults;
import hu.schonherz.training.web.supervisor.accessories.UserResultsComparator;

@ManagedBean(name = "resultsBean", eager = true)
@ViewScoped
public class MBResultsBean implements Serializable {

	@EJB
	private UserGroupService userGroupService;

	@EJB
	private TrainingService trainingService;

	@EJB
	private ThemeService themeService;

	@EJB
	private UserService userService;

	@EJB
	private HomeworkResultService homeworkResultService;

	@EJB
	private ExamResultService examResultService;

	// List of Courses
	private List<Course> courses = new ArrayList<>();

	private UserVo loggedInUser;

	// init method for resultsBean
	@PostConstruct
	public void init() {
		String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		try {
			loggedInUser = userService.findUserByName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<TrainingVo> trainings = new ArrayList<TrainingVo>();
		try {
			trainings = trainingService.getAllTrainings();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < trainings.size(); i++) {
			courses.add(new Course());
		}
		Iterator<TrainingVo> trainingIterator = trainings.iterator();
		for (Course course : courses) {
			if (trainingIterator.hasNext()) {
				course.setTraining(trainingIterator.next());
			}
			List<UserResults> userResultsList = new ArrayList<>();
			List<UserVo> userVos = course.getTraining().getUsers();
			for (int i = 0; i < userVos.size(); i++) {
				userResultsList.add(new UserResults());
			}
			Iterator<UserVo> userVoIterator = userVos.iterator();
			for (UserResults userResults : userResultsList) {
				if (userVoIterator.hasNext()) {
					userResults.setUser(userVoIterator.next());
				}
			}
			course.setUserResults(userResultsList);
			course.setThemes(course.getTraining().getThemes());
		}

		/// --------Works
		// Filling the Results

		for (Course course : courses) {
			for (UserResults userResult : course.getUserResults()) {
				List<ExamResultVo> examResults = new ArrayList<>();
				List<HomeworkResultVo> homeworkResults = new ArrayList<>();
				homeworkResults = homeworkResultService.getHomeworkResultsByUser(userResult.getUser());
				try {
					examResults = examResultService.getExamResultByUser(userResult.getUser());
				} catch (Exception e) {
					e.printStackTrace();
				}
				Integer examSum = new Integer(0);
				Integer homeworkSum = new Integer(0);
				for (ExamResultVo examResultVo : examResults) {
					examSum += examResultVo.getPoints();
				}
				for (HomeworkResultVo homeworkResultVo : homeworkResults) {
					homeworkSum += homeworkResultVo.getScore();
				}

				userResult.setExamResults(examResults);
				userResult.setHomeworkResults(homeworkResults);
				userResult.setExamSum(examSum);
				userResult.setHomeworkSum(homeworkSum);
			}
			Collections.sort(course.getUserResults(), new UserResultsComparator());
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MBResultsBean() {
		super();
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public UserVo getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(UserVo loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

}
