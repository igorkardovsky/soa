package test1;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.ejb.Ejb3Configuration;


public class Test1Mapper {

	public static void main(String[] args) throws Exception {
		Configuration hibernateConfiguration;
		final Ejb3Configuration ejb3Configuration = new Ejb3Configuration();
		List<Class> entities = new LinkedList<Class>();
		entities.add(test1.User.class);
		entities.add(test1.Role.class);
    	for (final Class entity : entities) {
    		ejb3Configuration.addAnnotatedClass(entity);
    	}
    	Properties dialectProps;
    	dialectProps = new Properties();
    	dialectProps.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");

    	hibernateConfiguration = ejb3Configuration.getHibernateConfiguration();
    	StringBuilder script = new StringBuilder();

    	final String[] creationScript = hibernateConfiguration.generateSchemaCreationScript(Dialect
    			.getDialect(dialectProps));
    	for (final String string : creationScript) {
    		script.append(string).append(";\n");
    	}
  

    	System.out.println(script.toString());
	}

}
