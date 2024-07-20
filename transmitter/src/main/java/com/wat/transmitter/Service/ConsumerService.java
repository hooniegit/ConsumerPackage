package com.wat.transmitter.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.lmax.disruptor.RingBuffer;

import com.wat.transmitter.Module.DisruptorManager;
import com.wat.transmitter.Class.PollEvent;


@Service
public class ConsumerService {
	private Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
	private RingBuffer<PollEvent> ringBuffer = DisruptorManager.getTaskRingBuffer();
	
	// [Define] KafkaListener
    @KafkaListener(topics = "DemoTopic64", containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<byte[], byte[]> record, Consumer<?, ?> consumer) {
    	
    	// record.value()
    	// record.partition()
    	// record.offset()
    	// record.timestamp()
    	
    	// [Event/Async] Create & Send to Handler
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
    		ringBuffer.publishEvent((event, sequence) -> event.setRecord(record));
            System.out.println(">>>>>>>> Sent Event to Handler");
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
                System.out.printf("Offsets committed: %s%n", offsets);
            }
        });
        
        // [Initialize]
        future = null;
        consumer = null;
    }
}
