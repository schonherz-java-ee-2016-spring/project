package hu.schonherz.training.core.exam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import hu.schonherz.training.core.admin.entity.BaseEntity;

/**
 * The database entity of an Option
 * 
 * These entities serve as markable options in a {@link Question}, if the
 * {@link QuestionType} is not text-based. In that case, the {@link Question}
 * will only have one Option, which can be referenced in the {@link Answer}
 */
@Entity
@Table(name = "option")
public class Option extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "correct", columnDefinition = "boolean default false")
	private Boolean correct;

	@Column(name = "option_text", nullable = true)
	private String optionText;

	public Option() {
		super();
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	public String getOptionText() {
		return optionText;
	}

	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

	@PrePersist
	public void prePersist() {
		if (getCorrect() == null) {
			setCorrect(false);
		}
	}

}
