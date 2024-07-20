package com.project.Consumer.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.BytesDeserializer;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DefaultConfig {
    @Bean
    public ConsumerFactory<byte[], byte[]> consumerFactory() {
    	// [Define] Properties
        Map<String, Object> props = new HashMap<>();
		File fileToParse = new File("src\\main\\java\\com\\project\\Consumer\\Config\\srvr.ini");
		try {
			Ini ini = new Ini(fileToParse);
			String srvr = ini.get("kafka", "bootstrap.servers");
			props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, srvr);
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "DemoGroup64");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, BytesDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, BytesDeserializer.class);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "False");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, "1048576");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.RECONNECT_BACKOFF_MS_CONFIG, "1000");
        props.put(ConsumerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG, "10000");
        props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, "org.apache.kafka.clients.consumer.CooperativeStickyAssignor");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        
        // [Return] Properties
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<byte[], byte[]> kafkaListenerContainerFactory() {
    	// [Define] ContainerFactory<byte, byte>
        ConcurrentKafkaListenerContainerFactory<byte[], byte[]> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(64); // Multi-Thread Count
//        factory.getContainerProperties().setAckMode(AckMode.MANUAL); // MANUAL AckMode (for Manual Offset Commit)
        return factory;
    }
}
