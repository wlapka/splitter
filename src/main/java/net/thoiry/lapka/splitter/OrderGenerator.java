/**
 * @author wlapka
 *
 * @created May 12, 2014 12:06:23 PM
 */
package net.thoiry.lapka.splitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import net.thoiry.lapka.splitter.model.Order;
import net.thoiry.lapka.splitter.model.OrderItem;

/**
 * @author wlapka
 * 
 */
public class OrderGenerator implements MessageGenerator<Order> {

	private static final AtomicLong NEXTORDERID = new AtomicLong(1);
	private static final int MAXNUMBEROFORDERS = 10;
	private static final int MAXNUMBEROFITEMSINORDER = 20;
	private final Random random = new Random();

	@Override
	public Order generateMessage() {
		return new Order(NEXTORDERID.getAndIncrement(), this.generateOrderItems());
	}

	private List<OrderItem> generateOrderItems() {
		List<OrderItem> orderItems = new ArrayList<>();
		int itemId = 1;
		for (int i = 0; i < random.nextInt(MAXNUMBEROFORDERS); i++) {
			orderItems.add(this.generateOrderItem(itemId++));
		}
		return orderItems;
	}

	private OrderItem generateOrderItem(int id) {
		return new OrderItem(id, "", random.nextInt(MAXNUMBEROFITEMSINORDER));
	}

}
