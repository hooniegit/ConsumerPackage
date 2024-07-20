package com.hooniegit;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.hooniegit.Consumer.ConsumerGroup;
import com.hooniegit.Modules.GarbageCollector;
import com.hooniegit.Modules.ThreadMonitor;
import com.hooniegit.Config.ConsumerConfig;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ConsumerApplication {
	
	public static void main(String[] args) {
		
		// Activate GC
		GarbageCollector.run();
		
		// Activate Thread Monitor
		ThreadMonitor.run();
		
		// Activate Kafka Consumer
		String topic = ConsumerConfig.getTopic();
		int range = ConsumerConfig.getRange();
		ConsumerGroup group = new ConsumerGroup(topic, range);
		Runtime.getRuntime().addShutdownHook(new Thread(group::shutdown));

	}
}
