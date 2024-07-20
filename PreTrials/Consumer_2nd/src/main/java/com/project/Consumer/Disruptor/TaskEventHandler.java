package com.project.Consumer.Disruptor;

import com.lmax.disruptor.WorkHandler;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import com.project.Consumer.Module.Measurer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskEventHandler implements WorkHandler<TaskEvent> {
    private static final Logger logger = LogManager.getLogger(TaskEventHandler.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public void onEvent(TaskEvent event) {
//        Measurer.measureExecutionTime(() -> {
    	    // [Test] Print
            ConsumerRecord<byte[], byte[]> record = event.getRecord();
            
            System.out.println(record.value());
            Date date = new Date(record.timestamp());
//            System.out.println("[Received] Partition: "
//                    + record.partition() + ", Offset: " + record.offset() + 
//                    ", timestamp: " + sdf.format(date) +
//                    ", by ThreadID: " + Thread.currentThread().getId());
            
            // [Initialize]
            record = null; // Clear
            event = null;
//        }, "EventHandlerTask");
    }

    private void deserialize(ConsumerRecord<byte[], byte[]> record) {
        // [Convert] Deserialize
    }
    
    private void transport() {
    	// [Transport] GRPC
    }
    
}
