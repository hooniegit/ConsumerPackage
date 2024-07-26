package com.wat.DataPoller.Module;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {
    private SocketChannel client;

    public NIOClient(String host, int port) throws IOException {
        client = SocketChannel.open(new InetSocketAddress(host, port));
        client.configureBlocking(false);
    }

    // Need to Change Data Type Here
    public void send(String message) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
        client.write(buffer);
    }

    public void close() throws IOException {
        client.close();
    }
}