package hu.schonherz.training.admin.service.test;

import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateContext {
	static final Logger logger = LogManager.getLogger(CreateContext.class.getName());

	protected static EJBContainer ejbContainer;

	@Test
	public void startTheContainer() throws Exception {
		try {
			final Properties p = new Properties();

			p.put("hu.schonherz.training.admin.service.test.jpa.hibernate.hbm2ddl.auto", "create");
			p.put("hu.schonherz.training.admin.service.test.jpa.hibernate.default_schema", "PUBLIC");
			p.put("hu.schonherz.training.admin.service.test.jpa.hibernate.transaction.jta.platform",
					"org.apache.openejb.hibernate.OpenEJBJtaPlatform2");
			p.put("hu.schonherz.training.admin.service.test.jpa.hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
			p.put("hu.schonherz.training.admin.service.test.jpa.database.test", "new://Resource?type=DataSource");
			p.put("hu.schonherz.training.admin.service.test.jpa.database.test.JtaManaged", "true");
			p.put("hu.schonherz.training.admin.service.test.jpa.database.test.JdbcDriver", "org.hsqldb.jdbcDriver");
			p.put("hu.schonherz.training.admin.service.test.jpa.database.test.JdbcUrl", "jdbc:hsqldb:file:target/db/test_db;shutdown=true");

			ejbContainer = EJBContainer.createEJBContainer(p);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}
	}

}
