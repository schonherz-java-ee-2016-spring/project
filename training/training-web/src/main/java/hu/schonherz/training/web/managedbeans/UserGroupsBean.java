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

	/**
	 * UserGroupVo a kiválasztott csoport és a dialog ablakban megjelenő csoport
	 * adataihoz
	 */
	private UserGroupVo selected;

	/**
	 * Kivan-e kapcsolva a szerkesztés,törlés gomb?
	 */
	private Boolean isDisabled = true;

	/**
	 * Init metódus, beolvassuk a csoportokat.
	 */
	@PostConstruct
	public void init() {
		try {
			userGroups = userGroupService.getUserGroups();
			selected = new UserGroupVo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Listener a táblázat selectek érdekében, csoport választásra engedélyezi a
	 * gombokat.
	 * 
	 * @param event
	 */
	public void selectGroupListener(SelectEvent event) {
		isDisabled = false;
	}

	/**
	 * Action metódus ha létrehozásra kattintunk új példányt hozunk létre a
	 * dialoghoz.
	 */
	public void createAction() {
		selected = new UserGroupVo();
	}

	/**
	 * Metódus mely az új csoportok mentését és a meglévők frissítését kezeli.
	 */
	public void save() {
		// Letároljuk hogy milyen az id, ha null akkor létrehozni akart, így a
		// végén megint példányosítunk. Egyébként a kiválasztott csoport id-ja
		// kerül bele.
		Long isCreateAction = selected.getId();
		try {
			// Bekérjük a csoportot a DB-ből név alapján(dialogon beírt név
			// mentés előtt), ha létezik ilyen nevű az adatbázisban és nem azt
			// akarjuk módosítani, akkor nem adható névnek a használt név. Avagy
			// ha újat gyártunk nem adható a használt név névnek.
			UserGroupVo gvo = userGroupService.findGroupByName(selected.getGroupName());
			if ((gvo != null) && !gvo.getId().equals(selected.getId())) {
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "WARNING",
						"User Group name is already exists!");
				FacesContext.getCurrentInstance().addMessage(null, msg);
			} else {
				// Egyébként lementjük a csoportot az új dialog adatokkal,
				// töröljük a táblázatból,
				// betöltjük a DB-ből a változásokat(moddate,moduser,id(ha
				// létrehozás van,stb))
				// Majd beírjuk a táblázatba is.
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
		// Ha létrehozás volt, akkor újat készítünk, ha létre akarna hozni
		// egyből egy másikat is, így nem az előzőleg létrehozott fog módosulni.
		if (isCreateAction == null) {
			selected = new UserGroupVo();
		}
	}

	/**
	 * Csoport törlése, töröljük a DB-ből majd töröljük a listából is.
	 */
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
