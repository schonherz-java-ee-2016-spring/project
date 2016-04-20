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
		
		// minden teszt előtt beregisztrálunk valakit
		UserVo userVO = new UserVo();
//		userVo.setId( 978 );
		userVO.setUserName("IWantToLogin");
		userVO.setPassword("WithMyPassword");
		userVO.setFullName("Mr IWantToLogin");
		userVO.setEmail("IWantToLogin@login.log");
		
		serviceLocal.registrationUser(userVO);
	}
	
	@After
	public void tearDown(){
		try {
			UserVo user = serviceLocal.findUserByName("IWantToLogin");
			serviceLocal.deleteUserById(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* TESZTELNI:
	 * findUserByName kész
	 * registrationUser kész
	 * findUserByEmail kész
	 * findAllUser kész
	 * deleteUserById 
	 * modifyUser kész
	 * findUserById
	 * updateUser kész
	 * */

	@Test
	public void test1FindAllUser() {
		List<UserVo> users = null;
		try {
			users = serviceLocal.findAllUser();
			Assert.assertEquals(true, (users == null ? false : true));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test2FindByUsername(){
		
		// először regisztráljunk be valakit
		UserVo userVO = new UserVo();
		userVO.setUserName("IWantToLogin");
		userVO.setPassword("WithMyPassword");
		userVO.setFullName("Mr IWantToLogin");
		userVO.setEmail("IWantToLogin@login.log");
		
		// nézzük meg hogy vissza tudjuk-e hozni
		UserVo back = null;
		try {
			back = serviceLocal.findUserByName("IWantToLogin");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		Assert.assertEquals("Mr IWantToLogin", back.getFullName());
	}
	
	@Test
	public void test3FindUserByEmail(){
		
		// nézzük meg hogy vissza tudjuk-e hozni email alapján
		UserVo back = null;
		try {
			back = serviceLocal.findUserByEmail("IWantToLogin@login.log");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
				
		Assert.assertEquals("IWantToLogin@login.log", back.getEmail());
	}
	
//	@Test(expected=Exception.class)
//	public void test4DeleteUserById(){
//		try {
//			serviceLocal.deleteUserById( 978L );
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail();
//		}
//		
//		serviceLocal.findUserById( 978L );
//	}
	
	@Test
	public void test5ModifyUser(){
		
		// megkeressük az user-t
		try {
			UserVo user = serviceLocal.findUserByName("IWantToLogin");
			
			user.setFullName("AnotherUser Name");
			serviceLocal.modifyUser(user);
			
			UserVo back = serviceLocal.findUserByName("IWantToLogin");
			
			Assert.assertEquals("AnotherUser Name", back.getFullName());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void test6UpdateUser(){
		try {
			UserVo user = serviceLocal.findUserByName("IWantToLogin");
			
			user.setFullName("AnotherUser Name");
			serviceLocal.updateUser(user);
			
			UserVo back = serviceLocal.findUserByName("IWantToLogin");
			
			Assert.assertEquals("AnotherUser Name", back.getFullName());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
//	@Test
//	public void test7FindUserById(){
//		UserVo user = serviceLocal.findUserById( 978L );
//		
//		Assert.assertEquals("IWantToLogin", user.getUserName());
//	}

}
