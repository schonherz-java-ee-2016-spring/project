package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import hu.schonherz.training.service.exam.ExamService;
import hu.schonherz.training.service.exam.QuestionService;
import hu.schonherz.training.service.exam.vo.ExamVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@ManagedBean(name = "questionBean")
@SessionScoped
public class QuestionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String examIdAsString;
	private List<QuestionVo> questionList;
	private String examTitleInputText;

	@EJB
	private ExamService examService;
	@EJB
	private QuestionService questionService;

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public String getExamIdAsString() {
		return examIdAsString;
	}

	public void setExamIdAsString(String examIdAsString) {
		this.examIdAsString = examIdAsString;
	}

	public ExamService getExamService() {
		return examService;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	public List<QuestionVo> getQuestionList() {
		try {
			Long examId = Long.parseLong(examIdAsString);
			questionList = questionService.getAll(examId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return questionList;
	}

	public void setQuestionList(List<QuestionVo> questionList) {
		this.questionList = questionList;
	}

	public String getExamTitleInputText() {
		ExamVo examVo;
		try {
			examVo = examService.getById(Long.parseLong(examIdAsString));
			examTitleInputText = examVo.getTitle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return examTitleInputText;
	}

	public void setExamTitleInputText(String examTitleInputText) {
		this.examTitleInputText = examTitleInputText;
		ExamVo examVo;
		try {
			examVo = examService.getById(Long.parseLong(examIdAsString));
			examVo.setTitle(examTitleInputText);
			examService.updateTitle(examVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
