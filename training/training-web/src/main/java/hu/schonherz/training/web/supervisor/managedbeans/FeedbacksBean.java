package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.supervisor.FeedbackService;
import hu.schonherz.training.service.supervisor.vo.FeedbackVo;

@ManagedBean(name = "feedbacksBean")
@ViewScoped
public class FeedbacksBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6239504593798844987L;

	@EJB
	FeedbackService feedbackService;

	private List<FeedbackVo> feedbacks = new ArrayList<>();
	private List<FeedbackVo> userFeedbacks = new ArrayList<>();
	private List<FeedbackVo> writtenFeedbacks = new ArrayList<>();
	private List<FeedbackVo> userWrittenFeedback = new ArrayList<>();
	private String ratedName;

	@PostConstruct
	public void init() {
		try {
			List<FeedbackVo> feedbacksTmp = feedbackService.getAll();
			String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
			for (FeedbackVo feedback : feedbacksTmp) {
				List<UserVo> rateds = new ArrayList<>();
				rateds.addAll(feedback.getRated());
				if (feedback.getEvent() != null) {
					for (UserVo rated : rateds) {
						if (rated.getUserName().equals(username)) {
							feedbacks.add(feedback);
						}
					}
					if (feedback.getSender().getUserName().equals(username)) {
						writtenFeedbacks.add(feedback);
					}
				} else {
					for (UserVo rated : rateds) {
						if (rated.getUserName().equals(username)) {
							userFeedbacks.add(feedback);
						}
					}
					if (feedback.getSender().getUserName().equals(username)) {
						userWrittenFeedback.add(feedback);
						for (UserVo user : feedback.getRated()) {
							ratedName = user.getFullName();
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	/**
	 * @return the writtenFeedbacks
	 */
	public List<FeedbackVo> getWrittenFeedbacks() {
		return writtenFeedbacks;
	}

	/**
	 * @param writtenFeedbacks
	 *            the writtenFeedbacks to set
	 */
	public void setWrittenFeedbacks(List<FeedbackVo> writtenFeedbacks) {
		this.writtenFeedbacks = writtenFeedbacks;
	}

	/**
	 * @return the userFeedbacks
	 */
	public List<FeedbackVo> getUserFeedbacks() {
		return userFeedbacks;
	}

	/**
	 * @param userFeedbacks the userFeedbacks to set
	 */
	public void setUserFeedbacks(List<FeedbackVo> userFeedbacks) {
		this.userFeedbacks = userFeedbacks;
	}

	/**
	 * @return the userWrittenFeedback
	 */
	public List<FeedbackVo> getUserWrittenFeedback() {
		return userWrittenFeedback;
	}

	/**
	 * @param userWrittenFeedback the userWrittenFeedback to set
	 */
	public void setUserWrittenFeedback(List<FeedbackVo> userWrittenFeedback) {
		this.userWrittenFeedback = userWrittenFeedback;
	}

	/**
	 * @return the ratedName
	 */
	public String getRatedName() {
		return ratedName;
	}

	/**
	 * @param ratedName the ratedName to set
	 */
	public void setRatedName(String ratedName) {
		this.ratedName = ratedName;
	}
}
