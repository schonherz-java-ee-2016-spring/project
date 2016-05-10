package hu.schonherz.training.web.exam.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
			score = examResultService.getByExamIdAndUserId(examId, userId).getPoints();
			maxScore = questionList.size();
		}
	}

	protected void updateTextBasedOptions() {
		questionList.forEach(q -> {

			if (q.getQuestionType().getName().equalsIgnoreCase("TEXT")) {
				OptionVo optionVo = q.getOptions().get(0);
				AnswerVo currentAnswer = answerList.stream().filter(a -> a.getOption().getId().equals(optionVo.getId()))
						.findFirst().get();
				AnswerTextVo answerText = null;
				try {
					answerText = answerTextService.getByAnswerId(currentAnswer.getId());
					q.getOptions().get(0).setText(answerText.getText());
					q.getOptions().get(0).setCorrect(currentAnswer.getGood());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
