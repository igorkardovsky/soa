package test2;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="store")
public class Store {
	String guid;
	
	String name;
}
