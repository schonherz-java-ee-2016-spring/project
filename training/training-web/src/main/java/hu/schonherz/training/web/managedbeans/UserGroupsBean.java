package hu.schonherz.training.web.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import hu.schonherz.training.service.UserGroupService;
import hu.schonherz.training.vo.UserGroupVo;

@ManagedBean(name = "userGroupsBean")
@RequestScoped
public class UserGroupsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserGroupService userGroupService;

	private List<UserGroupVo> userGroups;

	private UserGroupVo selected;

	@PostConstruct
	public void init() {
		try {
			userGroups = userGroupService.getUserGroups();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		try {
			userGroupService.deleteUserGroup(selected.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<UserGroupVo> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<UserGroupVo> userGroups) {
		this.userGroups = userGroups;
	}

	public UserGroupVo getSelected() {
		return selected;
	}

	public void setSelected(UserGroupVo selected) {
		this.selected = selected;
	}

}
