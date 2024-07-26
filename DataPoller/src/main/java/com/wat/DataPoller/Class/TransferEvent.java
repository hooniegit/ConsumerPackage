package com.wat.DataPoller.Class;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import com.lmax.disruptor.EventFactory;

@Getter
@Setter
public class TransferEvent {
    private List<RAW_BODY> list;
    
    public final static EventFactory<TransferEvent> EVENT_FACTORY = TransferEvent::new;
}
