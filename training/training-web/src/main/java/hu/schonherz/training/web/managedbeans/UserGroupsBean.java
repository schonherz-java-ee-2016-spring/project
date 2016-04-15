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

	private String groupName;
	private String description;

	private Boolean isSelected = true;

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
		isSelected = false;
	}

	public void create() {
		try {
			if (userGroupService.findGroupByName(groupName) != null) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "WARNING",
						"User Group already exists!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				UserGroupVo gvo = new UserGroupVo();
				gvo.setName(groupName);
				gvo.setDescription(description);
				userGroupService.saveUserGroup(gvo);
				userGroups.add(userGroupService.findGroupByName(gvo.getGroupName()));
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS", "User Group created!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	public void updateGroup() {
		try {
			UserGroupVo gvo = userGroupService.findGroupByName(selected.getGroupName());
			if ((gvo != null) && !gvo.getId().equals(selected.getId())) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "WARNING",
						"User Group already exists!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				userGroupService.saveUserGroup(selected);
				gvo = userGroupService.getUserGroupById(selected.getId());
				selected.setModUser(gvo.getModUser());
				selected.setModDate(gvo.getModDate());
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS", "User Group updated!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
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

	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
