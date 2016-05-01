package hu.schonherz.training.service.exam.vo.eval;

import java.util.List;

public class EvalOption {
	private List<EvalAnswer> answers;

	public EvalOption() {
		super();
	}

	public EvalOption(List<EvalAnswer> answers) {
		super();
		this.answers = answers;
	}

	public List<EvalAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<EvalAnswer> answers) {
		this.answers = answers;
	}
}
