package test2;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="order")
public class Order {
	@Id
	@Column(length=32)
	String guid;
	@OneToMany(fetch=FetchType.LAZY,orphanRemoval=true)
	List<OrderItem> orderItems;
	@ManyToOne(fetch=FetchType.LAZY)
	Store store;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
	
	
	
	
}
