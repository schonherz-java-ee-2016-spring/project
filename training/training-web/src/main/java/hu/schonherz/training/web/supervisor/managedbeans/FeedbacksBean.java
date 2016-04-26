package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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

	List<FeedbackVo> feedbacks = new ArrayList<>();

	@PostConstruct
	public void init() {
		try {
			List<FeedbackVo> feedbacksTmp = feedbackService.getAll();
			String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
			for (FeedbackVo feedback : feedbacksTmp) {
				if (feedback.getRated().getUserName().equals(username)) {
					feedbacks.add(feedback);
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
}
