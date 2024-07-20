package com.hooniegit.Consumer;

import java.util.Map;
import java.util.HashMap;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.Executors;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadFactory;

import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.ProducerType;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hooniegit.LMAX.*;
//import com.hooniegit.Modules.Measurer;

public class ConsumerThread implements Runnable {
	private final ConsumerClass consumer = new ConsumerClass();
	private final AtomicBoolean running = new AtomicBoolean(true);
	private static final Logger logger = LogManager.getLogger(ConsumerThread.class);
	private Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
	private ConsumerGroup group;
	private RingBuffer<TaskEvent> ringBuffer;

	public ConsumerThread() {
		consumer.connect();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        Disruptor<TaskEvent> disruptor = new Disruptor<>(
                TaskEvent::new,
                1024,
                threadFactory,
                ProducerType.SINGLE,
                new BlockingWaitStrategy() // SleepingWaitStrategy or BlockingWaitStrategy
        );
        
        WorkHandler<TaskEvent>[] handlers = new TaskEventHandler[20];
        for (int i = 0; i < handlers.length; i++) {
            handlers[i] = new TaskEventHandler();
        }
        disruptor.handleEventsWithWorkerPool(handlers);
        disruptor.start();
        ringBuffer = disruptor.getRingBuffer();
	}


	@Override
	public void run() {
	    try {
	        while (running.get()) {
//	            Measurer.measureExecutionTime(() -> {
	            	try {
	                    if (!consumer.isConnected()) {
	                    	System.out.println("Is Not Connected.. Trying to ReConnect << ");
	                        consumer.connect();
	                        Thread.sleep(10000);
	                    }
		                consumer.consumer.poll(Duration.ofMillis(100)).forEach(record -> {
		                	System.out.println(">>>>>>>>>>>> Data Received");
		                	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
		                        ringBuffer.publishEvent((event, sequence) -> event.setRecord(record));
		                        System.out.println(">>>>>>>> Sent Event to Handler");
		                    });
	                        currentOffsets.put(
		                        new TopicPartition(record.topic(), record.partition()), 
		                        new OffsetAndMetadata(record.offset() + 1, null)
		                    );
	                        consumer.commitAsync(currentOffsets);
		                    System.out.println(">>>>> offset committed");
		                });
	            	} catch (Exception E) {
	            		System.out.println(">>>>> error appeared");
	            		System.out.println(E);
	            	}
	                
//	            }, "DataPollAndTask");
	        }
	    } catch (WakeupException e) {
	        String message = "[Expected] Wakeup Activated.";
	        logger.error(message);
	    } catch (Exception e) {
	        String message = String.format("[Others] %s", e.getMessage());
	        logger.error(message);
	    } finally {
	        try {
	            consumer.commitSync(currentOffsets);
	        } finally {
	            consumer.close();
	            group.notifyConsumerError(this);
	        }
	    }
	}

    public void shutdown() {
        consumer.wakeup();
    }
}