/**
 * @author wlapka
 *
 * @created May 12, 2014 10:05:32 AM
 */
package net.thoiry.lapka.splitter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wlapka
 * 
 */
public class Receiver<O, I> implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
	private volatile boolean stop = false;
	private final CountDownLatch countDownLatch;
	private final BlockingQueue<O> inQueue;
	private final BlockingQueue<I> outItemQueue;
	private final Splitter<O, I> splitter;

	public Receiver(BlockingQueue<O> inQueue, BlockingQueue<I> outItemQueue, Splitter<O, I> splitter,
			CountDownLatch countDownLatch) {
		this.inQueue = inQueue;
		this.outItemQueue = outItemQueue;
		this.countDownLatch = countDownLatch;
		this.splitter = splitter;
	}

	@Override
	public void run() {
		try {
			while (!this.stop) {
				O message = this.inQueue.poll();
				if (message != null) {
					LOGGER.info("Received message '{}'", message);
					for (I splittedItem : this.splitter.split(message)) {
						this.outItemQueue.offer(splittedItem);
						LOGGER.info("Sent splitted message item '{}'", splittedItem);
					}
				}
			}
		} finally {
			if (this.countDownLatch != null) {
				this.countDownLatch.countDown();
			}
		}

	}

	public void stop() {
		this.stop = true;
		LOGGER.info("Received stop signal");
	}
}
