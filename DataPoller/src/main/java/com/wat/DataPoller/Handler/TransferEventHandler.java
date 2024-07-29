package com.wat.DataPoller.Handler;

import com.lmax.disruptor.WorkHandler;

import com.wat.DataPoller.Class.RAW_BODY;
import com.wat.DataPoller.Class.TransferEvent;
import com.wat.DataPoller.Module.NIOClient;
import com.wat.DataPoller.Module.NIOClientPool;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public class TransferEventHandler implements WorkHandler<TransferEvent> {
	@Value("${nio.host}")
	private String host;
	@Value("$({nio.port.LOC01}")
	private int port01;
	@Value("$({nio.port.LOC02}")
	private int port02;
	@Value("$({nio.port.LOC03}")
	private int port03;
	private NIOClientPool clientPool;
	
	// [Initialize] Create Client Pool
	public TransferEventHandler(String SOURCE) {
		try {
			if (SOURCE == "LOC01") {
				this.clientPool = new NIOClientPool(host, port01, 10);
			} else if (SOURCE == "LOC02") {
				this.clientPool = new NIOClientPool(host, port02, 10);
			} else if (SOURCE == "LOC03") {
				this.clientPool = new NIOClientPool(host, port03, 10);
			}	
		} catch (Exception ex) {
			this.clientPool = null;
		}
	}
	
	// [NIO] Send List<RAW_BODY> to Server
    @Override
    public void onEvent(TransferEvent event) {
        try {
            List<RAW_BODY> list = event.getList();
            NIOClient client = clientPool.getClient();
            client.send(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
