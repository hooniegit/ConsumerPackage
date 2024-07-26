package com.wat.DataPoller.Class;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import lombok.Getter;
import lombok.Setter;
import com.lmax.disruptor.EventFactory;

@Getter
@Setter
public class PollEvent {
    private ConsumerRecord<byte[], byte[]> record;
    
    public final static EventFactory<PollEvent> EVENT_FACTORY = PollEvent::new;
}