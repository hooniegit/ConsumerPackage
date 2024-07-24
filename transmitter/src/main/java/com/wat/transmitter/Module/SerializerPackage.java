package com.wat.transmitter.Module;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.wat.transmitter.Transfer.Class.ITEM;
import com.wat.transmitter.GRPC.Data;
import com.wat.transmitter.GRPC.DataList;

public class SerializerPackage {
	public static DataList deserialize(ConsumerRecord<byte[], byte[]> record) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		byte[] bytes = record.value();
		String json = new String(bytes, StandardCharsets.UTF_8);

		JsonNode root = objectMapper.readTree(json);
		JsonNode sourceArray = root.path("source");

		List<ITEM> AllItemList = new ArrayList<ITEM>();

		for (JsonNode source : sourceArray) {
			JsonNode dataArray = source.path("data");

			List<ITEM> itemList = objectMapper.convertValue(dataArray,
					objectMapper.getTypeFactory().constructCollectionType(List.class, ITEM.class));

			AllItemList.addAll(itemList);
		}

		List<Data> protoDataList = AllItemList.stream()
				.map(item -> Data.newBuilder().setTag(item.getTag())
						.setValue(item.getValue())
						.setTimestamp(item.getTimestamp()).build())
				.collect(Collectors.toList());

		return DataList.newBuilder().addAllDataList(protoDataList).build();
	}

}
