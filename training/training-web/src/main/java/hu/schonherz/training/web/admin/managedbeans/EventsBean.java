package hu.schonherz.training.web.admin.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
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
	private boolean disabled = true;
	
	// 
	private DualListModel<UserVo> userPickList;
	
	// 
	private DualListModel<UserGroupVo> userGroupPickList;
	
	@PostConstruct
	public void init() {
		
		// betöltjük az eddigi összes eseményt
		try {
			allEvent = eventService.findAllEvent();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// inicializáljuk a két picklistet
		userPickList = new DualListModel<UserVo>();
		userGroupPickList = new DualListModel<UserGroupVo>();
		
		// lekérdezünk mindent is
		try {
			allUser = userService.findAllUser();
			allUserGroup = userGroupService.getUserGroups();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// új esemény létrehozásakor fut le, az adattagokban benne vannak a megfelelő infók
	public void create(){
		
		EventVo newEvent = new EventVo();
		newEvent.setName(newEventName);
		newEvent.setType(newEventType);
		newEvent.setDescription(newEventDescription);
		newEvent.setPlace(newEventPlace);
		newEvent.setDate(newEventDate);
		
		// kezdetben nincs se user se usergroup hozzárendelve
		newEvent.setGroups(null);
		newEvent.setUsers(null);
		
		// létrehozzuk az eseményt
		eventService.createEvent(newEvent);
		
//		 hozzáadjuk az új eseményt a táblázathoz
		try {
			allEvent.add(eventService.findEventByName(newEventName));
		} catch (Exception e) {
			System.out.println("Nem találom");
			e.printStackTrace();
		}
		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS",
				"Event created!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	// ha kiválasztunk egy sort a táblázatban, akkor fut le
	public void onRowSelect(SelectEvent event) {
		
		// TODO ha kiválasztok egy sort, akkor a field-ekbe töltődjenek be a kiválasztott
		// esemény adatai és edit-nél azok jelenjenek meg
//		newEventName = selectedEvent.getName();
//		newEventType = selectedEvent.getType();
//		newEventDescription = selectedEvent.getDescription();
//		newEventPlace = selectedEvent.getPlace();
//		newEventDate = selectedEvent.getDate();

		// lekérjük a kiválasztott eseményhez tartozó felhasználókat
		List<UserVo> esemenyFelhasznaloi = (List<UserVo>)selectedEvent.getUsers();

		// lekérjük a kiválasztott eseményhez tartozó felhasználó csoportokat
		List<UserGroupVo> esemenyFelhasznaloCsoportjai = (List<UserGroupVo>)selectedEvent.getGroups();
		
		// a picklist bal oldala úgy áll elő, hogy
		// kivesszük az összes user közül azokat, akik már szerepelnek a kiválasztott eseménynél és
		// a maradékot belerakjuk ebbe a listába
		List<UserVo> megNemSzereploFelhasznalok = new ArrayList<>();		
		
		// itt csináljuk meg a picklist bal oldalát
		// végigmegyünk az összes létező felhasználón
		for (UserVo userVo : allUser) {
			boolean volt = false;
			// végigmegyünk a kiválasztott esemény felhasználóin
			for ( UserVo userVo2 : esemenyFelhasznaloi ){
				// ha van egyezés, akkor nem fogjuk belerakni a listába azt a felhasználót
				if( userVo.getId().equals(userVo2.getId()) ){
					volt = true;				
				}
			}
			// ha nem volt ilyen felhasználó akkor belerakjuk a listába
			if( !volt ){
				megNemSzereploFelhasznalok.add(userVo);
			}
		}

		// megvan a picklist két oldala!
		userPickList = new DualListModel<UserVo>( megNemSzereploFelhasznalok, esemenyFelhasznaloi );
		
		// Ugyanezt meg kell csinálni a felhasználó csoportokra
		
		// a picklist bal oldala úgy áll elő, hogy
		// kivesszük az összes usergroup közül azokat, amelyek már szerepelnek a kiválasztott eseménynél és
		// a maradékot belerakjuk ebbe a listába
		List<UserGroupVo> megNemSzereploFelhasznaloCsoportok = new ArrayList<>();		
				
		// itt csináljuk meg a picklist bal oldalát
		// végigmegyünk az összes létező felhasználócsoporton
		for (UserGroupVo userGroupVo : allUserGroup) {
			boolean volt = false;
			// végigmegyünk a kiválasztott esemény felhasználócsoportjain
			for ( UserGroupVo userGroupVo2 : esemenyFelhasznaloCsoportjai){
				// ha van egyezés, akkor nem fogjuk belerakni a listába azt a felhasználó csoportot
				if( userGroupVo.getId().equals(userGroupVo2.getId()) ){
					volt = true;				
				}
			}
			// ha nem volt ilyen jog akkor belerakjuk a listába
			if( !volt ){
				megNemSzereploFelhasznaloCsoportok.add(userGroupVo);
			}
		}

		// megvan a picklist két oldala!
		userGroupPickList = new DualListModel<UserGroupVo>( megNemSzereploFelhasznaloCsoportok, esemenyFelhasznaloCsoportjai );
		
		// ha valaki kiválaszt egy sort, akkor a gombok elérhetővé válnak
		disabled = false;
	}
	
	// meglévő esemény módosításakor fut le, a kijelölt esemény a selectedEvent-ben van. 
	public void edit(){
		// a kiválasztott esemény új adatai a field-ekben vannak (newEventName stb..)
		
		// megkeressük a listában a kiválasztott eseményt és átírjuk az adatait
		allEvent.get(allEvent.indexOf(selectedEvent)).setName(newEventName);
		
		allEvent.get(allEvent.indexOf(selectedEvent)).setType(newEventType);
		
		allEvent.get(allEvent.indexOf(selectedEvent)).setDescription(newEventDescription);
		
		allEvent.get(allEvent.indexOf(selectedEvent)).setPlace(newEventPlace);
		
		allEvent.get(allEvent.indexOf(selectedEvent)).setDate(newEventDate);
		
		// updateljük
		eventService.updateEvent(allEvent.get(allEvent.indexOf(selectedEvent)));
		
		// lefrissítjük a táblázatot
		try {
			allEvent.set(allEvent.indexOf(selectedEvent), eventService.findEventByName(newEventName));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS",
				"Event edited!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void delete(){
		try {
			eventService.deleteEventById(selectedEvent.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		allEvent.remove(selectedEvent);
		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS",
				"Event deleted!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		disabled = true;
	}
	
	public void saveManagedUsers(){
		
		// beállítjuk a kiválasztott esemény felhasználóit a picklist jobb oldalára
		allEvent.get(allEvent.indexOf(selectedEvent)).setUsers(userPickList.getTarget());
		
		// updateljük az eseményt
		eventService.updateEvent(allEvent.get(allEvent.indexOf(selectedEvent)));
		
		// lefrissítjük a táblázatot
		try {
			allEvent.set(allEvent.indexOf(selectedEvent), eventService.findEventByName(selectedEvent.getName()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS",
				"Event's users edited!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void saveManagedUserGroups(){
		// beállítjuk a kiválasztott esemény felhasználóit a picklist jobb oldalára
		allEvent.get(allEvent.indexOf(selectedEvent)).setGroups(userGroupPickList.getTarget());
			
		// updateljük az eseményt
		eventService.updateEvent(allEvent.get(allEvent.indexOf(selectedEvent)));
				
		// lefrissítjük a táblázatot
		try {
			allEvent.set(allEvent.indexOf(selectedEvent), eventService.findEventByName(selectedEvent.getName()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCCESS",
				"Event's users groups edited!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
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
