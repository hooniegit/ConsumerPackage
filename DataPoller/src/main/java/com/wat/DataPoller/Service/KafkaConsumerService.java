package com.wat.DataPoller.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
	private Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
	private final PollRingBufferService ringBufferService;
	
	// [Add] RingBufferService
	public KafkaConsumerService(PollRingBufferService ringBufferService) {
		this.ringBufferService = ringBufferService;
	}
	
    @KafkaListener(topics = "WAT01", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<byte[], byte[]> record, Consumer<?, ?> consumer) {
    	
    	// [Event/Async] Create & Send to Handler
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
    		ringBufferService.handleEvent(record);
        });
    	
    	// [Commit/Async] Offset
        currentOffsets.put(
            new TopicPartition(record.topic(), record.partition()), 
            new OffsetAndMetadata(record.offset() + 1, null)
        );
        consumer.commitAsync((offsets, exception) -> {
            if (exception != null) {
                System.err.printf("Failed to commit offsets: %s%n", exception.getMessage());
            } else {
//                System.out.printf("Offsets committed: %s%n", offsets);
            }
        });
        
        // [Initialize]
        future = null;
        consumer = null;
    }
	
}
