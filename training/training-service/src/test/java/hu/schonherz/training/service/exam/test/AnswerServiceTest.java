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

import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.AnswerService;
import hu.schonherz.training.service.exam.vo.AnswerVo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ManagedBean
public class AnswerServiceTest {

	/*
	 * TESTING: getAll getById removeById add getAllByUserId modifyGood
	 */

	static final Logger logger = LogManager.getLogger(AnswerServiceTest.class.getName());

	@EJB
	UserService userServiceLocal;
	
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

		for (int i = 0; i < 3; i++) {
			AnswerVo answer = new AnswerVo();
			answer.setModUser("TestUser " + i);
			answerServiceLocal.add(answer);
		}
	}

	@After
	public void tearDown() {
		try {
			List<AnswerVo> answerList = answerServiceLocal.getAll();
			if (!answerList.isEmpty()) {
				for (AnswerVo answerVo : answerList) {
					answerServiceLocal.removeById(answerVo.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test1GetAll() {
		List<AnswerVo> answerList = new ArrayList<>();
		try {
			answerList = answerServiceLocal.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		for (int i = 0; i < answerList.size(); i++) {
			Assert.assertEquals("TestUser " + i, answerList.get(i).getModUser());
		}
	}

	@Test
	public void test2GetById() {
		AnswerVo answer = new AnswerVo();
		try {
			AnswerVo answerFirst = answerServiceLocal.getAll().get(0);
			answer = answerServiceLocal.getById(answerFirst.getId());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals("TestUser 0", answer.getModUser());
	}

	@Test
	public void test3RemoveById() {
		List<AnswerVo> answerList = new ArrayList<>();
		try {
			AnswerVo answerFirst = answerServiceLocal.getAll().get(0);
			answerServiceLocal.removeById(answerFirst.getId());
			answerList = answerServiceLocal.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		for (int i = 0; i < answerList.size(); i++) {
			Assert.assertEquals("TestUser " + (i + 1), answerList.get(i).getModUser());
		}
	}

	@Test
	public void test4Add() {
		List<AnswerVo> answerList = new ArrayList<>();
		try {
			AnswerVo answer = new AnswerVo();
			answer.setModUser("TestUser 3");

			answerServiceLocal.add(answer);
			answerList = answerServiceLocal.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		for (int i = 0; i < answerList.size(); i++) {
			Assert.assertEquals("TestUser " + i, answerList.get(i).getModUser());
		}
	}

	// TODO
	// @Test
	// public void test5GetAllByUserId() {
	// List<AnswerVo> answerList = new ArrayList<>();
	// try {
	// UserVo user = new UserVo();
	// user.setEmail("a");
	// user.setFullName("a");
	// user.setPassword("a");
	// user.setUserName("a");
	// user.setActive(true);
	//
	// AnswerVo answer = new AnswerVo();
	// answer.setModUser("TestUser 3");
	//
	// userServiceLocal.registrationUser(user);
	//
	// answer.setUser(userServiceLocal.findUserByName("a"));
	//
	// answerServiceLocal.add(answer);
	//
	// AnswerVo answerWithUser = answerServiceLocal.getAll().get(3);
	// answerList =
	// answerServiceLocal.getAllByUserId(answerWithUser.getUser().getId());
	// } catch (Exception e) {
	// e.printStackTrace();
	// Assert.fail();
	// }
	// for (int i = 0; i < answerList.size(); i++) {
	// Assert.assertEquals("TestUser " + i, answerList.get(i).getModUser());
	// }
	// }

	@Test
	public void test6ModifyGood() {
		AnswerVo answer = new AnswerVo();
		try {
			AnswerVo answerFirst = answerServiceLocal.getAll().get(0);
			answerFirst.setGood(true);
			answerServiceLocal.modifyGood(answerFirst);
			answer = answerServiceLocal.getById(answerFirst.getId());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals("TestUser 0", answer.getModUser());
		Assert.assertEquals(true, answer.getGood());
	}
	
	@Test(expected = Exception.class)
	public void removeByIdTestWithFail() throws Exception {
		answerServiceLocal.removeById(-1L);
	}
	
	@Test
	public void getAllByUserId() throws Exception {
		// minden teszt előtt beregisztrálunk valakit
		UserVo userVO = new UserVo();
//		userVo.setId( 978 );
		userVO.setUserName("IWantToLogin");
		userVO.setPassword("WithMyPassword");
		userVO.setFullName("Mr IWantToLogin");
		userVO.setEmail("IWantToLogin@login.log");
		
		userServiceLocal.registrationUser(userVO);
		userVO = userServiceLocal.findUserByName("IWantToLogin");
		
		AnswerVo answerVo = new AnswerVo();
		answerVo.setUser(userVO);
		
		answerServiceLocal.add(answerVo);
		List<AnswerVo> answerList = answerServiceLocal.getAllByUserId(userVO.getId());
		Assert.assertEquals(1, answerList.size());
	}
}
