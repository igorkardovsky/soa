package test2;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="uom")
public class UnitOfMeasure {
	String guid;
	
	String name;
	
	boolean weighted;
	
	boolean packed;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isWeighted() {
		return weighted;
	}

	public void setWeighted(boolean weighted) {
		this.weighted = weighted;
	}

	public boolean isPacked() {
		return packed;
	}

	public void setPacked(boolean packed) {
		this.packed = packed;
	}
	
	
}
