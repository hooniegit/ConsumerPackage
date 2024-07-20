package com.hooniegit.Modules;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GarbageCollector {
    public static void run() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(GarbageCollector::clean, 0, 30, TimeUnit.SECONDS);
    }
    
    private static void clean() {
    	System.gc();
    	System.out.println("Garbage Memory Collected <<<<<<");
    }
}
