package com.project.Consumer.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.springframework.kafka.support.Acknowledgment;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.project.Consumer.Disruptor.TaskEvent;
import com.project.Consumer.Disruptor.TaskEventHandler;

@Service
public class DefaultService {
	private Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
	private RingBuffer<TaskEvent> ringBuffer;
	
	public DefaultService() {
        // [Define] LMAX Disruptor & Handler & Ring Buffer
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        Disruptor<TaskEvent> disruptor = new Disruptor<>(
                TaskEvent::new,
                1024,
                threadFactory,
                ProducerType.SINGLE,
                new BlockingWaitStrategy() // SleepingWaitStrategy or BlockingWaitStrategy
        );
        WorkHandler<TaskEvent>[] handlers = new TaskEventHandler[20]; // add if NECESSARY
        for (int i = 0; i < handlers.length; i++) {
            handlers[i] = new TaskEventHandler();
        }
        disruptor.handleEventsWithWorkerPool(handlers);
        disruptor.start();
        ringBuffer = disruptor.getRingBuffer();
	}
	
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
        
//        // [Commit/Manual] Offset
//        acknowledgment.acknowledge();
    }
}
