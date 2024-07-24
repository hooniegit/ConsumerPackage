package com.wat.transmitter.Transfer.Class;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import com.lmax.disruptor.EventFactory;

@Getter
@Setter
public class TransferEvent {
    private List<ITEM> record;
    
    public final static EventFactory<TransferEvent> EVENT_FACTORY = TransferEvent::new;
}
