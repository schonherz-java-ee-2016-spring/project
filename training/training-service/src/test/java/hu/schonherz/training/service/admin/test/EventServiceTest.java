package hu.schonherz.training.service.admin.test;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import hu.schonherz.training.service.admin.EventService;
import hu.schonherz.training.service.admin.vo.EventVo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ManagedBean
public class EventServiceTest {
	static final Logger logger = LogManager.getLogger(EventServiceTest.class.getName());

	@EJB
	EventService serviceLocal;

	@Before
	public void startTheContainer() throws Exception {
		try {
			CreateContext.ejbContainer.getContext().bind("inject", this);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		
		// minden teszt előtt beregisztrálunk valakit
		EventVo eventVo = new EventVo();
//		eventVo.setId(1L);
		eventVo.setName("EventName");
		eventVo.setType("EventType");
		eventVo.setDescription("EventDescription");
		eventVo.setPlace("EventPlace");
//		eventVo.setTime(new Date());
		serviceLocal.createEvent(eventVo);
	}
	
	@After
	public void tearDown(){
		try {
			EventVo event = serviceLocal.findEventByName("EventName");
			serviceLocal.deleteEventById(event.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* TESZTELNI:
	 * 	void createEvent(EventVo eventVo); kész
	 * 	public List<EventVo> findAllEvent(); kész
	 * 	public EventVo findEventByName(String name); kész
	 * 	public EventVo findEventByType(String type); kész
	 * 	public EventVo findEventById(Long id); kész
	 * 	public EventVo findEventByPlace(String place); kész
	 * 	public void deleteEventById(Long id); kész
	 * 	public void updateEvent(EventVo eventVo); kész
	 * */

	@Test
	public void test1findAllEvent() {
		List<EventVo> events = null;
		try {
			events = serviceLocal.findAllEvent();
			Assert.assertEquals(true, (events == null ? false : true));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test2findEventByName(){
		try {
			EventVo back = serviceLocal.findEventByName("EventName");
			Assert.assertEquals(true, (back == null ? false : true));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void test3findEventByType(){
		try {
			List<EventVo> back = serviceLocal.findEventByType("EventType");
			Assert.assertEquals(true, (back == null ? false : true));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void test4findEventById() {
		try {
			EventVo back = serviceLocal.findEventById(serviceLocal.findEventByName("EventName").getId());
			Assert.assertEquals(true, (back == null ? false : true));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void test5findEventByPlace() {
		try {
			List<EventVo> vos = serviceLocal.findEventByPlace("EventPlace");
			Assert.assertEquals(true, (vos == null ? false : true));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void test6updateEvent(){
		try {
			EventVo event = serviceLocal.findEventByName("EventName");
			
			event.setType("Another EventType");
			serviceLocal.updateEvent(event);
			
			List<EventVo> vos = serviceLocal.findEventByType("Another EventType");
			Assert.assertEquals(true, (vos == null ? false : true));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
