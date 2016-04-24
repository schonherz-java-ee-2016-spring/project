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

import hu.schonherz.training.service.admin.RoleService;
import hu.schonherz.training.service.admin.vo.RoleVo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ManagedBean
public class RoleServiceTest {
	static final Logger logger = LogManager.getLogger(RoleServiceTest.class.getName());

	@EJB
	RoleService serviceLocal;

	@Before
	public void startTheContainer() throws Exception {
		try {
			CreateContext.ejbContainer.getContext().bind("inject", this);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		
		RoleVo rv = new RoleVo();
		rv.setName("Test_Role");
		rv.setRoleCode("4");
		
		
		serviceLocal.createRole(rv);
	}
	
	@After
	public void tearDown(){
		try {
			RoleVo rv = serviceLocal.getRoleByName("Test_Role");
			if( rv != null )
				serviceLocal.deleteRole(rv.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tesztelt metódusok:
	 * findAllRole
	 * createRole
	 * getRoleByName
	 * getRoleByRoleCode
	 * getRoleById
	 * deleteRole
	 * updateRole
	 */
	
	@Test
	public void test1CreateRole(){
		
		RoleVo test = null;
		try {
			test = serviceLocal.getRoleByName("Test_Role");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		Assert.assertEquals("Test_Role", test.getName());
	}
	
	@Test/*(expected=Exception.class)*/
	public void test2DeleteRole() throws Exception{
		RoleVo test = null;
		try {
			test = serviceLocal.getRoleByName("Test_Role");
			serviceLocal.deleteRole(test.getId());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

//		serviceLocal.getRoleByName("Test_Role");
	}
	
	@Test
	public void test3GetAllRole(){
		
		List<RoleVo> allRole = null;
		try {
			allRole = serviceLocal.findAllRole();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		Assert.assertNotNull(allRole);
		Assert.assertTrue( allRole.size() > 0 );
		
	}

	@Test
	public void test4GetRoleByName(){
		try {
			serviceLocal.getRoleByName("Test_Role");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void test6UpdateRole() {
		
		try {
			RoleVo rv = serviceLocal.getRoleByName("Test_Role");
			
			rv.setName("Another Role Name");
			
			serviceLocal.createRole(rv);
			
			RoleVo test = serviceLocal.getRoleByName("Another Role Name");
			
			Assert.assertEquals("Another Role Name", test.getName());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	

}
