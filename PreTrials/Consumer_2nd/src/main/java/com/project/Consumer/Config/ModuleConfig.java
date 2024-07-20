package com.project.Consumer.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.Consumer.Service.ModuleService;

@Configuration
public class ModuleConfig {
	
    @Bean
    public CommandLineRunner commandLineRunner(ModuleService moduleService) {
    	return args -> {
        	moduleService.backgroundTasks();
    	};
    }
    
}
