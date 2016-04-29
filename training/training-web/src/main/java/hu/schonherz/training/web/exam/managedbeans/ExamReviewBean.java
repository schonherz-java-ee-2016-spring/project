package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.AnswerService;
import hu.schonherz.training.service.exam.AnswerTextService;
import hu.schonherz.training.service.exam.ExamService;
import hu.schonherz.training.service.exam.ExamUserRelationService;
import hu.schonherz.training.service.exam.QuestionService;
import hu.schonherz.training.service.exam.vo.AnswerTextVo;
import hu.schonherz.training.service.exam.vo.AnswerVo;
import hu.schonherz.training.service.exam.vo.ExamVo;
import hu.schonherz.training.service.exam.vo.OptionVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@ManagedBean(name = "examReviewBean")
@SessionScoped
public class ExamReviewBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ExamService examService;
	@EJB
	private QuestionService questionService;
	@EJB
	private UserService userService;
	@EJB
	private AnswerService answerService;
	@EJB
	private ExamUserRelationService examUserRelationService;
	@EJB
	private AnswerTextService answerTextService;

	private List<ExamVo> examList;
	private String selectedExamIdAsString;

	private UserVo user;

	private List<QuestionVo> questionList;
	private List<AnswerVo> answerList;

	@PostConstruct
	public void initBean() {
		try {
			questionList = new ArrayList<>();

			setUser(userService.findUserByName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()));
			examList = examUserRelationService.getAllExamByUserId(user.getId());
			setAnswerList(new ArrayList<>());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadContent() throws Exception {
		try {
			questionList = questionService.getAllById(Long.parseLong(selectedExamIdAsString));
			answerList = getAnswerService().getAllByUserId(user.getId());
			answerList = answerList.stream()
					.filter(a -> questionList.stream().flatMap(q -> q.getOptions().stream())
							.filter(qq -> qq.getId().equals(a.getOption().getId())).count() > 0)
					.collect(Collectors.toList());
			updateQuestionList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().update("optionTableForm");
	}

	private void updateQuestionList() throws Exception {
		for (QuestionVo question : questionList) {
			if (question.getQuestionType().getName().equalsIgnoreCase("TEXT")) {
				for (OptionVo option : question.getOptions()) {
					AnswerTextVo answerText = answerTextService.getByAnswerId(answerList.stream().filter(a -> {
						if (a.getOption().getId().equals(option.getId())) {
							option.setCorrect(a.getGood());
							return true;
						}
						return false;
					}).findFirst().get().getId());
					option.setText(answerText.getText());
				}
			}
		}
	}

	public ExamService getExamService() {
		return examService;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	public List<ExamVo> getExamList() {
		return examList;
	}

	public void setExamList(List<ExamVo> examList) {
		this.examList = examList;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public AnswerService getAnswerService() {
		return answerService;
	}

	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}

	public List<AnswerVo> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<AnswerVo> answerList) {
		this.answerList = answerList;
	}

	public String getSelectedExamIdAsString() {
		return selectedExamIdAsString;
	}

	public void setSelectedExamIdAsString(String selectedExamIdAsString) {
		this.selectedExamIdAsString = selectedExamIdAsString;
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public List<QuestionVo> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<QuestionVo> questionList) {
		this.questionList = questionList;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public ExamUserRelationService getExamUserRelationService() {
		return examUserRelationService;
	}

	public void setExamUserRelationService(ExamUserRelationService examUserRelationService) {
		this.examUserRelationService = examUserRelationService;
	}

	public AnswerTextService getAnswerTextService() {
		return answerTextService;
	}

	public void setAnswerTextService(AnswerTextService answerTextService) {
		this.answerTextService = answerTextService;
	}

}
