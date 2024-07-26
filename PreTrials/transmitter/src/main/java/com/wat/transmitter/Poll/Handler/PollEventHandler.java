package com.wat.transmitter.Poll.Handler;

import com.lmax.disruptor.WorkHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wat.transmitter.Poll.Class.PollEvent;
import com.wat.transmitter.Poll.Task.ReFactor;

public class PollEventHandler implements WorkHandler<PollEvent> {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private static final Logger logger = LogManager.getLogger(PollEventHandler.class);
    
	@Override
	public void onEvent(PollEvent event) {
//        Measurer.measureExecutionTime(() -> {
		
		ConsumerRecord<byte[], byte[]> record = event.getRecord();
		
		// [Test/Print]  
		PrintRecordInfo(record);
		
		// [DeSerialize]
		ReFactor refactor = new ReFactor();
		refactor.refactor(record);
		
		
		// [Initialize]
		record = null;
		event = null;
		
//        }, "PollEventTask");
	}
	
	
	public static void PrintRecordInfo(ConsumerRecord<byte[], byte[]> record) {
	    Date date = new Date(record.timestamp());
	    try {
	        System.out.println("[Received] Partition: "
			+ record.partition() + ", Offset: " + record.offset() + 
				", Timestamp: " + sdf.format(date) +
				", by ThreadID: " + Thread.currentThread().getId());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}

