package hu.schonherz.training.service.exam.vo.eval;

import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.vo.AnswerTextVo;
import hu.schonherz.training.service.exam.vo.AnswerVo;
import hu.schonherz.training.service.exam.vo.OptionVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

public class EvalRecord {
	private UserVo user;
	private QuestionVo question;
	private OptionVo option;
	private AnswerVo answer;
	private AnswerTextVo answerText;
	
	public EvalRecord() {
		super();
	}
	
	

	public EvalRecord(UserVo user, QuestionVo question, OptionVo option, AnswerVo answer, AnswerTextVo answerText) {
		super();
		this.user = user;
		this.question = question;
		this.option = option;
		this.answer = answer;
		this.answerText = answerText;
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

	public AnswerVo getAnswer() {
		return answer;
	}

	public void setAnswer(AnswerVo answer) {
		this.answer = answer;
	}



	public OptionVo getOption() {
		return option;
	}



	public void setOption(OptionVo option) {
		this.option = option;
	}
}
