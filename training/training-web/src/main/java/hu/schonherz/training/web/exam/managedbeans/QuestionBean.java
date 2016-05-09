package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import hu.schonherz.training.service.exam.ExamService;
import hu.schonherz.training.service.exam.QuestionService;
import hu.schonherz.training.service.exam.vo.ExamVo;
import hu.schonherz.training.service.exam.vo.OptionVo;
import hu.schonherz.training.service.exam.vo.QuestionTypeVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@ManagedBean(name = "questionBean")
@SessionScoped
public class QuestionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String examIdAsString;
	private List<QuestionVo> questionList;
	private String examTitleInputText;

	@ManagedProperty("#{out}")
	private ResourceBundle bundle;

	@EJB
	private ExamService examService;
	@EJB
	private QuestionService questionService;
	
	private String questionIdAsString;
	private String questionText;
	
	public void renameQuestion() throws Exception{
		QuestionVo renamedQuestion = questionService.getById(Long.parseLong(questionIdAsString));
		renamedQuestion.setText(questionText);
		questionService.modifyText(renamedQuestion);
		RequestContext.getCurrentInstance().update("questionTable");
	}
	
	public void setUpEditQuestion(ActionEvent event) {
		questionIdAsString = event.getComponent().getAttributes().get("questionId").toString();
		questionText = event.getComponent().getAttributes().get("questionName").toString();
	}

	public void addSingleQuestion() {
		try {
			QuestionTypeVo questionType = new QuestionTypeVo();
			questionType.setId(1L);
			QuestionVo question = new QuestionVo();
			question.setText(bundle.getString("newsingleaq"));
			question.setNote(bundle.getString("insertnote"));
			question.setQuestionType(questionType);
			questionService.add(question, Long.parseLong(examIdAsString));
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().update("questionTable");
	}

	public void addMultiQuestion() {
		try {
			QuestionTypeVo questionType = new QuestionTypeVo();
			questionType.setId(2L);
			QuestionVo question = new QuestionVo();
			question.setText(bundle.getString("newmultiaq"));
			question.setNote(bundle.getString("insertnote"));
			question.setQuestionType(questionType);
			questionService.add(question, Long.parseLong(examIdAsString));
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().update("questionTable");
	}

	public void addTextBasedQuestion() {
		try {
			QuestionTypeVo questionType = new QuestionTypeVo();
			questionType.setId(3L);
			QuestionVo question = new QuestionVo();
			question.setText(bundle.getString("newtextbasedq"));
			question.setNote(bundle.getString("insertnote"));
			question.setQuestionType(questionType);
			OptionVo optionVo = new OptionVo();
			List<OptionVo> options = new ArrayList<>();
			options.add(optionVo);
			question.setOptions(options);
			questionService.add(question, Long.parseLong(examIdAsString));
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().update("questionTable");
	}

	public void removeQuestion(ActionEvent event) {
		String questionIdAsString = event.getComponent().getAttributes().get("questionIdAsString").toString();
		Long questionId = Long.parseLong(questionIdAsString);

		try {
			questionService.removeById(questionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
			questionList = questionService.getAllByExamId(examId);
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
			examService.modifyTitle(examVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getQuestionIdAsString() {
		return questionIdAsString;
	}

	public void setQuestionIdAsString(String questionIdAsString) {
		this.questionIdAsString = questionIdAsString;
	}
	
}
