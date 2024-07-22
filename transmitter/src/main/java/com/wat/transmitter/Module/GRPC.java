package com.wat.transmitter.Module;

import com.wat.transmitter.GRPC.Client;
import com.wat.transmitter.GRPC.DataList;

public class GRPC {
	public static void transport(Client client, DataList list) {
		// [Transport] GRPC
		try {
			client.send(list);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
