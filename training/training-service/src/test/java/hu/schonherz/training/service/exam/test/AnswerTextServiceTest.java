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

import hu.schonherz.training.service.exam.AnswerTextService;
import hu.schonherz.training.service.exam.vo.AnswerTextVo;
import hu.schonherz.training.service.exam.vo.AnswerVo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ManagedBean
public class AnswerTextServiceTest {

	/*
	 * TESTING: getAll getById removeById add getByAnswerId
	 */

	static final Logger logger = LogManager.getLogger(AnswerTextServiceTest.class.getName());

	@EJB
	AnswerTextService answerTextServiceLocal;

	@Before
	public void startTheContainer() throws Exception {
		try {
			CreateContext.ejbContainer.getContext().bind("inject", this);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw e;
		}

		for (int i = 0; i < 3; i++) {
			AnswerTextVo answerText = new AnswerTextVo();
			answerText.setText("Test answer text " + i);
			answerTextServiceLocal.add(answerText);
		}
	}

	@After
	public void tearDown() {
		try {
			List<AnswerTextVo> answerTextList = answerTextServiceLocal.getAll();
			if (!answerTextList.isEmpty()) {
				for (AnswerTextVo answerTextVo : answerTextList) {
					answerTextServiceLocal.removeById(answerTextVo.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test1GetAll() {
		List<AnswerTextVo> answerTextList = new ArrayList<>();
		try {
			answerTextList = answerTextServiceLocal.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		for (int i = 0; i < answerTextList.size(); i++) {
			Assert.assertEquals("Test answer text " + i, answerTextList.get(i).getText());
		}
	}

	@Test
	public void test2GetById() {
		AnswerTextVo answerText = new AnswerTextVo();
		try {
			AnswerTextVo answerTextFirst = answerTextServiceLocal.getAll().get(0);
			answerText = answerTextServiceLocal.getById(answerTextFirst.getId());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals("Test answer text 0", answerText.getText());
	}

	@Test
	public void test3RemoveById() {
		List<AnswerTextVo> answerTextList = new ArrayList<>();
		try {
			AnswerTextVo answerTextFirst = answerTextServiceLocal.getAll().get(0);
			answerTextServiceLocal.removeById(answerTextFirst.getId());
			answerTextList = answerTextServiceLocal.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		for (int i = 0; i < answerTextList.size(); i++) {
			Assert.assertEquals("Test answer text " + (i + 1), answerTextList.get(i).getText());
		}
	}

	@Test
	public void test4Add() {
		List<AnswerTextVo> answerTextList = new ArrayList<>();
		try {
			AnswerTextVo answerText = new AnswerTextVo();
			answerText.setText("Test answer text 3");

			answerTextServiceLocal.add(answerText);
			answerTextList = answerTextServiceLocal.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		for (int i = 0; i < answerTextList.size(); i++) {
			Assert.assertEquals("Test answer text " + i, answerTextList.get(i).getText());
		}
	}

	@Test
	public void test5GetByAnswerId() {
		AnswerTextVo answerText = new AnswerTextVo();
		try {
			answerText.setText("Test answer text 3");
			AnswerVo answer = new AnswerVo();
			answerText.setAnswer(answer);

			answerTextServiceLocal.add(answerText);

			AnswerTextVo answerTextWithAnswer = answerTextServiceLocal.getAll().get(3);
			answerText = answerTextServiceLocal.getByAnswerId(answerTextWithAnswer.getAnswer().getId());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals("Test answer text 3", answerText.getText());
	}
}
