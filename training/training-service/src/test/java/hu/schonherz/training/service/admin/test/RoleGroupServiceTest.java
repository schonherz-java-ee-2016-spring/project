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

import hu.schonherz.training.service.admin.RoleGroupService;
import hu.schonherz.training.service.admin.vo.RoleGroupVo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ManagedBean
public class RoleGroupServiceTest {
	static final Logger logger = LogManager.getLogger(RoleGroupServiceTest.class.getName());

	@EJB
	RoleGroupService serviceLocal;

	@Before
	public void startTheContainer() throws Exception {
		try {
			CreateContext.ejbContainer.getContext().bind("inject", this);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		
		RoleGroupVo rgv = new RoleGroupVo();
		rgv.setName("Teszt Jogcsoport");
		
//		RoleVo role1 = new RoleVo();
//		role1.setName("ROLE1_NAME");
//		role1.setRoleCode("ROLE1_ROLECODE");
//		
//		roleLocal.createRole(role1);
//		
//		RoleVo role2 = new RoleVo();
//		role2.setName("ROLE2_NAME");
//		role2.setRoleCode("ROLE2_ROLECODE");
//		
//		roleLocal.createRole(role2);
//		
//		RoleVo role3 = new RoleVo();
//		role3.setName("ROLE3_NAME");
//		role3.setRoleCode("ROLE3_ROLECODE");
//		
//		roleLocal.createRole(role3);
		
//		rgv.setRoles(Arrays.asList( role1, role2, role3  ));
		
		serviceLocal.createRoleGroup(rgv);
	}
	
	@After
	public void tearDown(){
		try {
			RoleGroupVo rgv = serviceLocal.getRoleGroupByName("Teszt Jogcsoport");
			if( rgv != null )
				serviceLocal.deleteRoleGroup(rgv.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* TESZTELNI:
	 * createRoleGroup kész
	 * deleteRoleGroup kész
	 * getAllRoleGroup kész
	 * getRoleGroupByName kész 
	 * updateRoleGroup kész
	 * */

	@Test
	public void test1CreateRoleGroup(){
		
		RoleGroupVo back = null;
		try {
			back = serviceLocal.getRoleGroupByName("Teszt Jogcsoport");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		Assert.assertEquals("Teszt Jogcsoport", back.getName());
	}
	

	@Test(expected=Exception.class)
	public void test2DeleteRoleGroup() throws Exception{
		RoleGroupVo back = null;
		try {
			back = serviceLocal.getRoleGroupByName("Teszt Jogcsoport");
			serviceLocal.deleteRoleGroup(back.getId());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

		serviceLocal.getRoleGroupByName("Teszt Jogcsoport");
	}

	@Test
	public void test3GetAllRoleGroup(){
		
		List<RoleGroupVo> allRoleGroup = null;
		try {
			allRoleGroup = serviceLocal.getAllRoleGroup();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		Assert.assertNotNull(allRoleGroup);
		Assert.assertTrue( allRoleGroup.size() > 0 );
		
	}

	@Test
	public void test4GetRoleGroupByName(){
		try {
			serviceLocal.getRoleGroupByName("Teszt Jogcsoport");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@Test
	public void test6UpdateRoleGroup() {
		
		try {
			RoleGroupVo rgv = serviceLocal.getRoleGroupByName("Teszt Jogcsoport");
			
			rgv.setName("Another Rolegroup Name");
			
			serviceLocal.updateRoleGroup(rgv);
			
			RoleGroupVo bck = serviceLocal.getRoleGroupByName("Another Rolegroup Name");
			
			Assert.assertEquals("Another Rolegroup Name", bck.getName());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
