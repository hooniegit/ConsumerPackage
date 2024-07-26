package com.wat.DataPoller.Module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NIOClientPool {
    private List<NIOClient> clients;
    private int currentIndex = 0;

    public NIOClientPool(String host, int port, int poolSize) throws IOException {
        clients = new ArrayList<>();
        for (int i = 0; i < poolSize; i++) {
            clients.add(new NIOClient(host, port));
        }
    }

    public synchronized NIOClient getClient() {
        NIOClient client = clients.get(currentIndex);
        currentIndex = (currentIndex + 1) % clients.size();
        return client;
    }

    public void closeAll() throws IOException {
        for (NIOClient client : clients) {
            client.close();
        }
    }
}
