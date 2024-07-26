package com.wat.DataPoller.Handler;

import com.lmax.disruptor.EventHandler;
import com.wat.DataPoller.Class.RAW_BODY;
import com.wat.DataPoller.Class.TransferEvent;
import com.wat.DataPoller.Module.NIOClient;
import com.wat.DataPoller.Module.NIOClientPool;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

public class TransferEventHandler implements EventHandler<TransferEvent> {
    private final NIOClientPool clientPool;

    public TransferEventHandler(NIOClientPool clientPool) {
        this.clientPool = clientPool;
    }

    @Override
    public void onEvent(TransferEvent event, long sequence, boolean endOfBatch) {
        try {
        	// [Task] Refactor Received Datas : Map
            List<RAW_BODY> list = event.getList();
            
            NIOClient client = clientPool.getClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
