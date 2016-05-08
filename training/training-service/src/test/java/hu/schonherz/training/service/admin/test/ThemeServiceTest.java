package hu.schonherz.training.service.admin.test;

import java.util.ArrayList;
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

import hu.schonherz.training.service.admin.ThemeService;
import hu.schonherz.training.service.admin.vo.ThemeVo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ManagedBean
public class ThemeServiceTest {
	static final Logger logger = LogManager.getLogger(ThemeServiceTest.class.getName());

	@EJB
	ThemeService serviceLocal;

	@Before
	public void startTheContainer() throws Exception {
		try {
			CreateContext.ejbContainer.getContext().bind("inject", this);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		
		// minden teszt előtt beregisztrálunk valakit
		ThemeVo theme = new ThemeVo();
		theme.setName("ThemeName");
		theme.setThemeCode("1");
		theme.setType("MainTheme");
		serviceLocal.createTheme(theme);
	}
	
	@After
	public void tearDown(){
		try {
			ThemeVo theme = serviceLocal.getThemeByName("ThemeName");
			serviceLocal.deleteTheme(theme.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* TESZTELNI:
	 * findAllTheme 
	 * createTheme kész
	 * deleteTheme kész
	 * getThemeByName
	 * getThemeByThemeCode
	 * getThemesByType
	 * */

	@Test
	public void test1findAllTheme() {
		List<ThemeVo> themes = null;
		try {
			themes = serviceLocal.findAllTheme();
			Assert.assertEquals(true, (themes == null ? false : true));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test2getThemeByName(){
		
		ThemeVo back = null;
		try {
			back = serviceLocal.getThemeByName("ThemeName");
			Assert.assertEquals(true, (back == null ? false : true));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Test
	public void test3getThemesByType(){
		
		List<ThemeVo> back = new ArrayList<>();
		try {
			back = serviceLocal.getThemesByType("MainTheme");
			Assert.assertEquals(true, (back == null ? false : true));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
