package hu.schonherz.training.web.admin.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DualListModel;

import hu.schonherz.training.service.admin.EventService;
import hu.schonherz.training.service.admin.UserGroupService;
import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.EventVo;
import hu.schonherz.training.service.admin.vo.UserGroupVo;
import hu.schonherz.training.service.admin.vo.UserVo;

@ManagedBean(name= "eventsBean")
@ViewScoped
public class EventsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
//	private static Logger logger = LoggerFacto
	
	@EJB
	EventService eventService;
	
	@EJB
	UserService userService;
	
	@EJB
	UserGroupService userGroupService;
	
	// az összes eseményt tartalmazó lista
	private List<EventVo> allEvent;
	
	// az összes felhasználócsoport
	private List<UserGroupVo> allUserGroup;
	
	// az összes felhasználó
	private List<UserVo> allUser;
	
	// az éppen kiválasztott esemény
	private EventVo selectedEvent;
	
	// a létrehozandó új esemény neve
	private String newEventName;
	
	// a létrehozandó új esemény típusa
	private String newEventType;
	
	// a létrehozandó új esemény leírása
	private String newEventDescription;
	
	// a létrehozandó új esemény helye
	private String newEventPlace;
	
	// a létrehozandó új esemény ideje
	private Date newEventDate;
	
	// ki van-e választva valamelyik sor?
	private boolean disabled;
	
	// 
	private DualListModel<UserVo> userPickList;
	
	// 
	private DualListModel<UserGroupVo> userGroupPickList;
	
	@PostConstruct
	public void init() {
		// inicializáljuk a két picklistet
		userPickList = new DualListModel<UserVo>();
		userGroupPickList = new DualListModel<UserGroupVo>();
		
		// lekérdezünk mindent is
		try {
			setAllUser(userService.findAllUser());
			setAllUserGroup(userGroupService.getUserGroups());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// új esemény létrehozásakor fut le, az adattagokban benne vannak a megfelelő infók
	public void create(){
//		EventVo 
	}
	
	public void edit(){
		
	}
	
	public void delete(){
		
	}
	
	public void saveManagedUsers(){
		
	}
	
	public void saveManagedUserGroups(){
		
	}
	
	public Boolean getDisabled(){
		return this.disabled;
	}
	
	public void setDisabled( Boolean disabled ){
		this.disabled = disabled;
	}

	/**
	 * @return the allEvent
	 */
	public List<EventVo> getAllEvent() {
		return allEvent;
	}

	/**
	 * @param allEvent the allEvent to set
	 */
	public void setAllEvent(List<EventVo> allEvent) {
		this.allEvent = allEvent;
	}

	/**
	 * @return the selectedEvent
	 */
	public EventVo getSelectedEvent() {
		return selectedEvent;
	}

	/**
	 * @param selectedEvent the selectedEvent to set
	 */
	public void setSelectedEvent(EventVo selectedEvent) {
		this.selectedEvent = selectedEvent;
	}

	/**
	 * @return the newEventName
	 */
	public String getNewEventName() {
		return newEventName;
	}

	/**
	 * @param newEventName the newEventName to set
	 */
	public void setNewEventName(String newEventName) {
		this.newEventName = newEventName;
	}

	/**
	 * @return the newEventType
	 */
	public String getNewEventType() {
		return newEventType;
	}

	/**
	 * @param newEventType the newEventType to set
	 */
	public void setNewEventType(String newEventType) {
		this.newEventType = newEventType;
	}

	/**
	 * @return the newEventDescription
	 */
	public String getNewEventDescription() {
		return newEventDescription;
	}

	/**
	 * @param newEventDescription the newEventDescription to set
	 */
	public void setNewEventDescription(String newEventDescription) {
		this.newEventDescription = newEventDescription;
	}

	/**
	 * @return the newEventPlace
	 */
	public String getNewEventPlace() {
		return newEventPlace;
	}

	/**
	 * @param newEventPlace the newEventPlace to set
	 */
	public void setNewEventPlace(String newEventPlace) {
		this.newEventPlace = newEventPlace;
	}

	/**
	 * @return the newEventDate
	 */
	public Date getNewEventDate() {
		return newEventDate;
	}

	/**
	 * @param newEventDate the newEventDate to set
	 */
	public void setNewEventDate(Date newEventDate) {
		this.newEventDate = newEventDate;
	}

	public DualListModel<UserVo> getUserPickList() {
		return userPickList;
	}

	public void setUserPickList(DualListModel<UserVo> userPickList) {
		this.userPickList = userPickList;
	}

	public DualListModel<UserGroupVo> getUserGroupPickList() {
		return userGroupPickList;
	}

	public void setUserGroupPickList(DualListModel<UserGroupVo> userGroupPickList) {
		this.userGroupPickList = userGroupPickList;
	}

	public List<UserVo> getAllUser() {
		return allUser;
	}

	public void setAllUser(List<UserVo> allUser) {
		this.allUser = allUser;
	}

	public List<UserGroupVo> getAllUserGroup() {
		return allUserGroup;
	}

	public void setAllUserGroup(List<UserGroupVo> allUserGroup) {
		this.allUserGroup = allUserGroup;
	}


}
