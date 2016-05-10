package hu.schonherz.training.core.supervisor.test;

import java.util.ArrayList;
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

import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.admin.entity.Event;
import hu.schonherz.training.core.admin.entity.Role;
import hu.schonherz.training.core.admin.entity.RoleGroup;
import hu.schonherz.training.core.admin.entity.User;
import hu.schonherz.training.core.admin.test.TestTematics;
import hu.schonherz.training.core.supervisor.entity.Feedback;
import hu.schonherz.training.core.supervisor.repository.FeedbackRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test-core.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional

public class FeedbackTest {

	private static final Logger logger = Logger.getLogger(TestTematics.class);

	@Autowired
	FeedbackRepository feedbackRepository;
	
	Feedback feedback = new Feedback();
	
	@Test
	public void setGetTest() {
		Event event = new Event();
		event.setId((long) 2000);
		String feedbackMessage = "Message";
		boolean isPublic = true;
		User rated = new User();
		rated.setId((long)3000);
		List<User> rateds = new ArrayList<>();
		rateds.add(rated);
		
		feedback.setId((long) 1000);
		Assert.assertTrue(feedback.getId() == 1000);
		feedback.setEvent(event);
		Assert.assertTrue(feedback.getEvent().equals(event));
		feedback.setFeedbackMessage(feedbackMessage);
		Assert.assertTrue(feedback.getFeedbackMessage().contentEquals(feedbackMessage));
		feedback.setPublic(isPublic);
		Assert.assertTrue(feedback.isPublic());
		feedback.setRated(rateds);
		Assert.assertTrue(feedback.getRated().contains(rated));
		Assert.assertTrue(feedback.getRated().equals(rateds));
		
	}
	
}
