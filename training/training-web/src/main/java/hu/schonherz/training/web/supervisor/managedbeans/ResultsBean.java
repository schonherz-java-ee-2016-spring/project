package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hu.schonherz.training.service.admin.UserGroupService;
import hu.schonherz.training.service.admin.vo.UserGroupVo;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.vo.ExamResultVo;
import hu.schonherz.training.service.supervisor.vo.HomeworkResultVo;
import hu.schonherz.training.service.supervisor.vo.LessonsVo;

@ManagedBean(name = "resultsBean")
@ViewScoped
public class ResultsBean implements Serializable {

	@EJB
	private UserGroupService userGroupService;

	private List<UserGroupVo> userGroups = new ArrayList<>();

	private List<UserVo> users = new ArrayList<>();
	private static String[] names = { "Ölveti József", "Bohán Márk", "Kovács Szabolcs", "Naményi János",
			"Iványi-Nagy Gábor", "Fekete Attila", "Erdei Krisztián", "Preznyák László", "Magyari Norbert",
			"Bertalan Ádám" };

	private List<LessonsVo> lessons = new ArrayList<>();
	private static String[] lessonNames = { "Verzió kezelés", "Fejesztői eszközök", "Java alapok",
			"Objektum orientált design", "Maven", "Web Előismeretek", "Servlet API", "SQL", "JDBC",
			"Multitier architecture", "Spring", "Security", "JPA", "JEE Alapismeretek", "JSF", "EJB", "Webservice",
			"Fejlesztési módszertanok", "Összesen" };

	private List<HomeworkResultVo> homeworkResults = new ArrayList<>();
	private static Integer[] homeworkResultPoints = { 8, 10, 9, 7, 5, 4, 7, 10, 9, 10, 6, 4, 8, 9, 10, 8, 9, 10, 132 };
	private List<ExamResultVo> examResults = new ArrayList<>();
	private static Integer[] examResultPoints = { 9, 8, 7, 9, 8, 9, 8, 9, 8, 9, 8, 10, 9, 8, 9, 10, 7, 4, 143 };

	@PostConstruct
	public void init() {

		// Lessons & Results
		for (int i = 0; i < 18 + 1; i++) {
			lessons.add(new LessonsVo());
			homeworkResults.add(new HomeworkResultVo());
			examResults.add(new ExamResultVo());
		}
		int k = 0;
		for (LessonsVo lesson : lessons) {
			lesson.setLessonName(lessonNames[k]);
			k++;
		}
		k = 0;
		for (HomeworkResultVo homeworkResult : homeworkResults) {
			homeworkResult.setScore(homeworkResultPoints[k]);
			k++;
		}
		k = 0;
		for (ExamResultVo examResult : examResults) {
			examResult.setScore(examResultPoints[k]);
			examResult.setMaxScore(10);
			k++;
		}

		// UserGroups
		try {
			userGroups = userGroupService.getUserGroups();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// userGroups.add(new UserGroupVo());
		// userGroups.add(new UserGroupVo());
		// for (UserGroupVo userGroup : userGroups) {
		// userGroup.setGroupName("Schonherz Java EE Training 2016");
		// }

		// Users
		for (int i = 0; i < 10; i++) {
			users.add(new UserVo());
		}
		k = 0;
		for (UserVo userVo : users) {
			userVo.setFullName(names[k]);
			k++;
		}

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResultsBean() {
		// TODO Auto-generated constructor stub
	}

	public List<UserVo> getUsers() {
		return users;
	}

	public void setUsers(List<UserVo> users) {
		this.users = users;
	}

	public List<UserGroupVo> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<UserGroupVo> userGroups) {
		this.userGroups = userGroups;
	}

	public List<LessonsVo> getLessons() {
		return lessons;
	}

	public void setLessons(List<LessonsVo> lessons) {
		this.lessons = lessons;
	}

	public void setHomeworkResults(List<HomeworkResultVo> homeworkResults) {
		this.homeworkResults = homeworkResults;
	}

	public void setExamResults(List<ExamResultVo> examResults) {
		this.examResults = examResults;
	}

	public List<HomeworkResultVo> getHomeworkResults() {
		return homeworkResults;
	}

	public List<ExamResultVo> getExamResults() {
		return examResults;
	}

}
