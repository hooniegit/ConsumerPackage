package com.wat.transmitter.Map.Class;

import java.util.List;

import com.lmax.disruptor.EventFactory;
import com.wat.transmitter.Poll.Class.RAW_BODY;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapEvent {
	private List<RAW_BODY> rawBody;

    public final static EventFactory<MapEvent> EVENT_FACTORY = MapEvent::new;
}
