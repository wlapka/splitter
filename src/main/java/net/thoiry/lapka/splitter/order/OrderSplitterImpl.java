/**
 * @author wlapka
 *
 * @created May 12, 2014 11:49:08 AM
 */
package net.thoiry.lapka.splitter.order;

import java.util.ArrayList;
import java.util.List;

import net.thoiry.lapka.splitter.order.model.Order;
import net.thoiry.lapka.splitter.order.model.OrderItem;
import net.thoiry.lapka.splitter.order.model.SplittedOrderItem;
import net.thoiry.lapka.splitter.service.Splitter;

/**
 * @author wlapka
 * 
 */
public class OrderSplitterImpl implements Splitter<Order, SplittedOrderItem> {

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
