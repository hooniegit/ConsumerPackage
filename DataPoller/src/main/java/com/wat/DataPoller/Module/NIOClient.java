package com.wat.DataPoller.Module;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wat.DataPoller.Class.RAW_BODY;

public class NIOClient {
    private SocketChannel client;

    public NIOClient(String host, int port) throws IOException {
        client = SocketChannel.open(new InetSocketAddress(host, port));
        client.configureBlocking(false);
    }

    // Need to Change Data Type Here
    public void send(List<RAW_BODY> list) throws IOException {
    	ObjectMapper objectMapper = new ObjectMapper();
        ByteBuffer buffer = ByteBuffer.wrap(objectMapper.writeValueAsBytes(list));
        client.write(buffer);
    }

    public void close() throws IOException {
        client.close();
    }
}