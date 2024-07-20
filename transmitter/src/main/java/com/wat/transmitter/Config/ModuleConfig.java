package com.wat.transmitter.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wat.transmitter.Service.ModuleService;

@Configuration
public class ModuleConfig {
	
	// [Return] args (Running Background Tasks)
    @Bean
    public CommandLineRunner commandLineRunner(ModuleService moduleService) {
    	return args -> {
        	moduleService.backgroundTasks();
    	};
    }
    
}

