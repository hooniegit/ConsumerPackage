package com.wat.DataPoller.Class;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import com.lmax.disruptor.EventFactory;

@Getter
@Setter
public class TransferEvent {
    private List<RAW_BODY> list;
    
    // [Create] Create Event Based on List<RAW_BODY>
    public final static EventFactory<TransferEvent> EVENT_FACTORY = TransferEvent::new;
}
