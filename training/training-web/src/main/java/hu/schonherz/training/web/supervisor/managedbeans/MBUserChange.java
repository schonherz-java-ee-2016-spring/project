package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.TabChangeEvent;

import hu.schonherz.training.service.supervisor.vo.ExamResultVo;
import hu.schonherz.training.service.supervisor.vo.HomeworkResultVo;

@ManagedBean(name = "userChangeBean", eager = true)
@RequestScoped
public class MBUserChange implements Serializable {

	/**
	 * 
	 */

	@ManagedProperty(value = "#{resultsBean}")
	private MBResultsBean viewBean;

	private static final long serialVersionUID = 1L;

	public MBUserChange() {
		super();
	}

	public void onUserTabChange(TabChangeEvent event) {
		List<HomeworkResultVo> homeworkResults = viewBean.getHomeworkResults();
		List<ExamResultVo> examResults = viewBean.getExamResults();

		Random rand = new Random();
		for (HomeworkResultVo homeworkResult : homeworkResults) {
			homeworkResult.setScore(rand.nextInt(10));
		}
		for (ExamResultVo examResult : examResults) {
			examResult.setScore(rand.nextInt(10));
		}
		viewBean.setExamResults(examResults);
		viewBean.setHomeworkResults(homeworkResults);
	}

	public MBResultsBean getViewBean() {
		return viewBean;
	}

	public void setViewBean(MBResultsBean viewBean) {
		this.viewBean = viewBean;
	}

}
