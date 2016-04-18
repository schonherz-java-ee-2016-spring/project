package hu.schonherz.training.web.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import hu.schonherz.training.service.RoleGroupService;
import hu.schonherz.training.service.RoleService;
import hu.schonherz.training.vo.RoleGroupVo;
import hu.schonherz.training.vo.RoleVo;
import hu.schonherz.training.vo.UserGroupVo;
import hu.schonherz.training.vo.UserVo;

@ManagedBean(name= "roleGroupsBean")
@ViewScoped
public class RoleGroupsBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	RoleGroupService roleGroupService;
	
	@EJB
	RoleService roleService;
	
	// ebben tároljuk az összes jogcsoportot
	private List<RoleGroupVo> allRoleGroup;
	
	// az összes létező jog
	private List<RoleVo> allRoles;
	
	// itt tároljuk azt a jogcsoportott amit kiválasztott a user
	private RoleGroupVo selectedRoleGroup;
	
	// a picklisthez
	private DualListModel<RoleVo> selectedRoleGroup_sRoles;
	
	private List<RoleVo> jogcsoportJogai;
	
	// ki van-e választva valamilyen sor?
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
			// betöltjük az összes jogcsoportot
			setAllRoleGroup(roleGroupService.getAllRoleGroup());
			// kezdetben a kiválasztott csoport egy üres rolegroup
			setSelectedRoleGroup(new RoleGroupVo());
			
			// felhozzuk az összes jogot
			allRoles = roleService.getAllRole();
			
			// kezdetben a picklist elemei üresek
			selectedRoleGroup_sRoles = new DualListModel<RoleVo>();

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

		// lekérjük a kiválasztott jogcsoport jogait
		jogcsoportJogai = (List<RoleVo>)selectedRoleGroup.getRoles();
		
		// kivesszük az összes jogok közül azokat amik megvannak ennél a jogcsoportnál
		List<RoleVo> aTobbiJog = new ArrayList<>();
		
//		System.out.println("JOGCsOPORT JOGAI");
//		for (RoleVo roleVo : jogcsoportJogai) {
//			System.out.println( roleVo.getId() );
//		}
		
		
		for (RoleVo roleVo : allRoles) {
			boolean volt = false;
			for ( RoleVo roleVo2 : jogcsoportJogai){
//				System.out.println("Vizsgalat: " + roleVo.getId() + " " + roleVo2.getId());
				if( roleVo.getId().equals(roleVo2.getId()) ){
//					System.out.println("Ilyen jog van mar! "+ roleVo.getId());
					volt = true;				
				}
			}
			if( !volt ){
				aTobbiJog.add(roleVo);
			}
		}
		
//		System.out.println("A TOBBI JOG");
//		for (RoleVo roleVo : aTobbiJog) {
//			System.out.println( roleVo.getId() );
//		}

		// megvan a picklist két oldala!
		selectedRoleGroup_sRoles = new DualListModel<RoleVo>( aTobbiJog, jogcsoportJogai );
		
		
		// ha valaki kiválaszt egy sort, akkor a gombok elérhetővé válnak
		disabled = false;
	}
	
	 public void onTransfer(TransferEvent event) {
	 }
	
	public void saveManaged(){
	
		System.out.println("A JOGCSOPRT UJ JOGAI:");
//		
//		// beállítom a kiválasztott csoport új jogait
		selectedRoleGroup.setRoles(selectedRoleGroup_sRoles.getTarget());
//
		System.out.println( selectedRoleGroup.getRoles() );
//		
//		// elmentem a kivaálsztott jogcsoportot
		roleGroupService.updateRoleGroup(selectedRoleGroup);
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

	public List<RoleVo> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<RoleVo> allRoles) {
		this.allRoles = allRoles;
	}

	public List<RoleVo> getJogcsoportJogai() {
		return jogcsoportJogai;
	}

	public void setJogcsoportJogai(List<RoleVo> jogcsoportJogai) {
		this.jogcsoportJogai = jogcsoportJogai;
	}
	

}
