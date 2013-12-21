package test2;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="orderline")
public class OrderItem {
	String guid;
	
	long count;
	
	float weightStart;
	
	float weightEnd;
	
	Sku orderedSku;
	
	Order order;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public float getWeightStart() {
		return weightStart;
	}

	public void setWeightStart(float weightStart) {
		this.weightStart = weightStart;
	}

	public float getWeightEnd() {
		return weightEnd;
	}

	public void setWeightEnd(float weightEnd) {
		this.weightEnd = weightEnd;
	}

	public Sku getOrderedSku() {
		return orderedSku;
	}

	public void setOrderedSku(Sku orderedSku) {
		this.orderedSku = orderedSku;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	
	
}
