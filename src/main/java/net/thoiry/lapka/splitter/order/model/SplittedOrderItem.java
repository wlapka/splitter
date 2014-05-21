/**
 * @author wlapka
 *
 * @created May 12, 2014 11:14:52 AM
 */
package net.thoiry.lapka.splitter.order.model;

/**
 * @author wlapka
 * 
 */
public class SplittedOrderItem {

	private final long orderId;
	private final OrderItem orderItem;
	private final int position;
	private final int numberOfItems;

	public SplittedOrderItem(long orderId, OrderItem orderItem, int position, int numberOfItems) {
		this.orderId = orderId;
		this.orderItem = orderItem;
		this.position = position;
		this.numberOfItems = numberOfItems;
	}

	public long getOrderId() {
		return orderId;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public int getPosition() {
		return position;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numberOfItems;
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		result = prime * result + ((orderItem == null) ? 0 : orderItem.hashCode());
		result = prime * result + position;
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
		SplittedOrderItem other = (SplittedOrderItem) obj;
		if (numberOfItems != other.numberOfItems)
			return false;
		if (orderId != other.orderId)
			return false;
		if (orderItem == null) {
			if (other.orderItem != null)
				return false;
		} else if (!orderItem.equals(other.orderItem))
			return false;
		if (position != other.position)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SplittedOrderItem [orderId=" + this.orderId + ", orderItem=" + this.orderItem + ", position="
				+ this.position + "/" + this.numberOfItems + "]";
	}
}
