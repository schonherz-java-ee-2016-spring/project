package hu.schonherz.training.supervisor.web.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import hu.schonherz.training.supervisor.service.InterviewService;
import hu.schonherz.training.supervisor.vo.InterviewVo;

@ManagedBean(name = "interviewsBean")
@SessionScoped
public class InterviewsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3607430344352669043L;

	@EJB
	private InterviewService interviewService;
	
	private String company;
	private String description;
	private Date interviewDate;
	private Long interviewedId;
	private Long interviewerId;
	private List<InterviewVo> interviews;
	
	@PostConstruct
	public void pcon() {
		try {
			interviews = interviewService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
