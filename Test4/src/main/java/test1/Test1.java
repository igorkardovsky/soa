package test1;
//http://www.alexecollins.com/content/tutorial-hibernate-jpa-part-1/

import org.hibernate.LazyInitializationException;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import java.sql.DriverManager;
import java.sql.SQLNonTransientConnectionException;
import java.util.Date;
import java.util.logging.Logger;

import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;

public class Test1 extends TestCase {
	
	private static Logger logger = Logger.getLogger(Test1.class.getName());
	
	  @Override
	    protected void setUp() throws Exception {
	        super.setUp();
	        try {
	            logger.info("Starting in-memory database for unit tests");
	            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
	            DriverManager.getConnection("jdbc:derby:memory:unit-testing-jpa;create=true").close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            fail("Exception during database startup.");
	        }
	    }

	    @Override
	    protected void tearDown() throws Exception {
	        super.tearDown();

	        logger.info("Stopping in-memory database.");
	        try {
	            DriverManager.getConnection("jdbc:derby:memory:unit-testing-jpa;shutdown=true").close();
	        } catch (SQLNonTransientConnectionException ex) {
	            if (ex.getErrorCode() != 45000) {
	                throw ex;
	            }
	            // Shutdown success
	        }
	       
	    }
	    
	@Test
	public void testNewUser() {

		EntityManager entityManager = Persistence.createEntityManagerFactory("tutorialPU").createEntityManager();

		entityManager.getTransaction().begin();

		User user = new User();

		user.setName(Long.toString(new Date().getTime()));

		entityManager.persist(user);

		entityManager.getTransaction().commit();

		// see that the ID of the user was set by Hibernate
		System.out.println("user=" + user + ", user.id=" + user.getId());

		User foundUser = entityManager.find(User.class, user.getId());

		// note that foundUser is the same instance as user and is a concrete
		// class (not a JDX proxy)
		System.out.println("foundUser=" + foundUser);

		assertEquals(user.getName(), foundUser.getName());

		entityManager.close();
	}

	@Test(expected = Exception.class)
	public void testNewUserWithTxn() throws Exception {

		EntityManager entityManager = Persistence.createEntityManagerFactory("tutorialPU").createEntityManager();

		entityManager.getTransaction().begin();
		try {
			User user = new User();

			user.setName(Long.toString(new Date().getTime()));

			entityManager.persist(user);

			if (true) { throw new Exception(); }

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw e;
		}

		entityManager.close();
	}

	@Test
	public void testNewUserAndAddRole() {

		EntityManager entityManager = Persistence.createEntityManagerFactory("tutorialPU").createEntityManager();

		entityManager.getTransaction().begin();

		User user = new User();

		user.setName(Long.toString(new Date().getTime()));

		Role role = new Role();

		role.setName(Long.toString(new Date().getTime()));

		entityManager.persist(user);
		entityManager.persist(role);

		entityManager.getTransaction().commit();

		assertEquals(0, user.getRoles().size());

		entityManager.getTransaction().begin();

		user.addRole(role);

		entityManager.merge(user);

		entityManager.getTransaction().commit();

		assertEquals(1, user.getRoles().size());

		entityManager.close();
	}

	@Test
	public void testFindUser() throws Exception {

		EntityManager entityManager = Persistence.createEntityManagerFactory("tutorialPU").createEntityManager();

		entityManager.getTransaction().begin();

		User user = new User();

		String name = Long.toString(new Date().getTime());

		user.setName(name);

		Role role = new Role();

		role.setName(name);

		user.addRole(role);

		entityManager.persist(role);
		entityManager.persist(user);

		entityManager.getTransaction().commit();

		entityManager.close();

		entityManager = Persistence.createEntityManagerFactory("tutorialPU").createEntityManager();

		User foundUser = entityManager.createNamedQuery("User.findByName", User.class).setParameter("name", name)
				.getSingleResult();

		System.out.println(foundUser);

		assertEquals(name, foundUser.getName());

		assertEquals(1, foundUser.getRoles().size());

		System.out.println(foundUser.getRoles().getClass());

		entityManager.close();
	}

	@Test(expected = LazyInitializationException.class)
	public void testFindUser1() throws Exception {

		EntityManager entityManager = Persistence.createEntityManagerFactory("tutorialPU").createEntityManager();

		entityManager.getTransaction().begin();

		User user = new User();

		String name = Long.toString(new Date().getTime());

		user.setName(name);

		Role role = new Role();

		role.setName(name);

		user.addRole(role);

		entityManager.persist(role);
		entityManager.persist(user);

		entityManager.getTransaction().commit();

		entityManager.close();

		entityManager = Persistence.createEntityManagerFactory("tutorialPU").createEntityManager();

		User foundUser = entityManager.createNamedQuery("User.findByName", User.class).setParameter("name", name)
				.getSingleResult();

		entityManager.close();

		assertEquals(1, foundUser.getRoles().size());

	}
}