package com.project.Consumer.Module;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Measurer {
	private static final Logger logger = LogManager.getLogger(Measurer.class);
	
    public static void measureExecutionTime(Runnable task, String methodName) {
        long startTime = System.currentTimeMillis();
        try {
            task.run();
        } finally {
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            logger.info(String.format("[%s] Spent Time for >> %d ms", methodName, elapsedTime));
        }
    }
}