package hu.schonherz.training.service.admin.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CreateContext.class, TrainingServiceTest.class, CloseContext.class })
public class TrainingServiceSuite {
}