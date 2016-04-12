package hu.schonherz.training.entity.exam;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import hu.schonherz.training.entity.User;

@Entity
@Table(name = "answer")
public class Answer extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "option_id", referencedColumnName = "id")
	private Option option;

	@Column(name = "is_right", nullable = true)
	private Boolean isRight;

	public Answer() {
		super();
	}

	public Boolean isRight() {
		return isRight;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public void setRight(Boolean isRight) {
		this.isRight = isRight;
	}

}
