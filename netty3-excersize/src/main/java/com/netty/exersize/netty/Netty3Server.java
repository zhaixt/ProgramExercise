package com.netty.exersize.netty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by zhaixiaotong on 2016-4-18.
 */
public class Netty3Server {

    public static void main(String[] args){

        ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

        // Set up the default event_listener pipeline.
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline serverPipeline = Channels.pipeline();
                serverPipeline.addLast("decoder", new StringDecoder());
                serverPipeline.addLast("encoder", new StringEncoder());
                serverPipeline.addLast("handler", new ServerHandler());
                return serverPipeline;
            }
        });

        // Bind and start to accept incoming connections.
        Channel bind = bootstrap.bind(new InetSocketAddress(9080));
        System.out.println("Server已经启动，监听端口: " + bind.getLocalAddress() + "， 等待客户端注册。。。");

    }
}
