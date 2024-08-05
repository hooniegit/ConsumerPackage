package com.wat.DataTransmitter.Handler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import com.wat.DataTransmitter.Class.RAW_BODY;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        String[] parts = msg.split(",");

        for (String part : parts) {
        	System.out.println(part);
        	
            String[] fields = part.split(":");

            RAW_BODY body = new RAW_BODY();
            body.setSOURCE(fields[0]);
            body.setTAG(fields[1]);
            body.setVALUE(fields[2]);
            body.setTIMESTAMP(fields[3]);
            
            System.out.println("Received: " + body);
        }

        String response = "Data received\n";
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 예외 처리
        cause.printStackTrace();
        ctx.close();
    }
    
}

