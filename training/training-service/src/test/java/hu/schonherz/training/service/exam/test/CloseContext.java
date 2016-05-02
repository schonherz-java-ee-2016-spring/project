package hu.schonherz.training.service.exam.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

public class CloseContext {
	static final Logger logger = LogManager.getLogger(CloseContext.class.getName());

	@Test
	public void stopTheContainer() throws Exception {
		CreateContext.ejbContainer.close();
	}

}
