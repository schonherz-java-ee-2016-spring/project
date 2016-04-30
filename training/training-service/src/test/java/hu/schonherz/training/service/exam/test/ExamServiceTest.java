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
	ExamService serviceLocal;

	@Before
	public void startTheContainer() throws Exception {
		try {
			CreateContext.ejbContainer.getContext().bind("inject", this);
		} catch (Throwable ex) {
			logger.error(ex.getMessage(), ex);
		}

		ExamVo examVo = new ExamVo();
		examVo.setTitle("JUNIT");
		serviceLocal.save(examVo);
	}

	@After
	public void tearDown() {
		try {
			ExamVo examVo = serviceLocal.getByTitle("JUNIT");
			serviceLocal.removeById(examVo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAllExamTest() {
		try {
			List<ExamVo> examList = serviceLocal.getAll();
			Assert.assertEquals(true, (examList == null ? false : true));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

}
