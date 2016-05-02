package hu.schonherz.training.service.exam.test;

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

import hu.schonherz.training.service.exam.ExamService;
import hu.schonherz.training.service.exam.vo.ExamVo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ManagedBean
public class ExamServiceTest {
	static final Logger logger = LogManager.getLogger(ExamServiceTest.class.getName());

	@EJB
	ExamService examServiceLocal;

	@Before
	public void startTheContainer() throws Exception {
		try {
			CreateContext.ejbContainer.getContext().bind("inject", this);
		} catch (Throwable ex) {
			logger.error(ex.getMessage(), ex);
		}

		ExamVo examVo = new ExamVo();
		examVo.setTitle("JUNIT alpha test");
		examServiceLocal.add(examVo);
	}

	@After
	public void tearDown() {
		try {
			ExamVo examVo = examServiceLocal.getByTitle("JUNIT alpha test");
			examServiceLocal.removeById(examVo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(expected = Exception.class)
	public void addTestWithFail() throws Exception {
		examServiceLocal.add(new ExamVo());
	}

	@Test(expected = Exception.class)
	public void removeByIdTestWithFail() throws Exception {
		examServiceLocal.removeById(-1L);
	}

	@Test
	public void getAllTest() {
		try {
			List<ExamVo> examList = examServiceLocal.getAll();
			Assert.assertEquals(true, (examList == null ? false : true));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}
	
	@Test
	public void getByIdTest() {
		try {
			ExamVo examVo = examServiceLocal.getByTitle("JUNIT alpha test");
			examVo = examServiceLocal.getById(examVo.getId());
			Assert.assertEquals(true, (examVo != null ? true : false));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}
	
	@Test
	public void modifyTitleTest() {
		try {
			ExamVo examVo = examServiceLocal.getByTitle("JUNIT alpha test");
			examVo.setTitle("JUNIT beta test");
			examServiceLocal.modifyTitle(examVo);
			examVo = examServiceLocal.getByTitle("JUNIT beta test");
			Assert.assertEquals(examVo.getTitle(), "JUNIT beta test");
			examVo.setTitle("JUNIT alpha test");
			examServiceLocal.modifyTitle(examVo);
			
			examVo = examServiceLocal.getByTitle("JUNIT alpha test");
			Assert.assertEquals("JUNIT alpha test", examVo.getTitle());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}
	
	@Test(expected = Exception.class)
	public void modifyTitleWithFail() throws Exception {
		ExamVo examVo = new ExamVo();
		examVo.setTitle("JUNIT final test");
		examServiceLocal.add(examVo);
		examVo = examServiceLocal.getByTitle("JUNIT final test");
		examVo.setTitle("JUNIT alpha test");
		examServiceLocal.modifyTitle(examVo);
	}
}
