package hu.schonherz.training.web.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import hu.schonherz.training.service.RoleGroupService;
import hu.schonherz.training.vo.RoleGroupVo;

@ManagedBean(name= "roleGroupsBean")
public class RoleGroupsBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	RoleGroupService roleGroupService;
	
	private List<RoleGroupVo> allRoleGroup;
	private RoleGroupVo selectedRoleGroup;
	
	
	// bevitelhez szükséges adatok
	String roleGroupName;
	
	// valahogy meg kellene oldani hogy a jogokat is lehessen módosítani
	
	@PostConstruct
	public void init() {
//		allRoleGroup.get(0).getId();
//		allRoleGroup.get(0).getModDate();
//		allRoleGroup.get(0).getModUser();
//		allRoleGroup.get(0).getName();
//		allRoleGroup.get(0).getRecDate();
//		allRoleGroup.get(0).getRecUser();
//		allRoleGroup.get(0).getRoles();
		try {
			setAllRoleGroup(roleGroupService.getAllRoleGroup());
			setSelectedRoleGroup(new RoleGroupVo());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	

}
