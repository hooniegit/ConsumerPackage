package com.wat.transmitter.Poll.Task;

import java.io.IOException;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wat.transmitter.Poll.Class.RAW;
import com.wat.transmitter.Poll.Class.RAW_BODY;

public class ReFactor {
	
	public void refactor(ConsumerRecord<byte[], byte[]> record) {
		try {
				List<RAW_BODY> list = deserialize(record);
				TEST(list);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	private List<RAW_BODY> deserialize(ConsumerRecord<byte[], byte[]> record) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
        byte[] value = record.value();
        RAW raw = objectMapper.readValue(value, RAW.class);
        return raw.getBody();
	}
	
    private void TEST(List<RAW_BODY> bodyList) {
        for (RAW_BODY body : bodyList) {
            System.out.println("TAG: " + body.getTAG() + ", VALUE: " + body.getVALUE() + ", TIMESTAMP: " + body.getTIMESTAMP());
        }
    }
	
}
