package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.FeedbackService;
import hu.schonherz.training.service.supervisor.vo.FeedbackVo;
import hu.schonherz.training.service.supervisor.vo.InterviewVo;

@ManagedBean(name = "feedbacksBean")
@ViewScoped
public class FeedbacksBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6239504593798844987L;

	@EJB
	FeedbackService feedbackService;
	List<FeedbackVo> feedbacks = new ArrayList<>();

	// @PostConstruct
	// public void init() {
	// try {
	// feedbacks = feedbackService.getAll();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	@PostConstruct
	public void init() {
		FeedbackVo feedback = new FeedbackVo();
		InterviewVo interview = new InterviewVo();
		UserVo user = new UserVo();

		List<InterviewVo> interviews = new ArrayList<>();
		List<UserVo> users = new ArrayList<>();

		String detailedFeedback;

		String dateToParse;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date inputDate = new Date();

		user.setId((long) 8000);
		user.setUserName("natila");
		user.setFullName("Nagy Attila");
		user.setEmail("na@example.com");
		user.setActive(true);
		users.add(user);

		user.setId((long) 8100);
		user.setUserName("lahe");
		user.setFullName("Lantos Henrietta");
		user.setEmail("lahe@example.com");
		user.setActive(true);
		users.add(user);

		interview.setCompany("Micronome");
		interview.setId((long) 8200);
		interview.setInterviewer(users.get(0));
		interview.setInterviewed(users.get(1));
		dateToParse = "2016-06-12";
		try {
			inputDate = format.parse(dateToParse);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		interview.setInterviewDate(inputDate);
		interviews.add(interview);

		feedback.setId((long) 8300);
		feedback.setInterview(interviews.get(0));
		feedback.setPublic(true);
		feedback.setScore(5);
		detailedFeedback = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed turpis gravida sapien volutpat vestibulum et et mi. Nam sit amet scelerisque felis. Morbi sodales mattis quam, eget euismod arcu dignissim vel. Donec posuere lacus ut sem euismod sagittis. Curabitur mollis sem eget metus bibendum consectetur. Vivamus luctus turpis sed iaculis auctor. Maecenas mattis nibh orci, quis convallis tellus maximus ac. Nam leo lorem, dignissim et dignissim ut, rhoncus eget nisl. Quisque iaculis iaculis ipsum in placerat. Aliquam erat volutpat.";
		feedback.setDetailedFeedback(detailedFeedback);
		feedback.setRated(users.get(1));
		feedback.setSender(users.get(0));
		dateToParse = "2016-06-13";
		try {
			inputDate = format.parse(dateToParse);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		feedback.setRecDate(inputDate);
		feedbacks.add(feedback);
		
		user.setId((long) 8001);
		user.setUserName("jantoth");
		user.setFullName("Tóth János");
		user.setEmail("tjan@example.com");
		user.setActive(true);
		users.add(user);

		user.setId((long) 8101);
		user.setUserName("kov111");
		user.setFullName("Kováts Péter");
		user.setEmail("peter.kovats@example.com");
		user.setActive(true);
		users.add(user);

		interview.setCompany("SSL Techngologies");
		interview.setId((long) 8201);
		interview.setInterviewer(users.get(2));
		interview.setInterviewed(users.get(3));
		dateToParse = "2016-06-15";
		try {
			inputDate = format.parse(dateToParse);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		interview.setInterviewDate(inputDate);
		interviews.add(interview);

		feedback.setId((long) 8301);
		feedback.setInterview(interviews.get(1));
		feedback.setPublic(true);
		feedback.setScore(4);
		detailedFeedback = "Mauris aliquet est ut sapien laoreet sollicitudin. Aliquam sed lectus imperdiet, tempor arcu at, interdum orci. Aliquam euismod posuere nibh, in iaculis mi. Morbi sit amet elit at arcu consectetur maximus et luctus sem. Donec tristique nulla elementum suscipit scelerisque. Sed ac neque lobortis, mollis felis ac, molestie justo. Donec mauris velit, rhoncus quis purus consequat, suscipit efficitur orci. Maecenas tincidunt ipsum felis, et gravida risus posuere sed. Mauris imperdiet nec neque vitae ullamcorper. Cras auctor pharetra justo vel maximus. Phasellus euismod eu tortor ac ullamcorper. Aenean faucibus venenatis ligula. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Aenean vehicula ante sed sem consequat malesuada non at magna. Pellentesque mi metus, finibus eu eros non, venenatis rutrum elit. Pellentesque ac feugiat ipsum.";
		feedback.setDetailedFeedback(detailedFeedback);
		feedback.setRated(users.get(3));
		feedback.setSender(users.get(2));
		dateToParse = "2016-06-15";
		try {
			inputDate = format.parse(dateToParse);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		feedback.setRecDate(inputDate);
		feedbacks.add(feedback);
		
		for (FeedbackVo tmp : feedbacks) {
			System.out.println(tmp.getId());
		}
	}

	public FeedbacksBean() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the feedbacks
	 */
	public List<FeedbackVo> getFeedbacks() {
		return feedbacks;
	}

	/**
	 * @param feedbacks
	 *            the feedbacks to set
	 */
	public void setFeedbacks(List<FeedbackVo> feedbacks) {
		this.feedbacks = feedbacks;
	}
}
