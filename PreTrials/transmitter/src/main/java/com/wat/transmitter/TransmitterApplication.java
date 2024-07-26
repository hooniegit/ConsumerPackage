package com.wat.transmitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wat.transmitter.Default.Config.IniConfig;

@SpringBootApplication
public class TransmitterApplication {

	public static void main(String[] args) {
		// [Run] Run Application with INI Configurations
	    SpringApplication application = new SpringApplication(TransmitterApplication.class);
	    application.addInitializers(new IniConfig());
	    application.run(args);
	}

}
