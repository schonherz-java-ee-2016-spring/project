package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hu.schonherz.training.service.admin.UserGroupService;
import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.UserGroupVo;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.HomeworkResultService;
import hu.schonherz.training.service.supervisor.vo.LessonVo;
import hu.schonherz.training.web.supervisor.accessories.Course;
import hu.schonherz.training.web.supervisor.accessories.UserResults;

@ManagedBean(name = "resultsBean", eager = true)
@ViewScoped
public class MBResultsBean implements Serializable {

	@EJB
	private UserGroupService userGroupService;

	@EJB
	private UserService userService;

	@EJB
	private HomeworkResultService homeworkResultService;

	// List of Courses
	private List<Course> courses = new ArrayList<>();

	// init method for resultsBean
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
