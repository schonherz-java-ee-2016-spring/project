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

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

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

@ManagedBean(name = "statisticsBean", eager = true)
@ViewScoped
public class StatisticsBean implements Serializable {

	@EJB
	private UserGroupService userGroupService;

	@EJB
	private UserService userService;

	@EJB
	private HomeworkResultService homeworkResultService;

	// List of Courses
	private List<Course> courses = new ArrayList<>();

	private Long trainingId;

	private int maxExam;
	private int maxHomework;
	
	private Course training;

	private Map<String, Long> trainingList;

	private LineChartModel examModel;
	private LineChartModel homeworkModel;

	public void onTrainingIdChange() {
		if (trainingId != null) {
			training = courses.stream().filter(a -> {
				return a.getUserGroup().getId() == trainingId;
			}).findFirst().get();
			createLineModel();
		}
	}

	private LineChartModel initExamModel() {
		LineChartModel model = new LineChartModel();

		int size = training.getUserResults().size();

		LineChartSeries series[] = new LineChartSeries[size];
		for (int i = 0; i < size; ++i) {
			series[i] = new LineChartSeries();
			series[i].setLabel(training.getUserResults().get(i).getUser().getFullName());

			
			List<ExamResultVo> examResultVos = training.getUserResults().get(i).getExamResults();

			for (ExamResultVo examResultVo : examResultVos) {
				series[i].set(examResultVo.getExam().getTitle(), examResultVo.getScore());
				if(examResultVo.getScore() > maxExam){
					maxExam = examResultVo.getScore();
				}
			}
			
			model.addSeries(series[i]);

		}
		return model;
	}
	private LineChartModel initHomeworkModel() {
		LineChartModel model = new LineChartModel();

		int size = training.getUserResults().size();

		LineChartSeries series[] = new LineChartSeries[size];
		for (int i = 0; i < size; ++i) {

			series[i] = new LineChartSeries();
			series[i].setLabel(training.getUserResults().get(i).getUser().getFullName());

			
			List<HomeworkResultVo> homeworkResultVos = training.getUserResults().get(i).getHomeworkResults();

			for (HomeworkResultVo homeworkResultVo : homeworkResultVos) {
				series[i].set(homeworkResultVo.getHomework().getName(), homeworkResultVo.getScore());
				if(homeworkResultVo.getScore() > maxHomework)
					maxHomework = homeworkResultVo.getScore();
			}
			
			model.addSeries(series[i]);

		}
		return model;
	}
	private void createLineModel() {
		maxExam = 0;
		examModel = initExamModel();
		examModel.setTitle("");
		examModel.setLegendPosition("e");
		examModel.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		Axis yAxis = examModel.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(maxExam);
				
		maxHomework = 0;
		homeworkModel = initHomeworkModel();
		homeworkModel.setTitle("");
		homeworkModel.setLegendPosition("e");
		homeworkModel.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		yAxis = homeworkModel.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(maxHomework);

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
				"Összesen" };
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
				"Fekete Attila", "Erdei Krisztián", "Preznyák László", "Magyari Norbert", "Bertalan Ádám" };

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
					examResult.setScore(rand.nextInt(10));
					ExamVo exam = new ExamVo();
					exam.setTitle(lessonNames[i]);
					examResult.setExam(exam);
					examResults.add(examResult);
					HomeworkResultVo homeworkResult = new HomeworkResultVo();
					homeworkResult.setScore(rand.nextInt(10));
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

	public LineChartModel getExamModel() {
		return examModel;
	}

	public LineChartModel getHomeworkModel() {
		return homeworkModel;
	}

}
