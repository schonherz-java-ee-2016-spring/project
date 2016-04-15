package hu.schonherz.training.web.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hu.schonherz.training.service.UserService;
import hu.schonherz.training.vo.UserVo;

@ManagedBean(name = "usersBean")
@SessionScoped
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
	}

	public void modifyUser() {
		UserVo userVo = new UserVo();
////		UserVo confirmUsername = new UserVo();
//		UserVo confirmUseremail = new UserVo();
		FacesContext currentInstance = FacesContext.getCurrentInstance();
//		try {
//			confirmUsername = userService.findUserByName(selectedUser.getUserName());
//			confirmUseremail = userService.findUserByEmail(selectedUser.getEmail());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		// Confirm username
		if (selectedUser.getUserName() == null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
					bundle.getString("usernameReq"));
			currentInstance.addMessage(null, facesMessage);
			return;
		}
//		if (confirmUsername != null) {
//			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
//					bundle.getString("usernameExists"));
//			currentInstance.addMessage(null, facesMessage);
//			return;
//		}
		// Confirm email
		if (selectedUser.getEmail() == null) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
					bundle.getString("emailReq"));
			currentInstance.addMessage(null, facesMessage);
			return;
		}
//		if (confirmUseremail != null) {
//			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
//					bundle.getString("emailExists"));
//			currentInstance.addMessage(null, facesMessage);
//			return;
//		}
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
	}

	public List<UserVo> getAllUser() {
		List<UserVo> vos = null;
		try {
			if (userService.findAllUser() == null) {
				vos = new ArrayList<>();
			} else {
				vos = userService.findAllUser();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vos;
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
