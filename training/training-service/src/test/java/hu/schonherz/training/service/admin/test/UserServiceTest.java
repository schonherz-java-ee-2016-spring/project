package hu.schonherz.training.service.admin.test;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.UserVo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ManagedBean
public class UserServiceTest {
	static final Logger logger = LogManager.getLogger(UserServiceTest.class.getName());

	@EJB
	UserService serviceLocal;

	@Before
	public void startTheContainer() throws Exception {
		try {
			CreateContext.ejbContainer.getContext().bind("inject", this);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@Test
	public void test1Registration() {
		UserVo userVO = new UserVo();
		userVO.setUserName("test");
		userVO.setFullName("test");
		userVO.setEmail("test");
		List<UserVo> users = null;
		try {
			serviceLocal.registrationUser(userVO);
			users = serviceLocal.findAllUser();
			Assert.assertEquals(true, (users == null ? false : true));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Test
	public void test2ModifyUser() {
		UserVo userVO = new UserVo();
		userVO.setId(1L);
		userVO.setUserName("test");
		userVO.setFullName("test");
		userVO.setEmail("test");
		UserVo userVO2 = new UserVo();
		userVO2.setUserName("test2");
		userVO2.setFullName("test2");
		userVO2.setEmail("test2");
		try {
			serviceLocal.registrationUser(userVO);
			serviceLocal.modifyUser(userVO2);
			int confirm = serviceLocal.findUserById(1L).getUserName().length();
			Assert.assertEquals(5, confirm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		
	}
	
	@Test
	public void test3DeleteUser() {
		UserVo userVO = new UserVo();
		userVO.setId(1L);
		userVO.setUserName("test");
		userVO.setFullName("test");
		userVO.setEmail("test");
		UserVo userVO2 = new UserVo();
		userVO2.setId(2L);
		userVO2.setUserName("test2");
		userVO2.setFullName("test2");
		userVO2.setEmail("test2");
		List<UserVo> users = null;
		try {
			serviceLocal.registrationUser(userVO);
			serviceLocal.registrationUser(userVO2);
			serviceLocal.deleteUserById(userVO2.getId());
			users = serviceLocal.findAllUser();
			int confirm = users.size();
			Assert.assertEquals(1, confirm);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
