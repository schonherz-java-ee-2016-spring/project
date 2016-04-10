package hu.schonherz.training.vo;

import java.io.Serializable;
import java.util.List;

public class UserVo implements Serializable {

	private static final long serialVersionUID = 5932000328505763772L;

	private Long id;
	private String userName;
	private String password;
	private List<RoleVo> roles;

	private byte[] image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<RoleVo> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleVo> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", userName=" + userName + ", password=" + password + ", roles=" + roles + "]";
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
