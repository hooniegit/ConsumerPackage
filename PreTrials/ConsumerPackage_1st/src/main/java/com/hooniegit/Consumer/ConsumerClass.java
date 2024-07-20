package com.hooniegit.Consumer;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;

import com.hooniegit.Config.ConsumerConfig;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConsumerClass {
    public KafkaConsumer<byte[], byte[]> consumer;
    private final Properties props = ConsumerConfig.getProps();
    private final String topic = ConsumerConfig.getTopic();
	private Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
    
	private class HandleRebalance implements ConsumerRebalanceListener{
		public void onPartitionsAssigned(Collection<TopicPartition>partitions) {
		}
		
		public void onPartitionsRevoked(Collection<TopicPartition>partitions) {
			consumer.commitSync(currentOffsets);
		}
	}
    
    public void close() {
    	consumer.close();
    }
    
    public void wakeup( ) {
    	consumer.wakeup();
    }
    
    public void commitAsync(Map<TopicPartition, OffsetAndMetadata> currentOffsets) {
    	consumer.commitAsync(currentOffsets, null);
    }
    
    public void commitSync(Map<TopicPartition, OffsetAndMetadata> currentOffsets) {
    	consumer.commitSync(currentOffsets);
    }

    public boolean isConnected() {
        try {
            consumer.partitionsFor(topic);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void connect() {
        if (consumer != null) {
            try {
                consumer.close();
            } catch (Exception e) {}
        }
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic), new HandleRebalance());
    }

    private static volatile boolean closed = false;

    public void shutdown() {
        closed = true;
        if (consumer != null) {
            consumer.wakeup();
        }
    }
}
