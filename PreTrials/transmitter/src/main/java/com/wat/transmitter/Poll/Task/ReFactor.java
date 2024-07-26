package com.wat.transmitter.Poll.Task;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wat.transmitter.Poll.Class.RAW;
import com.wat.transmitter.Poll.Class.RAW_BODY;

public class ReFactor {
	
	public void refactor(ConsumerRecord<byte[], byte[]> record) {
		try {
				Map<String, List<RAW_BODY>> map = deserialize(record);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	private Map<String, List<RAW_BODY>> deserialize(ConsumerRecord<byte[], byte[]> record) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
        byte[] value = record.value();
        RAW raw = objectMapper.readValue(value, RAW.class);
        String source = raw.getHeader().getSOURCE();

        Map<String, List<RAW_BODY>> map = new HashMap<>();
        map.put(raw.getHeader().getSOURCE(), raw.getBody());
        
        return map;
	}
	
    private void TEST(List<RAW_BODY> bodyList) {
        for (RAW_BODY body : bodyList) {
            System.out.println("TAG: " + body.getTAG() + ", VALUE: " + body.getVALUE() + ", TIMESTAMP: " + body.getTIMESTAMP());
        }
    }
    
    private void event(Map<String, List<RAW_BODY>> map) {
    	// String 데이터를 기반으로 객체 ID 판별
    	// 판별한 객체 ID의 RingBuffer로 이벤트 전송
    }
	
}
