package hu.schonherz.training.exam.vo;

import java.io.Serializable;

public class BaseIdentityVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	public BaseIdentityVo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
