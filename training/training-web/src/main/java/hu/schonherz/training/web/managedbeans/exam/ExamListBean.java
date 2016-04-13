package hu.schonherz.training.web.managedbeans.exam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import hu.schonherz.training.exam.service.ExamService;
import hu.schonherz.training.exam.vo.ExamVo;

@ManagedBean(name = "examListBean")
@SessionScoped
public class ExamListBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ExamService examService;
	
	public List<ExamVo> getExamList() {
		List<ExamVo> result = new ArrayList<ExamVo>();
		try {
			result = getExamService().getExamList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ExamService getExamService() {
		return examService;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}
}
