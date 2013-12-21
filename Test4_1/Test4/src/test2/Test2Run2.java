package test2;

import java.sql.DriverManager;
import java.util.LinkedList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test2Run2 {

	public static void main(String[] args) throws Exception{
		EntityManagerFactory emFactory;

	    EntityManager entityManager;
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        DriverManager.getConnection("jdbc:derby:memory:unit-testing-jpa;create=true").close();
        emFactory = Persistence.createEntityManagerFactory("test2");
        entityManager = emFactory.createEntityManager();
        
        entityManager.getTransaction().begin();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom1.setName("ךד");
        uom1.setPacked(false);
        uom1.setWeighted(true);

        uom2.setName("רע");
        uom2.setPacked(false);
        uom2.setWeighted(true);
        
        Sku sku1 = new Sku();
        sku1.setName("46 נאחלונ");
        sku1.setSaleUnit(uom2);
        Item item1 = new Item();
        item1.setItemName("Shoes");
        
        sku1.setParentItem(item1);
        
        
        
       
        entityManager.persist(uom1);
        entityManager.persist(uom2);
        entityManager.persist(sku1);
        entityManager.persist(item1);
        entityManager.getTransaction().commit();
        entityManager.clear();
        entityManager.createQuery("select i from  Item i join i.skuList s join s.saleUnit su  where su.name=?").setParameter(1, "ךד").getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();
        emFactory.close();
       // DriverManager.getConne

	}

}
