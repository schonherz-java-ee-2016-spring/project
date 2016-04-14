package hu.schonherz.training.web.managedbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import hu.schonherz.training.service.UserGroupService;
import hu.schonherz.training.vo.UserGroupVo;

@ManagedBean(name = "userGroupBean")
@RequestScoped
public class UserGroupBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserGroupService userGroupService;

	private String groupName;
	private String description;

	public void create() {

		if (userGroupService.findGroupByName(groupName) != null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "WARNING", "User Group already exists!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			UserGroupVo gvo = new UserGroupVo();
			gvo.setName(groupName);
			gvo.setDescription(description);
			try {
				userGroupService.createUserGroup(gvo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS", "User Group created!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
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
