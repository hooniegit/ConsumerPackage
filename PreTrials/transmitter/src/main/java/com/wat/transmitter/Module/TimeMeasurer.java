package com.wat.transmitter.Module;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TimeMeasurer {
	// [Define] Logger
	private static final Logger logger = LogManager.getLogger(TimeMeasurer.class);
	
    public static void measureExecutionTime(Runnable task, String methodName) {
    	// [Set] Time Variables
        long startTime = System.currentTimeMillis();
        try {
            task.run();
        } finally {
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            
        	// [Log] Spent Time for Method Running
            logger.info(String.format("[%s] Spent Time for >> %d ms", methodName, elapsedTime));
        }
    }
}
