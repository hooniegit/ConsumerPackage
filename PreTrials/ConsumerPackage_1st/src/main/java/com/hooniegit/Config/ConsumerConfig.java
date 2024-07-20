package com.hooniegit.Config;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

public class ConsumerConfig {
	private static String topic;
	private static int range;
	private static Properties props = new Properties();

	public static String getTopic() {
		topic = "DemoTopic64";
		return topic;
	}
	
	public static int getRange() {
		range = 64;
		return range;
	}
	
	public static Properties getProps() {
		File fileToParse = new File("src\\main\\java\\com\\hooniegit\\Config\\srvr.ini");
		try {
			Ini ini = new Ini(fileToParse);
			String srvr = ini.get("kafka", "bootstrap.servers");
			props.put("bootstrap.servers", srvr);
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		props.put("group.id", "DemoGroup7");
		props.put("enable.auto.commit", "False");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("auto.offset.reset", "latest");
		props.put("fetch.min.bytes", "1048576");
		props.put("partition.assignment.strategy", "org.apache.kafka.clients.consumer.CooperativeStickyAssignor");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		return props;
	}
}

