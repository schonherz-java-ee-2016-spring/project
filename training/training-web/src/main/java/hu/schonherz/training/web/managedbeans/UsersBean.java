package hu.schonherz.training.web.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import hu.schonherz.training.service.UserService;
import hu.schonherz.training.vo.UserVo;

@ManagedBean(name="usersBean")
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void onSelect(SelectEvent event) throws Exception {
//			System.out.println("----------------------------------------------------------------------------------------------------"+selectedUser.getFullName());
			setSelected(false);
	}
	public void deleteUser() {
		try {
			userService.deleteUserById(selectedUser.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, getBundle().getString("succes"), getBundle().getString("succesDelete"));
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
//	public void modifyUser() {
//		FacesMessage message = null;
//		UserVo confirmUser = userService.findUserById(selectedUser.getId());
//		if (selectedUser.getUserName().equals(confirmUser.getUserName())) {
//			message = new FacesMessage(FacesMessage.SEVERITY_INFO, getBundle().getString("error"), getBundle().getString("usernameExists"));
//			return;
//		}
//		if (selectedUser.getEmail().equals(confirmUser.getEmail())) {
//			message = new FacesMessage(FacesMessage.SEVERITY_INFO, getBundle().getString("error"), getBundle().getString("emailExists"));
//			return;
//		}
//		if (!selectedUser.getUserName().equals(confirmUser.getUserName()) && !selectedUser.getEmail().equals(confirmUser.getEmail()))
//		userService.modifyUser(selectedUser);
//        FacesContext.getCurrentInstance().addMessage(null, message);
//	}
     
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
//	public void createUser(UserVo userVo) {
//		
//	}
//	
//	public void removeUser(UserVo userVo) {
//		
//	}
	
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
