package test2;

import java.util.List;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="item")
@Access(value=AccessType.FIELD)
public class Item {

	String guid;
	@Column(length=64)
	String itemName;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category")
	Category owningCategory;
	@OneToMany(fetch=FetchType.LAZY,orphanRemoval=true)
	
	List<Sku> skuList;
	
	
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
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Category getOwningCategory() {
		return owningCategory;
	}
	public void setOwningCategory(Category owningCategory) {
		this.owningCategory = owningCategory;
	}
	public List<Sku> getSkuList() {
		return skuList;
	}
	public void setSkuList(List<Sku> skuList) {
		this.skuList = skuList;
	}

}
