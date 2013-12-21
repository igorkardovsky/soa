package test2;

import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="uom")
@Access(value=AccessType.FIELD)
public class UnitOfMeasure {
	
		
	String guid;
	@Column(length=40,nullable=false)		
	String name;
	
	boolean weighted;
	
	boolean packed;
	
	@Access(value=AccessType.PROPERTY)
	@Id
	@Column(length=40)

	public String getGuid() {
		if(guid == null){
			guid = UUID.randomUUID().toString();
		}
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
