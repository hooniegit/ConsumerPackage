package com.hooniegit.LMAX;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.lmax.disruptor.EventFactory;

public class TaskEvent {
    private ConsumerRecord<byte[], byte[]> record;

    public ConsumerRecord<byte[], byte[]> getRecord() {
        return record;
    }

    public void setRecord(ConsumerRecord<byte[], byte[]> record) {
        this.record = record;
    }
    
    public final static EventFactory<TaskEvent> EVENT_FACTORY = TaskEvent::new;
}