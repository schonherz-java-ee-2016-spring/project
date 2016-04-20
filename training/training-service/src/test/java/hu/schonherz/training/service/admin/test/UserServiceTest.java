package hu.schonherz.training.service.admin.test;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
	
	private UserVo userVO;

	@Test
	public void test1Registration() {
		userVO = new UserVo();
		userVO.setId(1L);
		userVO.setUserName("test");
		userVO.setPassword("test");
		userVO.setFullName("test");
		userVO.setEmail("test");
		try {
			serviceLocal.registrationUser(userVO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test2ModifyUser() {
		try {
			userVO = serviceLocal.findUserByName("test");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		userVO.setUserName("test2");
		userVO.setFullName("test2");
		userVO.setEmail("test2");
		try {
			serviceLocal.modifyUser(userVO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test3DeleteUser() {
		try {
			UserVo vo = serviceLocal.findUserByName("test2");
			serviceLocal.deleteUserById(vo.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
