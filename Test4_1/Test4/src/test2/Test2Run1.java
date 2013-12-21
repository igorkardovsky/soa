package test2;

import java.sql.DriverManager;
import java.util.LinkedList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test2Run1 {

	public static void main(String[] args) throws Exception {
		EntityManagerFactory emFactory;

	    EntityManager entityManager;
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        DriverManager.getConnection("jdbc:derby:memory:unit-testing-jpa;create=true").close();
        emFactory = Persistence.createEntityManagerFactory("test2");
        entityManager = emFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        Category cat1 = new Category();
        cat1.setGuid("132132");
        cat1.setCategoryName("first");
        Category cat2 = new Category();
        cat2.setGuid("132133");
        cat2.setCategoryName("second");
        cat2.setParent(cat1);
        cat1.setChilds(new LinkedList<Category>());
        cat1.getChilds().add(cat2);
        entityManager.persist(cat2);
        entityManager.persist(cat1);
        entityManager.getTransaction().commit();
      //  entityManager.clear();
        entityManager.getTransaction().begin();
        //http://docs.oracle.com/html/E24396_01/ejb3_overview_query.html
        Category cat3 = (Category)entityManager.createQuery("select x from Category x where x.categoryName=?").setParameter(1, "first").getSingleResult();
        System.err.println(cat3.getCategoryName());
        System.err.println(cat3.getChilds().get(0).getCategoryName());
        entityManager.getTransaction().commit();
        entityManager.close();
        emFactory.close();
       // DriverManager.getConnection("jdbc:derby:memory:unit-testing-jpa;shutdown=true");

	}

}
