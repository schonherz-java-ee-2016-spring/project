package hu.schonherz.training.core.admin.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import hu.schonherz.training.core.admin.entity.Theme;
import hu.schonherz.training.core.admin.repository.ThemeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test-core.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class TestTematics {
	
	private static final Logger logger = Logger.getLogger(TestTematics.class);
	
	@Autowired
	ThemeRepository themeRepository;
	
	/*
	 * Tesztelni:
	 * létrehozás
	 * visszaolvasás
	 * */

	@Test
	public void DataCreationTest() {
		Theme theme = new Theme();
		
		theme.setName("Tematika1");
		theme.setDescription("Tematika1 leírása");
		theme.setHours(5);
		theme.setThemeCode("TKOD1");
		theme.setType("Theme1 Type");
		
		themeRepository.save(theme);
	}
	
	@Test
	public void DataFetchTest() {
		
		List<Theme> themes = themeRepository.findAll();
		
		Assert.assertTrue( !themes.isEmpty() );
		Assert.assertTrue( themes.size() == 1 );
	}

}
