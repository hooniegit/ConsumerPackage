package com.wat.DataPoller.Module;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.wat.DataPoller.Event.PollEvent;
import com.wat.DataPoller.Event.TransferEvent;
import com.wat.DataPoller.Handler.PollEventHandler;

import com.wat.DataPoller.Handler.TransferEventHandler;

public class RingBufferGenerator {
	// [LMAX] Return Ring Buffer: PollEvent
	public static RingBuffer<PollEvent> getPollRingBuffer(int threadCount) {
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        Disruptor<PollEvent> disruptor = new Disruptor<>(
        		PollEvent::new,
                1024,
                threadFactory,
                ProducerType.SINGLE,
                new BlockingWaitStrategy() // SleepingWaitStrategy or BlockingWaitStrategy
        );
        
        WorkHandler<PollEvent>[] handlers = new PollEventHandler[threadCount]; // add if NECESSARY
        for (int i = 0; i < handlers.length; i++) {
            handlers[i] = new PollEventHandler();
        }
        
        disruptor.handleEventsWithWorkerPool(handlers);
        disruptor.start();
        
        return disruptor.getRingBuffer();
	}
	
	// [LMAX] Return Ring Buffer: TransferEvent
	public static RingBuffer<TransferEvent> getTransferRingBuffer(int threadCount, String LOCATION) {
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        Disruptor<TransferEvent> disruptor = new Disruptor<>(
        		TransferEvent::new,
                1024,
                threadFactory,
                ProducerType.SINGLE,
                new BlockingWaitStrategy() // SleepingWaitStrategy or BlockingWaitStrategy
        );
        
        WorkHandler<TransferEvent>[] handlers = new TransferEventHandler[threadCount]; // add if NECESSARY
        for (int i = 0; i < handlers.length; i++) {
            handlers[i] = new TransferEventHandler(LOCATION);
        }
        
        disruptor.handleEventsWithWorkerPool(handlers);
        disruptor.start();
                
        return disruptor.getRingBuffer();

	}
}

