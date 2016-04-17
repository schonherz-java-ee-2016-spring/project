package hu.schonherz.training.web.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

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
	
	private List<UserVo> users;

	@ManagedProperty("#{out}")
	private ResourceBundle bundle;
	
	@PostConstruct
    public void init() {
        try {
			users = userService.findAllUser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	
}
