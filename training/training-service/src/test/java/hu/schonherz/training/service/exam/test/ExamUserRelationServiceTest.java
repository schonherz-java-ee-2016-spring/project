package hu.schonherz.training.service.exam.test;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.schonherz.training.service.admin.UserService;
import hu.schonherz.training.service.admin.vo.UserVo;
import hu.schonherz.training.service.exam.ExamService;
import hu.schonherz.training.service.exam.ExamUserRelationService;
import hu.schonherz.training.service.exam.vo.ExamUserRelationVo;
import hu.schonherz.training.service.exam.vo.ExamVo;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
		examVo.setTitle("JUNIT alpha test");
		examService.add(examVo);

		UserVo userVO = new UserVo();
		userVO.setUserName("IWantToLogin");
		userVO.setPassword("WithMyPassword");
		userVO.setFullName("Mr IWantToLogin");
		userVO.setEmail("IWantToLogin@login.log");
		userService.registrationUser(userVO);

		ExamUserRelationVo examUserRelationVo = new ExamUserRelationVo();
		examUserRelationVo.setExam(examService.getByTitle("JUNIT alpha test"));
		examUserRelationVo.setUser(userService.findUserByName("IWantToLogin"));

		examUserRelationService.add(examUserRelationVo);
	}

	@After
	public void tearDown() {
		try {
			ExamVo examVo = examService.getByTitle("JUNIT alpha test");
			examUserRelationService.removeAllByExamId(examVo.getId());
			examService.removeById(examService.getByTitle("JUNIT alpha test").getId());
			userService.deleteUserById(userService.findUserByName("IWantToLogin").getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addTest() throws Exception {
		ExamUserRelationVo examUserRelationVo = new ExamUserRelationVo();
		examUserRelationVo.setExam(null);
		examUserRelationVo.setUser(null);
		examUserRelationService.add(examUserRelationVo);
	}

	@Test
	public void removeAllTest() throws Exception {
		examUserRelationService.removeAllByUserId(-1L);
		examUserRelationService.removeAllByUserId(-1L);
	}

	@Test
	public void getAllExamByUserId() {
		try {
			UserVo userVo = userService.findUserByName("IWantToLogin");
			List<ExamVo> examList = examUserRelationService.getAllExamByUserId(userVo.getId());
			Assert.assertEquals(1, examList.size());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

	@Test
	public void getAllUserByExamId() {
		try {
			ExamVo examVo = examService.getByTitle("JUNIT alpha test");
			List<UserVo> userList = examUserRelationService.getAllUserByExamId(examVo.getId());
			Assert.assertEquals(1, userList.size());
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
	}

}
