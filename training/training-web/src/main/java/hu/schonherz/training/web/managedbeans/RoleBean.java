package hu.schonherz.training.web.managedbeans;

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

import hu.schonherz.training.service.RoleService;
import hu.schonherz.training.vo.RoleVo;

@ManagedBean(name="RoleBean")
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
			setSelectedRole(new RoleVo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void create(){
		try {
			if (roleService.getRoleByName(roleName) != null || roleService.getRoleByRoleCode(roleCode)!= null) {
				FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
						"Role already exists!");
				FacesContext.getCurrentInstance().addMessage(null, msgs);
			} else {
				RoleVo newR = new RoleVo();
				newR.setName(roleName);
				newR.setRoleCode(roleCode);
				
				roleService.createRole(newR);
				roles.add(roleService.getRoleByName(roleName));
				FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS",
						"Role created!");
				FacesContext.getCurrentInstance().addMessage(null, msgs);
				roleName = null;
				roleCode = null;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(){
		try {
			roleService.deleteRole(selectedRole.getId());
			roles.remove(selectedRole);
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS",
					"Role deleted!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			roleName = null;
			roleCode = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void edit(){
		System.out.println("EEEEEEEEEEEEEEEEEEE");
		try {
			disabled = true;
			
			// kitöröljük a listából
			roles.remove(selectedRole);
			
			// beállítjuk az új nevet
			selectedRole.setName(roleName);
			
			// updateljük a jogcsoportot
			roleService.updateRole(selectedRole);
			
			// visszarakjuk a listába
			roles.add(roleService.getRoleByName(roleName));
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS",
					"Role edited!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			roleName = null;
			roleCode = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onRowSelect(SelectEvent event) {
		disabled = false;
	}
	
	public void setVariableToNull(){
		roleCode = null;
		roleName = null;
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
			// TODO Auto-generated catch block
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
