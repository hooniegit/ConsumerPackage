package com.wat.DataPoller.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.lmax.disruptor.RingBuffer;
import com.wat.DataPoller.Class.RAW_BODY;
import com.wat.DataPoller.Event.TransferEvent;
import com.wat.DataPoller.Module.RingBufferGenerator;

@Service
public class TransferRingBufferService {
    private static final TransferRingBufferService instance = new TransferRingBufferService();
	private final RingBuffer<TransferEvent> ringbufferLOC01;
//	private final RingBuffer<TransferEvent> ringbufferLOC02;
//	private final RingBuffer<TransferEvent> ringbufferLOC03;
	
	// [Initialize] Create Ring Buffer Service
	private TransferRingBufferService() {
		this.ringbufferLOC01 = RingBufferGenerator.getTransferRingBuffer(20, "LOC01");
//		this.ringbufferLOC02 = RingBufferGenerator.getTransferRingBuffer(20, "LOC02");
//		this.ringbufferLOC03 = RingBufferGenerator.getTransferRingBuffer(20, "LOC03");
	}
	
    public static TransferRingBufferService getInstance() {
        return instance;
    }
	
	// [Handler] Publish Event
	public void handleEvent(Map<String, List<RAW_BODY>> map) {
		
		System.out.println("[Notify] Transfer Event Started");
		
        CompletableFuture.runAsync(() -> {
        	if (map.get("LOC01") != null) {
        		ringbufferLOC01.publishEvent((event, sequence) -> event.setMap(map));
        	} else if (map.get("LOC02") != null) {
//        		ringbufferLOC02.publishEvent((event, sequence) -> event.setMap(map));
        	} else if (map.get("LOC03") != null) {
//        		ringbufferLOC03.publishEvent((event, sequence) -> event.setMap(map));
        	}
        });
	}
}
