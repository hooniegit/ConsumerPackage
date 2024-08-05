package com.wat.DataPoller.Handler;

import com.lmax.disruptor.WorkHandler;

import com.wat.DataPoller.Class.RAW_BODY;
import com.wat.DataPoller.Event.TransferEvent;
import com.wat.DataPoller.Service.ClientService;

import io.netty.channel.Channel;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class TransferEventHandler implements WorkHandler<TransferEvent> {
	private Channel channel;
	private ClientService clientService;
	private final String LOCATION;
	
	// [Initialize] Create Client Pool
	public TransferEventHandler(String LOCATION) {
		this.LOCATION = LOCATION;
		this.clientService = new ClientService();
		try {
			if (LOCATION.equals("LOC01")) {
				this.channel = clientService.getConnection("localhost", 10100);
			} else if (LOCATION.equals("LOC02")) {
//				this.channel = clientService.getConnection("localhost", 10200);
			} else if (LOCATION.equals("LOC03")) {
//				this.channel = clientService.getConnection("localhost", 10300);
			}
		} catch (Exception ex) {
			System.out.println("[WARN] :" + ex);
			this.channel = null;
		}
	}
	
	// [NIO] Send List<RAW_BODY> to Server
    @Override
    public void onEvent(TransferEvent event) throws Exception {
    	
    	System.out.println("[Notify] Transfer Event Started");
    	
        try {
            List<RAW_BODY> list = event.getMap().get(LOCATION);

            String message = list.stream()
                    .map(RAW_BODY -> RAW_BODY.toStream())
                    .collect(Collectors.joining(",")) + "\n";
            
            clientService.sendData(channel, message);
            
            System.out.println("[Notify] Sent Data to Tramsmitter.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
