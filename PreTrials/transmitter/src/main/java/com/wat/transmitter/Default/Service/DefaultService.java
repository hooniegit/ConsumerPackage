package com.wat.transmitter.Default.Service;

import org.springframework.stereotype.Service;

import com.wat.transmitter.Module.ThreadMonitor;

@Service
public class DefaultService {
	public void backgroundTasks() { 
		// [Activate] Thread Monitor
		ThreadMonitor.run();
	}
}

