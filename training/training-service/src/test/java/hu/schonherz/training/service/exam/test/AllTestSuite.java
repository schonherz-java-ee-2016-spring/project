package hu.schonherz.training.service.exam.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CreateContext.class, AnswerServiceTest.class, AnswerTextServiceTest.class, ExamServiceTest.class, ExamUserRelationServiceTest.class,
	OptionServiceTest.class, QuestionServiceTest.class, QuestionTypeServiceTest.class, CloseContext.class })
public class AllTestSuite {

}
