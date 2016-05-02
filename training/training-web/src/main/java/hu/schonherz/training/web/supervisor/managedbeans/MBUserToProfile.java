package hu.schonherz.training.web.supervisor.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.UserVo;

@ManagedBean(name = "profileBean")
@ViewScoped
public class MBUserToProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserVo user;

	private String email;

	@EJB
	private UserService userService;

	@PostConstruct
	public void init() {

		String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
		try {
			user = userService.findUserByName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void changeData() {
		user.setEmail(email);
		userService.updateUser(user);
	}

	public MBUserToProfile() {
		// TODO Auto-generated constructor stub
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
