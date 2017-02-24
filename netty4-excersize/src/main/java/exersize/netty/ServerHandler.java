package exersize.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by zhaixiaotong on 2016-5-24.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        try {
            System.out.println("Netty-Server:Receive Message," + msg);

//            ByteBuf buf = (ByteBuf) msg;
//            byte[] req = new byte[buf.readableBytes()];
//            buf.readBytes(req);
//            String message = new String(req, "UTF-8");
            //System.out.println("Netty-Server:Receive Message," + msg);
            ctx.writeAndFlush("server get:"+msg);
//            ctx.channel().writeAndFlush("Server已收到刚发送的:" + msg);
////            e.getChannel().write("Server已收到刚发送的:" + message);
//
//            System.out.println("\n等待客户端" + "输入。。。");
        }catch(Exception e){
            e.printStackTrace();
        }


    }

//    @Override
//    public void channelConnected(ChannelHandlerContext ctx,Object msg) throws Exception {
//        System.out.println("有一个客户端注册上来了。。。");
//        System.out.println("Client:" );
//        System.out.println("Server:" );
//        System.out.println("\n等待客户端"+"输入。。。");

//    }
}
