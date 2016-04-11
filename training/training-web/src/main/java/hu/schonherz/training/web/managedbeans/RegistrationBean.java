package hu.schonherz.training.web.managedbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hu.schonherz.training.service.UserService;
import hu.schonherz.training.vo.UserVo;

@ManagedBean(name="registrationBean")
@SessionScoped
public class RegistrationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserService userService;

	private String username;
	private String fullname;
	private String email;

	private String password;

	private String passwordConfirm;

	public void registration() {
		FacesContext currentInstance = FacesContext.getCurrentInstance();

		UserVo userVo = new UserVo();
		userVo.setUserName(username);
		userVo.setFullName(fullname);
		userVo.setEmail(email);

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		userVo.setPassword(bCryptPasswordEncoder.encode(password));

		if (password == null || passwordConfirm == null ) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Passwords must filled!");
			currentInstance.addMessage(null, facesMessage);
			return;
		} else if( !password.equals(passwordConfirm) ) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Passwords not match!");
			currentInstance.addMessage(null, facesMessage);
			return;
		}
		try {
			// itt kell majd az userService regisztrációs szolgáltatását meghívni, majd ha lesz.
			// getUserService().registrationUser(userVO);
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
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
