package com.wat.DataTransmitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wat.DataTransmitter.Config.IniConfig;


@SpringBootApplication
public class DataTransmitterApplication {

	public static void main(String[] args) {
	    SpringApplication application = new SpringApplication(DataTransmitterApplication.class);
	    
	    // [Initialize] IniConfig: Use `config.ini` Data as Property
	    application.addInitializers(new IniConfig());
	    application.run(args);
	}

}
