package com.wat.DataTransmitter.Config;

import org.ini4j.Ini;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class IniConfig implements ApplicationContextInitializer<ConfigurableApplicationContext> {
	
    private final ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        try {
            Properties props = new Properties();
            Resource resource = resourceLoader.getResource("classpath:config.ini");
            Ini ini = new Ini(new FileReader(resource.getFile()));
            ini.forEach((sectionName, section) -> {
                section.forEach((optionName, optionValue) -> {
                	props.put(sectionName + "." + optionName, optionValue);
                });
            });

            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            PropertiesPropertySource iniPropertySource = new PropertiesPropertySource("iniPropertySource", props);
            environment.getPropertySources().addFirst(iniPropertySource);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties from config.ini", e); // Need to Fix
        }
    }
    
}
