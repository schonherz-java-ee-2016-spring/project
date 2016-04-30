package hu.schonherz.training.service.exam.vo.eval;

import java.util.List;

public class EvalTextBasedQuestion {
	private Long id;
	private String text;
	private String note;
	private List<EvalAnswer> answers;

	public EvalTextBasedQuestion() {
		super();
	}

	public EvalTextBasedQuestion(Long id, String text, String note) {
		super();
		this.id = id;
		this.text = text;
		this.note = note;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<EvalAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<EvalAnswer> answers) {
		this.answers = answers;
	}
}
