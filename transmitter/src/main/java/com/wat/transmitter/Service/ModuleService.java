package com.wat.transmitter.Service;

import org.springframework.stereotype.Service;

import com.wat.transmitter.Module.ThreadMonitor;

@Service
public class ModuleService {
	public void backgroundTasks() { 
		// [Activate] Thread Monitor
		ThreadMonitor.run();
	}
}