package hu.schonherz.training.exam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import hu.schonherz.training.entity.BaseEntity;
import hu.schonherz.training.entity.User;

@Entity
@Table(name = "answer")
public class Answer extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id", referencedColumnName = "id")
	private Option option;

	@Column(name = "good", nullable = true)
	private Boolean good;

	public Answer() {
		super();
	}

	public Boolean isGood() {
		return good;
	}

	public void setGood(Boolean good) {
		this.good = good;
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

}
