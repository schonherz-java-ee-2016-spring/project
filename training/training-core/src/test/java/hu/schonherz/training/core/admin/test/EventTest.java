package hu.schonherz.training.core.admin.test;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.admin.entity.Event;
import hu.schonherz.training.core.admin.entity.Theme;
import hu.schonherz.training.core.admin.repository.EventRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test-core.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class EventTest {

private static final Logger logger = Logger.getLogger(TestTematics.class);
	
	@Autowired
	EventRepository eventRepository;
	
	/*
	 * Tesztelni:
	 * létrehozás
	 * visszaolvasás
	 * */

	@Test
	public void DataCreationTest() {
		Event event = new Event();
		
		event.setName("Esemény1");
		event.setDescription("Esemény1 leírása");
		event.setPlace("Esemény helyszín1");
		event.setTime(new Date());
		event.setType("Esemény típus1");
		
		
		eventRepository.save(event);
	}
	
	@Test
	public void DataFetchTest() {
		
		List<Event> themes = eventRepository.findAll();
		
		Assert.assertTrue( !themes.isEmpty() );
		Assert.assertTrue( themes.size() == 1 );
	}

}
