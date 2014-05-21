/**
 * @author wlapka
 *
 * @created May 12, 2014 10:05:15 AM
 */
package net.thoiry.lapka.splitter.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wlapka
 * 
 */
public class Sender<O> implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);
	private volatile boolean stop = false;
	private final BlockingQueue<O> outQueue;
	private final MessageGenerator<O> messageGenerator;
	private final CountDownLatch countDownLatch;

	public Sender(BlockingQueue<O> outQueue, MessageGenerator<O> messageGenerator, CountDownLatch countDownLatch) {
		this.outQueue = outQueue;
		this.messageGenerator = messageGenerator;
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		try {
			while (!this.stop) {
				O message = this.messageGenerator.generateMessage();
				if (this.outQueue.offer(message)) {
					LOGGER.info("Message '{}' has been succesfully sent.", message);
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
