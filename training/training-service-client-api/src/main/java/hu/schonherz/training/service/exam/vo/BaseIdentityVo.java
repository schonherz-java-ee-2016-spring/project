package hu.schonherz.training.service.exam.vo;

import java.io.Serializable;
import java.util.Date;

public class BaseIdentityVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	String recUser;
	String modUser;
	Date recDate;
	Date modDate;

	public BaseIdentityVo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecUser() {
		return recUser;
	}

	public void setRecUser(String recUser) {
		this.recUser = recUser;
	}

	public String getModUser() {
		return modUser;
	}

	public void setModUser(String modUser) {
		this.modUser = modUser;
	}

	public Date getRecDate() {
		return recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

}
