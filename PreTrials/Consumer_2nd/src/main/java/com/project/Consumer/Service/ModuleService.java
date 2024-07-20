package com.project.Consumer.Service;

import org.springframework.stereotype.Service;

import com.project.Consumer.Module.GarbageCollector;
import com.project.Consumer.Module.Monitor;

@Service
public class ModuleService {
	public void backgroundTasks() { 
		// [Activate] GC
//		GarbageCollector.run();
		
		// [Activate] Thread Monitor
		Monitor.run();
	}
}
