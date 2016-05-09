package hu.schonherz.training.web.exam.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;

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
import hu.schonherz.training.service.supervisor.ExamResultService;

public abstract class ExamReview implements Serializable {
	private static final long serialVersionUID = 1L;

	protected String selectedExamIdAsString;
	protected UserVo user;

	protected List<ExamVo> examList;
	protected List<QuestionVo> questionList;
	protected List<AnswerVo> answerList;
	protected List<Long> selectedOptionIdList;

	protected Integer score;
	protected Integer maxScore;

	@EJB
	protected ExamService examService;
	@EJB
	protected QuestionService questionService;
	@EJB
	protected UserService userService;
	@EJB
	protected AnswerService answerService;
	@EJB
	protected ExamUserRelationService examUserRelationService;
	@EJB
	protected AnswerTextService answerTextService;
	@EJB
	protected ExamResultService examResultService;

	public abstract void loadContent();


	protected void calculateExamScore() throws Exception {
		if (questionList.isEmpty()) {
			score = null;
		} else {
			Long examId = Long.parseLong(selectedExamIdAsString);
			Long userId = user.getId();
			score = examResultService.getByExamIdAndUserId(examId, userId).getScore();
			maxScore = questionList.size();
		}
	}

	protected void updateQuestionList() throws Exception {
		for (QuestionVo question : questionList) {
			if (question.getQuestionType().getName().equalsIgnoreCase("TEXT")) {
				OptionVo option = question.getOptions().get(0);
				setUpOptionByTextBased(option);
			} else {
				for (OptionVo option : question.getOptions()) {
					setUpOptionByNonTextBased(option);
				}
			}
		}
	}

	protected void setUpOptionByTextBased(OptionVo option) throws Exception {
		AnswerTextVo answerText = answerTextService.getByAnswerId(answerList.stream().filter(a -> {
			if (a.getOption().getId().equals(option.getId())) {
				option.setCorrect(a.getGood());
				return true;
			}
			return false;
		}).findFirst().get().getId());
		option.setText(answerText.getText());
	}

	protected void setUpOptionByNonTextBased(OptionVo option) throws Exception {
		List<AnswerVo> list = answerList.stream().filter(a -> a.getOption().getId().equals(option.getId()))
				.collect(Collectors.toList());

		if (list.isEmpty()) {
			if (option.getCorrect() == false) {
				option.setCorrect(null);
			}
			return;
		}

		AnswerVo answer = list.get(0);
		if (answer.getGood() == true && option.getCorrect() == true) {
			option.setCorrect(true);
		} else {
			option.setCorrect(false);
		}
	}

	protected void setUpselectedOptionIdList() {
		selectedOptionIdList = new ArrayList<Long>();
		answerList.forEach(a -> selectedOptionIdList.add(a.getOption().getId()));
	}

	public String getSelectedExamIdAsString() {
		return selectedExamIdAsString;
	}

	public void setSelectedExamIdAsString(String selectedExamIdAsString) {
		this.selectedExamIdAsString = selectedExamIdAsString;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public List<ExamVo> getExamList() {
		return examList;
	}

	public void setExamList(List<ExamVo> examList) {
		this.examList = examList;
	}

	public List<QuestionVo> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<QuestionVo> questionList) {
		this.questionList = questionList;
	}

	public List<AnswerVo> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<AnswerVo> answerList) {
		this.answerList = answerList;
	}

	public List<Long> getSelectedOptionIdList() {
		return selectedOptionIdList;
	}

	public void setSelectedOptionIdList(List<Long> selectedOptionIdList) {
		this.selectedOptionIdList = selectedOptionIdList;
	}

	public ExamService getExamService() {
		return examService;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
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

	public ExamResultService getExamResultService() {
		return examResultService;
	}

	public void setExamResultService(ExamResultService examResultService) {
		this.examResultService = examResultService;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}

}
