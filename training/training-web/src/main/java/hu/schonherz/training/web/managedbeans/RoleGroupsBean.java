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

import hu.schonherz.training.service.RoleGroupService;
import hu.schonherz.training.vo.RoleGroupVo;
import hu.schonherz.training.vo.UserGroupVo;

@ManagedBean(name= "roleGroupsBean")
@ViewScoped
public class RoleGroupsBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	RoleGroupService roleGroupService;
	
	private List<RoleGroupVo> allRoleGroup;
	private RoleGroupVo selectedRoleGroup;
	
	private Boolean disabled = true;
	
	
	// bevitelhez szükséges adatok
	private String roleGroupName;
	
	// valahogy meg kellene oldani hogy a jogokat is lehessen módosítani
	
	@PostConstruct
	public void init() {
//		.getId();
//		.getModDate();
//		.getModUser();
//		.getName();
//		.getRecDate();
//		.getRecUser();
//		.getRoles();
		try {
			setAllRoleGroup(roleGroupService.getAllRoleGroup());
			setSelectedRoleGroup(new RoleGroupVo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void create(){
		
		try {
			if (roleGroupService.getRoleGroupByName(roleGroupName) != null ) {
				System.out.println("ALREADY EXISTS");
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "WARNING",
						"Role Group already exists!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				System.out.println("CREATING NEW ROLEGROUP");
				RoleGroupVo newRG = new RoleGroupVo();
				newRG.setName(roleGroupName);
				
				roleGroupService.createRoleGroup(newRG);
				allRoleGroup.add(roleGroupService.getRoleGroupByName(roleGroupName));
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS",
						"Role Group created!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(){
		try {
			roleGroupService.deleteRoleGroup(selectedRoleGroup.getId());
			allRoleGroup.remove(selectedRoleGroup);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event) {
		disabled = false;
	}

	public List<RoleGroupVo> getAllRoleGroup() {
		return allRoleGroup;
	}

	public void setAllRoleGroup(List<RoleGroupVo> allRoleGroup) {
		this.allRoleGroup = allRoleGroup;
	}

	public RoleGroupVo getSelectedRoleGroup() {
		return selectedRoleGroup;
	}

	public void setSelectedRoleGroup(RoleGroupVo selectedRoleGroup) {
		this.selectedRoleGroup = selectedRoleGroup;
	}


	public Boolean getDisabled() {
		return disabled;
	}


	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}


	public String getRoleGroupName() {
		return roleGroupName;
	}


	public void setRoleGroupName(String roleGroupName) {
		this.roleGroupName = roleGroupName;
	}
	

}
