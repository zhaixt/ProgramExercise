package com.netty.exersize.netty;

import org.jboss.netty.channel.*;

/**
 * Created by zhaixiaotong on 2016-5-24.
 */
public class ServerHandler extends SimpleChannelHandler {
    @Override
    public void messageReceived(ChannelHandlerContext ctx,MessageEvent e) throws Exception{
        if (e.getMessage() instanceof String) {
            String message = (String) e.getMessage();
            System.out.println("Client发来:" + message);

            e.getChannel().write("Server已收到刚发送的:" + message);

            System.out.println("\n等待客户端"+e.getChannel().getRemoteAddress()+"输入。。。");
        }

        super.messageReceived(ctx, e);

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);
    }
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("有一个客户端注册上来了。。。");
        System.out.println("Client:" + e.getChannel().getRemoteAddress());
        System.out.println("Server:" + e.getChannel().getLocalAddress());
        System.out.println("\n等待客户端"+e.getChannel().getRemoteAddress()+"输入。。。");
        super.channelConnected(ctx, e);
    }
}
