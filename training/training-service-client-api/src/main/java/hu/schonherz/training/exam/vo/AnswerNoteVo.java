package hu.schonherz.training.exam.vo;

import java.io.Serializable;

public class AnswerNoteVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private AnswerVo answer;
	private String note;

	public AnswerNoteVo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AnswerVo getAnswer() {
		return answer;
	}

	public void setAnswer(AnswerVo answer) {
		this.answer = answer;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
