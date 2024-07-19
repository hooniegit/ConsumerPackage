package com.project.Consumer.Disruptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmax.disruptor.WorkHandler;
import com.wat.grpc.Client;
import com.wat.grpc.Data;
import com.wat.grpc.DataList;
import com.wat.grpc.Item;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

//import com.project.Consumer.Module.Measurer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TaskEventHandler implements WorkHandler<TaskEvent> {
	private static final Logger logger = LogManager.getLogger(TaskEventHandler.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	private final Client client;
	
	@Autowired
	public TaskEventHandler(Client client) {
		this.client = client;
	}

	@Override
	public void onEvent(TaskEvent event) {

//        Measurer.measureExecutionTime(() -> {
		// [Test] Print
		ConsumerRecord<byte[], byte[]> record = event.getRecord();
		try {
			DataList list = deserialize(record);
			transport(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//            Date date = new Date(record.timestamp());
//            System.out.println("[Received] Partition: "
//                    + record.partition() + ", Offset: " + record.offset() + 
//                    ", timestamp: " + sdf.format(date) +
//                    ", by ThreadID: " + Thread.currentThread().getId());

		// [Initialize]
		record = null; // Clear
		event = null;
//        }, "EventHandlerTask");
	}

	private DataList deserialize(ConsumerRecord<byte[], byte[]> record) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		// [Convert] Deserialize
		byte[] bytes = record.value();
		String json = new String(bytes, StandardCharsets.UTF_8);

		JsonNode root = objectMapper.readTree(json);
		JsonNode sourceArray = root.path("source");

		List<Item> AllItemList = new ArrayList<Item>();

		for (JsonNode source : sourceArray) {
			JsonNode dataArray = source.path("data");

			List<Item> itemList = objectMapper.convertValue(dataArray,
					objectMapper.getTypeFactory().constructCollectionType(List.class, Item.class));

			AllItemList.addAll(itemList);
		}

		List<Data> protoDataList = AllItemList.stream()
				.map(item -> Data.newBuilder().setTag(item.getTag()).setUnit(item.getUnit())
						.setDescription(item.getDescription()).setValue(item.getValue())
						.setTimestamp(item.getTimestamp()).build())
				.collect(Collectors.toList());

		DataList list = DataList.newBuilder().addAllDataList(protoDataList).build();

		return list;
	}

	private void transport(DataList list) {
		// [Transport] GRPC
		try {
			client.send(list);
			Thread.sleep(1000);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
