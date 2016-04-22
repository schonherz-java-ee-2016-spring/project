package hu.schonherz.training.service.exam.vo;

public class QuestionTypeVo extends BaseIdentityVo {
	private static final long serialVersionUID = 9122075670750880034L;

	private String name;

	public QuestionTypeVo() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
