package hu.schonherz.training.service.supervisor.vo;

import java.io.Serializable;

import hu.schonherz.training.service.admin.vo.UserVo;

/**
 * Value object represents homework results
 * @author Mark Bohan
 *
 */
public class HomeworkResultVo implements Serializable {

	private static final long serialVersionUID = -8110648162093644756L;

	private Long id;
	private UserVo user;
	private HomeworkVo homework;
	private Integer score;
	private String comment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public HomeworkVo getHomework() {
		return homework;
	}

	public void setHomework(HomeworkVo homework) {
		this.homework = homework;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "HomeworkResultVo [id=" + id + ", user=" + user + ", homework=" + homework + ", score=" + score
				+ ", comment=" + comment + "]";
	}

}
