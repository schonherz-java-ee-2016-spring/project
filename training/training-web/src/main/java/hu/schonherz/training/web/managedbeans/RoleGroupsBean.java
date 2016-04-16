package hu.schonherz.training.web.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

import hu.schonherz.training.service.RoleGroupService;
import hu.schonherz.training.vo.RoleGroupVo;
import hu.schonherz.training.vo.RoleVo;

@ManagedBean(name= "roleGroupsBean")
@ViewScoped
public class RoleGroupsBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	RoleGroupService roleGroupService;
	
//	RoleService roleService;
	
	private List<RoleGroupVo> allRoleGroup;
	private RoleGroupVo selectedRoleGroup;
	
	private DualListModel<RoleVo> selectedRoleGroup_sRoles;
	
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
//			selectedRoleGroup_sRoles = new DualListModel<RoleVo>( new ArrayList<RoleVo>(),
//					(List<RoleVo>)selectedRoleGroup.getRoles());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void create(){
		
		try {
			if (roleGroupService.getRoleGroupByName(roleGroupName) != null ) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
						"Role Group already exists!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
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
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS",
					"Role Group deleted!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void edit(){
		try {
			disabled = true;
			
			// kitöröljük a listából
			allRoleGroup.remove(selectedRoleGroup);
			
			// beállítjuk az új nevet
			selectedRoleGroup.setName(roleGroupName);
			
			// updateljük a jogcsoportot
			roleGroupService.updateRoleGroup(selectedRoleGroup);
			
			// visszarakjuk a listába
			allRoleGroup.add(roleGroupService.getRoleGroupByName(roleGroupName));
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS",
					"Role Group edited!");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onRowSelect(SelectEvent event) {
//		List<RoleVo> roles = (List<RoleVo>)selectedRoleGroup.getRoles();
//		setSelectedRoleGroup_sRoles(new DualListModel<RoleVo>( new ArrayList<RoleVo>(),  roles));
//		
//		if( selectedRoleGroup_sRoles == null )
//		{
//			System.out.println("selectedRoleGroup_sRoles is NULL!");			
//		} else {
//			System.out.println("selectedRoleGroup_sRoles is NOT NOT NOT NULL!");
//		}
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

	public DualListModel<RoleVo> getSelectedRoleGroup_sRoles() {
		return selectedRoleGroup_sRoles;
	}

	public void setSelectedRoleGroup_sRoles(DualListModel<RoleVo> selectedRoleGroup_sRoles) {
		this.selectedRoleGroup_sRoles = selectedRoleGroup_sRoles;
	}
	

}
