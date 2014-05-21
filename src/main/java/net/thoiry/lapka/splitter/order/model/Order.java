/**
 * @author wlapka
 *
 * @created May 12, 2014 10:05:06 AM
 */
package net.thoiry.lapka.splitter.order.model;

import java.util.List;

/**
 * @author wlapka
 * 
 */
public class Order {

	private final long id;
	private final List<OrderItem> orderItems;

	public Order(long id, List<OrderItem> orderItems) {
		this.id = id;
		this.orderItems = orderItems;
	}

	public long getId() {
		return id;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orders=" + orderItems + "]";
	}

}
