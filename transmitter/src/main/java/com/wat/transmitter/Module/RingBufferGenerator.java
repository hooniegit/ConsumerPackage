package com.wat.transmitter.Module;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import com.wat.transmitter.Poll.Class.PollEvent;
import com.wat.transmitter.Poll.Handler.PollEventHandler;
import com.wat.transmitter.GRPC.Client;

public class RingBufferGenerator {
	
	// [Return] Ring Buffer : TaskEvent
	public static RingBuffer<PollEvent> getTaskRingBuffer(int threadCount) {
        // [Define] LMAX Disruptor
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        Disruptor<PollEvent> disruptor = new Disruptor<>(
        		PollEvent::new,
                1024,
                threadFactory,
                ProducerType.SINGLE,
                new BlockingWaitStrategy() // SleepingWaitStrategy or BlockingWaitStrategy
        );
        
        // [Define] Multiple Handlers
        WorkHandler<PollEvent>[] handlers = new PollEventHandler[threadCount]; // add if NECESSARY
    	Client client = new Client();
        for (int i = 0; i < handlers.length; i++) {
            handlers[i] = new PollEventHandler();
        }
        
        // [Task] Add Handlers and Start
        disruptor.handleEventsWithWorkerPool(handlers);
        disruptor.start();
        
        return disruptor.getRingBuffer();
	}
}

