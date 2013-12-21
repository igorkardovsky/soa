package test1;

import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.ejb.HibernatePersistence;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class Test1Mapper2 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Configuration cfg = new Ejb3Configuration().configure(
				"tutorialPU", null).getHibernateConfiguration();

		SchemaExport export = new SchemaExport(cfg);
		//HibernatePersistence pers = new HibernatePersistence();
		//pers.createEntityManagerFactory("tutorialPU", null).
		export.execute(true, false, false, false);
	}

}
