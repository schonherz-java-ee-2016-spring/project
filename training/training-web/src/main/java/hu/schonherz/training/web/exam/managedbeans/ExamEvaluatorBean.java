package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.AnswerService;
import hu.schonherz.training.service.exam.AnswerTextService;
import hu.schonherz.training.service.exam.ExamService;
import hu.schonherz.training.service.exam.QuestionService;
import hu.schonherz.training.service.exam.vo.AnswerTextVo;
import hu.schonherz.training.service.exam.vo.AnswerVo;
import hu.schonherz.training.service.exam.vo.ExamVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@ManagedBean(name = "examEvaluatorBean")
@ViewScoped
public class ExamEvaluatorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private UserService userService;
	@EJB
	private ExamService examService;
	@EJB
	private QuestionService questionService;
	@EJB
	private AnswerService answerService;
	@EJB
	private AnswerTextService answerTextService;

	private List<ExamVo> examList;
	private String selectedExamIdAsString;

	private List<UserVo> userList;
	private String selectedUserIdAsString;

	private List<QuestionVo> textBasedQuestionList;
	private List<AnswerVo> answerList;
	private List<AnswerTextVo> answerTextList;

	@PostConstruct
	public void initBean() {
		try {
			examList = examService.getAllSortedById();
			userList = userService.findAllUser();
			textBasedQuestionList = new ArrayList<>();
			answerList = new ArrayList<>();
			setAnswerTextList(new ArrayList<>());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadContent() {
		try {
			textBasedQuestionList = questionService.getAllById(Long.parseLong(selectedExamIdAsString)).stream()
					.filter(q -> q.getQuestionType().getId() == 3).collect(Collectors.toList());

			answerList = answerService.getAllByUserId(Long.parseLong(selectedUserIdAsString));
			answerList.stream().filter(a -> textBasedQuestionList.stream().flatMap(q -> q.getOptions().stream())
					.filter(o -> o.getId() == a.getOption().getId()).count() > 0).collect(Collectors.toList());

			answerTextList.clear();
			for (AnswerVo answerVo : answerList) {
				AnswerTextVo answerText = answerTextService.getByAnswerId(answerVo.getId());
				answerTextList.add(answerText);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().update("evaluatorForm");
	}

	public void applyEvaluation() {
		// TODO
	}

	/**
	 * @return the examService
	 */
	public ExamService getExamService() {
		return examService;
	}

	/**
	 * @param examService
	 *            the examService to set
	 */
	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	/**
	 * @return the examList
	 */
	public List<ExamVo> getExamList() {
		return examList;
	}

	/**
	 * @param examList
	 *            the examList to set
	 */
	public void setExamList(List<ExamVo> examList) {
		this.examList = examList;
	}

	public String getSelectedExamIdAsString() {
		return selectedExamIdAsString;
	}

	public void setSelectedExamIdAsString(String selectedExamIdAsString) {
		this.selectedExamIdAsString = selectedExamIdAsString;
	}

	public List<QuestionVo> getTextBasedQuestionList() {
		return textBasedQuestionList;
	}

	public void setTextBasedQuestionList(List<QuestionVo> textBasedQuestionList) {
		this.textBasedQuestionList = textBasedQuestionList;
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public List<AnswerVo> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<AnswerVo> answerList) {
		this.answerList = answerList;
	}

	public AnswerService getAnswerService() {
		return answerService;
	}

	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}

	public List<UserVo> getUserList() {
		return userList;
	}

	public void setUserList(List<UserVo> userList) {
		this.userList = userList;
	}

	public String getSelectedUserIdAsString() {
		return selectedUserIdAsString;
	}

	public void setSelectedUserIdAsString(String selectedUserIdAsString) {
		this.selectedUserIdAsString = selectedUserIdAsString;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<AnswerTextVo> getAnswerTextList() {
		return answerTextList;
	}

	public void setAnswerTextList(List<AnswerTextVo> answerTextList) {
		this.answerTextList = answerTextList;
	}

	public AnswerTextService getAnswerTextService() {
		return answerTextService;
	}

	public void setAnswerTextService(AnswerTextService answerTextService) {
		this.answerTextService = answerTextService;
	}
}
