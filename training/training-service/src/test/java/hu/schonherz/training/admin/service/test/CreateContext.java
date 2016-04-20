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

			p.put("training-core.hibernate.hbm2ddl.auto", "create");
			p.put("training-core.hibernate.default_schema", "PUBLIC");
			p.put("training-core.hibernate.transaction.jta.platform",
					"org.apache.openejb.hibernate.OpenEJBJtaPlatform2");
			p.put("training-core.hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
			p.put("training-core.database.test", "new://Resource?type=DataSource");
			p.put("training-core.database.test.JtaManaged", "true");
			p.put("training-core.database.test.JdbcDriver", "org.hsqldb.jdbcDriver");
			p.put("training-core.database.test.JdbcUrl", "jdbc:hsqldb:file:target/db/test_db;shutdown=true");

			
//			<properties>
//			<property name="hibernate.show_sql" value="true" />
//			<property name="hibernate.format_sql" value="true" />
//			<property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect" />
//			<property name="hibernate.hbm2ddl.auto" value="create" />
//			<property name="hibernate.default_schema" value="public" />
//			<property name="hibernate.hbm2ddl.import_files" value="create.sql" />
//			<property name="hibernate.transaction.jta.platform"
//				value="org.hibernate.service.jta.platform.internal.JBossAppServerJtaPlatform" />
//			<property name="hibernate.ejb.entitymanager_factory_name"
//				value="EntityManagerJNDI" />
//		</properties>
			
			ejbContainer = EJBContainer.createEJBContainer(p);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}
	}

}
