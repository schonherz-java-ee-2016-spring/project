package hu.schonherz.training.service.exam.vo.eval;

import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.vo.AnswerTextVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

public class EvalRecord {
	private UserVo user;
	private QuestionVo question;
	private AnswerTextVo answerText;
	private boolean correct;
	
	public EvalRecord() {
		super();
	}

	public EvalRecord(UserVo user, QuestionVo question, AnswerTextVo answerText, boolean correct) {
		super();
		this.user = user;
		this.question = question;
		this.answerText = answerText;
		this.correct = correct;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public QuestionVo getQuestion() {
		return question;
	}

	public void setQuestion(QuestionVo question) {
		this.question = question;
	}

	public AnswerTextVo getAnswerText() {
		return answerText;
	}

	public void setAnswerText(AnswerTextVo answerText) {
		this.answerText = answerText;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
}
