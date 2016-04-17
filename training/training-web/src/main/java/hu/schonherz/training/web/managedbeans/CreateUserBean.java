package hu.schonherz.training.web.managedbeans;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hu.schonherz.training.service.UserService;
import hu.schonherz.training.vo.UserVo;

@ManagedBean(name="createUserBean")
@SessionScoped
public class CreateUserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserService userService;

	private String username;
	private String fullname;
	private String email;

	@ManagedProperty("#{out}")
	private ResourceBundle bundle;
	
	public void create() throws Exception {

		UserVo validUsername = new UserVo();
		UserVo validemail = new UserVo();
		try {
			validUsername = userService.findUserByName(username);
			validemail = userService.findUserByEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (username == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
					bundle.getString("usernameReq"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if (validUsername != null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
					bundle.getString("usernameExists"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if (email == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
					bundle.getString("emailReq"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if (validemail != null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
					bundle.getString("emailExists"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		try {
			UserVo uservo = new UserVo();
			uservo.setUserName(username);
			uservo.setEmail(email);
			uservo.setFullName(fullname);
			
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			String uuid = UUID.randomUUID().toString();
			uservo.setPassword(bCryptPasswordEncoder.encode(uuid));
			userService.registrationUser(uservo);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("succes"),
					bundle.getString("succesCreate"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		@SuppressWarnings("unused")
		String username = null;
		@SuppressWarnings("unused")
		String fullname = null;		
		@SuppressWarnings("unused")
		String email = null;
		
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

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}
	
}
