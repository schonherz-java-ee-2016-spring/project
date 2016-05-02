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
import hu.schonherz.training.service.exam.QuestionService;
import hu.schonherz.training.service.exam.vo.ExamVo;
import hu.schonherz.training.service.exam.vo.QuestionVo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ManagedBean
public class QuestionServiceTest {
	static final Logger logger = LogManager.getLogger(QuestionServiceTest.class.getName());

	@EJB
	ExamService examServiceLocal;
	@EJB
	QuestionService questionServiceLocal;

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
		examVo = examServiceLocal.getByTitle("JUNIT alpha test");

		QuestionVo questionVo = new QuestionVo();
		questionVo.setText("Test Question for JUNIT alpha test");
		questionServiceLocal.add(questionVo, examVo.getId());
	}

	@After
	public void tearDown() {
		try {
			ExamVo examVo = examServiceLocal.getByTitle("JUNIT alpha test");
			examServiceLocal.removeById(examVo.getId());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

	@Test(expected = Exception.class)
	public void addTestWithFail() throws Exception {
		questionServiceLocal.add(new QuestionVo(), -1L);
	}

	@Test
	public void getAllTest() {
		try {
			List<QuestionVo> questionList = questionServiceLocal.getAll();
			Assert.assertEquals(true, (questionList == null ? false : true));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

	@Test
	public void getByIdTest() {
		try {
			ExamVo examVo = examServiceLocal.getByTitle("JUNIT alpha test");
			QuestionVo questionVo = questionServiceLocal.getById(examVo.getQuestions().get(0).getId());
			Assert.assertEquals("Test Question for JUNIT alpha test", questionVo.getText());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

	@Test
	public void getAllByExamIdTest() throws Exception {
		ExamVo examVo = examServiceLocal.getByTitle("JUNIT alpha test");
		List<QuestionVo> questionList = questionServiceLocal.getAllByExamId(examVo.getId());
		Assert.assertEquals(1, questionList.size());
	}

	@Test
	public void removeByIdTest() throws Exception {
		ExamVo examVo = new ExamVo();
		examVo.setTitle("JUNIT beta test");
		examServiceLocal.add(examVo);
		examVo = examServiceLocal.getByTitle("JUNIT beta test");

		QuestionVo questionVo = new QuestionVo();
		questionVo.setText("Test Question for JUNIT beta test");
		questionServiceLocal.add(questionVo, examVo.getId());

		questionVo = examServiceLocal.getByTitle("JUNIT beta test").getQuestions().get(0);
		questionServiceLocal.removeById(questionVo.getId());
	}

	@Test(expected = Exception.class)
	public void removeByIdTestWithFail() throws Exception {
		questionServiceLocal.removeById(-1L);
	}

	@Test
	public void modifyNoteTest() throws Exception {
		QuestionVo questionVo = examServiceLocal.getByTitle("JUNIT alpha test").getQuestions().get(0);
		questionVo.setNote("Note");
		questionServiceLocal.modifyNote(questionVo);
		questionVo = examServiceLocal.getByTitle("JUNIT alpha test").getQuestions().get(0);
		Assert.assertEquals("Note", questionVo.getNote());
	}

	@Test
	public void modifyTextTest() throws Exception {
		QuestionVo questionVo = examServiceLocal.getByTitle("JUNIT alpha test").getQuestions().get(0);
		questionVo.setText("Text");
		questionServiceLocal.modifyText(questionVo);
		questionVo = examServiceLocal.getByTitle("JUNIT alpha test").getQuestions().get(0);
		Assert.assertEquals("Text", questionVo.getText());
	}

}
