package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import hu.schonherz.training.service.admin.TrainingService;
import hu.schonherz.training.service.admin.vo.TrainingVo;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.ExamResultService;
import hu.schonherz.training.service.supervisor.HomeworkResultService;
import hu.schonherz.training.service.supervisor.vo.ExamResultVo;
import hu.schonherz.training.service.supervisor.vo.HomeworkResultVo;
import hu.schonherz.training.web.supervisor.accessories.Course;
import hu.schonherz.training.web.supervisor.accessories.UserResults;

@ManagedBean(name = "statisticsBean")
@ViewScoped
public class StatisticsBean implements Serializable {


	@EJB
	private TrainingService trainingService;

	@EJB
	private HomeworkResultService homeworkResultService;

	@EJB
	private ExamResultService examResultService;

	@ManagedProperty("#{out}")
	private ResourceBundle bundle;

	private List<Course> courses = new ArrayList<>();

	private Long trainingId;

	private Course training;

	private HashMap<String, Long> trainingList;

	private LineChartModel testCategoryModel;
	private LineChartModel homeworkCategoryModel;

	public void onTrainingIdChange() {
		if (trainingId != null) {
			for (Course course : courses) {
				if (course.getTraining().getId().equals(trainingId)) {
					training = course;
					break;
				}
			}
			if (training != null) {
				initTestCategoryModel();
				initHomeworkCategoryModel();
			}
		}
	}

	private void fillTestCategoryModel() {

		if (training.getUserResults().isEmpty())
			return;
		Integer sum[] = new Integer[training.getUserResults().get(0).getExamResults().size()];
		for (Integer i = 0; i < sum.length; ++i) {
			sum[i] = new Integer(0);
		}

		for (UserResults user : training.getUserResults()) {
			LineChartSeries userSerie = new LineChartSeries();
			userSerie.setLabel(user.getUser().getFullName());
			Integer k = -1;
			for (ExamResultVo examResultVo : user.getExamResults()) {
				userSerie.set(examResultVo.getExam().getTitle(), examResultVo.getPoints());
				sum[++k] += examResultVo.getPoints();
			}
			testCategoryModel.addSeries(userSerie);
		}

		// average
		Integer k = -1;
		Integer size = training.getUserResults().size();
		LineChartSeries avgSerie = new LineChartSeries();
		avgSerie.setLabel(bundle.getString("statisticsaverage"));
		for (ExamResultVo examResultVo : training.getUserResults().get(0).getExamResults()) {
			avgSerie.set(examResultVo.getExam().getTitle(), (double) sum[++k] / size);
		}
		testCategoryModel.addSeries(avgSerie);
	}

	private void initTestCategoryModel() {
		testCategoryModel = new LineChartModel();
		fillTestCategoryModel();

		testCategoryModel.setTitle(bundle.getString("statisticsexam"));
		testCategoryModel.setAnimate(true);
		testCategoryModel.setLegendPlacement(LegendPlacement.OUTSIDE);
		testCategoryModel.setLegendPosition("e");
		testCategoryModel.setExtender("extFn");
		testCategoryModel.setShowDatatip(true);
		testCategoryModel.setShowPointLabels(false);
		testCategoryModel.getAxes().put(AxisType.X, new CategoryAxis(bundle.getString("statisticstopics")));
		Axis xAxis = testCategoryModel.getAxis(AxisType.X);
		xAxis.setTickAngle(-30);
		Axis yAxis = testCategoryModel.getAxis(AxisType.Y);
		yAxis.setLabel(bundle.getString("statisticsscore"));
		yAxis.setMin(0);
		yAxis.setTickFormat("%d");
	}

	private void fillHomeworkCategoryModel() {

		if (training.getUserResults().isEmpty())
			return;
		Integer sum[] = new Integer[training.getUserResults().get(0).getHomeworkResults().size()];
		for (Integer i = 0; i < sum.length; ++i) {
			sum[i] = new Integer(0);
		}

		for (UserResults user : training.getUserResults()) {
			LineChartSeries userSerie = new LineChartSeries();
			userSerie.setLabel(user.getUser().getFullName());
			Integer k = -1;
			for (HomeworkResultVo homeworkResultVo : user.getHomeworkResults()) {
				userSerie.set(homeworkResultVo.getHomework().getName(), homeworkResultVo.getScore());
				sum[++k] += homeworkResultVo.getScore();
			}
			homeworkCategoryModel.addSeries(userSerie);
		}

		// average
		Integer k = -1;
		Integer size = training.getUserResults().size();
		LineChartSeries avgSerie = new LineChartSeries();
		avgSerie.setLabel(bundle.getString("statisticsaverage"));
		for (HomeworkResultVo homeworkResultVo : training.getUserResults().get(0).getHomeworkResults()) {
			avgSerie.set(homeworkResultVo.getHomework().getName(), (double) sum[++k] / size);
		}
		homeworkCategoryModel.addSeries(avgSerie);
	}

	private void initHomeworkCategoryModel() {
		homeworkCategoryModel = new LineChartModel();

		fillHomeworkCategoryModel();
		homeworkCategoryModel.setTitle(bundle.getString("statisticshomework"));
		homeworkCategoryModel.setAnimate(true);
		homeworkCategoryModel.setLegendPlacement(LegendPlacement.OUTSIDE);
		homeworkCategoryModel.setLegendPosition("e");
		homeworkCategoryModel.setExtender("extFn");
		homeworkCategoryModel.setShowDatatip(true);
		homeworkCategoryModel.setShowPointLabels(false);
		homeworkCategoryModel.getAxes().put(AxisType.X, new CategoryAxis(bundle.getString("statisticstopics")));
		Axis xAxis = homeworkCategoryModel.getAxis(AxisType.X);
		xAxis.setTickAngle(-30);
		Axis yAxis = homeworkCategoryModel.getAxis(AxisType.Y);
		yAxis.setLabel(bundle.getString("statisticsscore"));
		yAxis.setMin(0);
		yAxis.setTickFormat("%d");
	}

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

				userResult.setExamResults(examResults);
				userResult.setHomeworkResults(homeworkResults);
			}
		}

		trainingList = new HashMap<>();
		for (Course course : courses) {
			trainingList.put(course.getTraining().getName(), course.getTraining().getId());
		}
		try {
			trainingId = courses.get(0).getTraining().getId();
			onTrainingIdChange();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
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

	public LineChartModel getTestCategoryModel() {
		return testCategoryModel;
	}

	public void setTestCategoryModel(LineChartModel testCategoryModel) {
		this.testCategoryModel = testCategoryModel;
	}

	public LineChartModel getHomeworkCategoryModel() {
		return homeworkCategoryModel;
	}

	public void setHomeworkCategoryModel(LineChartModel homeworkCategoryModel) {
		this.homeworkCategoryModel = homeworkCategoryModel;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

}
