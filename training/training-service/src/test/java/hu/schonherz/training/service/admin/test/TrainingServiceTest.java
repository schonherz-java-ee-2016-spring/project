package hu.schonherz.training.service.admin.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

import hu.schonherz.training.service.admin.RoleService;
import hu.schonherz.training.service.admin.TrainingService;
import hu.schonherz.training.service.admin.vo.RoleVo;
import hu.schonherz.training.service.admin.vo.TrainingVo;
import hu.schonherz.training.service.admin.vo.UserGroupVo;
import hu.schonherz.training.service.admin.vo.UserVo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ManagedBean
public class TrainingServiceTest {
	static final Logger logger = LogManager.getLogger(TrainingServiceTest.class.getName());

	@EJB
	TrainingService serviceLocal;

	@Before
	public void startTheContainer() throws Exception {
		try {
			CreateContext.ejbContainer.getContext().bind("inject", this);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		
		TrainingVo tv = new TrainingVo();
		tv.setName("Test_Training");
		// 946684800 = 2000.01.01 00:00:00
		Date date = new Timestamp(946684800);
		tv.setBeginning(date);
		tv.setEndDate(date);
		List<UserVo> users = new ArrayList<>();
		List<UserGroupVo> userGroups = new ArrayList<>();
		UserVo user = new UserVo();
		UserGroupVo ug = new UserGroupVo();
		ug.setGroupName("Group");
		user.setFullName("User");
		users.add(user);
		userGroups.add(ug);
		tv.setDescription("Test_Desc");
		serviceLocal.saveTraining(tv);
	}
	
	@After
	public void tearDown(){
		try {
			TrainingVo tv = serviceLocal.getTrainingByName("Test_Training");
			if( tv != null )
				serviceLocal.deleteTraining(tv.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void test1CreateTraining() {
		
		TrainingVo test = null;
		try {
			test = serviceLocal.getTrainingByName("Test_Training");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		Assert.assertEquals("Test_Training", test.getName());
	}
	
	@Test
	public void test2DeleteTraining() throws Exception {
		TrainingVo test = null;
		try {
			test = serviceLocal.getTrainingByName("Test_Training");
			serviceLocal.deleteTraining(test.getId());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void test3findAllTraining() {
		List<TrainingVo> allTraining = null;
		try {
			allTraining = serviceLocal.getAllTrainings();
			Assert.assertEquals(true, (allTraining == null ? false : true));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		Assert.assertNotNull(allTraining);
		Assert.assertTrue(allTraining.size() > 0);
	}

	@Test
	public void test4GetTrainingByName() {
		try {
			serviceLocal.getTrainingByName("Test_Training");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void test6getTrainingById() {
		TrainingVo vo = null;
		TrainingVo vo2 = null;
		try {
			vo = serviceLocal.getTrainingByName("Test_Training");
			vo2 = serviceLocal.getTrainingById(vo.getId());
			Assert.assertEquals(true, (vo2 == null ? false : true));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void test8UpdateTraining() {
		try {
			TrainingVo tv = serviceLocal.getTrainingByName("Test_Training");
			tv.setName("Another Training Name");
			serviceLocal.saveTraining(tv);
			TrainingVo test = serviceLocal.getTrainingByName("Another Training Name");
			Assert.assertEquals("Another Training Name", test.getName());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void test9GetUsers() {
		try {
			List<UserVo> users = serviceLocal.getAllUsers(serviceLocal.getTrainingByName("Test_Training").getId());
			Assert.assertEquals("User", users.get(0).getFullName());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void test9GetUserGroups() {
		try {
			List<UserGroupVo> ugs = serviceLocal
					.getAllUserGroups(serviceLocal.getTrainingByName("Test_Training").getId());
			Assert.assertEquals("Group", ugs.get(0).getName());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
