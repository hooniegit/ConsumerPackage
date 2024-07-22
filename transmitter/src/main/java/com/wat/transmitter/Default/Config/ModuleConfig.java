package com.wat.transmitter.Default.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wat.transmitter.Default.Service.DefaultService;

@Configuration
public class ModuleConfig {
	
	// [Return] args (Running Background Tasks)
    @Bean
    public CommandLineRunner commandLineRunner(DefaultService defaultService) {
    	return args -> {
    		defaultService.backgroundTasks();
    	};
    }
    
}

