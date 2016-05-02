package hu.schonherz.training.service.exam.test;

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
		List<ExamVo> examList = examServiceLocal.getAll();
		for (ExamVo exam : examList) {
			examServiceLocal.removeById(exam.getId());
		}
		List<QuestionVo> questionList = questionServiceLocal.getAll();
		for (QuestionVo question : questionList) {
			questionServiceLocal.removeById(question.getId());
		}
		List<OptionVo> optionList = optionServiceLocal.getAll();
		for (OptionVo option : optionList) {
			optionServiceLocal.removeById(option.getId());
		}

		ExamVo exam = new ExamVo();
		exam.setTitle("Test title");
		examServiceLocal.add(exam);
		QuestionVo question = new QuestionVo();
		questionServiceLocal.add(question, examServiceLocal.getAll().get(0).getId());
		OptionVo option = new OptionVo();
		option.setText("Test option text 0");
		optionServiceLocal.add(option, questionServiceLocal.getAll().get(0).getId());
		option.setText("Test option text 1");
		optionServiceLocal.add(option, questionServiceLocal.getAll().get(0).getId());
		option.setText("Test option text 2");
		optionServiceLocal.add(option, questionServiceLocal.getAll().get(0).getId());
		for (OptionVo optionVo : optionServiceLocal.getAll()) {
			System.out.println(optionVo.getText());
		}
	}

	@After
	public void tearDown() {
		try {
			List<ExamVo> examList = examServiceLocal.getAll();
			for (ExamVo exam : examList) {
				examServiceLocal.removeById(exam.getId());
			}
			List<QuestionVo> questionList = questionServiceLocal.getAll();
			for (QuestionVo question : questionList) {
				questionServiceLocal.removeById(question.getId());
			}
			List<OptionVo> optionList = optionServiceLocal.getAll();
			for (OptionVo option : optionList) {
				optionServiceLocal.removeById(option.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test1GetAll() {
		List<OptionVo> optionList = new ArrayList<>();
		try {
			optionList = optionServiceLocal.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		for (int i = 0; i < optionList.size(); i++) {
			Assert.assertEquals("Test option text " + i, optionList.get(i).getText());
		}
	}

	@Test
	public void test2GetById() {
		OptionVo optionText = new OptionVo();
		try {
			OptionVo optionFirst = optionServiceLocal.getAll().get(0);
			optionText = optionServiceLocal.getById(optionFirst.getId());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals("Test option text 0", optionText.getText());
	}

	@Test
	public void test3RemoveById() {
		List<OptionVo> optionList = new ArrayList<>();
		try {
			OptionVo optionFirst = optionServiceLocal.getAll().get(0);
			optionServiceLocal.removeById(optionFirst.getId());
			optionList = optionServiceLocal.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		for (int i = 0; i < optionList.size(); i++) {
			Assert.assertEquals("Test option text " + (i + 1), optionList.get(i).getText());
		}
	}

	@Test
	public void test4Add() {
		List<OptionVo> optionList = new ArrayList<>();
		try {
			OptionVo option = new OptionVo();
			option.setText("Test option text 3");

			optionServiceLocal.add(option, questionServiceLocal.getAll().get(0).getId());
			optionList = optionServiceLocal.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		for (int i = 0; i < optionList.size(); i++) {
			Assert.assertEquals("Test option text " + i, optionList.get(i).getText());
		}
	}
}
