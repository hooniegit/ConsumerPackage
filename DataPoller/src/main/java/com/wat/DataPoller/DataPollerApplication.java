package com.wat.DataPoller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wat.DataPoller.Config.IniConfig;

@SpringBootApplication
public class DataPollerApplication {

	public static void main(String[] args) {
	    SpringApplication application = new SpringApplication(DataPollerApplication.class);
	    
	    // [Config] Use config.ini
	    application.addInitializers(new IniConfig());
	    application.run(args);
	}

}
