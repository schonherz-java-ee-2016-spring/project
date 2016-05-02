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
import hu.schonherz.training.service.exam.OptionService;
import hu.schonherz.training.service.exam.QuestionService;
import hu.schonherz.training.service.exam.vo.ExamVo;
import hu.schonherz.training.service.exam.vo.OptionVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ManagedBean
public class OptionServiceTest {

	/*
	 * TESTING: getAll getById removeById add
	 */

	static final Logger logger = LogManager.getLogger(OptionServiceTest.class.getName());

	@EJB
	OptionService optionServiceLocal;
	@EJB
	QuestionService questionServiceLocal;
	@EJB
	ExamService examServiceLocal;

	@Before
	public void startTheContainer() throws Exception {
		try {
			CreateContext.ejbContainer.getContext().bind("inject", this);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		ExamVo exam = new ExamVo();
		exam.setTitle("Test exam");
		examServiceLocal.add(exam);
		List<ExamVo> examList = examServiceLocal.getAll();

		QuestionVo question = new QuestionVo();
		question.setText("Test question");
		questionServiceLocal.add(question, examList.get(examList.size() - 1).getId());
	}
	
	@After
	public void tearDown() {
		try {
			List<ExamVo> examList = examServiceLocal.getAll();
			examServiceLocal.removeById(examList.get(examList.size() - 1).getId());
			List<QuestionVo> questionList = questionServiceLocal.getAll();
			questionServiceLocal.removeById(questionList.get(questionList.size() - 1).getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testAll() {
		try {
			List<QuestionVo> questionList = questionServiceLocal.getAll();
			
			OptionVo option = new OptionVo();
			option.setText("Test option text");

			// add
			optionServiceLocal.add(option, questionList.get(questionList.size() - 1).getId());

			// getAll
			List<OptionVo> optionList = optionServiceLocal.getAll();
			option = optionList.get(optionList.size() - 1);

			// getById
			option = optionServiceLocal.getById(option.getId());

			// TESTING
			Assert.assertEquals("Test option text", option.getText());

			// removeById
			optionServiceLocal.removeById(option.getId());

			// TESTING
			optionList = optionServiceLocal.getAll();
			for (OptionVo optionVo : optionList) {
				Assert.assertTrue(optionVo.getId().longValue() != option.getId().longValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
