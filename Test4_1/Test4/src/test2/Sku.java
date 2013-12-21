package test2;
//http://stackoverflow.com/questions/6608812/jpa-give-a-name-to-a-foreign-key-on-db
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="sku")
@Access(value=AccessType.FIELD)
public class Sku {
	
	String guid;
	@Column(length=32)	
	String name;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="item_guid")
	@ForeignKey(name="FK_SKU_ITEM") 
	Item parentItem;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="uom_guid")
	UnitOfMeasure saleUnit;

	@Id
	@Column(length=40)	
	@Access(value=AccessType.PROPERTY)
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

	public Item getParentItem() {
		return parentItem;
	}

	public void setParentItem(Item parentItem) {
		this.parentItem = parentItem;
	}

	public UnitOfMeasure getSaleUnit() {
		return saleUnit;
	}

	public void setSaleUnit(UnitOfMeasure saleUnit) {
		this.saleUnit = saleUnit;
	}
	
	
	
	
}
