package com.wat.DataPoller.Module;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.wat.DataPoller.Class.RAW;
import com.wat.DataPoller.Class.RAW_BODY;
import com.wat.DataPoller.Service.TransferRingBufferService;

public class Task {
    private final TransferRingBufferService ringBufferService = TransferRingBufferService.getInstance();
	
	// [Task] Overall
	public void refactor(ConsumerRecord<byte[], byte[]> record) {
		
		System.out.println("[Notify] Task Started");
		try {
				Map<String, List<RAW_BODY>> map = deserialize(record);
				event(map);
				System.out.println("[Notify] Task Finished");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// [ReFactor] De-Serialize Consumed Data & Create Map
	private Map<String, List<RAW_BODY>> deserialize(ConsumerRecord<byte[], byte[]> record) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
        byte[] value = record.value();
        RAW raw = objectMapper.readValue(value, RAW.class);
        
        System.out.printf("[Check] LOCATION: %s%n", raw.getHEADER().getLOCATION());

        Map<String, List<RAW_BODY>> map = new HashMap<>();
        map.put(raw.getHEADER().getLOCATION(), raw.getBODY());
        
        return map;
	}
    
	// [LMAX] Send Data to Event Handler
    private void event(Map<String, List<RAW_BODY>> map) {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
    		ringBufferService.handleEvent(map);
        });
    }
	
}
