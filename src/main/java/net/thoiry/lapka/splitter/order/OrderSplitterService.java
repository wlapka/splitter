/**
 * @author wlapka
 *
 * @created May 12, 2014 10:06:27 AM
 */
package net.thoiry.lapka.splitter.order;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import net.thoiry.lapka.splitter.order.model.Order;
import net.thoiry.lapka.splitter.order.model.SplittedOrderItem;
import net.thoiry.lapka.splitter.service.MessageGenerator;
import net.thoiry.lapka.splitter.service.Receiver;
import net.thoiry.lapka.splitter.service.Sender;
import net.thoiry.lapka.splitter.service.Splitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wlapka
 * 
 */
public class OrderSplitterService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderSplitterService.class);
	private static final int NUMBEROFTHREADS = 3;
	private static final int EXECUTIONTIMEINMILISECONDS = 1000;
	private final CountDownLatch countDownLatch = new CountDownLatch(NUMBEROFTHREADS);
	private final ExecutorService executorService = Executors.newFixedThreadPool(NUMBEROFTHREADS);
	private final BlockingQueue<Order> inOrderQueue = new LinkedBlockingQueue<>();
	private final BlockingQueue<SplittedOrderItem> outItemQueue;
	private final MessageGenerator<Order> orderGenerator = new OrderGeneratorImpl();
	private final Sender<Order> sender1 = new Sender<>(inOrderQueue, orderGenerator, countDownLatch);
	private final Sender<Order> sender2 = new Sender<>(inOrderQueue, orderGenerator, countDownLatch);
	private final Splitter<Order, SplittedOrderItem> splitter = new OrderSplitterImpl();
	private final Receiver<Order, SplittedOrderItem> orderReceiver;

	public OrderSplitterService() {
		this.outItemQueue = new LinkedBlockingQueue<>();
		this.orderReceiver = new Receiver<>(this.inOrderQueue, this.outItemQueue, this.splitter, this.countDownLatch);
	}

	public OrderSplitterService(BlockingQueue<SplittedOrderItem> outItemQueue) {
		this.outItemQueue = outItemQueue;
		this.orderReceiver = new Receiver<>(this.inOrderQueue, this.outItemQueue, this.splitter, this.countDownLatch);
	}

	public void start() {
		this.executorService.submit(sender1);
		this.executorService.submit(sender2);
		this.executorService.submit(orderReceiver);
	}

	public void stop() throws InterruptedException {
		this.sender1.stop();
		this.sender2.stop();
		this.orderReceiver.stop();
		this.countDownLatch.await();
		this.executorService.shutdown();
	}

	public static void main(String[] args) throws InterruptedException {
		LOGGER.info("Splitter service started");
		BlockingQueue<SplittedOrderItem> outItemQueue = new LinkedBlockingQueue<>();
		OrderSplitterService splitterService = new OrderSplitterService(outItemQueue);
		splitterService.start();
		Thread.sleep(EXECUTIONTIMEINMILISECONDS);
		splitterService.stop();
		LOGGER.info("Splitter service finished");
	}

}
