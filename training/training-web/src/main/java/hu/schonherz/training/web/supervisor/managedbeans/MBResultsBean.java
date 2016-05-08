package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hu.schonherz.training.service.admin.ThemeService;
import hu.schonherz.training.service.admin.TrainingService;
import hu.schonherz.training.service.admin.UserGroupService;
import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.TrainingVo;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.HomeworkResultService;
import hu.schonherz.training.service.supervisor.vo.ExamResultVo;
import hu.schonherz.training.service.supervisor.vo.HomeworkResultVo;
import hu.schonherz.training.web.supervisor.accessories.Course;
import hu.schonherz.training.web.supervisor.accessories.UserResults;

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

	// List of Courses
	private List<Course> courses = new ArrayList<>();

	// init method for resultsBean
	@PostConstruct
	public void init() {

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

		Random rand = new Random();
		for (Course course : courses) {
			for (UserResults userResult : course.getUserResults()) {
				List<ExamResultVo> examResults = new ArrayList<>();
				List<HomeworkResultVo> homeworkResults = new ArrayList<>();
				Integer examSum = new Integer(0);
				Integer homeworkSum = new Integer(0);
				for (int i = 0; i < course.getThemes().size(); i++) {
					ExamResultVo examResult = new ExamResultVo();
					examResult.setScore(rand.nextInt(10));
					examSum += examResult.getScore();
					examResults.add(examResult);
					HomeworkResultVo homeworkResult = new HomeworkResultVo();
					homeworkResult.setScore(rand.nextInt(10));
					homeworkSum += homeworkResult.getScore();
					homeworkResults.add(homeworkResult);
				}

				userResult.setExamResults(examResults);
				userResult.setHomeworkResults(homeworkResults);
				userResult.setExamSum(examSum);
				userResult.setHomeworkSum(homeworkSum);
			}
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

}
