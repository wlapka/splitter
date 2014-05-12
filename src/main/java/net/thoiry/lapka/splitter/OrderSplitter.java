/**
 * @author wlapka
 *
 * @created May 12, 2014 11:49:08 AM
 */
package net.thoiry.lapka.splitter;

import java.util.ArrayList;
import java.util.List;

import net.thoiry.lapka.splitter.model.Order;
import net.thoiry.lapka.splitter.model.OrderItem;
import net.thoiry.lapka.splitter.model.SplittedOrderItem;

/**
 * @author wlapka
 * 
 */
public class OrderSplitter implements Splitter<Order, SplittedOrderItem> {

	@Override
	public List<SplittedOrderItem> split(Order order) {
		List<SplittedOrderItem> splittedItems = new ArrayList<>();

		int numberOfItems = order.getOrderItems().size();
		int orderPosition = 1;
		for (OrderItem item : order.getOrderItems()) {
			splittedItems.add(new SplittedOrderItem(order.getId(), item, orderPosition++, numberOfItems));
		}

		return splittedItems;
	}
}
