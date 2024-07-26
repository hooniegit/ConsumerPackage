package com.wat.transmitter.Poll.Service;

import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;
import com.lmax.disruptor.RingBuffer;

import com.wat.transmitter.Poll.Class.PollEvent;
import com.wat.transmitter.Module.RingBufferGenerator;

@Service
public class PollRingBufferService {
    private final RingBuffer<PollEvent> ringBuffer;

    public PollRingBufferService() {
        this.ringBuffer = RingBufferGenerator.getPollRingBuffer(200);
    }

    public void handleEvent(ConsumerRecord<byte[], byte[]> record) {
        CompletableFuture.runAsync(() -> {
            ringBuffer.publishEvent((event, sequence) -> event.setRecord(record));
        });
    }
}
