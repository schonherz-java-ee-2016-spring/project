package hu.schonherz.training.web.exam.managedbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "singleQuestionDetailsBean")
@SessionScoped
public class SingleQuestionDetailsBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String questionIdAsString;

	public String getQuestionIdAsString() {
		return questionIdAsString;
	}

	public void setQuestionIdAsString(String questionIdAsString) {
		this.questionIdAsString = questionIdAsString;
	}

}
