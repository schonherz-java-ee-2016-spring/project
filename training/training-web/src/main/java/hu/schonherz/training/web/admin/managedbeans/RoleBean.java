package hu.schonherz.training.web.admin.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import hu.schonherz.training.service.admin.RoleService;
import hu.schonherz.training.service.admin.vo.RoleVo;

@ManagedBean(name = "roleBean")
@ViewScoped
public class RoleBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private RoleService roleService;

	private String roleCode;
	private String roleName;

	private List<RoleVo> roles;
	private RoleVo selectedRole;

	private Boolean disabled = true;

	@ManagedProperty("#{out}")
	private ResourceBundle bundle;

	@PostConstruct
	public void init() {
		try {
			setRoles(roleService.findAllRole());
			selectedRole = new RoleVo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void create() {
		try {
			// ha már van ilyen, akkor hibaüzenet
			if (roleService.getRoleByName(roleName) != null || roleService.getRoleByRoleCode(roleCode) != null) {
				FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
						"Role/Rolecode already exists!");
				FacesContext.getCurrentInstance().addMessage(null, msgs);
				roleName = null;
				roleCode = null;
			} else {
				RoleVo newR = new RoleVo();
				newR.setName(roleName);
				newR.setRoleCode(roleCode);

				roleService.createRole(newR);
				roles.add(roleService.getRoleByName(roleName));
				FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS", "Role created!");
				FacesContext.getCurrentInstance().addMessage(null, msgs);
				roleName = null;
				roleCode = null;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		try {
			roleService.deleteRole(selectedRole.getId());
			roles.remove(selectedRole);

			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS", "Role deleted!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void edit() {
		try {
			disabled = true;
			roleService.createRole(selectedRole);
			roles = roleService.findAllRole();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("succes"), "Role edited!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("error"),
					"Couldn't edit the role!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event) {
		disabled = false;
	}

	public List<RoleVo> getAllRole() {
		List<RoleVo> vos = null;
		try {
			if (roleService.findAllRole() == null) {
				vos = new ArrayList<>();
			} else {
				vos = roleService.findAllRole();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vos;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public List<RoleVo> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleVo> roles) {
		this.roles = roles;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public RoleVo getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(RoleVo selectedRole) {
		this.selectedRole = selectedRole;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
}
