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

import hu.schonherz.training.service.exam.QuestionTypeService;
import hu.schonherz.training.service.exam.vo.QuestionTypeVo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ManagedBean
public class QuestionTypeServiceTest {

	static final Logger logger = LogManager.getLogger(QuestionServiceTest.class.getName());

	@EJB
	QuestionTypeService questionTypeService;

	@Before
	public void startTheContainer() throws Exception {
		try {
			CreateContext.ejbContainer.getContext().bind("inject", this);
		} catch (Throwable ex) {
			logger.error(ex.getMessage(), ex);
		}

		QuestionTypeVo questionTypeVo = new QuestionTypeVo();
		questionTypeVo.setName("Teszt");
		questionTypeService.add(questionTypeVo);

	}

	@After
	public void tearDown() {
		try {
			QuestionTypeVo questionTypeVo = questionTypeService.getByName("Teszt");
			questionTypeService.removeById(questionTypeVo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(expected = Exception.class)
	public void saveTestWithFail() throws Exception {
		QuestionTypeVo questionTypeVo = new QuestionTypeVo();
		questionTypeService.add(questionTypeVo);
	}

	@Test(expected = Exception.class)
	public void removeByIdTestWithFail() throws Exception {
		questionTypeService.removeById(-1L);
	}

	@Test
	public void getAllTest() {
		try {
			List<QuestionTypeVo> questionTypeVos = questionTypeService.getAll();
			Assert.assertEquals(true, (questionTypeVos == null ? false : true));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

	@Test
	public void getByIdTest() {
		try {
			QuestionTypeVo questionTypeVo = questionTypeService.getByName("Teszt");
			questionTypeVo = questionTypeService.getById(questionTypeVo.getId());
			Assert.assertEquals(true, (questionTypeVo != null ? true : false));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

}
