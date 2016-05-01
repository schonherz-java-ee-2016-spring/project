package hu.schonherz.training.service.exam.test;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.ExamService;
import hu.schonherz.training.service.exam.ExamUserRelationService;
import hu.schonherz.training.service.exam.vo.ExamUserRelationVo;
import hu.schonherz.training.service.exam.vo.ExamVo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ManagedBean
public class ExamUserRelationServiceTest {
	static final Logger logger = LogManager.getLogger(ExamUserRelationServiceTest.class.getName());

	@EJB
	ExamUserRelationService examUserRelationService;
	@EJB
	UserService userService;
	@EJB
	ExamService examService;

	@Before
	public void startTheContainer() throws Exception {
		try {
			CreateContext.ejbContainer.getContext().bind("inject", this);
		} catch (Throwable ex) {
			logger.error(ex.getMessage(), ex);
		}

		ExamVo examVo = new ExamVo();
		examVo.setTitle("JUNIT");
		examService.add(examVo);

		UserVo userVO = new UserVo();
		userVO.setUserName("IWantToLogin");
		userVO.setPassword("WithMyPassword");
		userVO.setFullName("Mr IWantToLogin");
		userVO.setEmail("IWantToLogin@login.log");
		userService.registrationUser(userVO);

		ExamUserRelationVo examUserRelationVo = new ExamUserRelationVo();
		examUserRelationVo.setExam(examService.getByTitle("JUNIT"));
		examUserRelationVo.setUser(userService.findUserByName("IWantToLogin"));

		examUserRelationService.add(examUserRelationVo);
	}

	@After
	public void tearDown() {
		try {
			ExamVo examVo = examService.getByTitle("JUNIT");
			examUserRelationService.removeAllByExamId(examVo.getId());
			examService.removeById(examService.getByTitle("JUNIT").getId());
			userService.deleteUserById(userService.findUserByName("IWantToLogin").getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test(expected = Exception.class)
	// public void addTestWithFail() throws Exception {
	// ExamVo examVo = new ExamVo();
	// UserVo userVo = new UserVo();
	// ExamUserRelationVo examUserRelationVo = new ExamUserRelationVo();
	// examUserRelationVo.setExam(examVo);
	// examUserRelationVo.setUser(userVo);
	// examUserRelationService.add(examUserRelationVo);
	// }

	@Test
	public void removeAllByUserIdTest() throws Exception {
		examUserRelationService.removeAllByUserId(-1L);
	}

	@Test
	public void removeAl2lByUserIdTest() throws Exception {
		examUserRelationService.removeAllByUserId(-1L);
	}

}
