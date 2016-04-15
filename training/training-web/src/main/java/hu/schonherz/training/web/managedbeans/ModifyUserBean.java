package hu.schonherz.training.web.managedbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import hu.schonherz.training.service.UserService;
import hu.schonherz.training.vo.UserVo;

@ManagedBean(name="modifyUserBean")
@SessionScoped
public class ModifyUserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserService userService;

	private String username;
	private String fullname;
	private String email;

	public void modifyUser() {
		UserVo user = null;
		UserVo useremail = null;
		UserVo userVo = new UserVo();
		try {
			user = userService.findUserByName(username);
			useremail = userService.findUserByEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		
		userVo.setUserName(username);
		
		if (username == null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Username must filled!");
			currentInstance.addMessage(null, facesMessage);
			return;
		}
		if (user != null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Username already exists!");
			currentInstance.addMessage(null, facesMessage);
			return;
		}
		
		userVo.setFullName(fullname);
		
		userVo.setEmail(email);
		
		if (email == null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "E-mail must filled!");
			currentInstance.addMessage(null, facesMessage);
			return;
		}
		if (useremail != null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "E-mail already exists!");
			currentInstance.addMessage(null, facesMessage);
			return;
		}

		try {
			// itt kell majd az userService regisztrációs szolgáltatását meghívni, majd ha lesz.
			 userService.modifyUser(userVo);
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error in creating new user!");
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
		}

		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes!", "Succes registration!");
		currentInstance.addMessage(null, facesMessage);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
