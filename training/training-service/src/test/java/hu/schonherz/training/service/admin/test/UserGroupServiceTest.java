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

import hu.schonherz.training.service.admin.UserGroupService;
import hu.schonherz.training.service.admin.vo.UserGroupVo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ManagedBean
public class UserGroupServiceTest {
	static final Logger logger = LogManager.getLogger(UserGroupServiceTest.class.getName());

	@EJB
	UserGroupService service;

	@Before
	public void startTheContainer() throws Exception {
		try {
			CreateContext.ejbContainer.getContext().bind("inject", this);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw e;
		}

		UserGroupVo ugvo = new UserGroupVo();
		ugvo.setGroupName("test");
		ugvo.setDescription("test");
		service.saveUserGroup(ugvo);
	}

	@After
	public void testDeleteUserGroup() {
		try {
			UserGroupVo ugvo = service.findGroupByName("test");
			service.deleteUserGroup(ugvo.getId());
			ugvo = service.findGroupByName("test");
			Assert.assertNull(ugvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSaveUserGroup() {
		try {
			UserGroupVo ugvo = new UserGroupVo();
			UserGroupVo retGroup = new UserGroupVo();
			ugvo.setGroupName("test2");
			ugvo.setDescription("test2");
			service.saveUserGroup(ugvo);
			retGroup = service.findGroupByName("test2");
			Assert.assertEquals(true, (retGroup == null ? false : true));
			Assert.assertEquals(true, (retGroup.getGroupName().equals("test2") ? true : false));
			Assert.assertEquals(true, (retGroup.getDescription().equals("test2") ? true : false));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testGetUserGroups() {
		try {
			List<UserGroupVo> ugvos = service.getUserGroups();
			Assert.assertEquals(true, (ugvos == null ? false : true));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testFindGroupByName() {
		try {
			UserGroupVo retGroup = new UserGroupVo();
			retGroup = service.findGroupByName("test");
			Assert.assertEquals(true, (retGroup == null ? false : true));
			Assert.assertEquals(true, (retGroup.getGroupName().equals("test") ? true : false));
			Assert.assertEquals(true, (retGroup.getDescription().equals("test") ? true : false));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testGetGroupById() {
		try {
			UserGroupVo retGroup = new UserGroupVo();
			UserGroupVo retGroup2 = new UserGroupVo();
			retGroup = service.findGroupByName("test");
			retGroup2 = service.getUserGroupById(retGroup.getId());
			Assert.assertEquals(true, (retGroup2 == null ? false : true));
			Assert.assertEquals(true, (retGroup2.getGroupName().equals("test") ? true : false));
			Assert.assertEquals(true, (retGroup2.getDescription().equals("test") ? true : false));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
