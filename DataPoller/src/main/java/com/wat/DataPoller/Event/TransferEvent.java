package com.wat.DataPoller.Event;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import com.lmax.disruptor.EventFactory;
import com.wat.DataPoller.Class.RAW_BODY;

@Getter
@Setter
public class TransferEvent {
    private Map<String, List<RAW_BODY>> map;
    
    // [Create] Create Event Based on List<RAW_BODY>
    public final static EventFactory<TransferEvent> EVENT_FACTORY = TransferEvent::new;
}
