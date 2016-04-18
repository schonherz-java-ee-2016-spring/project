package hu.schonherz.training.web.managedbeans;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hu.schonherz.training.service.UserService;
import hu.schonherz.training.vo.UserVo;

@ManagedBean(name = "usersBean")
@ViewScoped
public class UsersBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserService userService;

	private String username;
	private String fullname;
	private String email;
	private boolean selected;

	@ManagedProperty("#{out}")
	private ResourceBundle bundle;

	private UserVo selectedUser;

	private List<UserVo> users;

	@PostConstruct
	public void init() {
		selected = true;
		try {
			users = userService.findAllUser();
			selectedUser = new UserVo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onSelect(SelectEvent event) throws Exception {
		setSelected(false);
	}

	public void create() {

		UserVo user = null;
		UserVo useremail = null;

		try {
			user = userService.findUserByName(username);
			useremail = userService.findUserByEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesContext currentInstance = FacesContext.getCurrentInstance();

		// Username confirmation
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

		// Email confirm
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

		// Random password generation
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String uuid = UUID.randomUUID().toString();

		UserVo userVo = new UserVo();
		userVo.setFullName(fullname);
		userVo.setUserName(username);
		userVo.setEmail(email);
		userVo.setPassword(bCryptPasswordEncoder.encode(uuid));

		try {
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

	public void deleteUser() {
		try {
			userService.deleteUserById(selectedUser.getId());
			users.remove(selectedUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, getBundle().getString("succes"),
				getBundle().getString("succesDelete"));
		FacesContext.getCurrentInstance().addMessage(null, message);
		selectedUser = null;
		// selected = true;
	}

	public void modifyUser() {
		UserVo userVo = new UserVo();
		FacesContext currentInstance = FacesContext.getCurrentInstance();
		// Confirm username
		if (selectedUser.getUserName() == null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
					bundle.getString("usernameReq"));
			currentInstance.addMessage(null, facesMessage);
			return;
		}
		// Confirm email
		if (selectedUser.getEmail() == null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
					bundle.getString("emailReq"));
			currentInstance.addMessage(null, facesMessage);
			return;
		}
		try {
			userVo.setUserName(username);
			userVo.setFullName(fullname);
			userVo.setEmail(email);
			userService.modifyUser(selectedUser);
		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
					bundle.getString("updateFail"));
			currentInstance.addMessage(null, facesMessage);
			e.printStackTrace();
		}
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("succes"),
				bundle.getString("succesUpdate"));
		currentInstance.addMessage(null, facesMessage);
		selectedUser = null;
		// selected = true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public List<UserVo> getUsers() {
		return users;
	}

	public void setUsers(List<UserVo> users) {
		this.users = users;
	}

	public UserVo getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UserVo selectedUser) {
		this.selectedUser = selectedUser;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
