package com.wat.transmitter.Poll.Class;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import com.lmax.disruptor.EventFactory;

public class PollEvent {
    private ConsumerRecord<byte[], byte[]> record;

    public ConsumerRecord<byte[], byte[]> getRecord() {
        return record;
    }

    public void setRecord(ConsumerRecord<byte[], byte[]> record) {
        this.record = record;
    }
    
    public final static EventFactory<PollEvent> EVENT_FACTORY = PollEvent::new;
}