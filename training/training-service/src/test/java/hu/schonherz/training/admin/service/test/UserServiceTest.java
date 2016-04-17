package hu.schonherz.training.admin.service.test;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import hu.schonherz.training.service.UserService;
import hu.schonherz.training.vo.UserVo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ManagedBean
public class UserServiceTest {
	static final Logger logger = LogManager.getLogger(UserServiceTest.class.getName());

	@EJB
	UserService serviceLocal;

	@Before
	public void startTheContainer() throws Exception {
//		try {
//			CreateContext.ejbContainer.getContext().bind("inject", this);
//		} catch (Throwable e) {
//			logger.error(e.getMessage(), e);
//			throw e;
//		}
		CreateContext.ejbContainer = EJBContainer.createEJBContainer();
//	    service = (CommentService) ejbContainer.getContext().lookup("java:global/classes/CommentService");
	}

	@Test
	public void test1Registration() {
		try {
			UserVo userVO = new UserVo();
			userVO.setUserName("test");
			userVO.setFullName("test");
			userVO.setEmail("test");
			serviceLocal.registrationUser(userVO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	@Test
	public void test2ModifyUser() {
		try {
			UserVo userVO = new UserVo();
			userVO.setId(1L);
			userVO.setUserName("test");
			userVO.setFullName("test");
			userVO.setEmail("test");
			serviceLocal.modifyUser(userVO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test3DeleteUser() {
		try {
			UserVo userVO = new UserVo();
			userVO.setId(1L);
			userVO.setUserName("test");
			userVO.setFullName("test");
			userVO.setEmail("test");
			serviceLocal.deleteUserById(userVO.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
