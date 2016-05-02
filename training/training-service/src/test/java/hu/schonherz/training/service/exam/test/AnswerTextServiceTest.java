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

import hu.schonherz.training.service.exam.AnswerService;
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
	@EJB
	AnswerService answerServiceLocal;

	@Before
	public void startTheContainer() throws Exception {
		try {
			CreateContext.ejbContainer.getContext().bind("inject", this);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw e;
		}

		AnswerVo answer = new AnswerVo();
		answerServiceLocal.add(answer);
	}

	@After
	public void tearDown() {
		try {
			List<AnswerVo> answerList = answerServiceLocal.getAll();
			answerServiceLocal.removeById(answerList.get(answerList.size() - 1).getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAll() {
		try {
			List<AnswerVo> answerList = answerServiceLocal.getAll();
			AnswerVo answer = answerList.get(answerList.size() - 1);

			AnswerTextVo answerText = new AnswerTextVo();
			answerText.setText("Test answer text");
			answerText.setAnswer(answer);

			// add
			answerTextServiceLocal.add(answerText);

			// getAll
			List<AnswerTextVo> answerTextList = answerTextServiceLocal.getAll();
			answerText = answerTextList.get(answerTextList.size() - 1);

			// getById
			answerText = answerTextServiceLocal.getById(answerText.getId());

			// TESTING
			Assert.assertEquals("Test answer text", answerText.getText());

			// getByAnswerId
			answerText = answerTextServiceLocal.getByAnswerId(answer.getId());

			// TESTING
			Assert.assertEquals("Test answer text", answerText.getText());

			// removeById
			answerTextServiceLocal.removeById(answerText.getId());

			// TESTING
			answerTextList = answerTextServiceLocal.getAll();
			for (AnswerTextVo answerTextVo : answerTextList) {
				Assert.assertTrue(answerTextVo.getId().longValue() != answerText.getId().longValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
