package hu.schonherz.training.core.exam.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import hu.schonherz.training.core.admin.entity.BaseEntity;
import hu.schonherz.training.core.admin.entity.User;

/**
 * The database entity of an Answer
 * 
 * This entity works like a relational table to connect a {@link User} with an
 * {@link Option} he chose.
 */
@Entity
@Table(name = "answer")
@NamedQueries(value = {
		@NamedQuery(name = "findAnswersByUserId", query = "SELECT a FROM Answer a WHERE a.user.id = :id") })
public class Answer extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id")
	private Option option;

	@Column(name = "good", nullable = true)
	private Boolean good;

	public Answer() {
		super();
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

	public Boolean getGood() {
		return good;
	}

	public void setGood(Boolean good) {
		this.good = good;
	}

}
