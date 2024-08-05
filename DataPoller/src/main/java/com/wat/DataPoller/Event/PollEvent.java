package com.wat.DataPoller.Event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import lombok.Getter;
import lombok.Setter;
import com.lmax.disruptor.EventFactory;

@Getter
@Setter
public class PollEvent {
    private ConsumerRecord<byte[], byte[]> record;
    
    // [Create] Create Event Based on ConsumerRecord<byte[], byte[]>
    public final static EventFactory<PollEvent> EVENT_FACTORY = PollEvent::new;
}