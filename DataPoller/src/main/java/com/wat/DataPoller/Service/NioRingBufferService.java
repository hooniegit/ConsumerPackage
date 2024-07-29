package com.wat.DataPoller.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.lmax.disruptor.RingBuffer;
import com.wat.DataPoller.Class.RAW_BODY;
import com.wat.DataPoller.Class.TransferEvent;
import com.wat.DataPoller.Module.RingBufferGenerator;

@Service
public class NioRingBufferService {
	private final RingBuffer<TransferEvent> ringbufferLOC01;
	private final RingBuffer<TransferEvent> ringbufferLOC02;
	private final RingBuffer<TransferEvent> ringbufferLOC03;
	
	// [Initialize] Create Ring Buffer Service
	public NioRingBufferService() {
		this.ringbufferLOC01 = RingBufferGenerator.getTransferRingBuffer(20, "LOC01");
		this.ringbufferLOC02 = RingBufferGenerator.getTransferRingBuffer(20, "LOC02");
		this.ringbufferLOC03 = RingBufferGenerator.getTransferRingBuffer(20, "LOC03");
	}
	
	// [Handler] Publish Event
	public void handleEvent(Map<String, List<RAW_BODY>> map) {
        CompletableFuture.runAsync(() -> {
        	if (map.get("LOC01") != null) {
        		ringbufferLOC01.publishEvent((event, sequence) -> event.setList(map.get("LOC01")));
        	} else if (map.get("LOC02") != null) {
        		ringbufferLOC02.publishEvent((event, sequence) -> event.setList(map.get("LOC02")));
        	} else if (map.get("LOC03") != null) {
        		ringbufferLOC03.publishEvent((event, sequence) -> event.setList(map.get("LOC03")));
        	}
        });
	}
}
