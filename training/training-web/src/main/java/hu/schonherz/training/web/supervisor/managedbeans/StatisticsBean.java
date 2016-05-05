package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hu.schonherz.training.service.admin.UserGroupService;
import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.EventVo;
import hu.schonherz.training.service.admin.vo.UserGroupVo;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.vo.ExamVo;
import hu.schonherz.training.service.supervisor.HomeworkResultService;
import hu.schonherz.training.service.supervisor.vo.ExamResultVo;
import hu.schonherz.training.service.supervisor.vo.HomeworkResultVo;
import hu.schonherz.training.service.supervisor.vo.LessonVo;
import hu.schonherz.training.web.supervisor.accessories.Course;
import hu.schonherz.training.web.supervisor.accessories.UserResults;

@ManagedBean(name = "statisticsBean")
@ViewScoped
public class StatisticsBean implements Serializable {

	@EJB
	private UserGroupService userGroupService;

	@EJB
	private UserService userService;

	@EJB
	private HomeworkResultService homeworkResultService;

	private List<Course> courses = new ArrayList<>();

	private Long trainingId;

	private Course training;

	private Map<String, Long> trainingList;

	public void onTrainingIdChange() {
		if (trainingId != null) {
			training = courses.stream().filter(a -> {
				return a.getUserGroup().getId() == trainingId;
			}).findFirst().get();
		}
	}

	public String columnsToJsArray() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("['tests', ");
		int k = 0;

		if (training.getUserResults() != null)
			for (UserResults userResults : training.getUserResults()) {
				stringBuilder.append("'");
				stringBuilder.append(userResults.getUser().getFullName());
				if (++k < training.getUserResults().get(0).getExamResults().size())
					stringBuilder.append("', ");
				else
					stringBuilder.append("'");
			}

		stringBuilder.append("]");
		return stringBuilder.toString();
	}

	public String testDataForChart() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[");

		for (int k = 0; k < training.getUserResults().get(0).getExamResults().size(); ++k) {

			stringBuilder.append("['");
			stringBuilder.append(training.getUserResults().get(0).getExamResults().get(k).getExam().getTitle());
			stringBuilder.append("'");

			for (UserResults userResults : training.getUserResults()) {

				stringBuilder.append(", ");
				stringBuilder.append(userResults.getExamResults().get(k).getScore());
			}
			if (++k < training.getUserResults().get(0).getExamResults().size())
				stringBuilder.append("], ");
			else
				stringBuilder.append("]");

		}

		stringBuilder.append("]");
		return stringBuilder.toString();
	}

	@PostConstruct
	public void init() {

		// Filling the Courses with userGroups
		List<UserGroupVo> userGroups = new ArrayList<UserGroupVo>();
		try {
			userGroups = userGroupService.getUserGroups();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < userGroups.size(); i++) {
			courses.add(new Course());
		}
		Iterator<UserGroupVo> userGroupIterator = userGroups.iterator();
		for (Course course : courses) {
			if (userGroupIterator.hasNext()) {
				course.setUserGroup(userGroupIterator.next());
			}
		}

		// Filling the Courses with Lessons
		List<LessonVo> lessons = new ArrayList<>();
		String[] lessonNames = { "Verzió kezelés", "Fejesztői eszközök", "Java alapok", "Objektum orientált design",
				"Maven", "Web Előismeretek", "Servlet API", "SQL", "JDBC", "Multitier architecture", "Spring",
				"Security", "JPA", "JEE Alapismeretek", "JSF", "EJB", "Webservice", "Fejlesztési módszertanok",
				"Átlag" };
		for (int i = 0; i < lessonNames.length; i++) {
			lessons.add(new LessonVo());
		}
		int k = 0;
		for (LessonVo lesson : lessons) {
			lesson.setLessonName(lessonNames[k]);
			k++;
		}

		// Filling the Courses with UserResults
		List<UserResults> userResults = new ArrayList<>();
		List<UserVo> users = new ArrayList<>();
		String[] names = { "Ölveti József", "Bohán Márk", "Kovács Szabolcs", "Naményi János", "Iványi-Nagy Gábor",
				"Fekete Attila", "Erdei Krisztián", "Preznyák László", "Magyari Norbert", "Bertalan Ádám", "Átlag" };

		for (int i = 0; i < names.length; i++) {
			userResults.add(new UserResults());
			users.add(new UserVo());
		}
		k = 0;
		for (UserVo user : users) {
			user.setFullName(names[k]);
			k++;
		}
		k = 0;
		Iterator<UserVo> userIterator = users.iterator();
		for (UserResults userResult : userResults) {
			if (userIterator.hasNext()) {
				userResult.setUser(userIterator.next());
			}
		}
		for (Course course : courses) {
			course.setLessons(lessons);
			course.setUserResults(userResults);
		}

		/// --------Works
		// Filling the Results

		Random rand = new Random();
		for (Course course : courses) {
			for (UserResults userResult : course.getUserResults()) {
				List<ExamResultVo> examResults = new ArrayList<>();
				List<HomeworkResultVo> homeworkResults = new ArrayList<>();
				for (int i = 0; i < lessons.size(); i++) {
					ExamResultVo examResult = new ExamResultVo();
					examResult.setScore(rand.nextInt(11));
					ExamVo exam = new ExamVo();
					exam.setTitle(lessonNames[i]);
					examResult.setExam(exam);
					examResults.add(examResult);
					HomeworkResultVo homeworkResult = new HomeworkResultVo();
					homeworkResult.setScore(rand.nextInt(11));
					EventVo homework = new EventVo();
					homework.setName(lessonNames[i]);
					homeworkResult.setHomework(homework);
					homeworkResults.add(homeworkResult);
				}

				userResult.setExamResults(examResults);
				userResult.setHomeworkResults(homeworkResults);
			}
		}

		trainingList = new HashMap<>();
		for (Course course : courses) {
			trainingList.put(course.getUserGroup().getName(), course.getUserGroup().getId());
		}

		trainingId = courses.get(0).getUserGroup().getId();
		onTrainingIdChange();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatisticsBean() {
		super();
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Map<String, Long> getTrainingList() {
		return trainingList;
	}

	public Long getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(Long trainingId) {
		this.trainingId = trainingId;
	}
	//
	// public LineChartModel getExamModel() {
	// return examModel;
	// }
	//
	// public LineChartModel getHomeworkModel() {
	// return homeworkModel;
	// }

}
