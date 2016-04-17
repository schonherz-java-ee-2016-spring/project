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
import org.primefaces.model.DualListModel;

import hu.schonherz.training.service.UserGroupService;
import hu.schonherz.training.service.UserService;
import hu.schonherz.training.vo.UserGroupVo;
import hu.schonherz.training.vo.UserVo;

@ManagedBean(name = "userGroupsBean")
@ViewScoped
public class UserGroupsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UserGroupService userGroupService;

	@EJB
	private UserService userService;

	private List<UserGroupVo> userGroups;

	private DualListModel<UserVo> users;
	private List<UserVo> allUser;
	private List<UserVo> usersSource;
	private List<UserVo> usersTarget;
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
			usersSource = new ArrayList<>();
			usersTarget = new ArrayList<>();
			users = new DualListModel<>();
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

	public void manageAction() {
		usersSource = new ArrayList<>();
		usersTarget = new ArrayList<>();
		try {
			allUser = userService.findAllUser();
			for (UserVo userVo : allUser) {
				int o = 0;
				for (UserGroupVo group : userVo.getGroups()) {
					if (group.getId().equals(selected.getId())) {
						usersTarget.add(userVo);
						o = 1 ;
						// break;
					} 
				}
				if (o != 1) {
					usersSource.add(userVo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		users = new DualListModel<UserVo>(usersSource, usersTarget);
	}

	public void saveUsers() {
		for (UserVo userVo : users.getSource()) {
			userVo.getGroups().remove(selected);
			userService.updateUser(userVo);
		}
		for (UserVo userVo : users.getTarget()) {
			userVo.getGroups().remove(selected);
			userVo.getGroups().add(selected);
			userService.updateUser(userVo);
		}
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

	public List<UserVo> getAllUser() {
		return allUser;
	}

	public void setAllUser(List<UserVo> allUser) {
		this.allUser = allUser;
	}

}
