package hu.schonherz.training.web.admin.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

import hu.schonherz.training.service.admin.RoleGroupService;
import hu.schonherz.training.service.admin.UserGroupService;
import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.RoleGroupVo;
import hu.schonherz.training.service.admin.vo.UserGroupVo;
import hu.schonherz.training.service.admin.vo.UserVo;

@ManagedBean(name = "userGroupsBean")
@ViewScoped
public class UserGroupsBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(UserGroupsBean.class);

	@EJB
	private UserGroupService userGroupService;

	@EJB
	private UserService userService;

	@EJB
	private RoleGroupService roleGroupService;

	@ManagedProperty("#{out}")
	private ResourceBundle bundle;

	private Boolean isDisabled = true;

	private List<UserGroupVo> userGroups;
	private UserGroupVo selected;

	private DualListModel<UserVo> users;
	private List<UserVo> usersSource;
	private List<UserVo> usersTarget;

	private DualListModel<RoleGroupVo> rGroups;
	private List<RoleGroupVo> rGroupsSource;
	private List<RoleGroupVo> rGroupsTarget;

	@PostConstruct
	public void init() {
		try {
			users = new DualListModel<>();
			rGroups = new DualListModel<>();
			selected = new UserGroupVo();
			userGroups = userGroupService.getUserGroups();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void selectGroupListener(SelectEvent event) {
		isDisabled = false;
	}

	public void createAction() {
		selected = new UserGroupVo();
	}

	public void userManageAction() {
		usersSource = new ArrayList<>();
		usersTarget = new ArrayList<>();
		try {
			for (UserVo userVo : userService.findAllUser()) {
				int o = 0;
				for (UserGroupVo group : userVo.getGroups()) {
					if (group.getId().equals(selected.getId())) {
						usersTarget.add(userVo);
						o = 1;
						break;
					}
				}
				if (o != 1) {
					usersSource.add(userVo);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		users = new DualListModel<UserVo>(usersSource, usersTarget);
	}

	public void roleManageAction() {
		rGroupsSource = new ArrayList<>();
		rGroupsTarget = new ArrayList<>();
		try {
			for (RoleGroupVo rGroupVo : roleGroupService.getAllRoleGroup()) {
				int o = 0;
				for (RoleGroupVo rgroup : userGroupService.findGroupByName(selected.getName()).getRoleGroups()) {
					if (rgroup.getId().equals(rGroupVo.getId())) {
						rGroupsTarget.add(rGroupVo);
						o = 1;
						break;
					}
				}
				if (o != 1) {
					rGroupsSource.add(rGroupVo);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		rGroups = new DualListModel<RoleGroupVo>(rGroupsSource, rGroupsTarget);
	}

	public void save() {
		Long isCreateAction = selected.getId();
		try {
			UserGroupVo gvo = userGroupService.findGroupByName(selected.getGroupName());
			if ((gvo != null) && !gvo.getId().equals(selected.getId())) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"),
						bundle.getString("userGroupNameExists"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
				FacesContext.getCurrentInstance().validationFailed();
				return;
			} else {
				userGroupService.saveUserGroup(selected);
				userGroups.remove(selected);
				selected = userGroupService.findGroupByName(selected.getGroupName());
				userGroups.add(selected);
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("succes"),
						bundle.getString("userGroupSaved"));
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		if (isCreateAction == null) {
			selected = new UserGroupVo();
			isDisabled = true;
		}
	}

	public void saveUsers() {
		for (UserVo userVo : users.getSource()) {
			Collection<UserGroupVo> ugvo = userVo.getGroups();
			for (UserGroupVo userGroupVo : ugvo) {
				if (userGroupVo.getId().equals(selected.getId())) {
					ugvo.remove(userGroupVo);
					break;
				}
			}
			userVo.setGroups(ugvo);
			userService.updateUser(userVo);
		}
		for (UserVo userVo : users.getTarget()) {
			Collection<UserGroupVo> ugvo = userVo.getGroups();
			for (UserGroupVo userGroupVo : ugvo) {
				if (userGroupVo.getId().equals(selected.getId())) {
					ugvo.remove(userGroupVo);
					break;
				}
			}
			ugvo.add(selected);
			userVo.setGroups(ugvo);
			userService.updateUser(userVo);
		}
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("succes"),
				bundle.getString("usersSaved"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void saveRoleGroups() {
		List<RoleGroupVo> rgvos = selected.getRoleGroups();
		for (RoleGroupVo rGroupVo : rGroups.getSource()) {
			for (RoleGroupVo rgvo : rgvos) {
				if (rGroupVo.getId().equals(rgvo.getId())) {
					rgvos.remove(rgvo);
					break;
				}
			}
		}
		for (RoleGroupVo rGroupVo : rGroups.getTarget()) {
			for (RoleGroupVo rgvo : rgvos) {
				if (rGroupVo.getId().equals(rgvo.getId())) {
					rgvos.remove(rgvo);
					break;
				}
			}
			rgvos.add(rGroupVo);
		}
		selected.setRoleGroups(rgvos);
		try {
			userGroupService.saveUserGroup(selected);
		} catch (Exception e) {
			logger.error(e);
		}

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("succes"),
				bundle.getString("roleGroupsSaved"));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void deleteGroup() {
		try {
			userGroupService.deleteUserGroup(selected.getId());
			userGroups.remove(selected);
			isDisabled = true;
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("nooption"),
					bundle.getString("nooptiontext"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			logger.error(e);
		}
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public Boolean getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
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

	public DualListModel<UserVo> getUsers() {
		return users;
	}

	public void setUsers(DualListModel<UserVo> users) {
		this.users = users;
	}

	public List<UserVo> getUsersSource() {
		return usersSource;
	}

	public void setUsersSource(List<UserVo> usersSource) {
		this.usersSource = usersSource;
	}

	public List<UserVo> getUsersTarget() {
		return usersTarget;
	}

	public void setUsersTarget(List<UserVo> usersTarget) {
		this.usersTarget = usersTarget;
	}

	public DualListModel<RoleGroupVo> getrGroups() {
		return rGroups;
	}

	public void setrGroups(DualListModel<RoleGroupVo> rGroups) {
		this.rGroups = rGroups;
	}

	public List<RoleGroupVo> getrGroupsSource() {
		return rGroupsSource;
	}

	public void setrGroupsSource(List<RoleGroupVo> rGroupsSource) {
		this.rGroupsSource = rGroupsSource;
	}

	public List<RoleGroupVo> getrGroupsTarget() {
		return rGroupsTarget;
	}

	public void setrGroupsTarget(List<RoleGroupVo> rGroupsTarget) {
		this.rGroupsTarget = rGroupsTarget;
	}
}
