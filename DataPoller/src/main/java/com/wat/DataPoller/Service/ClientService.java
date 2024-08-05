package com.wat.DataPoller.Service;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
//import com.sample.NettyClientService.NettyClient.Handler.ClientHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PreDestroy;

@Service
public class ClientService {
    private EventLoopGroup group;

    public ClientService() {
        this.group = new NioEventLoopGroup();
    }
    
    public Channel getConnection(String host, int port) throws Exception {
        Bootstrap b = new Bootstrap();
        b.group(group)
         .channel(NioSocketChannel.class)
         .handler(new ChannelInitializer<SocketChannel>() {
             @Override
             protected void initChannel(SocketChannel ch) throws Exception {
                 ChannelPipeline p = ch.pipeline();
                 p.addLast(new DelimiterBasedFrameDecoder(3276800, Delimiters.lineDelimiter()));
                 p.addLast(new StringDecoder());
                 p.addLast(new StringEncoder());
//               p.addLast(new ClientHandler()); // Add Handler if NECESSARY
             }
         });

        ChannelFuture f = b.connect(host, port).sync();
        return f.channel();
    }

    public void sendData(Channel channel, String data) throws Exception {
        if (channel != null && channel.isActive()) {
            channel.writeAndFlush(data);
        } else {
//            throw new IllegalStateException("[Connection] Channel is Not Opened.");
        }
    }
    
    public void disconnect(Channel channel) throws Exception {
        if (channel != null) {
            channel.close().sync();
        }
    } 
   
    @PreDestroy
    public void stop() {
        if (group != null) {
            group.shutdownGracefully();
        }
    }

}