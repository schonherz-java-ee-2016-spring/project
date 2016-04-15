package hu.schonherz.training.exam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import hu.schonherz.training.entity.BaseEntity;

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

	/**
	 * The {@link Question} that contains the Option
	 * 
	 * Represented as {@code question_id} in the database
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", referencedColumnName = "id")
	private Question question;

	/**
	 * The correctness of the Option in the context of the {@code question}
	 * field
	 * 
	 * It can be {@code null}, since if the {@code question} expects a
	 * text-based answer, it will be evaluated in an {@link Answer}
	 */
	@Column(name = "correct", nullable = true)
	private Boolean correct;

	/**
	 * The text of the Option
	 * 
	 * It can be {@code null}, since if the {@code question} expects a
	 * text-based answer, the text should be empty
	 */
	@Column(name = "option_text", nullable = true)
	private String optionText;

	public Option() {
		super();
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * @return the correct
	 */
	public Boolean isCorrect() {
		return correct;
	}

	/**
	 * @param correct
	 *            the correct to set
	 */
	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	/**
	 * @return the optionText
	 */
	public String getOptionText() {
		return optionText;
	}

	/**
	 * @param optionText
	 *            the optionText to set
	 */
	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

}
