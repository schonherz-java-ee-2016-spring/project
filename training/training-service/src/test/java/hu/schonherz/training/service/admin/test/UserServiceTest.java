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
	// static final Logger logger =
	// LogManager.getLogger(UserServiceTest.class.getName());
	//
	// @EJB
	// UserService serviceLocal;
	//
	// @Before
	// public void startTheContainer() throws Exception {
	// try {
	// CreateContext.ejbContainer.getContext().bind("inject", this);
	// } catch (Throwable e) {
	// logger.error(e.getMessage(), e);
	// throw e;
	// }
	// }
	//
	// @Test
	// public void test1Registration() {
	// UserVo userVO = new UserVo();
	// userVO.setUserName("test");
	// userVO.setPassword("test");
	// userVO.setFullName("test");
	// userVO.setEmail("test");
	// List<UserVo> users = null;
	// try {
	// serviceLocal.registrationUser(userVO);
	// users = serviceLocal.findAllUser();
	// Assert.assertEquals(true, (users == null ? false : true));
	// } catch (Exception e) {
	// logger.error(e.getMessage(), e);
	// throw new RuntimeException(e);
	// }
	// }

}
