package com.project.Consumer.Module;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Monitor {
    private static final int THREAD_THRESHOLD_ACTIVE = 64 * 22 + 10;
    private static final int THREAD_THRESHOLD_DAEMON = 64 + 7;
    private static final Logger logger = LogManager.getLogger(Monitor.class);

    public static void run() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(Monitor::measureThreads, 0, 30, TimeUnit.SECONDS);
    }

    public static void measureThreads() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        int activeThreadCount = threadMXBean.getThreadCount();
        int daemonThreadCount = threadMXBean.getDaemonThreadCount();
        int peakThreadCount = threadMXBean.getPeakThreadCount();

        System.out.println(">>>    Active Thread : " + activeThreadCount);
        System.out.println(">>>    Daemon Thread : " + daemonThreadCount);
        System.out.println(">>>   Maximum Thread : " + peakThreadCount);

        if (activeThreadCount < THREAD_THRESHOLD_ACTIVE | daemonThreadCount < THREAD_THRESHOLD_DAEMON) {
        	logger.error("[Expected] Less Threads: Thread is Deprecated.");
        } else if (activeThreadCount > THREAD_THRESHOLD_ACTIVE | daemonThreadCount > THREAD_THRESHOLD_DAEMON) {
        	logger.warn("[Expected] More Threads: Too Many Threads.");
        }
    }
}
