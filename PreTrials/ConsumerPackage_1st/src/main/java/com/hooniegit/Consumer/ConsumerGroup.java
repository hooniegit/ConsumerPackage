package com.hooniegit.Consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hooniegit.Config.ConsumerConfig;

public final class ConsumerGroup {
	private ExecutorService executor = Executors.newCachedThreadPool();
	private List<ConsumerThread> consumers;
	private static final Logger logger = LogManager.getLogger(ConsumerGroup.class);

	public ConsumerGroup(String topic, int numberOfConsumers) {		
	    consumers = new ArrayList<>();
		for (int i = 0; i < numberOfConsumers; i++) {
			startNewConsumer();
		}
	}
	
    public void startNewConsumer() {
    	ConsumerThread consumer = new ConsumerThread();
        consumers.add(consumer);
        executor.submit(consumer);
        logger.info("[Expected] Started New Consumer.");
    }

    public void notifyConsumerError(ConsumerThread consumer) {
        consumers.remove(consumer);
        startNewConsumer();
        logger.info("[Expected] Removed Target Consumer & Started New Consumer.");
    }

    public void shutdown() {
        for (ConsumerThread consumer : consumers) {
            consumer.shutdown();
        }
        executor.shutdown();
        logger.info("[Expected] Shut Down Target Consumer Thread.");
    }
    
}
