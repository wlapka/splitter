/**
 * @author wlapka
 *
 * @created May 12, 2014 10:06:27 AM
 */
package net.thoiry.lapka.splitter.family;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import net.thoiry.lapka.splitter.family.model.Family;
import net.thoiry.lapka.splitter.family.model.FamilyMember;
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
public class FamilySplitterServiceImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(FamilySplitterServiceImpl.class);
	private static final int NUMBEROFTHREADS = 3;
	private static final int EXECUTIONTIMEINMILISECONDS = 1000;
	private final CountDownLatch countDownLatch = new CountDownLatch(NUMBEROFTHREADS);
	private final ExecutorService executorService = Executors.newFixedThreadPool(NUMBEROFTHREADS);
	private final BlockingQueue<Family> inOrderQueue = new LinkedBlockingQueue<>();
	private final BlockingQueue<FamilyMember> outItemQueue;
	private final MessageGenerator<Family> orderGenerator = new FamilyGeneratorImpl();
	private final Sender<Family> sender1 = new Sender<>(inOrderQueue, orderGenerator, countDownLatch);
	private final Sender<Family> sender2 = new Sender<>(inOrderQueue, orderGenerator, countDownLatch);
	private final Splitter<Family, FamilyMember> splitter = new FamilySplitterImpl();
	private final Receiver<Family, FamilyMember> orderReceiver;

	public FamilySplitterServiceImpl() {
		this.outItemQueue = new LinkedBlockingQueue<>();
		this.orderReceiver = new Receiver<>(this.inOrderQueue, this.outItemQueue, this.splitter, this.countDownLatch);
	}

	public FamilySplitterServiceImpl(BlockingQueue<FamilyMember> outItemQueue) {
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
		BlockingQueue<FamilyMember> outItemQueue = new LinkedBlockingQueue<>();
		FamilySplitterServiceImpl splitterService = new FamilySplitterServiceImpl(outItemQueue);
		splitterService.start();
		Thread.sleep(EXECUTIONTIMEINMILISECONDS);
		splitterService.stop();
		LOGGER.info("Splitter service finished");
	}

}
