package test2;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sku")
public class Sku {
	
	String guid;
	
	String name;
	
	Item parentItem;
	
	UnitOfMeasure saleUnit;
	
	
	
	
}
