package hu.schonherz.training.web.managedbeans.exam;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import hu.schonherz.training.exam.service.ExamService;
import hu.schonherz.training.exam.service.QuestionService;
import hu.schonherz.training.exam.service.QuestionTypeService;
import hu.schonherz.training.exam.vo.ExamVo;
import hu.schonherz.training.exam.vo.QuestionTypeVo;
import hu.schonherz.training.exam.vo.QuestionVo;

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

	private String newQuestionExamID;
	private String newQuestionText;
	private String questionTypeIdAsString;
	private String examIdAsString;
	private Integer newQuestionType;

	public String goToCreateQuestionPage() {
		return "createQuestion";
	}

	public List<QuestionVo> getQuestionList() {
		Long examId = Long.parseLong(examIdAsString);
		List<QuestionVo> questionVoList = null;
		try {
			questionVoList = examService.getExamById(examId).getQuestionList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return questionVoList;
	}

	public List<QuestionTypeVo> getQuestionTypeList() {
		List<QuestionTypeVo> questionTypeVoList = null;
		try {
			questionTypeVoList = questionTypeService.getQuestionTypeList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return questionTypeVoList;
	}

	public void registerNewQuestion() throws Exception {
		FacesContext currentInstance = FacesContext.getCurrentInstance();

		if (newQuestionText.isEmpty()) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Text field is empty!");
			currentInstance.addMessage(null, facesMessage);
			return;
		}
		
		QuestionTypeVo questionTypeVo = questionTypeService.getQuestionTypeById(Long.parseLong(questionTypeIdAsString));
		
		ExamVo examVo = examService.getExamById(Long.parseLong(examIdAsString));
		
		QuestionVo questionVo = new QuestionVo();
		questionVo.setExam(examVo);
		questionVo.setOptionList(null);
		questionVo.setQuestionType(questionTypeVo);
		questionVo.setText(newQuestionText);

		try {
			questionService.createQuestion(questionVo);
			
//			System.out.println("ASDASD------------22");
//			questionService.
//			System.out.println("ASDASD------------22");
			
//			examVo = examService.getExamById(Long.parseLong(examIdAsString));
//			System.out.println("ASDASD------------22");
//			examVo.getQuestionList().forEach(System.out::println);
//			System.out.println("ASDASD------------22");
//			questionTypeVo = questionTypeService.getQuestionTypeById(Long.parseLong(questionTypeIdAsString));
//			System.out.println("ASDASD------------");
//			questionTypeVo.getQuestionList().forEach(System.out::println);
//			System.out.println("ASDASD------------");
			
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

	public String getNewQuestionExamID() {
		return newQuestionExamID;
	}

	public void setNewQuestionExamID(String newQuestionExamID) {
		this.newQuestionExamID = newQuestionExamID;
	}

	public Integer getNewQuestionType() {
		return newQuestionType;
	}

	public void setNewQuestionType(Integer newQuestionType) {
		this.newQuestionType = newQuestionType;
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

}
