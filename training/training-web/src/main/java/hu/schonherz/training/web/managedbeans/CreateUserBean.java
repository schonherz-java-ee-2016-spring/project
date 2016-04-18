package hu.schonherz.training.web.managedbeans;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hu.schonherz.training.service.UserService;
import hu.schonherz.training.vo.UserVo;

@ManagedBean(name = "createUserBean")
@ViewScoped
public class CreateUserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserService userService;

	private String username;
	private String fullname;
	private String email;

	@ManagedProperty("#{out}")
	private ResourceBundle bundle;

	public void create() {

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
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Username must filled!");
			currentInstance.addMessage(null, facesMessage);
			return;
		}
		if (user != null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Username already exists!");
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
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"E-mail already exists!");
			currentInstance.addMessage(null, facesMessage);
			return;
		}

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		String uuid = UUID.randomUUID().toString();

		userVo.setPassword(bCryptPasswordEncoder.encode(uuid));

		try {
			// itt kell majd az userService regisztrációs szolgáltatását
			// meghívni, majd ha lesz.
			userService.registrationUser(userVo);
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",
					"Error in creating new user!");
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

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

}
