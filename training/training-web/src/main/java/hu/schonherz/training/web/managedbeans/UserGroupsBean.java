package hu.schonherz.training.web.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import hu.schonherz.training.service.UserGroupService;
import hu.schonherz.training.vo.UserGroupVo;

@ManagedBean(name = "userGroupsBean")
@ViewScoped
public class UserGroupsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserGroupService userGroupService;

	private List<UserGroupVo> userGroups;

	private UserGroupVo selected;
	private Boolean isDisabled = true;

	@PostConstruct
	public void init() {
		try {
			userGroups = userGroupService.getUserGroups();
			selected = new UserGroupVo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectGroupListener(SelectEvent event) {
		isDisabled = false;
	}

	public void createAction() {
		selected = new UserGroupVo();
	}

	public void save() {
		Long isCreateAction = selected.getId();
		try {
			UserGroupVo gvo = userGroupService.findGroupByName(selected.getGroupName());
			if ((gvo != null) && !gvo.getId().equals(selected.getId())) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "WARNING",
						"User Group name is already exists!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				userGroupService.saveUserGroup(selected);
				userGroups.remove(selected);
				selected = userGroupService.findGroupByName(selected.getGroupName());
				userGroups.add(selected);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS", "User Group saved!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (isCreateAction == null) {
			selected = new UserGroupVo();
		}
	}

	public void deleteGroup() {
		try {
			userGroupService.deleteUserGroup(selected.getId());
			userGroups.remove(selected);
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

	public Boolean getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
}
