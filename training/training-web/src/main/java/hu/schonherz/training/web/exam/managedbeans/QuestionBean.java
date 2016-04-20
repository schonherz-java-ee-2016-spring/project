package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import hu.schonherz.training.service.exam.ExamService;
import hu.schonherz.training.service.exam.QuestionService;
import hu.schonherz.training.service.exam.QuestionTypeService;
import hu.schonherz.training.service.exam.vo.ExamVo;
import hu.schonherz.training.service.exam.vo.QuestionTypeVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@ManagedBean(name = "questionBean")
@SessionScoped
public class QuestionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private QuestionService questionService;
	@EJB
	private ExamService examService;
	@EJB
	private QuestionTypeService questionTypeService;

	private String newQuestionText;
	private String questionTypeIdAsString;
	private String examIdAsString;

	public List<QuestionVo> getQuestionList() {
		Long examId = Long.parseLong(examIdAsString);
		List<QuestionVo> questionVoList = null;
		try {
			questionVoList = examService.findById(examId).getQuestionList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return questionVoList;
	}

	public List<QuestionTypeVo> getQuestionTypeList() {
		List<QuestionTypeVo> questionTypeVoList = null;
		try {
			questionTypeVoList = questionTypeService.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return questionTypeVoList;
	}

	

	public void registerNewQuestion() throws Exception {
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		QuestionVo questionVo = new QuestionVo();
		setUpQuestionVo(questionVo);

		try {
			questionService.create(questionVo);
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!",
					"\"" + newQuestionText + "\" question created!");
			currentInstance.addMessage(null, facesMessage);
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Couldn't create question: \"" + newQuestionText + "\"");
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().update("questionTable");
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public String getNewQuestionText() {
		return newQuestionText;
	}

	public void setNewQuestionText(String newQuestionText) {
		this.newQuestionText = newQuestionText;
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

	public QuestionTypeService getQuestionTypeService() {
		return questionTypeService;
	}

	public void setQuestionTypeService(QuestionTypeService questionTypeService) {
		this.questionTypeService = questionTypeService;
	}

	public String getQuestionTypeIdAsString() {
		return questionTypeIdAsString;
	}

	public void setQuestionTypeIdAsString(String questionTypeAsString) {
		this.questionTypeIdAsString = questionTypeAsString;
	}
	
	private void setUpQuestionVo(QuestionVo questionVo) throws Exception {
		Long questionTyId = Long.parseLong(questionTypeIdAsString);
		Long examId = Long.parseLong(examIdAsString);

		QuestionTypeVo questionTypeVo = questionTypeService.findById(questionTyId);
		ExamVo examVo = examService.findById(examId);

		questionVo.setExam(examVo);
		questionVo.setOptionList(null);
		questionVo.setQuestionType(questionTypeVo);
		questionVo.setText(newQuestionText);
	}

}